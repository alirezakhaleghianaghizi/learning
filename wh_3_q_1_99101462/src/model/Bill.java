package model;

import java.util.Date;
import java.util.Random;

public class Bill {
    private int customerCod;
    private double amount;

    public int getCustomerCod() {
        return customerCod;
    }

    private long goodCod;
    private Date date;
    public long  id;
    public boolean checked;

    public Bill(int customerCod, double amount, long goodCod) {
        this.customerCod = customerCod;
        this.amount = amount;
        this.goodCod = goodCod;
        this.date= new Date();
        Random rand =new Random();
        this .id= Math.abs(rand.nextLong());
        this.checked=false;
    }

    public double getAmount() {
        return amount;
    }

    public long getGoodCod() {
        return goodCod;
    }
}
