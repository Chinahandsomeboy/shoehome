package com.springboot.shoehome.service;

import com.springboot.shoehome.domain.Customer;
import com.springboot.shoehome.repository.CustomerRepository;
import com.springboot.shoehome.utils.Query;
import com.springboot.shoehome.utils.QueryParamsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Query<Customer> query =new Query<>();
	    query.and(QueryParamsFilter.eq("id","4028860f656fa72201656fa7f7310004"));
        query.and(QueryParamsFilter.eq("name","15"));
//	    query.or(QueryParamsFilter.gt("balance",15),
//			    QueryParamsFilter.like("phoneNumber","2"));
        return getCustomerRepository().findAll(query);
    }
}
