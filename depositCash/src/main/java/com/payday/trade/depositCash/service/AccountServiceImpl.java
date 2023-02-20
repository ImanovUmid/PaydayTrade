package com.payday.trade.depositCash.service;

import com.payday.trade.depositCash.entity.Account;
import com.payday.trade.depositCash.entity.Users;
import com.payday.trade.depositCash.repository.AccountRepository;
import com.payday.trade.depositCash.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UsersRepository usersRepository;

    @Override
    public void createAccount(Long userId) {
        Date today = new Date();
        Random rand = new Random();
        int random = rand.nextInt(1000000);
        Account account = new Account();
        Optional<Users> usersOptional = usersRepository.findUserByUserOptionalId(userId);
        if (usersOptional.isPresent()) {
            account.setUserId(usersOptional.get());
            account.setCustomerName(usersOptional.get().getUsername());
            account.setAccountOpenDate(today);
            account.setAccountNumber(random);
            accountRepository.save(account);
        } else {
            throw new IllegalStateException("User is not found");
        }
    }

    @Override
    public void loadCash(Long userId, Double cash) {
        Optional<Users> usersOptional = usersRepository.findUserByUserOptionalId(userId);
        if (usersOptional.isPresent()) {
            Optional<Account> optionalAccount = accountRepository.findAccountByUserId(usersOptional.get());
            if (optionalAccount.isPresent()) {
                if (optionalAccount.get().getCashAmount() == null) {
                    optionalAccount.get().setCashAmount(cash);
                    optionalAccount.get().setAccountType("0");
                    accountRepository.save(optionalAccount.get());
                } else {
                    optionalAccount.get().setCashAmount(optionalAccount.get().getCashAmount() + cash);
                    accountRepository.save(optionalAccount.get());
                }

            } else {
                throw new IllegalStateException("Account is not found");
            }
        } else {
            throw new IllegalStateException("User is not found");
        }
    }

}
