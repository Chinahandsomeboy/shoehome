package com.springboot.shoehome.controller;

import com.springboot.shoehome.domain.User;
import com.springboot.shoehome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zn
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired UserService userService;


    @GetMapping("/{id}")
    public String getById(@PathVariable("id") String id){
        User user=new User();
        userService.insertUser(user);
        return id;
    }
}
