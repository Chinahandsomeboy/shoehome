package com.springboot.shoehome.service;

import com.springboot.shoehome.repository.SalesOrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by acer on 2018/7/28.
 */
@Service
public class SalesOrderItemService {

    @Autowired private SalesOrderItemRepository salesOrderItemRepository;
}
