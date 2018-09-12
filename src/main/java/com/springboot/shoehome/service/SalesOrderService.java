package com.springboot.shoehome.service;

import com.springboot.shoehome.domain.SalesOrder;
import com.springboot.shoehome.repository.SalesOrderRepository;
import com.springboot.shoehome.utils.Query;
import com.springboot.shoehome.utils.QueryParamsFilter;
import org.springframework.beans.factory.annotation.Autowired;
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
      //query.leftJoin("customer");

      return getSalesOrderRepository().findAll(query);
   }
}
