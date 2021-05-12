package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class Bank {
   private String bankName;
   private int bankId;
   public BigInteger bankProperty ;
   public int bankIntresetPercent;
   public int savingAcountIntrestPercentShortTime;
   public int savingAcountIntrestPercentLongTime;
    public ArrayList<SavingAcount> bankSavingAcounts;
    public ArrayList<CurrentAcount> bankCurrentAcounts;
    public HashMap<Person,ArrayList<SavingAcount>> mapPersonsSavingAcounts;
    public HashMap<Person,ArrayList<CurrentAcount>> mapPersonsCurrentAcounts;
    private ArrayList<Loan> loans;
    private HashMap<Person,ArrayList<Loan>> mapPersonsLoans;

    public Bank(String bankName) {
        this.bankName = bankName;
        this.bankId=setbankId(bankName);
        this.bankProperty= new BigInteger("3000000000");
        this.bankIntresetPercent=20;
        this.savingAcountIntrestPercentLongTime=15;
        this.savingAcountIntrestPercentShortTime=10;
        this.bankSavingAcounts=new ArrayList<>();
        this.bankCurrentAcounts=new ArrayList<>();
        this.mapPersonsCurrentAcounts=new HashMap<>();
        this.mapPersonsSavingAcounts=new HashMap<>();
        this.loans=new ArrayList<>();
        this.mapPersonsLoans=new HashMap<>();
    }

    public Bank(String bankName,BigInteger initialMoney) {
        this.bankName = bankName;
        this.bankId=setbankId(bankName);
        this.bankProperty= initialMoney;
        this.bankIntresetPercent=20;
        this.savingAcountIntrestPercentLongTime=15;
        this.savingAcountIntrestPercentShortTime=10;
        this.bankSavingAcounts=new ArrayList<>();
        this.bankCurrentAcounts=new ArrayList<>();
        this.mapPersonsCurrentAcounts=new HashMap<>();
        this.mapPersonsSavingAcounts=new HashMap<>();
        this.loans=new ArrayList<>();
        this.mapPersonsLoans=new HashMap<>();
    }

    /*public static void main(String[] args) {
        String bankName ="meli";
        for (BankIds value : BankIds.values()) {
            if (value.toString().equalsIgnoreCase(bankName)) System.out.println( value.getId());
        }

    }*/

    public int setbankId(String bankName){
        for (BankIds value : BankIds.values()) {
            if (value.toString().equalsIgnoreCase(bankName)) return  value.getId();
        }
        return 0;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public void setBankProperty(BigInteger bankProperty) {
        this.bankProperty = bankProperty;
    }

    public void setBankIntresetPercent(int bankIntresetPercent) {
        this.bankIntresetPercent = bankIntresetPercent;
    }

    public void setSavingAcountIntrestPercentShortTime(int savingAcountIntrestPercentShortTime) {
        this.savingAcountIntrestPercentShortTime = savingAcountIntrestPercentShortTime;
    }

    public void setSavingAcountIntrestPercentLongTime(int savingAcountIntrestPercentLongTime) {
        this.savingAcountIntrestPercentLongTime = savingAcountIntrestPercentLongTime;
    }

    public void setBankSavingAcounts(ArrayList<SavingAcount> bankSavingAcounts) {
        this.bankSavingAcounts = bankSavingAcounts;
    }

    public void setBankCurrentAcounts(ArrayList<CurrentAcount> bankCurrentAcounts) {
        this.bankCurrentAcounts = bankCurrentAcounts;
    }

    public void setMapPersonsSavingAcounts(HashMap<Person, ArrayList<SavingAcount>> mapPersonsSavingAcounts) {
        this.mapPersonsSavingAcounts = mapPersonsSavingAcounts;
    }

    public void setMapPersonsCurrentAcounts(HashMap<Person, ArrayList<CurrentAcount>> mapPersonsCurrentAcounts) {
        this.mapPersonsCurrentAcounts = mapPersonsCurrentAcounts;
    }

    public void setLoans(ArrayList<Loan> loans) {
        this.loans = loans;
    }

    public void setMapPersonsLoans(HashMap<Person, ArrayList<Loan>> mapPersonsLoans) {
        this.mapPersonsLoans = mapPersonsLoans;
    }

    public String getBankName() {
        return bankName;
    }

    public int getBankId() {
        return bankId;
    }

    public BigInteger getBankProperty() {
        return bankProperty;
    }

    public int getBankIntresetPercent() {
        return bankIntresetPercent;
    }

    public int getSavingAcountIntrestPercentShortTime() {
        return savingAcountIntrestPercentShortTime;
    }

    public int getSavingAcountIntrestPercentLongTime() {
        return savingAcountIntrestPercentLongTime;
    }

    public ArrayList<SavingAcount> getBankSavingAcounts() {
        return bankSavingAcounts;
    }

    public ArrayList<CurrentAcount> getBankCurrentAcounts() {
        return bankCurrentAcounts;
    }

    public HashMap<Person, ArrayList<SavingAcount>> getMapPersonsSavingAcounts() {
        return mapPersonsSavingAcounts;
    }

    public HashMap<Person, ArrayList<CurrentAcount>> getMapPersonsCurrentAcounts() {
        return mapPersonsCurrentAcounts;
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }

    public HashMap<Person, ArrayList<Loan>> getMapPersonsLoans() {
        return mapPersonsLoans;
    }

    public boolean openningCurrentAcount(Person person,long initialAmount, long acountId){
        if(acountId==-1) {
            System.err.println("acountId is not true");
            return false;
        }
        CurrentAcount newAcount=new CurrentAcount(person,this,acountId,initialAmount,new MyDate(),new BankCard());
        this.bankCurrentAcounts.add(newAcount);
        person.personCurrentAcount.add(newAcount);
        ArrayList<CurrentAcount> acounts=this.mapPersonsCurrentAcounts.get(person);
        if(acounts==null) acounts=new ArrayList<>();
        acounts.add(newAcount);
        this.mapPersonsCurrentAcounts.put(person,acounts);
        return true;
    }

    public boolean openningSavingAcount(Person person,long initialAmount, long acountId,String kindeOfTime,CurrentAcount catcherOfIntrest){
        if(acountId==-1) {
            System.err.println("acountId is not true");
            return false;
        }
        SavingAcount newAcount=new SavingAcount (person,this,acountId,initialAmount,new MyDate(),kindeOfTime,catcherOfIntrest);
        this.bankSavingAcounts.add(newAcount);
        person.personSavingAcount.add(newAcount);
        ArrayList<SavingAcount> acounts=this.mapPersonsSavingAcounts.get(person);
        if(acounts==null) acounts=new ArrayList<>();
        acounts.add(newAcount);
        this.mapPersonsSavingAcounts.put(person,acounts);
        return true;
    }


}
