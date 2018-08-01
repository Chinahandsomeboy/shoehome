package com.springboot.shoehome.service;

import com.springboot.shoehome.domain.Customer;
import com.springboot.shoehome.enums.QueryParamsMatchType;
import com.springboot.shoehome.repository.CustomerRepository;
import com.springboot.shoehome.utils.QueryParams;
import com.springboot.shoehome.utils.QueryParamsFilter;
import org.hibernate.sql.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author acer
 * @date 2018/7/28
 */
@Service
public class CustomerService {

    @Autowired private CustomerRepository customerRepository;

    private CustomerRepository getCustomerRepository(){
        return customerRepository;
    }

    public void insert(Customer customer){
        getCustomerRepository().save(customer);
    }

    public List<Customer> getCustomer(){
        QueryParams queryParams=new QueryParams<Customer>();
        queryParams.setQueryParamsFilterList(new QueryParamsFilter("1","1",QueryParamsMatchType.IN));
        queryParams.setQueryParamsFilterList(new QueryParamsFilter("2","2",QueryParamsMatchType.EQ));
        return getCustomerRepository().findAll(queryParams);
    }
}
