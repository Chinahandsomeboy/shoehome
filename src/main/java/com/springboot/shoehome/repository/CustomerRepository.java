package com.springboot.shoehome.repository;

import com.springboot.shoehome.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author acer
 * @date 2018/7/28
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,String>, JpaSpecificationExecutor<Customer> {

}
