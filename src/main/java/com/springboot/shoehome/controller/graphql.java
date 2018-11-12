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
        
        Map<String, GraphQLObjectType> map = new HashMap<>();

        GraphQLObjectType cus = buildGraphQLObject(Customer.class, map);

        GraphQLObjectType sas = buildGraphQLObject(SalesOrder.class, map);
        
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


    public static GraphQLObjectType buildGraphQLObject(Class object, Map maps) {

        String[] objectName = object.getName().split("\\.");

        List<Field> fields = getAllFields(object, new ArrayList()).stream().
                filter(field -> !"serialVersionUID".equals(field.getName())).collect(Collectors.toList());

        GraphQLObjectType.Builder builder = newObject().name(objectName[objectName.length - 1]);

        fields.stream().forEach(field -> builder.field(newFieldDefinition().name(field.getName()).type(matchType(field.getType().getName(), maps))));

        GraphQLObjectType graphQLObject = builder.build();

        maps.put(objectName[objectName.length - 1], graphQLObject);

        return graphQLObject;
    }
}
