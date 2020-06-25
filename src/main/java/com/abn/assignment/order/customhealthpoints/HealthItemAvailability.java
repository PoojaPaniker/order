package com.abn.assignment.order.customhealthpoints;

import com.abn.assignment.order.model.Items;
import com.abn.assignment.order.service.ItemsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HealthItemAvailability implements HealthIndicator {
    @Autowired
    ItemsManagementService itemsManagementService;

    @Override
    public Health health() {
        try {
            List<Items> items = itemsManagementService.getAllItems();

            if (items.size() > 0)
                return Health.up().withDetail("Message", "Items Available :" + items.size()).build();
            else
                return Health.down().withDetail("Error", "Item is not available").build();
        } catch (Exception e) {
            return Health.down().withDetail("Error", "Item is Unavailable").build();
        }
    }
}
