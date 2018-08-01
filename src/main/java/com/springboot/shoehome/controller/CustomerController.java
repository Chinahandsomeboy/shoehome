package com.springboot.shoehome.controller;

import com.springboot.shoehome.domain.Customer;
import com.springboot.shoehome.enums.DiscountType;
import com.springboot.shoehome.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 *
 * @author zn
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired private CustomerService customerService;

    @GetMapping("/{id}")
    public String insertCustomer(@PathVariable("id") String id){
        Customer customer=new Customer();

        customer.setCode("1");
        customer.setName("1");
        customer.setBalance(15.5);
        customer.setDiscount(DiscountType.SevenDiscount);
        customer.setNote("1");
        customer.setPhoneNumber("1");
        customer.setCreateDate(new Date());


        customerService.insert(customer);
        return id;
    }

    @GetMapping("/get")
    public String getCustomer(){

        List<Customer> customerList=customerService.getCustomer();
        return customerList.toString();
    }


}
