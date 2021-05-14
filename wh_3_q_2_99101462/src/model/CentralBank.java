package model;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CentralBank {
    private Random rand=new Random();
    public ArrayList<Bank> banks;
    public ArrayList<SavingAcount> allSnavingAcount;
    public ArrayList<CurrentAcount> allCurrentAcount;
    public HashMap<Person,ArrayList<SavingAcount>> allBanksMapPersonsSavingAcounts;
    public HashMap<Person,ArrayList<CurrentAcount>> allBanksMapPersonsCurrentAcount;
    public ArrayList<Loan> allLoans;
    public HashMap<Person,ArrayList<Loan>> allBankMapPersonsLoans;
   //NOT COMPLET --> NEEDS ENUMS
    public long makingId(){
        return rand.nextLong();
    }
    //
    public long savingAcountId(Bank bank){
        long acountId=0;
        acountId+=(long)bank.getBankId()*1000000000*10;
        acountId+=Math.abs(Math.random()*100)*100000000;
        acountId+=bank.getBankSavingAcounts().size()*10;
        int kindOfLastNumber=0;
        long secAcountId=acountId/10;
        for (int i = 2; i <=16 ; i++) {
            double currentNumber=secAcountId%Math.pow(10,i-1);
            if(i%2==1){
                currentNumber*=2;
                while(currentNumber>=10) currentNumber-=9;
            }
            kindOfLastNumber+=currentNumber;
            secAcountId/=Math.pow(10,i-1);
        }
        long last=10-(kindOfLastNumber%10);
        if(last%2==0){
            acountId+=last/2;
            return acountId;
        }
        return -1;
    }

    public long currentAcountId(Bank bank){
        long acountId=0;
        acountId+=(long)bank.getBankId()*1000000000*10;
        acountId+=Math.abs(Math.random()*100)*100000000;
        acountId+=bank.getBankCurrentAcounts().size()*10;
        int kindOfLastNumber=0;
        long secAcountId=acountId/10;
        for (int i = 2; i <=16 ; i++) {
            double currentNumber=secAcountId%Math.pow(10,i-1);
            if(i%2==1){
                currentNumber*=2;
                while(currentNumber>=10) currentNumber-=9;
            }
            kindOfLastNumber+=currentNumber;
            secAcountId/=Math.pow(10,i-1);
        }
        long last=10-(kindOfLastNumber%10);
        if(last%2==0){
            acountId+=last/2;
            return acountId;
        }
        return -1;
    }

    public static int makingPassworld(){
        int maxPasworldNumber=9999;
        int minPasworldNumber=1000;
        return (int)Math.random()*(maxPasworldNumber-minPasworldNumber)+minPasworldNumber;
    }
    public static int makingCvv2(){
        int maxPasworldNumber=999;
        int minPasworldNumber=100;
        return (int)Math.random()*(maxPasworldNumber-minPasworldNumber)+minPasworldNumber;
    }
    public boolean cardToCard( long situationAcuontId,long  destinationAcountId,long moneyOfTransfering,int passworld,int cvv2){
        int destinIndex=-1;
        for (int i = 0; i < this.allCurrentAcount.size(); i++) {
            if(this.allCurrentAcount.get(i).getAcountNumber()==destinationAcountId){
                destinIndex=i;
                break;
            }
        }
        if(destinIndex==-1){
            System.err.println("destinationIdCard Was Wrong . ");
            return false;
        }
        for (CurrentAcount currentAcount : this.allCurrentAcount) {
             if(currentAcount.getAcountNumber()==situationAcuontId){
                 if(currentAcount.getCreditCard().getPassworld()!=passworld){
                     System.err.println("Passworld Was Wrong .");
                     return false;
                 }
                 if(currentAcount.getCreditCard().getCvv2()!=cvv2){
                     System.err.println("Cvv2 Was Wrong .");
                     return false;
                 }
                 if(currentAcount.getCreditCard().getMoney()<moneyOfTransfering){
                     System.err.println("Money Of Acount Was Not Enough .");
                 }
                 else{
                     currentAcount.setMoney(currentAcount.getMoney()-moneyOfTransfering);
                     this.allCurrentAcount.get(destinIndex).setMoney(this.allCurrentAcount.get(destinIndex).getMoney()+moneyOfTransfering);
                     return true;
                 }

             }
        }
        System.err.println("destinationIdCard Was Wrong .");
        return false;
    }
}
