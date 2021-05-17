package model;

import java.util.ArrayList;

public class Person {
    private String firstName;
    private String lastName;
    private long nationalCode;
    private MyDate birthDate;
    public ArrayList<SavingAcount> personSavingAcount;
    public ArrayList<CurrentAcount> personCurrentAcount;
    public ArrayList<BankCard> personBankCards;
    private long deliverdLoansAmount;
    private int  numberOfdeliveringLoan;
    private long acountsMoney;
    public boolean isBlock;
    public Person(String firstName, String lastName, long nationalCode, MyDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.birthDate = new MyDate(birthDate.getYear(),birthDate.getMonth(),birthDate.getDay());
        this.personSavingAcount=new ArrayList<>();
        this.personBankCards=new ArrayList<>();
        this.personCurrentAcount=new ArrayList<>();
        this.deliverdLoansAmount=0;
        this.numberOfdeliveringLoan=0;
        this.acountsMoney=0;
        this.isBlock=false;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getNationalCode() {
        return nationalCode;
    }

    public MyDate getBirthDate() {
        return birthDate;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNationalCode(long nationalCode) {
        this.nationalCode = nationalCode;
    }

    public void setBirthDate(MyDate birthDate) {
        this.birthDate = birthDate;
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
}
