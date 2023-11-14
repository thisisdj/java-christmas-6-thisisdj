package christmas.controller;

import christmas.domain.Order;
import christmas.domain.Planner;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {

    InputView inputView;
    OutputView outputView;

    public EventPlannerController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        int date;
        Order order;

        while (true) {
            try {
                date = inputView.printGreeting();
                break;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }

        while (true) {
            try {
                order = new Order(inputView.printMenuSelect());
                break;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }

        outputView.printOrderList(order);
        outputView.printOrderTotalPrice(order);

        Planner plan = new Planner(date, order);
    }
}
