package com.springboot.shoehome.controller;

import com.springboot.shoehome.domain.Customer;
import com.springboot.shoehome.service.CustomerService;
import com.springboot.shoehome.service.SalesOrderService;
import graphql.GraphQL;
import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLSchema;
import jdk.nashorn.internal.ir.ReturnNode;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLObjectType.newObject;

@RestController
public class testController {
	private static GraphQLOutputType customerType;
	public static GraphQLSchema schema;

	@Autowired private CustomerService customerService;


	@PostMapping(value = "demo")
	public JSONObject demo(@RequestParam(value = "query", required = false) String query) {
		GraphSchema();
		/*
		 * Query example:
		 *
		 * String query1 = "{users(page:2,size:5,name:\"john\") {id,name}}";
		 * String query2 = "{user(id:6) {id,name}}";
		 * String query3 = "{user(id:6) {id,name},users(page:2,size:5,name:\"john\") {id,name}}"
		 * ;
		 */

		//Fetch the result with query string
		Map<String, Object> result = new GraphQL(schema).execute(query).getData();
		return new JSONObject(result);
	}

	
	/**
	 * Init the output type
	 */
	private void initOutputType() {

		customerType = newObject().name("Customer")
				.field(GraphQLFieldDefinition.newFieldDefinition().name("id").type(Scalars.GraphQLLong).build())
				.field(GraphQLFieldDefinition.newFieldDefinition().name("age").type(Scalars.GraphQLShort).build())
				.field(GraphQLFieldDefinition.newFieldDefinition().name("name").type(Scalars.GraphQLString).build())
				.build();
	}

	/**
	 * Check single user
	 * 
	 */
	private GraphQLFieldDefinition createUserField() {
		return GraphQLFieldDefinition.newFieldDefinition().name("user")
				.argument(newArgument().name("id").type(Scalars.GraphQLInt).build()).type(customerType).dataFetcher(environment -> {
					// 获取查询参数
					int id = environment.getArgument("id");

					// 执行查询, 这里随便用一些测试数据来说明问题
					return customerService.getCustomer();
				}).build();
	}

	/**
	 * Check multiple users
	 * 
	 */
//	private GraphQLFieldDefinition createUsersField() {
//		return GraphQLFieldDefinition.newFieldDefinition().name("users")
//				.argument(newArgument().name("page").type(Scalars.GraphQLInt).build())
//				.argument(newArgument().name("size").type(Scalars.GraphQLInt).build())
//				.argument(newArgument().name("name").type(Scalars.GraphQLString).build()).type(new GraphQLList(customerType))
//				.dataFetcher(environment -> {
//					// 获取查询参数
//					int page = environment.getArgument("page");
//					int size = environment.getArgument("size");
//					String name = environment.getArgument("name");
//
//					// 执行查询, 这里随便用一些测试数据来说明问题
//					List<Customer> list = new ArrayList<>(size);
//					for (int i = 0; i < size; i++) {
//						User user = new User();
//						user.setId(i);
//						user.setAge((short)i );
//						user.setName("Name_" + i);
//						list.add(user);
//					}
//					return list;
//				}).build();
//	}
	

    private void GraphSchema() {
        initOutputType();
        schema = GraphQLSchema.newSchema().query(newObject()
                .name("GraphQuery")
               // .field(createUsersField())
                .field(createUserField())
                .build()).build();
        
    }

}
