package main;

import controller.Controller;
import view.InputProcessorCustomer;
import view.InputProcessorSeller;

public class MainSeller {
    public static void main(String[] args) {
        Controller controller =new Controller(-1);
        InputProcessorSeller inputProcessoreSeller =new InputProcessorSeller(controller);
        inputProcessoreSeller.run();
    }
}
