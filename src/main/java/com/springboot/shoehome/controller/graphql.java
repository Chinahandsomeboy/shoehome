package com.springboot.shoehome.controller;

import com.springboot.shoehome.domain.Customer;
import com.springboot.shoehome.domain.SalesOrder;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLOutputType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static graphql.Scalars.GraphQLBoolean;
import static graphql.Scalars.GraphQLFloat;
import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

public class graphql {


    public static void main(String[] args) {



        SalesOrder salesOrder = new SalesOrder();
        Class salesOrderClass = salesOrder.getClass();
        Class classes = salesOrderClass.getSuperclass();
        Class classess = classes.getSuperclass();
        Class classesss = classess.getSuperclass();
        Field[] fields = SalesOrder.class.getDeclaredFields();


        //Arrays.asList(fields).stream().forEach(x -> System.out.println(x.getName()));

       // getAllFields(SalesOrder.class, new ArrayList()).stream().forEach(x -> System.out.println(x.getName()));

        List<Field> customerFields = getAllFields(Customer.class, new ArrayList()).stream().
                filter(field -> (!field.getName().equals("serialVersionUID"))).collect(Collectors.toList());

        List<Field> salesorderFields = getAllFields(SalesOrder.class, new ArrayList()).stream().
                filter(field -> (!field.getName().equals("serialVersionUID"))).collect(Collectors.toList());


        Arrays.asList(fields).stream().forEach(f -> System.out.println(f.getName()+" "+f.getType().getName()));

        Map<String, GraphQLObjectType> maps = new HashMap<>();

        GraphQLObjectType.Builder  builder = newObject().name("Customer");

        customerFields.stream().forEach(field -> builder.field(newFieldDefinition().name(field.getName()).type(matchType(field.getType().getName(), maps))));

        GraphQLObjectType customerType = builder.build();

        maps.put("Customer", customerType);



        GraphQLObjectType.Builder  builder1 = newObject().name("SalesOrder");

        salesorderFields.stream().forEach(field -> builder1.field(newFieldDefinition().name(field.getName()).type(matchType(field.getType().getName(), maps))));

        GraphQLObjectType salesorderType = builder1.build();

        maps.put("SalesOrder", salesorderType);



        System.out.println("1");
    }

    public static List<Field> getAllFields(Class object, List list) {
        if (object.getSuperclass() == null) {
            return list;
        }else {
            list.addAll(Arrays.asList(object.getDeclaredFields()));
            return getAllFields(object.getSuperclass(), list);
        }
    }

    public static GraphQLOutputType matchType(String type, Map<String,GraphQLObjectType> map){
        String[] types = type.split("\\.");
        type = types[types.length-1];

        switch (type){
            case "String":
                return GraphQLString;
            case "Double":
            case "double":
                return GraphQLFloat;
            case "int":
            case "Integer":
                return GraphQLInt;
            case "boolean":
            case "Boolean":
                return GraphQLBoolean;
            default:
                if (map.get(type) == null){
                    return GraphQLString;
                }else {
                    return map.get(type);
                }
        }
    }
}
