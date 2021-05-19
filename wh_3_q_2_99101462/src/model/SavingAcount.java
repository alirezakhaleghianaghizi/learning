package model;

public class SavingAcount {
    private Person ownerOfAcount;
    private Bank bank;
    private long acountNumber;
    private long money;
    private MyDate dateOfOpening;
    private String acountTimeKind;
    private CurrentAcount  acountOfGetingIntrest;
    public boolean isBlock;
    private boolean posibleityOfcashing=false;
    public SavingAcount(Person ownerOfAcount, Bank bank, long acountNumber, long money,  String acountTimeKind, CurrentAcount acountOfGetingIntrest) {
        this.ownerOfAcount = ownerOfAcount;
        this.bank = bank;
        this.acountNumber = acountNumber;
        this.money = money;
        this.dateOfOpening = new MyDate();
        this.acountTimeKind = acountTimeKind;
        this.acountOfGetingIntrest = acountOfGetingIntrest;
        this.isBlock=false;
        this.posibleityOfcashing=false;
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
        return false;
    }
    public boolean decreasingMoney(long money){
        if (!posibleityOfcashing){
            posibleityOfcashing=true;
            if(MyDate.currentYear==this.dateOfOpening.getYear()&&MyDate.currentMonth==this.dateOfOpening.getMonth()){

            }else if(MyDate.currentYear==this.dateOfOpening.getYear()||MyDate.currentYear==1+this.dateOfOpening.getYear()){
                this.money-=this.money*5/1000*((MyDate.currentYear-this.dateOfOpening.getYear())*12+MyDate.currentMonth-this.dateOfOpening.getMonth());
                this.bank.bankProperty+=this.money*5/1000*((MyDate.currentYear-this.dateOfOpening.getYear())*12+MyDate.currentMonth-this.dateOfOpening.getMonth());
                this.ownerOfAcount.setAcountsMoney(this.ownerOfAcount.getAcountsMoney()-this.money*5/1000*((MyDate.currentYear-this.dateOfOpening.getYear())*12+MyDate.currentMonth-this.dateOfOpening.getMonth()));
            }
        }
        this.money-=money;
        this.bank.bankProperty-=money;
        this.ownerOfAcount.setAcountsMoney(this.ownerOfAcount.getAcountsMoney()-money);
        return true;
    }



}
