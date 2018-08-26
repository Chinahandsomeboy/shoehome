package com.springboot.shoehome.service;

import com.springboot.shoehome.domain.SalesOrder;
import com.springboot.shoehome.repository.SalesOrderRepository;
import com.springboot.shoehome.utils.Query;
import com.springboot.shoehome.utils.QueryParamsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by acer on 2018/7/28.
 */
@Service
public class SalesOrderService {

   @Autowired private SalesOrderRepository salesOrderRepository;

   private SalesOrderRepository getSalesOrderRepository(){return salesOrderRepository;}

   public void insert(SalesOrder salesOrder){ getSalesOrderRepository().save(salesOrder);}

   public List getSalesOrder(){
      Query<SalesOrder> query =new Query<>();
      query.and(QueryParamsFilter.eq("note","1"),
                  QueryParamsFilter.eq("customer.note","2222"));
      query.leftJoin("customer");
      return getSalesOrderRepository().findAll(query);
   }
}
