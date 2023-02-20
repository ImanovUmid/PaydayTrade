package com.payday.trade.placeOrder.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payday.trade.placeOrder.dto.Result;
import com.payday.trade.placeOrder.dto.TickerInfoDTO;
import com.payday.trade.placeOrder.entity.Account;
import com.payday.trade.placeOrder.entity.Orders;
import com.payday.trade.placeOrder.entity.Users;
import com.payday.trade.placeOrder.repository.AccountRepository;
import com.payday.trade.placeOrder.repository.OrdersRepository;
import com.payday.trade.placeOrder.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final AccountRepository accountRepository;
    private final UsersRepository usersRepository;
    private final OrdersRepository ordersRepository;

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    @Override
    public Long createOrder(Long userId, Double buyPrice, Double cellPrice, Integer orderStatus, Integer tickerCount, String tickerName, String date1, String date2) {
        Orders orders = new Orders();
        Date today = new Date();
        Optional<Users> usersOptional = usersRepository.findUserByUserOptionalId(userId);
        if (usersOptional.isPresent()) {
            orders.setUserId(usersOptional.get());
            orders.setOrderDate(today);
            orders.setBuyAmount(buyPrice);
            orders.setCellAmount(cellPrice);
            orders.setTickerCount(tickerCount);
            orders.setOrderStatus(orderStatus);
            orders.setTickerName(tickerName);
            orders.setOpenDate(date1);
            orders.setCloseDate(date2);
            ordersRepository.save(orders);
        } else {
            throw new IllegalStateException("User is not found");
        }
        return orders.getId();
    }

    @Override
    @Transactional
    public void buyOrCellOrder(Long orderId) throws JSONException, IOException {
        Date today = new Date();
        Random rand = new Random();
        int random = rand.nextInt(1000000);
        Optional<Orders> optionalOrders = ordersRepository.findOrderById(orderId);
        if (optionalOrders.isPresent()) {
            JSONObject json = readJsonFromUrl("https://api.polygon.io/v2/aggs/ticker/" + optionalOrders.get().getTickerName() + "/range/1/day/" +
                    optionalOrders.get().getOpenDate() + "/" + optionalOrders.get().getCloseDate() + "?apiKey=HpvJarUrnoE5CB0dji3S7bKZVXORftxb");
            ObjectMapper objectMapper = new ObjectMapper();
            TickerInfoDTO jsonToTickerInfoDTO = objectMapper.readValue(json.toString(), TickerInfoDTO.class);
            Optional<Users> usersOptional = usersRepository.findUserByUserOptionalId(optionalOrders.get().getUserId().getId());
            if (usersOptional.isPresent()) {
                Optional<Account> optionalAccount = accountRepository.findAccountByUserIdAndAccountType(usersOptional.get());
                if (optionalAccount.isPresent()) {
                    if (optionalAccount.get().getCashAmount() != null) {
                        if (optionalOrders.get().getOrderStatus() == 1) {
                            if (jsonToTickerInfoDTO.getResults().get(0).getO() <= optionalOrders.get().getBuyAmount()) {
                                if ((jsonToTickerInfoDTO.getResults().get(0).getO() * optionalOrders.get().getTickerCount()) <= optionalAccount.get().getCashAmount()) {
                                    optionalAccount.get().setCashAmount(optionalAccount.get().getCashAmount() - (jsonToTickerInfoDTO.getResults().get(0).getO() * optionalOrders.get().getTickerCount()));
                                    accountRepository.save(optionalAccount.get());
                                    Optional<Account> optionalAccountTrade = accountRepository.findAccountByUserIdAndTickerType(usersOptional.get(), optionalOrders.get().getTickerName());
                                    if (optionalAccountTrade.isPresent()) {
                                        optionalAccountTrade.get().setTickerAmount(optionalAccountTrade.get().getTickerAmount() + Double.valueOf(optionalOrders.get().getTickerCount()));
                                        optionalAccountTrade.get().setTickerName(optionalOrders.get().getTickerName());
                                        accountRepository.save(optionalAccountTrade.get());
                                    } else {
                                        Account account = new Account();
                                        account.setCustomerName(optionalAccount.get().getCustomerName());
                                        account.setAccountOpenDate(today);
                                        account.setAccountNumber(random);
                                        account.setAccountType("1");
                                        account.setUserId(optionalAccount.get().getUserId());
                                        account.setCashAmount(jsonToTickerInfoDTO.getResults().get(0).getO());
                                        account.setTickerAmount(Double.valueOf(optionalOrders.get().getTickerCount()));
                                        account.setTickerName(optionalOrders.get().getTickerName());
                                        accountRepository.save(account);
                                    }
                                } else {
                                    throw new IllegalStateException("There is not enough money in the balance");
                                }
                            } else {
                                throw new IllegalStateException("Ticker price is not suitable for my buying price");
                            }
                        } else {
                            Optional<Account> optionalAccountTrade = accountRepository.findAccountByUserIdAndTickerType(usersOptional.get(), optionalOrders.get().getTickerName());
                            if (optionalAccountTrade.isPresent()) {
                                if (jsonToTickerInfoDTO.getResults().get(0).getO() >= optionalOrders.get().getCellAmount()) {
                                    if (optionalAccountTrade.get().getTickerAmount() >= Double.valueOf(optionalOrders.get().getTickerCount())) {
                                        optionalAccount.get().setCashAmount(optionalAccount.get().getCashAmount() + (jsonToTickerInfoDTO.getResults().get(0).getO() * optionalOrders.get().getTickerCount()));
                                        optionalAccountTrade.get().setTickerAmount(optionalAccountTrade.get().getTickerAmount() - Double.valueOf(optionalOrders.get().getTickerCount()));
                                        optionalAccountTrade.get().setCashAmount(jsonToTickerInfoDTO.getResults().get(0).getO());
                                        accountRepository.save(optionalAccount.get());
                                        accountRepository.save(optionalAccountTrade.get());
                                    } else {
                                        throw new IllegalStateException("There is not enough ticker in the balance");
                                    }
                                } else {
                                    throw new IllegalStateException("Ticker price is not suitable for my celling price");
                                }
                            } else {
                                throw new IllegalStateException("You don't have any ticker for this order");
                            }
                        }
                    } else {
                        throw new IllegalStateException("Insufficient balance");
                    }
                } else {
                    throw new IllegalStateException("Account is not found");
                }
            } else {
                throw new IllegalStateException("User is not found");
            }
        } else {
            throw new IllegalStateException("Order is not found");
        }

    }
}
