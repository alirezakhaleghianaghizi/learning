package controller;

import model.*;

import java.math.BigInteger;
import java.util.ArrayList;

public class Controller {
    CentralBank centralBank=new CentralBank();
    ArrayList <Person> people =new ArrayList<>();
    ArrayList <Company> companys =new ArrayList<>();
    //  NOT COMPLET ONES
    public void addPerson(String firstname,String lastname,long nationalCod, MyDate birthDate){
        this.people.add(new Person(firstname,lastname,nationalCod,birthDate));
    }

    public boolean addCompany(String companyName,long CEO_nationalCode){
            if(this.isPersonExist(CEO_nationalCode)==null){
                System.err.println("ther is no persone whit "+CEO_nationalCode+" cod in people.");
                return false;
            }
            this.companys.add(new Company(this.isPersonExist(CEO_nationalCode),companyName));
             System.out.println("the company made.");
             return true;
    }

    public Person isPersonExist(long personId){
        for (Person person : this.people) {
            if(person.getNationalCode()==personId){
                return person;
            }
        }
        return null;
    }

    public boolean addBank(String bankName){
        for (Bank bank : this.centralBank.banks) {
            if (bank.getBankName().equalsIgnoreCase(bankName))
                System.err.println("Ther is already a bank with this name .");
                return false;
        }
        Bank newBank=new Bank(bankName);
        this.centralBank.banks.add(newBank);
        if (new Bank(bankName).getBankId()==0){
            this.centralBank.banks.remove(newBank);
            System.err.println("you cant open bank in that name");
            return false;
        }
        System.out.println("the bank created.");
        return true;
    }

    public boolean addBankWithInitial(String bankName, BigInteger initialMoney){
        for (Bank bank : this.centralBank.banks) {
            if (bank.getBankName().equalsIgnoreCase(bankName))
                System.err.println("Ther is already a bank with this name .");
            return false;
        }
        Bank newBank=new Bank(bankName,initialMoney);
        this.centralBank.banks.add(newBank);
        if (new Bank(bankName).getBankId()==0){
            this.centralBank.banks.remove(newBank);
            System.err.println("you cant open bank in that name");
            return false;
        }
        System.out.println("the bank created.");
        return true;
    }

    public boolean setBankIncomePercent(String bankName,int incomePercent){
        for (Bank bank : this.centralBank.banks) {
            if(bank.getBankName().equalsIgnoreCase(bankName)){
                bank.setBankIntresetPercent(incomePercent);
                System.out.println("bank incomePercent set.");
                return true;
            }
        }
        System.err.println("ther is no bank with that name");
        return false;
    }

    public void openingSavingAcount(Person person, Bank bank,long money,String acountTimeKinde,CurrentAcount acountOfGetingIntrest){
       SavingAcount myAcount=new SavingAcount(person,bank,this.centralBank.makingId(),money,new MyDate(),acountTimeKinde,acountOfGetingIntrest);
        this.centralBank.allSnavingAcount.add(myAcount);
        person.personSavingAcount.add(myAcount);
    }

    public boolean setBankAcountIntrest(String bankName,int percent,String acountTypeOfTime){
        for (Bank bank : this.centralBank.banks) {
            if(bank.getBankName().equalsIgnoreCase(bankName)){
                if(acountTypeOfTime.equalsIgnoreCase("short")){
                    bank.setSavingAcountIntrestPercentShortTime(percent);
                    return true;
                }
                else if(acountTypeOfTime.equalsIgnoreCase("long")){
                    bank.setSavingAcountIntrestPercentLongTime(percent);
                    return true;
                }
                System.err.println("type of acountTime was wrong.");
                return false;
            }
        }
        System.err.println("ther is no bank with that name.");
        return false;
    }

    public boolean increasBankBalance(String bankName,BigInteger increasAmount){
        for (Bank bank : this.centralBank.banks) {
            if (bank.getBankName().equalsIgnoreCase(bankName)){
                bank.setBankProperty(bank.getBankProperty().add(increasAmount));
                return true;
            }
        }
        System.err.println("ther is no bank with that name.");
        return false;
    }

    public boolean openingCurentAcount(String bankName,long nationalCod,long initialAmount){
       boolean isNationalCodExist=false;
       Person acountOwner=this.acountOwner(isNationalCodExist,nationalCod);
        if(!isNationalCodExist&&acountOwner==null) {
            System.err.println("the nationalCode belongs to no one.");
            return false;
        }
        for (Bank bank : this.centralBank.banks) {
            if(bank.getBankName().equalsIgnoreCase(bankName)){
                if(bank.openningCurrentAcount(acountOwner,initialAmount,this.centralBank.currentAcountId(bank))){
                    return true;
                }
            }
        }
        System.err.println("ther is no bank with that name.");
        return false;
    }

    public boolean openingSavingAcount(String bankName,long nationalCod,long initialAmount,String kindeOfTime,CurrentAcount catcherIntrest){
        boolean isNationalCodExist=false;
        Person acountOwner=this.acountOwner(isNationalCodExist,nationalCod);
        if(!isNationalCodExist&&acountOwner==null) {
            System.err.println("the nationalCode belongs to no one.");
            return false;
        }
        for (Bank bank : this.centralBank.banks) {
            if(bank.getBankName().equalsIgnoreCase(bankName)){
                if(bank.openningSavingAcount(acountOwner,initialAmount,this.centralBank.savingAcountId(bank),kindeOfTime,catcherIntrest)){
                    return true;
                }
            }
        }
        System.err.println("ther is no bank with that name.");
        return false;
    }

    public boolean cloasingAcount(String bankName,long nationalCod,long acuntNum){
        boolean isNationalCodMach =false;
        Person acountOwner=this.acountOwner(isNationalCodMach,nationalCod,acuntNum);
        if(!isNationalCodMach){
            System.err.println("the "+nationalCod+" dosenot have a acuonts with "+acuntNum+" id.");
            return false;
        }
        if(acountOwner==null) {
            System.err.println("the nationalCode belongs to no one.");
            return false;
        }
        return removeAcount(bankName,acountOwner,acuntNum);
    }

    public Person acountOwner(boolean isNationalCodMach,long nationalCod,long acuntNum){
        for (Person person : this.people) {
            if(person.getNationalCode()==nationalCod) {
                for (CurrentAcount currentAcount : person.personCurrentAcount) {
                    if (currentAcount.getAcountNumber() == acuntNum) {
                        isNationalCodMach = true;
                        return person;
                    }
                }
                for (SavingAcount SavingAcount : person.personSavingAcount) {
                    if (SavingAcount.getAcountNumber() == acuntNum) {
                        isNationalCodMach = true;
                        return person;
                    }
                }
            }
        }
        return null;
    }

    public Person acountOwner(boolean isNationalCodMach,long nationalCod){
        for (Person person : this.people) {
            if(person.getNationalCode()==nationalCod) {
                isNationalCodMach=true;
                return  person;
            }
        }
        return null;
    }

    public boolean removeAcount(String bankName,Person acountOwner,long acuntNum){
        for (Bank bank : this.centralBank.banks) {
            if(bank.getBankName().equalsIgnoreCase(bankName)){
                for (CurrentAcount bankCurrentAcount : bank.bankCurrentAcounts) {
                    if(bankCurrentAcount.getAcountNumber()==acuntNum){
                        ArrayList<CurrentAcount> curentAcunt=bank.mapPersonsCurrentAcounts.get(acountOwner);
                        curentAcunt.remove(bankCurrentAcount);
                        bank.mapPersonsCurrentAcounts.put(acountOwner,curentAcunt);
                        bank.bankCurrentAcounts.remove(bankCurrentAcount);
                        this.centralBank.allCurrentAcount.remove(bankCurrentAcount);
                        this.centralBank.allBanksMapPersonsCurrentAcount.put(acountOwner,curentAcunt);
                        System.out.println("acount deleted");
                        return true;
                    }
                }
                for (SavingAcount bankSavingAcount : bank.bankSavingAcounts) {
                    if(bankSavingAcount.getAcountNumber()==acuntNum){
                        ArrayList<SavingAcount> curentAcunt=bank.mapPersonsSavingAcounts.get(acountOwner);
                        curentAcunt.remove(bankSavingAcount);
                        bank.mapPersonsSavingAcounts.put(acountOwner,curentAcunt);
                        bank.bankSavingAcounts.remove(bankSavingAcount);
                        this.centralBank.allSnavingAcount.remove(bankSavingAcount);
                        this.centralBank.allBanksMapPersonsSavingAcounts.put(acountOwner,curentAcunt);
                        System.out.println("acount deleted");
                        return true;
                    }
                }
            }
        }
        System.err.println("ther is no bank with that name.");
        return false;
    }

    public boolean changeCardPassworld(long cardId,int password,int newPassworld){
        for (CurrentAcount currentAcount : this.centralBank.allCurrentAcount) {
            if(cardId==currentAcount.getCreditCard().getCardNumber()){
                if(currentAcount.getCreditCard().getPassworld()==password){
                    currentAcount.getCreditCard().setPassworld(newPassworld);
                    System.out.println("passworld changed!");
                    return true;
                }
                System.err.println("passworld is incorrect!");
                return false;
            }
        }
        System.err.println("ther is no card whit that card num!");
        return false;
    }

    public boolean activateCardSecPassworld(long cardId,int password,int secPassworld){
        for (CurrentAcount currentAcount : this.centralBank.allCurrentAcount) {
            if(cardId==currentAcount.getCreditCard().getCardNumber()){
                if(currentAcount.getCreditCard().getPassworld()==password){
                    if(currentAcount.getCreditCard().getSecondPassworld()==0){
                        currentAcount.getCreditCard().setSecondPassworld(secPassworld);
                        System.out.println("second passworld set!");
                        return true;
                    }
                    System.err.println("the card already has second passworld!");
                    return false;
                }
                System.err.println("passworld is incorrect!");
                return false;
            }
        }
        System.err.println("ther is no card whit that card num!");
        return false;
    }

    public boolean changeCardsecPassworld(long cardId,int password,int newSecPassworld){
        for (CurrentAcount currentAcount : this.centralBank.allCurrentAcount) {
            if(cardId==currentAcount.getCreditCard().getCardNumber()){
                if(currentAcount.getCreditCard().getPassworld()==password){
                    currentAcount.getCreditCard().setPassworld(newSecPassworld);
                    System.out.println("seconde passworld changed!");
                    return true;
                }
                System.err.println("passworld is incorrect!");
                return false;
            }
        }
        System.err.println("ther is no card whit that card num!");
        return false;
    }

    //TO DOOOOO
    public boolean extendCardExpiraitionDate(){
        return true;
    }

    

}
