package com.payday.trade.placeOrder.service;

import org.json.JSONException;

import java.io.IOException;

public interface OrderService {
    Long createOrder(Long userId, Double buyPrice, Double cellPrice, Integer orderStatus, Integer tickerCount, String tickerName, String date1, String date2);
    void buyOrCellOrder(Long orderId) throws JSONException, IOException;
}
