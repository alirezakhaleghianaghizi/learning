package view;

import model.Goods;

import java.util.ArrayList;

public class TabeOfGoods {
    ArrayList<Goods> goods=new ArrayList<>();
    public void print_avaliblity(ArrayList<Goods> avalibleList){
        printing(avalibleList);
    }
    public void print_nonAvaliblity(ArrayList<Goods> nonAvalibleList){
        printing(nonAvalibleList);
    }
    public void print_allAvaliblity(ArrayList<Goods> avalibleList,ArrayList<Goods> nonAvalibleList){
        ArrayList<Goods> allGoods=new ArrayList<>();
        allGoods.addAll(avalibleList);
        allGoods.addAll(nonAvalibleList);
        printing(allGoods);
    }
    private void printing(ArrayList<Goods> goodsList){
        printline();
        System.out.println("| Good name\t\t\t \t      | Inventory\t\t\t \t    | Price(IRR)\t\t\t\t  |");
        printline();
        for (Goods goods1 : goodsList) {
            System.out.print("|"+goods1.getName());
            for (int i = 0; i < 29-goods1.getName().length(); i++) {
                System.out.print(" ");
            }
            System.out.print("|"+goods1.getAmount()+" "+goods1.getKindOfAmount());
            for (int i = 0; i < 29-Double.toString(goods1.getAmount()).length()-1-goods1.getKindOfAmount().length(); i++) {
                System.out.print(" ");
            }
            System.out.print("|"+goods1.getSell());
            for (int i = 0; i < 29-Double.toString(goods1.getSell()).length(); i++) {
                System.out.print(" ");
            }
            System.out.println("|");
        }
        printline();
    }
    private void printline(){
        for (int i = 0; i < 3; i++) {
            System.out.print('+');
            for (int j = 0; j < 29; j++) {
                System.out.print('-');
            }
        }
        System.out.println('+');
    }
}
