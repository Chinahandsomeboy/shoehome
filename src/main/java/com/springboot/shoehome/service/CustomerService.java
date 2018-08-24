package com.springboot.shoehome.service;

import com.springboot.shoehome.domain.Customer;
import com.springboot.shoehome.enums.DiscountType;
import com.springboot.shoehome.enums.QueryParamsMatchType;
import com.springboot.shoehome.repository.CustomerRepository;
import com.springboot.shoehome.utils.QueryParams;
import com.springboot.shoehome.utils.QueryParamsFilter;
import org.hibernate.sql.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
        QueryParams<Customer> queryParams=new QueryParams<>();
	    queryParams.and(QueryParamsFilter.eq("id","4028fe5c65661daa0165661e4df20004"));
//	    queryParams.or(QueryParamsFilter.gt("balance",15),
//			    QueryParamsFilter.like("phoneNumber","2"));
        return getCustomerRepository().findAll(queryParams);
    }
}
