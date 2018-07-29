package com.springboot.shoehome.repository;

import com.springboot.shoehome.domain.SalesOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by acer on 2018/7/28.
 */
@Repository
public interface SalesOrderItemRepository extends JpaRepository<SalesOrderItem, String>{
}
