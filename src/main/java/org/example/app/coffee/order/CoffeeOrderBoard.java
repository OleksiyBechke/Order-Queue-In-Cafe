package org.example.app.coffee.order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoffeeOrderBoard {
    List<Order> orders = new ArrayList<>();
    private int nextOrderNumber = 1;

    private static final Logger logger = LoggerFactory.getLogger(CoffeeOrderBoard.class);

    public void add(String customerName) {
        try {
            Order newOrder = new Order(nextOrderNumber++, customerName);
            orders.add(newOrder);
            logger.info("Order added successfully for customer: {}", customerName);
        } catch (Exception e) {
            logger.error("Error while adding order for customer: {}", customerName, e);
        }
    }

    public Order deliver() {
        try {
            if (orders.isEmpty()) {
                logger.warn("No orders to deliver.");
                return null;
            }

            logger.info("Delivering the order from a queue with {} orders.", orders.size());

            Order orderToDeliver = orders.stream()
                    .min(Comparator.comparingInt(Order::getOrderNumber))
                    .orElse(null);

            if (orderToDeliver != null) {
                orders.remove(orderToDeliver);
                logger.info("Delivered order with number: {}", orderToDeliver.getOrderNumber());
            } else {
                logger.warn("Failed to find the order to deliver.");
            }

            return orderToDeliver;
        } catch (Exception e) {
            logger.error("Error while delivering the order", e);
            return null;
        }
    }

    public Order deliver(int orderNumber) {
        try {
            Order orderToDeliver = orders.stream()
                    .filter(order -> order.getOrderNumber() == orderNumber)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Order not found"));

            orders.remove(orderToDeliver);
            logger.info("Delivered order with number: {}", orderNumber);
            return orderToDeliver;
        } catch (Exception e) {
            logger.error("Failed to deliver order with number: " + orderNumber, e);
            return null;
        }
    }

    public void draw() {
        try {
            System.out.println("Num | Name");

            orders.sort(Comparator.comparingInt(Order::getOrderNumber));

            for (Order order : orders) {
                System.out.println(order);
            }

            if (orders.isEmpty()) {
                logger.info("No orders available in the queue.");
            } else {
                logger.info("Displayed {} orders from the queue.", orders.size());
            }
        } catch (Exception e) {
            logger.error("Error while displaying the orders", e);
        }
    }
}
