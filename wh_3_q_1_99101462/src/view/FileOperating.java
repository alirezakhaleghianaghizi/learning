package view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.Controller;
import model.Goods;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperating {
    public void  jasonWriter(Controller controller){
        GsonBuilder builder=new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();
        String s=gson.toJson(controller);
       // System.out.println(s);
        writeToFile(s,"goodlist.txt",false);

    }
    public int writeToFile(String string,String filename,boolean append){
        try {
            File file =new File("resource\\"+filename);
            if(!file.exists())file.createNewFile();
            FileWriter fileWriter=new FileWriter(file);
            fileWriter.write(string);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public Controller readFile(String filename,Controller controller){
        File file =new File("resource\\"+"goodlist.txt");
        String output="";
        try {
            Scanner scanner =new Scanner(file);
            while (scanner.hasNextLine()){
                output+=scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
           controller=new Controller(-1);
            e.printStackTrace();
        }
        GsonBuilder builder=new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();
        controller=gson.fromJson(output, Controller.class);
        return controller;
    }

}
