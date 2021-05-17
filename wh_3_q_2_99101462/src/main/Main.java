package main;

import controller.Controller;
import view.InputProcessor;

public class Main {
    public static void main(String[] args) {
        Controller controller =new Controller();
        InputProcessor inputProcessor=new InputProcessor(controller);
        inputProcessor.run();
    }
}
