package com.springboot.shoehome.controller;

import com.springboot.shoehome.domain.SalesOrder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class graphql {

    public static void main(String[] args) {



        SalesOrder salesOrder = new SalesOrder();
        Class salesOrderClass = salesOrder.getClass();
        Class classes = salesOrderClass.getSuperclass();
        Class classess = classes.getSuperclass();
        Class classesss = classess.getSuperclass();
        Field[] fields = SalesOrder.class.getFields();


        //Arrays.asList(fields).stream().forEach(x -> System.out.println(x.getName()));

       // getAllFields(SalesOrder.class, new ArrayList()).stream().forEach(x -> System.out.println(x.getName()));

        getAllFields(SalesOrder.class, new ArrayList()).stream().
                filter(field -> (!field.getName().equals("serialVersionUID"))).collect(Collectors.toList())
                .forEach(x -> System.out.println(x.getName()));
    }

    public static List<Field> getAllFields(Class object, List list) {
        if (object.getSuperclass() == null) {
            return list;
        }else {
            list.addAll(Arrays.asList(object.getDeclaredFields()));
            return getAllFields(object.getSuperclass(), list);
        }
    }
}
