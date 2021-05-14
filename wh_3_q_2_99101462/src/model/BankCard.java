package model;

public class BankCard {
    private long cardNumber;
    private int passworld;
    private int cvv2;
    private int secondPassworld;
    private CurrentAcount cardAcount;
    private Person ownerOfCard;
    private MyDate dateOfCreatingCard;
    private MyDate dateOfExpiringCard;
    private long money;

    public BankCard(int passworld, int cvv2, CurrentAcount cardAcount, Person ownerOfCard, MyDate dateOfCreatingCard, MyDate dateOfExpiringCard) {
        this.cardAcount = cardAcount;
        this.cardNumber =this.cardAcount.getAcountNumber();
        this.passworld = passworld;
        this.cvv2 = cvv2;
        this.ownerOfCard = ownerOfCard;
        this.dateOfCreatingCard = dateOfCreatingCard;
        this.dateOfExpiringCard = dateOfExpiringCard;
        this.secondPassworld = 0;
    }

    //To Do
    private boolean posibilityOfCashing;
    private boolean posibilytyOfPutting;

    public long getCardNumber() {
        return cardNumber;
    }

    public int getPassworld() {
        return passworld;
    }

    public int getCvv2() {
        return cvv2;
    }

    public int getSecondPassworld() {
        return secondPassworld;
    }

    public CurrentAcount getCardAcount() {
        return cardAcount;
    }

    public Person getOwnerOfCard() {
        return ownerOfCard;
    }

    public MyDate getDateOfCreatingCard() {
        return dateOfCreatingCard;
    }

    public MyDate getDateOfExpiringCard() {
        return dateOfExpiringCard;
    }

    public long getMoney() {
        return money;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setPassworld(int passworld) {
        this.passworld = passworld;
    }

    public void setCvv2(int cvv2) {
        this.cvv2 = cvv2;
    }

    public void setSecondPassworld(int secondPassworld) {
        this.secondPassworld = secondPassworld;
    }

    public void setCardAcount(CurrentAcount cardAcount) {
        this.cardAcount = cardAcount;
    }

    public void setOwnerOfCard(Person ownerOfCard) {
        this.ownerOfCard = ownerOfCard;
    }

    public void setDateOfCreatingCard(MyDate dateOfCreatingCard) {
        this.dateOfCreatingCard = dateOfCreatingCard;
    }

    public void setDateOfExpiringCard(MyDate dateOfExpiringCard) {
        this.dateOfExpiringCard = dateOfExpiringCard;
    }

    public void setMoney(long money) {
        this.money = money;
    }
}
