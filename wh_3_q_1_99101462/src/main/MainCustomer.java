package main;

import controller.Controller;
import view.InputProcessorCustomer;

public class MainCustomer {
    public static void main(String[] args) {
        Controller controller =new Controller(-1);
        InputProcessorCustomer inputProcessoreCustomer =new InputProcessorCustomer(controller);
        inputProcessoreCustomer.run();
    }
}
