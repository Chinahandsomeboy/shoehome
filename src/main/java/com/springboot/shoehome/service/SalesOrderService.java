package com.springboot.shoehome.service;

import com.springboot.shoehome.domain.SalesOrder;
import com.springboot.shoehome.repository.SalesOrderRepository;
import com.springboot.shoehome.utils.Query;
import com.springboot.shoehome.utils.QueryOrderFilter;
import com.springboot.shoehome.utils.QueryPageable;
import com.springboot.shoehome.utils.QueryParamsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by acer on 2018/7/28.
 */
@Service
public class SalesOrderService {

   @Autowired private SalesOrderRepository salesOrderRepository;

   private SalesOrderRepository getSalesOrderRepository(){return salesOrderRepository;}

   public void insert(SalesOrder salesOrder){ getSalesOrderRepository().save(salesOrder);}

   public List getSalesOrder() {
      Query<SalesOrder> query =new Query<>();

      //query.and(QueryParamsFilter.eq("discountPrice", 10.0)
         //    );
//      query.or(QueryParamsFilter.eq("finalPrice", 1),
//              QueryParamsFilter.eq("note", 2)
//              );
     // query.leftJoin("customer");
     // query.and(QueryParamsFilter.between("customer.balance", 15.8, 16)
     // );
      query.leftJoin("customer");

      //query.and(QueryParamsFilter.between("customer.balance", 17, 19));

      return getSalesOrderRepository().findAll(query);
   }

   public Page<SalesOrder> getSalesOrder1() {
      Query<SalesOrder> query =new Query<>();

      //query.and(QueryParamsFilter.eq("discountPrice", 10.0)
      //    );
//      query.or(QueryParamsFilter.eq("finalPrice", 1),
//              QueryParamsFilter.eq("note", 2)
//              );
      // query.leftJoin("customer");
      // query.and(QueryParamsFilter.between("customer.balance", 15.8, 16)
      // );
      query.leftJoin("customer");
      query.order(QueryOrderFilter.asc("customer.balance"));
      //query.and(QueryParamsFilter.between("customer.balance", 17, 19));
      return getSalesOrderRepository().findAll(query, QueryPageable.page(0,10));
   }
}
