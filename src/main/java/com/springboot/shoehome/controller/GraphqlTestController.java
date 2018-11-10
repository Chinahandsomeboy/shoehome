package com.springboot.shoehome.controller;

import com.springboot.shoehome.domain.SalesOrder;
import com.springboot.shoehome.service.SalesOrderService;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static graphql.Scalars.GraphQLBoolean;
import static graphql.Scalars.GraphQLFloat;
import static graphql.Scalars.GraphQLID;
import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 *
 * @author zn
 */
@RestController
public class GraphqlTestController {

    @Autowired private SalesOrderService salesOrderService;


    //创建特殊类型
    public static final GraphQLScalarType EMAIL = new GraphQLScalarType("email", "user email",
            new Coercing() {
                @Override
                public Object serialize(Object dataFetchResult) throws CoercingSerializeException {
                    return dataFetchResult;
                }

                @Override
                public Object parseValue(Object input) throws CoercingParseValueException {
                    return input;
                }

                @Override
                public Object parseLiteral(Object input) throws CoercingParseLiteralException {
                    return input;
                }
            });


    @GetMapping("/test")
    public Object testGetGraphQL(String query){
        //查询全部（主子表 这里是一张user表，一张book表）
        List<SalesOrder> salesOrders = salesOrderService.getSalesOrder();

        //创建book查询type
        GraphQLObjectType customerType = newObject()
                .name("Customer")
                .field(newFieldDefinition()
                        .name("id")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("code")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("createDate")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("modificationDate")
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
                        .name("phoneNumber")
                        .type(GraphQLString))

                .build();

//        GraphQLInterfaceType base = newInterface()
//                .name("AbsEntity")
//
//                .field(newFieldDefinition()
//                        .name("id")
//                        .type(GraphQLString))
//
//                .field(newFieldDefinition()
//                        .name("code")
//                        .type(GraphQLString))
//
//                .field(newFieldDefinition()
//                        .name("createDate")
//                        .type(GraphQLString))
//
//                .field(newFieldDefinition()
//                        .name("modificationDate")
//                        .type(GraphQLString))
//                .build();



        //创建user查询type
        GraphQLObjectType salesOrderType = newObject()
                .name("SalesOrder")

                .field(newFieldDefinition()
                        .name("id")
                        .type(GraphQLID))

                .field(newFieldDefinition()
                        .name("code")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("createDate")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("modificationDate")
                        .type(GraphQLString))


                //.withInterface(base)

                .field(newFieldDefinition()
                        .name("discountPrice")
                        .type(GraphQLFloat))

                .field(newFieldDefinition()
                        .name("expectDate")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("finalPrice")
                        .type(GraphQLFloat))

                .field(newFieldDefinition()
                        .name("isModifiedPrice")
                        .type(GraphQLBoolean))

                .field(newFieldDefinition()
                        .name("note")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("orderStatus")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("totalPrice")
                        .type(GraphQLFloat))

                .field(newFieldDefinition()
                        .name("customer")
                        .type(customerType))


                .build();

        //定义暴露给客户端的查询 query api
        GraphQLObjectType queryType = newObject()//定义暴露给客户端的查询query api
                .name("salesorderQuery")
                .field(newFieldDefinition()
                        .type(salesOrderType)
                        .name("salesorder")
                        .argument(newArgument()
                                .name("code")
                                .type(new GraphQLNonNull(GraphQLString)))
                        .dataFetcher(environment -> {
                            String code = environment.getArgument("code");
                            for (SalesOrder salesOrder : salesOrders) {
                                if (salesOrder.getCode().equals(code)) {
                                    return salesOrder;
                                }
                            }
                            return salesOrders;
                        }))
                .field(newFieldDefinition()
                        .type(new GraphQLList(salesOrderType))
                        .name("salesorders")
                        .dataFetcher(evn -> {
                            return salesOrders;
                        }))
                .build();


        //创建schema，用于执行查询
        GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(queryType)
                .build();

        //传入schema，执行查询
        GraphQL graphQL = GraphQL.newGraphQL(schema).build();

        ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query).build();

        ExecutionResult executionResult = graphQL.execute(executionInput);

        Object data = executionResult.getData();

        List<GraphQLError> errors = executionResult.getErrors();


        //返回查询结果集
        if (errors.size()>0){
            return errors;
        }else {
            return data;
        }
        //return graphQL.execute(query).getData();
    }


}
