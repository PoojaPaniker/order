package com.abn.assignment.order;

import com.abn.assignment.order.service.OrderManagementService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class OrderApplication {
    @Bean
    public RestTemplate getRestTemplate() {
        // Do any additional configuration here
        return new RestTemplate();
    }
    @Bean
    public OrderManagementService getOrderManagementService() {
        // Do any additional configuration here
        return new OrderManagementService();
    }
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);


    }

}
