package model;

public class SavingAcount {
    private Person ownerOfAcount;
    private Bank bank;
    private long acountNumber;
    private long money;
    private MyDate dateOfOpening;
    private String acountTimeKind;
    private CurrentAcount  acountOfGetingIntrest;

    public SavingAcount(Person ownerOfAcount, Bank bank, long acountNumber, long money, MyDate dateOfOpening, String acountTimeKind, CurrentAcount acountOfGetingIntrest) {
        this.ownerOfAcount = ownerOfAcount;
        this.bank = bank;
        this.acountNumber = acountNumber;
        this.money = money;
        this.dateOfOpening = dateOfOpening;
        this.acountTimeKind = acountTimeKind;
        this.acountOfGetingIntrest = acountOfGetingIntrest;
    }

    public Person getOwnerOfAcount() {
        return ownerOfAcount;
    }

    public Bank getBank() {
        return bank;
    }

    public long getAcountNumber() {
        return acountNumber;
    }

    public long getMoney() {
        return money;
    }

    public MyDate getDateOfOpening() {
        return dateOfOpening;
    }


    public String getAcountTimeKind() {
        return acountTimeKind;
    }

    public CurrentAcount getAcountOfGetingIntrest() {
        return acountOfGetingIntrest;
    }

    public void setOwnerOfAcount(Person ownerOfAcount) {
        this.ownerOfAcount = ownerOfAcount;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setAcountNumber(long acountNumber) {
        this.acountNumber = acountNumber;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void setDateOfOpening(MyDate dateOfOpening) {
        this.dateOfOpening = dateOfOpening;
    }


    public void setAcountTimeKind(String acountTimeKind) {
        this.acountTimeKind = acountTimeKind;
    }

    public void setAcountOfGetingIntrest(CurrentAcount acountOfGetingIntrest) {
        this.acountOfGetingIntrest = acountOfGetingIntrest;
    }

    //To Do
    private boolean posibleityOfcashing;


}
