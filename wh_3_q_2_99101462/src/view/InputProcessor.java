package view;

import controller.Controller;

import java.util.Scanner;

public class InputProcessor {

    public Controller controller;
    public Scanner scanner = new Scanner(System.in);
    public String input;
    public InputProcessor(Controller controller) {
        this.controller=new Controller();
        this.controller = controller;
    }
    public void run(){

    }
}
