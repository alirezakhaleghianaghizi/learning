package model;

import java.util.Random;
import java.util.function.DoubleBinaryOperator;

public class Goods {
    private String name;
    private long cod;
    private double amount;
    private double buy;
    private double sell;
    private String kindOfAmount;
    private int numberOfOrders;
    private double amountOfOrders;
    private double profitOfOrders;
    private double sellOfOrders;


    public Goods(String name, double amount, double buy, double sell,String kindOfAmount) {
        this.name = name;
        Random rand =new Random();
        this.cod = Math.abs(rand.nextLong());
        this.amount = amount;
        this.buy = buy;
        this.sell = sell;
        this.kindOfAmount=kindOfAmount;
        this.numberOfOrders=0;
        this.amountOfOrders=0;
        this.profitOfOrders=0;
        this.sellOfOrders=0;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public long getCod() {
        return cod;
    }

    public double getSell() {
        return sell;
    }

    public String getKindOfAmount() {
        return kindOfAmount;
    }

    public double getBuy() {
        return buy;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public double getAmountOfOrders() {
        return amountOfOrders;
    }

    public double getProfitOfOrders() {
        return profitOfOrders;
    }

    public double getSellOfOrders() {
        return sellOfOrders;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }

    public void setKindOfAmount(String kindOfAmount) {
        this.kindOfAmount = kindOfAmount;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public void setAmountOfOrders(double amountOfOrders) {
        this.amountOfOrders = amountOfOrders;
    }

    public void setProfitOfOrders(double profitOfOrders) {
        this.profitOfOrders = profitOfOrders;
    }

    public void setSellOfOrders(double sellOfOrders) {
        this.sellOfOrders = sellOfOrders;
    }
}
