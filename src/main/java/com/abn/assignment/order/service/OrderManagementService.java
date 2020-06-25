package com.abn.assignment.order.service;

import com.abn.assignment.order.exceptions.OrderNotFoundException;
import com.abn.assignment.order.model.*;
import com.abn.assignment.order.repository.OrderManagementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service

public class OrderManagementService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private OrderManagementRepo ordersRepo;
    @Autowired
    private ItemsManagementService itemsService;

    public void addOrders(List<Orders> order) {
        ordersRepo.saveAll(order);
    }

    public OrderDetails addOrder(Orders order) {
        List<Stock> stock = checkStock(order);
        Orders orders = null;
        if (stock.size() == order.getItems().size()) {
            orders = ordersRepo.save(order);
            orders = addItem(orders);
            updateStock(stock);
        }
        if (orders != null)
            return new OrderDetails(orders.getOrderId(), orders.getCost());
        else {
            return null;
        }
    }

    public List<Orders> getAllOrders() {
        List<Orders> orders = new ArrayList();
        ordersRepo.findAll().forEach(e -> {
            List<OrderItems> orderItems = e.getItems();
            List<Items> items = new ArrayList();
            Double total_cost = 0.0;
            for (OrderItems j : orderItems) {
                Items item = itemsService.getItem(j.getItemId());
                items.add(item);
                total_cost = total_cost + item.getCost();

            }
            e.setOrderItems(items);
            e.setCost(total_cost);
            orders.add(e);
        });
        return orders;
    }

    public OrderView getOrder(int orderId) {
        try {
           // System.out.println("to check order");
            Orders orders =
                    ordersRepo.findById(orderId).get();

            orders = addItem(orders);
            OrderView orderView = changeToOrderView(orders);
            return orderView;
        } catch (Exception e) {
           // System.out.print("Excp: " + e.getMessage());
            return null;
        }

    }

    public OrderView changeToOrderView(Orders order) {
        OrderView orderView = new OrderView(order.getCustomerId(),
                order.getDateOfDelivery().toString(), order.getAddressOfDelivery(),
                order.getOrderItems(), order.getCost());
        return orderView;
    }

    @PostConstruct
    public void loadData() {

        addOrders(Arrays.asList
                (new Orders(123, parseDate("2020-05-22"),
                        "Mumbai", Arrays.asList(new OrderItems(1), new OrderItems(2)))));
        itemsService.addItems(Arrays.asList(new Items(1, "Book", 50.0), new Items(2, "Chocolate", 400.0), new Items(3, "Mobile", 750.0)));
    }

    public Orders addItem(Orders order) {
        List<Items> items = new ArrayList();
        order.getItems().stream().forEach(e -> {
            Items item = itemsService.getItem(e.getItemId());
            items.add(item);
            order.setOrderItems(items);
        });
        order.setCost(calculateTotalCost(order.getOrderItems()));
        return order;
    }

/*Method for calculating the total cost of order
    Input Parameter : Order
    Output : Order

            */

    public Double calculateTotalCost(List<Items> items) {

        Double totalCost = items.stream().map(e -> e.getCost()).reduce(0.0, Double::sum);
        return totalCost;
    }

    public final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public java.util.Date parseDate(String date) {
        try {
            return DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Stock> checkStock(Orders orders) {

        List<Stock> stock = orders.getItems().stream().map(e -> {
                    Stock stock1 = restTemplate.getForObject("http://localhost:8081/stock/"
                            + e.getItemId(), Stock.class);
                    return new Stock(stock1.getItemId(), stock1.getAvailableStock());
                }
        ).collect(Collectors.toList());

        return stock.stream().filter(s -> s.getAvailableStock() > 0).collect(Collectors.toList());

    }

    public void updateStock(List<Stock> stock) {
        Map<Integer, Integer> frequency = calculateFrequency(stock);
        frequency.entrySet().stream().forEach(e -> {
            Map<String, String> params = new HashMap<>();
            params.put("itemId", e.getKey().toString());
            params.put("count", e.getValue().toString());
            restTemplate.put("http://localhost:8081/stock/{itemId}/{count}", new Stock(), params);
        });
    }


    public Map<Integer, Integer> calculateFrequency(List<Stock> sList) {
        Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();
       // sList.forEach(e -> System.out.println(e.getItemId() + "-"));
        for (Stock s : sList) {
            Integer freq = frequency.get(s.getItemId());
            if (freq == null) freq = 0;
            frequency.put(s.getItemId(), freq + 1);
        }
        //frequency.forEach((a, b) -> System.out.println(a + " - " + b));
        return frequency;
    }
}
