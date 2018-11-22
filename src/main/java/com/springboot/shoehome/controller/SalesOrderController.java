package com.springboot.shoehome.controller;

import com.springboot.shoehome.domain.SalesOrder;
import com.springboot.shoehome.enums.OrderStatusType;
import com.springboot.shoehome.service.CustomerService;
import com.springboot.shoehome.service.SalesOrderService;
import com.springboot.shoehome.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 *
 * @author zn
 * @date 2018/7/28
 */
@RestController
@RequestMapping("/salesorder")
public class SalesOrderController {

    @Autowired private SalesOrderService salesOrderService;
    @Autowired private CustomerService customerService;

    @GetMapping("/{id}")
    public String addSalesOrder(@PathVariable("id") String id){
        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setCode(id);
        salesOrder.setCustomer(customerService.getCustomer().get(0));
        salesOrder.setDiscountPrice(10);
        salesOrder.setExpectDate(new Date());
        salesOrder.setFinalPrice(10);
        salesOrder.setNote("1");
        salesOrder.setTotalPrice(1000);
        salesOrder.setOrderStatus(OrderStatusType.Created);
        salesOrder.setIsModifiedPrice(true);
        salesOrder.setCreateDate(new Date());
        salesOrderService.insert(salesOrder);
        return id;
    }

    @GetMapping("/get")
    public List<SalesOrder> find(){
        return salesOrderService.getSalesOrder();
    }

    @GetMapping("/get1")
    public Page<SalesOrder> find1(){
        return salesOrderService.getSalesOrder1();
    }
}
