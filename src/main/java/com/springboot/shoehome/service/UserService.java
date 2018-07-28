package com.springboot.shoehome.service;

import com.springboot.shoehome.domain.User;
import com.springboot.shoehome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author acer
 * @date 2018/7/28
 */
@Service
public class UserService {

    @Autowired private UserRepository userRepository;

    public void insertUser(User user){
        userRepository.save(user);
    }

}
