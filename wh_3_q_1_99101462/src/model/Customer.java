package model;

import java.util.ArrayList;

public class Customer {
    private int id;
    private int passworld;
    public  ArrayList<Bill> bills=new ArrayList<>();

    public Customer(int id,int passworld ) {
        this.id = id;
        this.passworld=passworld;

    }

    public int getId() {
        return id;
    }

    public int getPassworld() {
        return passworld;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassworld(int passworld) {
        this.passworld = passworld;
    }
}
