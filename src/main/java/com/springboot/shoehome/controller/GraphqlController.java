package com.springboot.shoehome.controller;

import com.springboot.shoehome.domain.Customer;
import com.springboot.shoehome.domain.SalesOrder;
import com.springboot.shoehome.service.SalesOrderService;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInterfaceType.newInterface;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 *
 * @author zn
 */
@RestController
public class GraphqlController {

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


    @GetMapping("/testGetGraphQL")
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

        GraphQLInterfaceType base = newInterface()
                .name("base")

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
                .build();



        //创建user查询type
        GraphQLObjectType salesOrderType = newObject()
                .name("SalesOrder")

//                .field(newFieldDefinition()
//                        .name("id")
//                        .type(GraphQLID))
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


                .withInterface(base)

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
                        .type(new GraphQLList(customerType)))

//                .field(newFieldDefinition()
//                        .name("customer")
//                        .type(GraphQLList.list(customerType)))

                .build();

        //获取请求参数中id
        GraphQLFieldDefinition personDefinition =
                newFieldDefinition()
                        .name("salesOrder")
                        .type(salesOrderType)
                        .argument(GraphQLArgument.newArgument().name("id").type(GraphQLString))
                        .argument(GraphQLArgument.newArgument().name("code").type(GraphQLString))
                        .argument(GraphQLArgument.newArgument().name("note").type(GraphQLString))
                        .dataFetcher((DataFetcher) dataFetchingEnvironment -> {
                            String id = dataFetchingEnvironment.getArgument("id");
                            String code = dataFetchingEnvironment.getArgument("code");
                            String note = dataFetchingEnvironment.getArgument("note");
                            System.out.println(id + code + note);
                            for (SalesOrder salesOrder : salesOrders) {
                                if (salesOrder.getId().equals(id)) {
                                    return salesOrder;
                                }
                                if (salesOrder.getNote().equals(note)) {
                                    return salesOrder;
                                }
                                if (salesOrder.getCode().equals(code)) {
                                    return salesOrder;
                                }
                            }
                            return salesOrders;
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
