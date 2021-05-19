package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class Bank {
   private String bankName;
   private int bankId;
   public long bankProperty ;
   public int bankIntresetPercent;
   public int savingAcountIntrestPercentShortTime;
   public int savingAcountIntrestPercentLongTime;
    public ArrayList<SavingAcount> bankSavingAcounts;
    public ArrayList<CurrentAcount> bankCurrentAcounts;
    public HashMap<Person,ArrayList<SavingAcount>> mapPersonsSavingAcounts;
    public HashMap<Person,ArrayList<CurrentAcount>> mapPersonsCurrentAcounts;
    private ArrayList<Loan> loans;
    public HashMap<Person,ArrayList<Loan>> mapPersonsLoans;
    public boolean isBlock;
    public MyDate dateOfOpenning;
    public Bank(String bankName) {
        this.bankName = bankName;
        this.bankId=setbankId(bankName);
        this.bankProperty= 3_000_000_000L;
        this.bankIntresetPercent=20;
        this.savingAcountIntrestPercentLongTime=15;
        this.savingAcountIntrestPercentShortTime=10;
        this.bankSavingAcounts=new ArrayList<>();
        this.bankCurrentAcounts=new ArrayList<>();
        this.mapPersonsCurrentAcounts=new HashMap<>();
        this.mapPersonsSavingAcounts=new HashMap<>();
        this.loans=new ArrayList<>();
        this.mapPersonsLoans=new HashMap<>();
        this.dateOfOpenning=new MyDate();
        this.isBlock=false;
    }

    public Bank(String bankName,long initialMoney) {
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

    public void setBankProperty(long bankProperty) {
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

    public long getBankProperty() {
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
        CurrentAcount newAcount=new CurrentAcount(person,this,acountId,initialAmount,new MyDate(),null);
        newAcount.setCreditCard(new BankCard(CentralBank.makingPassworld(),CentralBank.makingCvv2(),newAcount,newAcount.getOwnerOfAcount(),new MyDate(),new MyDate(newAcount.getDateOfOpening().getYear()+4,newAcount.getDateOfOpening().getMonth(),newAcount.getDateOfOpening().getDay())));
        this.bankCurrentAcounts.add(newAcount);
        person.personCurrentAcount.add(newAcount);
        ArrayList<CurrentAcount> acounts=this.mapPersonsCurrentAcounts.get(person);
        if(acounts==null) acounts=new ArrayList<>();
        acounts.add(newAcount);
        person.setAcountsMoney(person.getAcountsMoney()+initialAmount);
        this.mapPersonsCurrentAcounts.put(person,acounts);
        this.bankProperty+=initialAmount;
        System.out.println("account created with id :"+acountId+" \t card password is :"+newAcount.creditCard.getPassworld());
        return true;
    }

    public boolean openningSavingAcount(Person person,long initialAmount, long acountId,String kindeOfTime){
        if(acountId==-1) {
            System.err.println("acountId is not true");
            return false;
        }
        boolean isCurrentAcountExistInTheBank=false;
        CurrentAcount catcherOfIntrest=null;
        for (CurrentAcount bankCurrentAcount : this.bankCurrentAcounts) {
            if(bankCurrentAcount.getOwnerOfAcount().getNationalCode()==person.getNationalCode()){
                isCurrentAcountExistInTheBank=true;
                catcherOfIntrest=bankCurrentAcount;
                break;
            }
        }
        if (!isCurrentAcountExistInTheBank||catcherOfIntrest==null){
            System.err.println("you dont have a current account in this bank.");
            return false;
        }
        for (Loan loan : this.loans) {
            if(loan.getLoanCatcher().getNationalCode()==person.getNationalCode()){
                if(loan.getLoanAmount()>loan.getRegivenAmount()){
                    System.err.println("you have an un completed loan");
                    return false;
                }
            }
        }
        SavingAcount newAcount=new SavingAcount (person,this,acountId,initialAmount,kindeOfTime,catcherOfIntrest);
        this.bankSavingAcounts.add(newAcount);
        person.personSavingAcount.add(newAcount);
        ArrayList<SavingAcount> acounts=this.mapPersonsSavingAcounts.get(person);
        if(acounts==null) acounts=new ArrayList<>();
        acounts.add(newAcount);
        this.mapPersonsSavingAcounts.put(person,acounts);
        this.bankProperty+=initialAmount;

        person.setAcountsMoney(person.getAcountsMoney()+initialAmount);
        System.out.println("account created with id :"+acountId);
        return true;
    }

    public boolean receiveLoan(Person getterLoan,long amount,ArrayList<Loan> loans){
        this.decrreaseForLoan(getterLoan,amount);
        loans.add(new Loan(getterLoan,amount,this.bankIntresetPercent,2));

        System.out.println("your loan amount is"+amount+"from "+bankName);
        return true;

    }

    public void decrreaseForLoan(Person getterLoan,long amount){
        getterLoan.setAcountsMoney(getterLoan.getAcountsMoney()+amount);
        getterLoan.setDeliverdLoansAmount(getterLoan.getDeliverdLoansAmount()+amount);
        getterLoan.setNumberOfdeliveringLoan(getterLoan.getNumberOfdeliveringLoan()+1);
        for (SavingAcount savingAcount : getterLoan.personSavingAcount) {
            if(savingAcount.getBank().getBankName().equalsIgnoreCase(this.bankName)){
                savingAcount.setMoney(savingAcount.getMoney()+amount);
                break;
            }
        }
        for (CurrentAcount currentAcount : getterLoan.personCurrentAcount) {
            if(currentAcount.getBank().getBankName().equalsIgnoreCase(this.bankName)){
                currentAcount.setMoney(currentAcount.getMoney()+amount);
                currentAcount.creditCard.setMoney(currentAcount.getMoney()+amount);
                break;
            }
        }
    }
}


