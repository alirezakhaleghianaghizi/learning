package controller;

import model.*;

import java.security.PublicKey;
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

    public boolean addBankWithInitial(String bankName, long initialMoney){
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

    public boolean increasBankBalance(String bankName,long increasAmount){
        for (Bank bank : this.centralBank.banks) {
            if (bank.getBankName().equalsIgnoreCase(bankName)){
                bank.setBankProperty(bank.getBankProperty()+increasAmount);
                return true;
            }
        }
        System.err.println("ther is no bank with that name.");
        return false;
    }

    public boolean openingCurentAcount(String bankName,long nationalCod,long initialAmount){
       boolean isNationalCodExist=false;
       boolean ageRequired=false;
       Person acountOwner=this.acountOwner(isNationalCodExist,nationalCod,ageRequired);
       if(!ageRequired){
           System.err.println("your age is not required for opening account ");
           return false;
       }
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
        System.err.println("there is no bank with that name.");
        return false;
    }

    public boolean openingSavingAcount(String bankName,long nationalCod,long initialAmount,String kindeOfTime,CurrentAcount catcherIntrest){
        boolean isNationalCodExist=false;
        if(kindeOfTime.equalsIgnoreCase("short")){
            if(initialAmount<5000){
                System.err.println("initial amount is not enough for short saving acount");
                return false;
            }
        }
        if(kindeOfTime.equalsIgnoreCase("long")){
            if(initialAmount<10000){
                System.err.println("initial amount is not enough for long saving acount");
                return false;
            }
        }
        boolean ageRequired=false;
        Person acountOwner=this.acountOwner(isNationalCodExist,nationalCod,ageRequired);
        if(!ageRequired){
            System.err.println("your age is not required for opening account ");
            return false;
        }
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
        System.err.println("there is no bank with that name.");
        return false;
    }

    public boolean cloasingAcount(String bankName,long nationalCod,long acuntNum){
        boolean isNationalCodMach =false;
        Person acountOwner=this.acountOwner(isNationalCodMach,nationalCod,acuntNum);
        if(!isNationalCodMach){
            System.err.println("the "+nationalCod+" dose not have a accounts with "+acuntNum+" id.");
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

    public Person acountOwner(boolean isNationalCodMach,long nationalCod,boolean ageRequired){
        for (Person person : this.people) {
            if(person.getNationalCode()==nationalCod) {
                if(person.getBirthDate().getYear()-MyDate.currentYear ==16&&person.getBirthDate().getMonth()==MyDate.currentMonth &&person.getBirthDate().getDay()<=MyDate.currentDay){
                    ageRequired=false;
                }
                else if(person.getBirthDate().getYear()-MyDate.currentYear ==16&&person.getBirthDate().getMonth()<=MyDate.currentMonth){
                    ageRequired=false;
                }
                else if(person.getBirthDate().getYear()-MyDate.currentYear <16){
                    ageRequired=false;
                }
                else ageRequired=true;

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
                        System.out.println("account deleted");
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
                        System.out.println("account deleted");
                        return true;
                    }
                }
            }
        }
        System.err.println("there is no bank with that name.");
        return false;
    }

    public boolean changeCardPassworld(long cardId,int password,int newPassworld){
        for (CurrentAcount currentAcount : this.centralBank.allCurrentAcount) {
            if(cardId==currentAcount.getCreditCard().getCardNumber()){
                if(currentAcount.getCreditCard().getPassworld()==password){
                    currentAcount.getCreditCard().setPassworld(newPassworld);
                    System.out.println("password changed!");
                    return true;
                }
                System.err.println("password is incorrect!");
                return false;
            }
        }
        System.err.println("there is no card whit that card num!");
        return false;
    }

    public boolean activateCardSecPassworld(long cardId,int password,int secPassworld){
        for (CurrentAcount currentAcount : this.centralBank.allCurrentAcount) {
            if(cardId==currentAcount.getCreditCard().getCardNumber()){
                if(currentAcount.getCreditCard().getPassworld()==password){
                    if(currentAcount.getCreditCard().getSecondPassworld()==0){
                        currentAcount.getCreditCard().setSecondPassworld(secPassworld);
                        System.out.println("second password set!");
                        return true;
                    }
                    System.err.println("the card already has second password!");
                    return false;
                }
                System.err.println("password is incorrect!");
                return false;
            }
        }
        System.err.println("there is no card whit that card num!");
        return false;
    }

    public boolean changeCardsecPassworld(long cardId,int password,int newSecPassworld){
        for (CurrentAcount currentAcount : this.centralBank.allCurrentAcount) {
            if(cardId==currentAcount.getCreditCard().getCardNumber()){
                if(currentAcount.getCreditCard().getPassworld()==password){
                    if(currentAcount.getCreditCard().getPassworld()==0){
                        System.out.println("second password is not enabled.");
                        return false;
                    }
                    currentAcount.getCreditCard().setPassworld(newSecPassworld);
                    System.out.println("second password changed!");
                    return true;
                }
                System.err.println("password is incorrect!");
                return false;
            }
        }
        System.err.println("there is no card whit that card num!");
        return false;
    }

    public boolean openingCompanysSavingAcount(String bankName, String companyCod, long initialAmount, String kindeOfTime, CurrentAcount catcherIntrest){
        for (Company company : this.companys) {
            if(company.getCompanyId().equalsIgnoreCase(companyCod)){
               return this.openingSavingAcount(bankName,company.getManager().getNationalCode(),initialAmount,kindeOfTime,catcherIntrest);
            }
        }
        System.err.println("there is no company with that id");
        return false;
    }

    public boolean openingCompaniesCurrentAccount(String bankName, String companyCod, long initialAmount){
        for (Company company : this.companys) {
            if(company.getCompanyId().equalsIgnoreCase(companyCod)){
                return this.openingCurentAcount(bankName,company.getManager().getNationalCode(),initialAmount);
            }
        }
        System.err.println("there is no company with that id");
        return false;
    }

    public boolean closingCompanyAccount(String bankName, String companyCod, long accountNum){
       for (Company company : this.companys) {
           if(company.getCompanyId().equalsIgnoreCase(companyCod)){
               return  this.cloasingAcount(bankName,company.getManager().getNationalCode(), accountNum);
               }
       }
       System.err.println("there is no company with that id");
       return false;
   }

    public boolean extendCardExpirationDate(String bankName,long cardNum,long nationalCod){
       Person cardOwner =isPersonExist(nationalCod);
       if(cardOwner==null){
           System.err.println("there is no person with that id in people");
           return false;
       }

        for (Bank bank : this.centralBank.banks) {
                if(bank.getBankName().equalsIgnoreCase(bankName)){
                    for (CurrentAcount bankCurrentAcount : bank.bankCurrentAcounts) {
                        if(cardNum==bankCurrentAcount.getAcountNumber()){
                            if(bankCurrentAcount.getOwnerOfAcount().getNationalCode()!=nationalCod){
                                System.err.println("the owner of carde is not"+nationalCod);
                                return false;
                            }
                            bankCurrentAcount.getCreditCard().setDateOfExpiringCard(new MyDate(MyDate.currentYear +4,MyDate.currentMonth,MyDate.currentDay));
                            System.out.println("Expiration of card date extended");
                            return true;
                        }

                    }
                    System.err.println("there is no card with that id in the bank");
                    return false;
                }
        }
        System.err.println("there is no bank with that name");
        return false;
    }

    public boolean extendCompanyCardExpirationDate(String bankName,long cardNum,String companyId){
        for (Company company : this.companys) {
            if(company.getCompanyId().equalsIgnoreCase(companyId)){
                return this.extendCardExpirationDate(bankName,cardNum,company.getManager().getNationalCode());
            }
        }
        System.err.println("there is no company with that id");
        return false;
    }

    public boolean depositMoneyToAccount(String bankname,long accountId,long money){
        for (Bank bank : this.centralBank.banks) {
            if(bank.getBankName().equalsIgnoreCase(bankname)){
                for (CurrentAcount bankCurrentAcount : bank.bankCurrentAcounts) {
                    if(bankCurrentAcount.getAcountNumber()==accountId){
                        bankCurrentAcount.setMoney(bankCurrentAcount.getMoney()+money);
                        bank.bankProperty+=money;
                        System.out.println("deposited "+money+"money to "+accountId);
                        return true;
                    }
                }
                for (SavingAcount bankSavingAcount : bank.bankSavingAcounts) {
                    if(bankSavingAcount.getAcountNumber()==accountId){
                        bankSavingAcount.setMoney(bankSavingAcount.getMoney()+money);
                        bank.bankProperty+=money;
                        System.out.println("deposited "+money+"money to "+accountId);
                        return true;
                    }
                }
                System.err.println("there is no account with that id in this bank");
                return false;
            }
        }
        System.err.println("there is no bank with that name");
        return false;
    }

    //TODO
    //public boolean withdrawMoneyFromAccount


    

}
