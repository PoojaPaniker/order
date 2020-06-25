package com.abn.assignment.order.controller;


import com.abn.assignment.order.model.*;
import com.abn.assignment.order.service.OrderManagementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;


@SpringBootTest
@AutoConfigureMockMvc
public class TestOrderController {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    OrderManagementService orderManagementService;

    @Test
    public void testGetOrder() throws Exception {
        //  System.out.println("Order : " + orderManagementService.getOrder(1));
        OrderView orderDetails = new OrderView(123, "2020-05-22 00:00:00.0", "Mumbai",
                Arrays.asList(new Items("Book", 50.0), new Items("Chocolate", 400.0)), 450.0);
        String expected = mapToJson(orderDetails);
        String URI = "/order?ordersId=1";
        RequestBuilder req = MockMvcRequestBuilders.get(URI);
        MvcResult result = mockMvc.perform(req).andReturn();
        Assertions.assertEquals(expected, result.getResponse().getContentAsString());

    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        return obj.writeValueAsString(object);
    }

    @Test
    public void testGetOrderNotFoundException() throws Exception {

        String URI = "/order?ordersId=2";
        RequestBuilder req = MockMvcRequestBuilders.get(URI);
        MvcResult result = mockMvc.perform(req).andReturn();
        String expectedMessage = "Order Not found in Repository";
        String result1 = result.getResponse().getContentAsString();
        Assertions.assertTrue(result1.contains(expectedMessage));
    }

    @Test
    public void testOrder() {

        Orders order = new Orders(156, orderManagementService.parseDate("2020-06-22"),
                "Bangalore", Arrays.asList(new OrderItems(1), new OrderItems(2)));
        OrderDetails orderDetails = orderManagementService.addOrder(order);
        System.out.println("ordersid" + orderDetails.getOrders_id());
        Assertions.assertEquals(450.0, orderDetails.getTotal_cost());


    }

    @Test
    public void testOrderStockNotAvailable() throws Exception {

        Orders order = new Orders(189, orderManagementService.parseDate("2020-06-22"),
                "Chennai", Arrays.asList(new OrderItems(3)));
        String URI = "/order";
        RequestBuilder req = MockMvcRequestBuilders.post(URI).content(mapToJson(order))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(req).andReturn();
        String expectedMessage = "Stock is not available";
        String result1 = result.getResponse().getContentAsString();
        Assertions.assertTrue(result1.contains(expectedMessage));

    }
}
