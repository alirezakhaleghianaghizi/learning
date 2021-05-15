package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Company {
    private Person manager;
    private MyDate companyFoundation;
    private String companyName;
    private String companyId;
    public ArrayList<SavingAcount> companysSavingAcount;



    public ArrayList<CurrentAcount> companysCurrentAcount;
    private long deliverdLoansAmount;
    private int  numberOfdeliveringLoan;
    private long acountsMoney;

    public Company(Person manager, String companyName) {
        this.manager = manager;
        this.companyName = companyName;
        this.acountsMoney=0;
        this.companyFoundation=new MyDate();
        this.companyId= String.valueOf(this.companyFoundation.getYear());
        if(this.companyFoundation.getMonth()<10){this.companyId+= String.valueOf(0)+String.valueOf(this.companyFoundation.getMonth());}
        else {this.companyId+=String.valueOf(this.companyFoundation.getMonth());}
        if(this.companyFoundation.getDay()<10){this.companyId+= String.valueOf(0);}
        this.companyId+=String.valueOf(this.companyFoundation.getDay())+"_"+this.companyName;
        this.companysCurrentAcount=new ArrayList<>();
        this.companysSavingAcount=new ArrayList<>();
        this.deliverdLoansAmount=0;
        this.numberOfdeliveringLoan=0;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }

    public void setCompanyFoundation(MyDate companyFoundation) {
        this.companyFoundation = companyFoundation;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setDeliverdLoansAmount(long deliverdLoansAmount) {
        this.deliverdLoansAmount = deliverdLoansAmount;
    }

    public void setNumberOfdeliveringLoan(int numberOfdeliveringLoan) {
        this.numberOfdeliveringLoan = numberOfdeliveringLoan;
    }

    public void setAcountsMoney(long acountsMoney) {
        this.acountsMoney = acountsMoney;
    }

    public Person getManager() {
        return manager;
    }

    public MyDate getCompanyFoundation() {
        return companyFoundation;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public long getDeliverdLoansAmount() {
        return deliverdLoansAmount;
    }

    public int getNumberOfdeliveringLoan() {
        return numberOfdeliveringLoan;
    }

    public long getAcountsMoney() {
        return acountsMoney;
    }

}
