package com.springboot.shoehome.service;

import com.springboot.shoehome.repository.DepositOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author acer
 * @date 2018/7/28
 */
@Service
public class DepositOrderService {

    @Autowired private DepositOrderRepository depositOrderRepository;
}
