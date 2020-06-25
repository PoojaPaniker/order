package com.abn.assignment.order.repository;

import com.abn.assignment.order.model.Items;
import org.springframework.data.repository.CrudRepository;

public interface ItemsManagementRepo extends CrudRepository<Items,Integer> {
}
