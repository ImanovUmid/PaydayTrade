package com.payday.trade.signUp.service;

import com.payday.trade.signUp.entity.Users;
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
public class UsersServiceImplTests {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private UsersCheckRepository usersCheckRepository;

    @InjectMocks
    private UsersServiceImpl usersServiceImpl;

    @Test
    public void testCreateUser() {
        String userName = "testUser";
        String password = "password123";
        String email = "testUser@example.com";


        Mockito.when(usersRepository.findUserByUserOptionalName(userName)).thenReturn(Optional.empty());


        usersServiceImpl.createUser(userName, password, email);


        Mockito.verify(usersRepository, Mockito.times(1)).save(Mockito.any());


        Mockito.verify(usersCheckRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testCreateUserAlreadyRegistered() {
        String userName = "testUser";
        String password = "password123";
        String email = "testUser@example.com";


        Mockito.when(usersRepository.findUserByUserOptionalName(userName)).thenReturn(Optional.of(new Users()));


        Exception exception = assertThrows(IllegalStateException.class, () -> {
            usersServiceImpl.createUser(userName, password, email);
        });


        assertEquals("Already registered user", exception.getMessage());


        Mockito.verify(usersRepository, Mockito.times(0)).save(Mockito.any());


        Mockito.verify(usersCheckRepository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    public void testCreateUserInvalidPassword() {
        String userName = "testUser";
        String password = "short";
        String email = "testUser@example.com";


        Exception exception = assertThrows(IllegalStateException.class, () -> {
            usersServiceImpl.createUser(userName, password, email);
        });


        assertEquals("Only allow passwords with 6 or more alphanumeric characters", exception.getMessage());


        Mockito.verify(usersRepository, Mockito.times(0)).save(Mockito.any());


        Mockito.verify(usersCheckRepository, Mockito.times(0)).save(Mockito.any());
    }
}
