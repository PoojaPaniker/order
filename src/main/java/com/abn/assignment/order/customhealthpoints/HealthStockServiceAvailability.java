package com.abn.assignment.order.customhealthpoints;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;

@Component

public class HealthStockServiceAvailability implements HealthIndicator {
    @Override
    public Health health() {
        try {
            URL urlSite = new URL("http://localhost:8081/stock");
            HttpURLConnection connection = (HttpURLConnection) urlSite.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int code = connection.getResponseCode();
            if (code == 200)
                return Health.up().build();
            else
                return Health.down().withDetail("Error", "Stock service is down").build();
        } catch (Exception e) {
            return Health.down().withDetail("Error", "Stock Service Unavailable").build();
        }
    }
}

