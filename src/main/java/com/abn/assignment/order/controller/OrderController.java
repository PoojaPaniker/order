package com.abn.assignment.order.controller;

import com.abn.assignment.order.exceptions.OrderNotFoundException;
import com.abn.assignment.order.exceptions.StockNotAvailableException;
import com.abn.assignment.order.model.OrderDetails;
import com.abn.assignment.order.model.OrderView;
import com.abn.assignment.order.model.Orders;
import com.abn.assignment.order.service.OrderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class OrderController {
    @Autowired
    private OrderManagementService orderManagementService;

    /*@GetMapping("/orders")
    public List<Orders> getOrders() {

        return orderManagementService.getAllOrders();
    }*/

    @GetMapping("/order")
    public ResponseEntity<Object> getOrder(@Validated @RequestParam int ordersId) throws OrderNotFoundException {
        OrderView order = orderManagementService.getOrder(ordersId);
        if (order == null)
            throw new OrderNotFoundException("Order with Order id -" + ordersId + " is not found");
        else return new ResponseEntity<>(order, HttpStatus.OK);

    }

    /*

    Method to create order
    1.check stock if available
    2.create order
    3.update stock available count
     */
    @PostMapping("/order")
    public ResponseEntity<OrderDetails> createOrder(@RequestBody Orders order) throws StockNotAvailableException {

        OrderDetails orderDetails = orderManagementService.addOrder(order);
        if (orderDetails != null)
            return new ResponseEntity<OrderDetails>(orderDetails, HttpStatus.CREATED);
        else
            throw new StockNotAvailableException("Stock for items not available");
    }


}
