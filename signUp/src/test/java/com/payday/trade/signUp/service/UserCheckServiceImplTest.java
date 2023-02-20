package com.payday.trade.signUp.service;

import com.payday.trade.signUp.entity.Users;
import com.payday.trade.signUp.entity.UsersCheck;
import com.payday.trade.signUp.repository.UsersCheckRepository;
import com.payday.trade.signUp.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserCheckServiceImplTest {

    @InjectMocks
    private UserCheckServiceImpl userCheckServiceImpl;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private UsersCheckRepository usersCheckRepository;

    @Test
    public void testCheckActivate_withValidSessionKey() {
        String sessionKey = "valid_session_key";
        UsersCheck usersCheck = new UsersCheck();
        usersCheck.setUsername("test_user");
        usersCheck.setSessionKey(sessionKey);

        Users users = new Users();
        users.setUsername("test_user");
        users.setStatus(0);

        Mockito.when(usersCheckRepository.findUserCheckOptonalBySessionKey(sessionKey))
                .thenReturn(Optional.of(usersCheck));
        Mockito.when(usersRepository.findUserByUserName("test_user"))
                .thenReturn(users);


        userCheckServiceImpl.checkActivate(sessionKey);


        Mockito.verify(usersRepository, Mockito.times(1)).save(users);
        Mockito.verify(usersCheckRepository, Mockito.times(1)).delete(usersCheck);
    }

}
