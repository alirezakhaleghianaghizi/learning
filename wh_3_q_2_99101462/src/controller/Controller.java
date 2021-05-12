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
        for (Person person : this.people) {
            if(person.getNationalCode()==CEO_nationalCode){
                this.companys.add(new Company(person,companyName));
                System.out.println("the company made.");
                return true;
            }
        }
        System.err.println("ther is no persone whit "+CEO_nationalCode+" cod in people.");
        return false;
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
       Person acountOwner=new Person(null,null,-1,null);
        for (Person person : this.people) {
            if(person.getNationalCode()==nationalCod) {
                isNationalCodExist=true;
                acountOwner =person;
            }
        }
        if(!isNationalCodExist&&acountOwner.getNationalCode()==-1) {
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
        Person acountOwner=new Person(null,null,-1,null);
        for (Person person : this.people) {
            if(person.getNationalCode()==nationalCod) {
                isNationalCodExist=true;
                acountOwner =person;
            }
        }
        if(!isNationalCodExist&&acountOwner.getNationalCode()==-1) {
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
        Person acountOwner=new Person(null,null,-1,null);
        for (Person person : this.people) {
            if(person.getNationalCode()==nationalCod) {
                acountOwner =person;
                for (CurrentAcount currentAcount : acountOwner.personCurrentAcount) {
                    if (currentAcount.getAcountNumber() == acuntNum) {
                        isNationalCodMach = true;
                        break;
                    }
                }
                for (SavingAcount SavingAcount : acountOwner.personSavingAcount) {
                    if (SavingAcount.getAcountNumber() == acuntNum) {
                        isNationalCodMach = true;
                        break;
                    }
                }

            }
        }
        if(!isNationalCodMach){
            System.err.println("the "+nationalCod+" dosenot have a acuonts with "+acuntNum+" id.");
            return false;
        }
        if(acountOwner.getNationalCode()==-1) {
            System.err.println("the nationalCode belongs to no one.");
            return false;
        }
        for (Bank bank : this.centralBank.banks) {
            if(bank.getBankName().equalsIgnoreCase(bankName)){
                for (CurrentAcount bankCurrentAcount : bank.bankCurrentAcounts) {
                    if(bankCurrentAcount.getAcountNumber()==acuntNum){
                        ArrayList<CurrentAcount> curentAcunt=bank.mapPersonsCurrentAcounts.get(acountOwner);
                        curentAcunt.remove(bankCurrentAcount);
                        bank.mapPersonsCurrentAcounts.put(acountOwner,curentAcunt);
                        bank.bankCurrentAcounts.remove(bankCurrentAcount);
                        //not complet yet
//dsffsd
                        


                    }
                }
            }
        }
        System.err.println("ther is no bank with that name.");
        return true;
    }

}
