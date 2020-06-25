package com.abn.assignment.order.repository;

import com.abn.assignment.order.model.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderManagementRepo extends CrudRepository<Orders,Integer> {
}
