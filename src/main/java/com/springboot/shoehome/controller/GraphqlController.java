package com.springboot.shoehome.controller;

import com.springboot.shoehome.domain.SalesOrder;
import com.springboot.shoehome.service.SalesOrderService;
import graphql.GraphQL;
import graphql.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 *
 * @author zn
 */
@RestController
public class GraphqlController {

    @Autowired private SalesOrderService salesOrderService;

    @GetMapping("/testGetGraphQL")
    public Object testGetGraphQL(String query){
        //查询全部（主子表 这里是一张user表，一张book表）
        List<SalesOrder> list = salesOrderService.getSalesOrder();

        //创建book查询type
        GraphQLObjectType customerType = newObject()
                .name("customer")
                .field(newFieldDefinition()
                        .name("id")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("code")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("create_date")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("modification_date")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("balance")
                        .type(GraphQLFloat))

                .field(newFieldDefinition()
                        .name("discount")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("name")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("note")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("phone_number")
                        .type(GraphQLString))

                .build();

        //创建user查询type
        GraphQLObjectType salesOrderType = newObject()
                .name("salesOrder")

                .field(newFieldDefinition()
                        .name("id")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("code")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("create_date")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("modification_date")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("discount_price")
                        .type(GraphQLFloat))

                .field(newFieldDefinition()
                        .name("expect_date")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("final_price")
                        .type(GraphQLFloat))

                .field(newFieldDefinition()
                        .name("is_modified_price")
                        .type(GraphQLChar))

                .field(newFieldDefinition()
                        .name("note")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("order_status")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("total_price")
                        .type(GraphQLFloat))

                .field(newFieldDefinition()
                        .name("customer")
                        .type(new GraphQLList(customerType)))


                .build();

        //获取请求参数中id
        GraphQLFieldDefinition personDefinition =
                newFieldDefinition()
                        .name("salesOrder")
                        .type(salesOrderType)
                        .argument(GraphQLArgument.newArgument().name("id").type(GraphQLString))
                        .dataFetcher((DataFetcher) dataFetchingEnvironment -> {
                            String id = dataFetchingEnvironment.getArgument("id");
                            for (SalesOrder salesOrder : list) {
                                if (salesOrder.getId().equals(id)) {
                                    return salesOrder;
                                }
                            }
                            return null;
                        })
                        .build();

        //创建schema，用于执行查询
        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(newObject()
                        .name("salesOrderQuery")
                        .field(personDefinition)
                        .build())
                .build();

        //传入schema，执行查询
        GraphQL graphQL = GraphQL.newGraphQL(schema).build();

        //返回查询结果集
        Object data = graphQL.execute(query).getData();

        return data;
    }


}