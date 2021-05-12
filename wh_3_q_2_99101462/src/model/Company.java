package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Company {
    private Person manager;
    private MyDate companyFoundation;
    private String companyName;
    private String companyId;
    private ArrayList<SavingAcount> companysSavingAcount;
    private ArrayList<CurrentAcount> companysCurrentAcount;
    private long deliverdLoansAmount;
    private int  numberOfdeliveringLoan;
    private long acountsMoney;

    public Company(Person manager, String companyName) {
        this.manager = manager;
        this.companyName = companyName;
        this.acountsMoney=0;
        this.companyFoundation=new MyDate();
        //not complet
        //this.companyId
        //not complet
        this.companysCurrentAcount=new ArrayList<>();
        this.companysSavingAcount=new ArrayList<>();
        this.deliverdLoansAmount=0;
        this.numberOfdeliveringLoan=0;
    }
}
