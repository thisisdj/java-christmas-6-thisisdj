package christmas.controller;

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
        inputView.printGreeting();
    }
}
