package view;

import controller.Controller;

import java.util.Scanner;

public class Input {
    public Controller controller;
    public Scanner scanner = new Scanner(System.in);
    public String input;
    public Input(Controller controller) {
        this.controller=new Controller(-1);
        this.controller = controller;
    }
    FileOperating fileOperating =new FileOperating();
}
