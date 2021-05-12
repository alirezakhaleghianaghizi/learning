package model;

public class CurrentAcount {
    private Person ownerOfAcount;
    private Bank bank;
    private long acountNumber;
    private long money;
    private MyDate dateOfOpening;
    private BankCard creditCard;

    public CurrentAcount(Person ownerOfAcount, Bank bank, long acountNumber, long money, MyDate dateOfOpening, BankCard creditCard) {
        this.ownerOfAcount = ownerOfAcount;
        this.bank = bank;
        this.acountNumber = acountNumber;
        this.money = money;
        this.dateOfOpening = dateOfOpening;
        this.creditCard = creditCard;
    }

    //To Do
    private boolean posibilityOfCashing;
    private boolean posibilityOfputting;

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

    public BankCard getCreditCard() {
        return creditCard;
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

    public void setCreditCard(BankCard creditCard) {
        this.creditCard = creditCard;
    }
}
