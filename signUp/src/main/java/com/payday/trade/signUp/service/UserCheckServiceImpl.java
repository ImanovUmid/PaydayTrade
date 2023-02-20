package com.payday.trade.signUp.service;

import com.payday.trade.signUp.entity.Users;
import com.payday.trade.signUp.entity.UsersCheck;
import com.payday.trade.signUp.repository.UsersCheckRepository;
import com.payday.trade.signUp.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserCheckServiceImpl implements UserCheckService {
    private final UsersRepository usersRepository;
    private final UsersCheckRepository usersCheckRepository;

    @Override
    public void checkActivate(String sessionKey){
        Optional<UsersCheck> usersCheckOptional = usersCheckRepository.findUserCheckOptonalBySessionKey(sessionKey);
        Users users = usersRepository.findUserByUserName(usersCheckOptional.get().getUsername());
        if (users != null   && usersCheckOptional.isPresent()) {
            users.setStatus(1);
           usersRepository.save(users);
           usersCheckRepository.delete(usersCheckOptional.get());
        } else {
                throw new IllegalStateException("User or Session key is not found");
        }
    }
}
