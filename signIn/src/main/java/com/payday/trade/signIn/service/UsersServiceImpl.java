package com.payday.trade.signIn.service;

import com.payday.trade.signIn.entity.Users;
import com.payday.trade.signIn.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    @Override
    public String signIn(String userName, String password){
        String result = "";
        if(password.length() >= 6 && password.matches("^[a-zA-Z0-9]*$")){
            Optional<Users> usersOptional = usersRepository.findUserByUserNameAndPassword(userName,password);
            if (usersOptional.isPresent() && usersOptional.get().getStatus() == 1){
                result = "Success";
            }else{
                throw new IllegalStateException("Username and password are incorrect or user not activated");
            }
        }else{
            throw new IllegalStateException("Only allow passwords with 6 or more alphanumeric characters");
        }

        return result;
    }
}
