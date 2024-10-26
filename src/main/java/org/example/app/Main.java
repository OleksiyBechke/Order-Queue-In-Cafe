package org.example.app;

import org.example.app.coffee.order.CoffeeOrderBoard;

public class Main {
    public static void main(String[] args) {
        CoffeeOrderBoard coffeeOrderBoard = new CoffeeOrderBoard();

        coffeeOrderBoard.add("Alice");
        coffeeOrderBoard.add("Bob");
        coffeeOrderBoard.add("Charlie");
        coffeeOrderBoard.add("Mike");

        coffeeOrderBoard.draw();

        coffeeOrderBoard.deliver();

        coffeeOrderBoard.draw();

        coffeeOrderBoard.deliver(3);

        coffeeOrderBoard.draw();

        coffeeOrderBoard.deliver(10);
    }
}