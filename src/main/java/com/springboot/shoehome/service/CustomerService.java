package com.springboot.shoehome.service;

import com.springboot.shoehome.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author acer
 * @date 2018/7/28
 */
@Service
public class CustomerService {

    @Autowired private CustomerRepository customerRepository;
}
