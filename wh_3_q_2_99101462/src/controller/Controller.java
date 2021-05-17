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
                        bankCurrentAcount.creditCard.setMoney(bankCurrentAcount.creditCard.getMoney()+money);
                        bankCurrentAcount.ownerOfAcount.setAcountsMoney(bankCurrentAcount.ownerOfAcount.getAcountsMoney()+money);
                        bank.bankProperty+=money;
                        System.out.println("deposited "+money+"money to "+accountId);
                        return true;
                    }
                }
                for (SavingAcount bankSavingAcount : bank.bankSavingAcounts) {
                    if(bankSavingAcount.getAcountNumber()==accountId&&this.isExistSavingAcount(bankSavingAcount)){
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

    public boolean withdrawMoneyFromAccount(long cardId,int  password,long amount){
            if(amount >2000){
                System.err.println("amount of needed money is more than atm effort  . pleas refer to bank fo mor than 2000 money");
                return false;
            }
        for (CurrentAcount currentAcount : this.centralBank.allCurrentAcount) {
            if(currentAcount.getAcountNumber()==cardId){
                if(currentAcount.creditCard.withDraw(password,amount)){
                    System.out.println(amount+"money withdraw from "+cardId);
                    return true;
                }
                return false;
            }
        }
        System.err.println("there is no card with that id");
        return false;
    }

    public boolean withdrawMoneyFromAccount(String bankNum,long accountId,long nationalCode,long money){
        Person accountOwner= this.isPersonExist(nationalCode);
        if(accountOwner==null){
            System.err.println("there is no person in people with that id");
            return false;
        }
        SavingAcount currentSavingAcount=null;
        for (SavingAcount savingAcount : accountOwner.personSavingAcount) {
            if(savingAcount.getAcountNumber()==accountId&&this.isExistSavingAcount(savingAcount)){
                currentSavingAcount=savingAcount;
                break;
            }
        }
        CurrentAcount currentCurrentAcount=null;
        for (CurrentAcount currentAcount : accountOwner.personCurrentAcount) {
            if(currentAcount.getAcountNumber()==accountId){
                currentCurrentAcount=currentAcount;
                break;
            }
        }
        if(currentCurrentAcount==null&&currentSavingAcount==null){
            System.err.println("that person has no account with that id");
            return false;
        }
        if(currentCurrentAcount.getBank().getBankName().equalsIgnoreCase(bankNum)&&currentCurrentAcount!=null){
             if(currentCurrentAcount.withdrawMoney(money,bankNum)){
                System.out.println("you withdraw"+money+ "money from this"+accountId+"account");
                return true;
             }
               return false;
        }
        if(currentSavingAcount.getBank().getBankName().equalsIgnoreCase(bankNum)&&currentSavingAcount!=null){
                if(!this.isExistSavingAcount(currentSavingAcount)) {
                    if (currentSavingAcount.withdrawMoney(money, bankNum)) {
                        System.out.println("you withdraw" + money + "money from this" + accountId + "account");
                        return true;
                    }
                }
                return false;
        }
        System.out.println("withdraw finished");
        return true;
    }

    public boolean isExistSavingAcount(SavingAcount currentAcount){
        int timeOfExpierring;
        if(currentAcount.getAcountTimeKind().equalsIgnoreCase("short")){
            timeOfExpierring=1;
        }
        else timeOfExpierring=2;
        if (currentAcount.getDateOfOpening().getYear()<MyDate.currentYear-timeOfExpierring){
            System.err.println("account is expiered");
            return false;
        }
        if (currentAcount.getDateOfOpening().getYear()==MyDate.currentYear-timeOfExpierring&&currentAcount.getDateOfOpening().getMonth()<MyDate.currentMonth){
            System.err.println("account is expiered");
            return false;
        }
        if (currentAcount.getDateOfOpening().getYear()==MyDate.currentYear-timeOfExpierring&&currentAcount.getDateOfOpening().getMonth()==MyDate.currentMonth&&currentAcount.getDateOfOpening().getDay()<MyDate.currentDay){
            System.err.println("account is expiered");
            return false;
        }
        return true;
    }

    public boolean withdrawMoneyForCompany(String bankNum,long accountId,String companyId,long money){
        for (Company company : this.companys) {
            if(company.getCompanyId().equalsIgnoreCase(companyId)){
                return this.withdrawMoneyFromAccount(bankNum,accountId,company.getManager().getNationalCode(),money);
            }
        }
        System.err.println("there is no company with that id");
        return false;
    }

    public boolean getAccountBalance(long cardId,int password){
        for (CurrentAcount currentAcount : this.centralBank.allCurrentAcount) {
            if(currentAcount.getCreditCard().getCardNumber()==cardId){
                if(currentAcount.getCreditCard().getPassworld()==password){
                    if(currentAcount.getCreditCard().getDateOfExpiringCard().getYear()<MyDate.currentYear||(currentAcount.getCreditCard().getDateOfExpiringCard().getYear()==MyDate.currentYear&&currentAcount.getCreditCard().getDateOfExpiringCard().getMonth()<MyDate.currentMonth)||(currentAcount.getCreditCard().getDateOfExpiringCard().getYear()==MyDate.currentYear&&currentAcount.getCreditCard().getDateOfExpiringCard().getMonth()==MyDate.currentMonth&&currentAcount.getCreditCard().getDateOfExpiringCard().getDay()<MyDate.currentDay)){
                        System.err.println("your card is expired");
                        return false;
                    }
                    System.out.println(cardId+"has "+currentAcount.getCreditCard().getMoney()+" money");
                    return true;
                }
                System.err.println("password was wrong");
                return false;
            }
        }
        System.err.println("there is no card with that id");
        return false;
    }

    public boolean transferMoneyFromecard(long cardNum,int password,long receiverCardNum,long money){
        CurrentAcount acount=null;
        CurrentAcount receive=null;
        for (CurrentAcount currentAcount : this.centralBank.allCurrentAcount) {
            if(currentAcount.creditCard.getCardNumber()==cardNum&&acount==null){
                if(currentAcount.getCreditCard().getDateOfExpiringCard().getYear()<MyDate.currentYear||(currentAcount.getCreditCard().getDateOfExpiringCard().getYear()==MyDate.currentYear&&currentAcount.getCreditCard().getDateOfExpiringCard().getMonth()<MyDate.currentMonth)||(currentAcount.getCreditCard().getDateOfExpiringCard().getYear()==MyDate.currentYear&&currentAcount.getCreditCard().getDateOfExpiringCard().getMonth()==MyDate.currentMonth&&currentAcount.getCreditCard().getDateOfExpiringCard().getDay()<MyDate.currentDay)){
                    System.err.println("your card is expired");
                    return false;
                }
                acount=currentAcount;
            }
            else if (currentAcount.creditCard.getCardNumber() == receiverCardNum&&receive==null) {
                if(currentAcount.getCreditCard().getDateOfExpiringCard().getYear()<MyDate.currentYear||(currentAcount.getCreditCard().getDateOfExpiringCard().getYear()==MyDate.currentYear&&currentAcount.getCreditCard().getDateOfExpiringCard().getMonth()<MyDate.currentMonth)||(currentAcount.getCreditCard().getDateOfExpiringCard().getYear()==MyDate.currentYear&&currentAcount.getCreditCard().getDateOfExpiringCard().getMonth()==MyDate.currentMonth&&currentAcount.getCreditCard().getDateOfExpiringCard().getDay()<MyDate.currentDay)){
                    System.err.println("destination card is expired");
                    return false;
                }
                receive=currentAcount;
            }
            }
        if(acount==null){
            System.err.println("there is no card with that id");
            return false;
        }
        if(receive==null){
            System.err.println("there is no receive card with that id");
            return false;
        }
        if(password!=acount.creditCard.getPassworld()){
            System.err.println("password is wrong");
            return false;
        }
        if(money> acount.getMoney()){
            System.err.println("your money is not enough for transferring");
            return false;
        }
            acount.decreasingMoney(money);
            receive.decreasingMoney(-money);
        System.out.println(money+"money transfered from "+cardNum+" to "+receiverCardNum);
        return true;
        }

    public boolean transferMoneyFromAcount(String bankName,long accountId,long nationalCod,long receiverAcountId,long amount){
        for (CurrentAcount currentAcount : this.centralBank.allCurrentAcount) {
            if(receiverAcountId==currentAcount.getAcountNumber()){
                if(currentAcount.getCreditCard().getDateOfExpiringCard().getYear()<MyDate.currentYear||(currentAcount.getCreditCard().getDateOfExpiringCard().getYear()==MyDate.currentYear&&currentAcount.getCreditCard().getDateOfExpiringCard().getMonth()<MyDate.currentMonth)||(currentAcount.getCreditCard().getDateOfExpiringCard().getYear()==MyDate.currentYear&&currentAcount.getCreditCard().getDateOfExpiringCard().getMonth()==MyDate.currentMonth&&currentAcount.getCreditCard().getDateOfExpiringCard().getDay()<MyDate.currentDay)){
                    System.err.println("your card is expired");
                    return false;
                }
               return this.transferToCurrentAcount(bankName,accountId,nationalCod,currentAcount,amount);
            }
        }
        for (SavingAcount savingAcount : this.centralBank.allSnavingAcount){
            if(receiverAcountId==savingAcount.getAcountNumber()){
                if(this.isExistSavingAcount(savingAcount)){
                    System.err.println("the account is expiered");
                    return false;
                }
                return this.transferToSavingAcount(bankName,accountId,nationalCod,savingAcount,amount);
            }
        }
        System.err.println("there is no account with receiver accountId");
        return false;
    }

    public Bank isBankExist(String bankName){
        for (Bank bank : this.centralBank.banks) {
            if(bank.getBankName().equalsIgnoreCase(bankName)) return bank;
        }
        System.err.println("there is no bank with that name");
        return null;
    }

    public boolean transferToCurrentAcount(String bankName,long accountId,long nationalCod,CurrentAcount receive,long money){
        if(this.isBankExist(bankName)==null){
            return false;
        }
        if(this.isPersonExist(nationalCod)==null){
            System.err.println("person with that id dose not exist");
            return false;
        }
        boolean isacountBelongsBank=false;
        for (CurrentAcount bankCurrentAcount : this.isBankExist(bankName).bankCurrentAcounts) {
            if(bankCurrentAcount.getAcountNumber()==accountId) isacountBelongsBank=true;
        }
        for (SavingAcount bankSavingAcount : this.isBankExist(bankName).bankSavingAcounts) {
            if(bankSavingAcount.getAcountNumber()==accountId) isacountBelongsBank=true;
        }
        if(!isacountBelongsBank){
            System.err.println("the bank does not have the account");
            return false;
        }
        for (CurrentAcount currentAcount : this.isPersonExist(nationalCod).personCurrentAcount) {
            if(currentAcount.getAcountNumber()==accountId){
                if(currentAcount.getMoney()<money){
                    System.err.println("account money is not enough");
                    return false;
                }
                currentAcount.decreasingMoney(money);
                receive.decreasingMoney(-money);
                System.out.println(money+"money transfered from "+currentAcount+" to "+receive);
                return true;
            }
        }
        for (SavingAcount savingAcount : this.isPersonExist(nationalCod).personSavingAcount) {
            if(savingAcount.getAcountNumber()==accountId){
                if(savingAcount.getMoney()<money){
                    System.err.println("account money is not enough");
                    return false;
                }
                if(this.isExistSavingAcount(savingAcount)){
                    System.err.println("the account is expiered");
                    return false;
                }
                savingAcount.decreasingMoney(money);
                receive.decreasingMoney(-money);
                System.out.println(money+"money transfered from "+savingAcount+" to "+receive);
                return true;
            }
        }
        System.err.println("person dose not have that account");
        return false;
    }

    public boolean transferToSavingAcount(String bankName,long accountId,long nationalCod,SavingAcount receive, long money){
        if(this.isBankExist(bankName)==null){
            return false;
        }
        if(this.isPersonExist(nationalCod)==null){
            System.err.println("person with that id dose not exist");
            return false;
        }
        boolean isacountBelongsBank=false;
        for (CurrentAcount bankCurrentAcount : this.isBankExist(bankName).bankCurrentAcounts) {
            if(bankCurrentAcount.getAcountNumber()==accountId) isacountBelongsBank=true;
        }
        for (SavingAcount bankSavingAcount : this.isBankExist(bankName).bankSavingAcounts) {
            if(bankSavingAcount.getAcountNumber()==accountId) isacountBelongsBank=true;
        }
        if(!isacountBelongsBank){
            System.err.println("the bank does not have the account");
            return false;
        }
        for (CurrentAcount currentAcount : this.isPersonExist(nationalCod).personCurrentAcount) {
            if(currentAcount.getAcountNumber()==accountId){
                if(currentAcount.getMoney()<money){
                    System.err.println("account money is not enough");
                    return false;
                }
                currentAcount.decreasingMoney(money);
                receive.decreasingMoney(-money);
                System.out.println(money+"money transfered from "+currentAcount+" to "+receive);
                return true;
            }
        }
        for (SavingAcount savingAcount : this.isPersonExist(nationalCod).personSavingAcount) {
            if(savingAcount.getAcountNumber()==accountId){
                if(savingAcount.getMoney()<money){
                    System.err.println("account money is not enough");
                    return false;
                }
                if(this.isExistSavingAcount(savingAcount)){
                    System.err.println("the account is expiered");
                    return false;
                }
                savingAcount.decreasingMoney(money);
                receive.decreasingMoney(-money);
                System.out.println(money+"money transfered from "+savingAcount+" to "+receive);
                return true;
            }
        }
        System.err.println("person dose not have that account");
        return false;
    }

    public boolean transferFromCompany(String bankName,long accountId,String companyId,long receiverAcountId,long amount){
        for (Company company : this.companys) {
            if(company.getCompanyId().equalsIgnoreCase(companyId)){
                return this.transferMoneyFromAcount(bankName,accountId,company.getManager().getNationalCode(),receiverAcountId,amount);
            }
        }
        System.err.println("there is mo company with that id .");
        return false;
    }

    public boolean requsetLoan(String bankName,long nationalCod,long amount){
        boolean isHeHasAccountInTheBank=false;
        for (SavingAcount bankSavingAcount : this.isBankExist(bankName).bankSavingAcounts) {
            if(bankSavingAcount.getOwnerOfAcount().getNationalCode()==nationalCod) isHeHasAccountInTheBank=true;
        }
        for (CurrentAcount bankSavingAcount : this.isBankExist(bankName).bankCurrentAcounts) {
            if(bankSavingAcount.getOwnerOfAcount().getNationalCode()==nationalCod) isHeHasAccountInTheBank=true;
        }
        if(!isHeHasAccountInTheBank){
            System.err.println("you dont have an Account in that bank");
            return false;
        }
        if(isPersonExist(nationalCod).getAcountsMoney()*5<amount){
            System.err.println("your money is not enough to get the loan");
            return false;
        }
        if(this.isBankExist(bankName).bankProperty<amount){
            System.err.println("bank has no enough money to pay");
            return false;
        }
        if(this.isBankExist(bankName)==null){
            System.err.println("there is no bank with that id");
            return false;
        }
        if(this.isPersonExist(nationalCod)==null){
            System.err.println("there is no person with that id");
            return false;
        }
        if(this.isPersonExist(nationalCod).getBirthDate().getYear()+18<MyDate.currentYear){
            System.err.println("your age is not requiered for grtting loan");
            return false;
        }
        ArrayList<Loan> loans=new ArrayList<>();
        if(this.centralBank.allBankMapPersonsLoans.get(this.isPersonExist(nationalCod))!=null){
            for (Loan loan : this.centralBank.allBankMapPersonsLoans.get(this.isPersonExist(nationalCod))) {
                if(loan.getLoanAmount()> loan.getRegivenAmount()){
                    System.err.println("you already have an incomplete loan");
                    return false;
                }
            }
            this.isBankExist(bankName).decrreaseForLoan(this.isPersonExist(nationalCod),amount);
            loans=this.centralBank.allBankMapPersonsLoans.get(this.isPersonExist(nationalCod));
            loans.add(new Loan(this.isPersonExist(nationalCod),amount,this.isBankExist(bankName).bankIntresetPercent,2));
            this.centralBank.allBankMapPersonsLoans.put(this.isPersonExist(nationalCod),this.centralBank.allBankMapPersonsLoans.get(this.isPersonExist(nationalCod)));
        }
        //
        if(!this.isBankExist(bankName).receiveLoan(this.isPersonExist(nationalCod),amount,loans)) return false;
        this.centralBank.allBankMapPersonsLoans.put(this.isPersonExist(nationalCod),loans);
        return true;
    }

    public boolean requestCompany(String bankName,String companyId,long amount){
        for (Company company : this.companys) {
            if(company.getCompanyId().equalsIgnoreCase(companyId)){
                return this.requsetLoan(bankName,company.getManager().getNationalCode(),amount);
            }
        }
        System.err.println("there is no company with that id");
        return false;
    }

    public boolean payLoan(String bankName,long nationalCod,long amount,boolean sout){
        if(isBankExist(bankName)==null){
            if(sout)System.err.println("there is no bank with that name");
            return false;
        }
        if(isPersonExist(nationalCod)==null){
            if(sout)System.err.println("there is no person with that name");
            return false;
        }
        if(isPersonExist(nationalCod).getAcountsMoney()<amount){
            if(sout)System.err.println("you dont have enough money");
            return false;
        }
        if(isBankExist(bankName).mapPersonsLoans.get(isPersonExist(nationalCod))!=null){
            for (Loan loan : isBankExist(bankName).mapPersonsLoans.get(isPersonExist(nationalCod))) {
                if(loan.getLoanAmount()>loan.getRegivenAmount()){
                    long notActualAmount=amount;
                    return this.payForLoan(isPersonExist(nationalCod),notActualAmount,loan,bankName,nationalCod,sout);
                    }
                }
            if(sout)System.err.println("the person has no oncomplete loan in that bank");
            return false;
            }

        if(sout)System.err.println("the person has no loan in that bank");
        return false;
    }

    public boolean payForLoan(Person person,long notActualAmount,Loan loan,String bankName,long nationalCod,boolean sout){
        long amount=notActualAmount;
        for (SavingAcount savingAcount : loan.getLoanCatcher().personSavingAcount) {
        if(savingAcount.getMoney()>amount&&notActualAmount>0){
            savingAcount.setMoney(savingAcount.getMoney()-amount);
            isBankExist(bankName).bankProperty+=amount;
            isPersonExist(nationalCod).setAcountsMoney(isPersonExist(nationalCod).getAcountsMoney()-amount);
            if(sout)System.out.println("you paid "+amount+"of loan");
            loan.monthlyPayment.put(new MyDate(),amount);
            return true;
        }
        else if(notActualAmount>0) {
            notActualAmount-=savingAcount.getMoney();
            savingAcount.setMoney(0);
            isBankExist(bankName).bankProperty+=amount;
            isPersonExist(nationalCod).setAcountsMoney(isPersonExist(nationalCod).getAcountsMoney()-amount);
        }
        else if (notActualAmount == 0) {
            if(sout)System.out.println("you paid "+amount+"of loan");
            loan.monthlyPayment.put(new MyDate(),amount);
            return true;
        }

    }
    for (CurrentAcount currentAcount : loan.getLoanCatcher().personCurrentAcount) {
        if(currentAcount.getMoney()>amount&&notActualAmount>0){
            currentAcount.setMoney(currentAcount.getMoney()-amount);
            isBankExist(bankName).bankProperty+=amount;
            isPersonExist(nationalCod).setAcountsMoney(isPersonExist(nationalCod).getAcountsMoney()-amount);
            if(sout)System.out.println("you paid "+amount+"of loan");
            loan.monthlyPayment.put(new MyDate(),amount);
            return true;
        }
        else if(notActualAmount>0) {
            notActualAmount-=currentAcount.getMoney();
            currentAcount.setMoney(0);
            isBankExist(bankName).bankProperty+=amount;
            isPersonExist(nationalCod).setAcountsMoney(isPersonExist(nationalCod).getAcountsMoney()-amount);
        }
        else if (notActualAmount == 0) {
            if(sout) System.out.println("you paid "+amount+"of loan");
            loan.monthlyPayment.put(new MyDate(),amount);
            return true;
        }

    }
    return true;
    }

    public boolean payLoanForCompany(String bankName,String companyId,long amount){
        for (Company company : this.companys) {
            if(company.getCompanyId().equalsIgnoreCase(companyId)){
                return this.payLoan(bankName,company.getManager().getNationalCode(),amount,true);
            }
        }
        System.err.println("there is no company with that id");
        return false;
    }

    public void dateTimeForward(int firstYear,int firstMonth,int firstDay){
        int yearsOfTraveling=MyDate.currentYear-firstYear;
        int monthOfTraveling=MyDate.currentMonth-firstMonth;
        int daysOfTraveling=MyDate.currentDay-firstDay;
        this.goingForward( yearsOfTraveling, monthOfTraveling, daysOfTraveling);
    }

    public void goingForward(int yearsOfTraveling,int monthsOfTraveling,int daysOfTraveling){
        for (Bank bank : this.centralBank.banks) {
            int isDaysChanges=0;
            if(MyDate.currentDay-isDaysChanges<bank.dateOfOpenning.getDay()) isDaysChanges++;
            int monthOfGettingPercent=monthsOfTraveling+12*yearsOfTraveling+isDaysChanges;
            this.goingForwardSavingAccount(bank,monthOfGettingPercent);
            this.goingForwardCardAccount(bank,monthOfGettingPercent);
            this.goingForwardLoan(bank,monthOfGettingPercent);

        }
    }

    public void goingForwardSavingAccount(Bank bank,int monthOfGettingPercent){
        for (SavingAcount bankSavingAcount : bank.bankSavingAcounts) {
            if(bankSavingAcount.isBlock!=true&&bankSavingAcount.getAcountTimeKind().equalsIgnoreCase("short")&&(bankSavingAcount.getDateOfOpening().getYear()-MyDate.currentDay*12+bankSavingAcount.getDateOfOpening().getMonth())>6){
                bankSavingAcount.getAcountOfGetingIntrest().setMoney(bankSavingAcount.getAcountOfGetingIntrest().getMoney()+bankSavingAcount.getMoney());
                bankSavingAcount.setMoney(0);
                bankSavingAcount.isBlock=true;
            }
            else if(bankSavingAcount.isBlock!=true&&bankSavingAcount.getAcountTimeKind().equalsIgnoreCase("long")&&(bankSavingAcount.getDateOfOpening().getYear()-MyDate.currentDay*12+bankSavingAcount.getDateOfOpening().getMonth())>12){
                bankSavingAcount.getAcountOfGetingIntrest().setMoney(bankSavingAcount.getAcountOfGetingIntrest().getMoney()+bankSavingAcount.getMoney());
                bankSavingAcount.setMoney(0);
                bankSavingAcount.isBlock=true;
            }
            else if(bankSavingAcount.getAcountTimeKind().equalsIgnoreCase("short")){
                bankSavingAcount.getAcountOfGetingIntrest().setMoney(bankSavingAcount.getAcountOfGetingIntrest().getMoney()+bank.savingAcountIntrestPercentShortTime*monthOfGettingPercent);
                bankSavingAcount.getOwnerOfAcount().setAcountsMoney(bankSavingAcount.getOwnerOfAcount().getAcountsMoney()+bank.savingAcountIntrestPercentShortTime*monthOfGettingPercent);
            }
            else if(bankSavingAcount.getAcountTimeKind().equalsIgnoreCase("long")){
                bankSavingAcount.getAcountOfGetingIntrest().setMoney(bankSavingAcount.getAcountOfGetingIntrest().getMoney()+bank.savingAcountIntrestPercentLongTime*monthOfGettingPercent);
                bankSavingAcount.getOwnerOfAcount().setAcountsMoney(bankSavingAcount.getOwnerOfAcount().getAcountsMoney()+bank.savingAcountIntrestPercentLongTime*monthOfGettingPercent);
            }
        }
    }

    public void goingForwardCardAccount(Bank bank,int monthOfGettingPercent){
        for (CurrentAcount acount : bank.bankCurrentAcounts) {
            if(acount.getCreditCard().isBlock!=true&&(acount.getCreditCard().getDateOfCreatingCard().getYear()-MyDate.currentDay*12+acount.getCreditCard().getDateOfCreatingCard().getMonth())>4*12){
                acount.getCreditCard().isBlock=true;
            }
        }
    }

    public void goingForwardLoan(Bank bank,int monthOfGettingPercent){
        for (Loan loan : bank.getLoans()) {
            for (int i = 0; i < monthOfGettingPercent; i++) {
                if(loan.getLoanAmount()*0.025>loan.getLoanCatcher().getAcountsMoney()){
                    loan.getLoanCatcher().isBlock=true;
                }
                else{
                    this.payLoan(bank.getBankName(),loan.getLoanCatcher().getNationalCode(),(long) loan.getLoanAmount()*25/1000,false);
                }
            }
        }
    }


    //TODO
    //public boolean giving loans
    

}
