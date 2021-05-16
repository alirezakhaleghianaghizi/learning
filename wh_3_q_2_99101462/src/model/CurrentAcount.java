package model;

public class CurrentAcount {
    public Person ownerOfAcount;
    protected Bank bank;
    private long acountNumber;
    private long money;
    private MyDate dateOfOpening;
    public BankCard creditCard;

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

    public boolean withdrawMoney(long money,String bankName){
        if(money>this.money){
            System.err.println("your money is not enough");
            return false;
        }
        if(!this.bank.getBankName().equalsIgnoreCase(bankName)){
            System.err.println("the acount is not belong to that bank.");
            return false;
        }
        this.decreasingMoney(money);
        return true;
    }

    public boolean decreasingMoney(long money){
        this.money-=money;
        this.creditCard.setMoney(this.creditCard.getMoney()-money);
        this.bank.bankProperty-=money;
        this.ownerOfAcount.setAcountsMoney(this.ownerOfAcount.getAcountsMoney()-money);
        return true;
    }
}
