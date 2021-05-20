package view;

import controller.Controller;
import model.*;

import java.util.Scanner;
import java.util.regex.Matcher;

public class InputProcessor {

    public Controller controller;
    public Scanner scanner = new Scanner(System.in);
    public String input;
    public InputProcessor(Controller controller) {
        this.controller=new Controller();
        this.controller = controller;
    }
    public void run(){
            Matcher matcher;
            if (this.controller==null){
                this.controller=new Controller();
            }
            while (!(input =scanner.nextLine()).equalsIgnoreCase("exit")){
                try {
                    if(( matcher = Algorithm.ADDBANK.inputMatcher(input)).find()){
                        this.controller.addBank(matcher.group(1));
                    }

                    else if(( matcher = Algorithm.ADDPERSON.inputMatcher(input)).find()){
                        this.controller.addPerson(matcher.group(1),matcher.group(2),Long.parseLong(matcher.group(3)),new MyDate(Integer.parseInt(matcher.group(6)),Integer.parseInt(matcher.group(5)),Integer.parseInt(matcher.group(4))));
                    }

                    else if(( matcher = Algorithm.ADDCOMPANY.inputMatcher(input)).find()){
                        this.controller.addCompany(matcher.group(1),Long.parseLong(matcher.group(2)));
                        System.out.println("company added to companies");
                    }

                    else if(( matcher = Algorithm.ADDBANKWITHINITIAL.inputMatcher(input)).find()){
                        this.controller.addBankWithInitial(matcher.group(1),Long.parseLong(matcher.group(2)));
                    }

                    else if(( matcher = Algorithm.SETBANKINCOMEPERCENT.inputMatcher(input)).find()){
                        this.controller.setBankIncomePercent(matcher.group(1),Integer.parseInt(matcher.group(2)));
                    }

                    else if(( matcher = Algorithm.SETBANKINTERESTPERCENTSHORT.inputMatcher(input)).find()){
                        this.controller.setBankAccountIntrest(matcher.group(1),Integer.parseInt(matcher.group(2)),matcher.group(3));
                    }

                    else if(( matcher = Algorithm.SETBANKINTERESTPERCENTLONG.inputMatcher(input)).find()){
                        this.controller.setBankAccountIntrest(matcher.group(1),Integer.parseInt(matcher.group(2)),matcher.group(3));
                    }

                    else if(( matcher = Algorithm.INCREASEBANKBALANCE.inputMatcher(input)).find()){
                        this.controller.increasBankBalance(matcher.group(1),Long.parseLong(matcher.group(2)));
                    }

                    else if(( matcher = Algorithm.OPENCURRENTACCOUNT.inputMatcher(input)).find()){
                        if(this.isPersonBlocke(Long.parseLong(matcher.group(2)))){
                            System.err.println("the person is blocked becuse of not paying loans ");
                        }
                        else this.controller.openingCurentAcount(matcher.group(1),Long.parseLong(matcher.group(2)),Long.parseLong(matcher.group(3)));
                    }

                    else if(( matcher = Algorithm.OPENCURRENTACCOUNTCOMPANY.inputMatcher(input)).find()){
                        if(this.isCompanyBlocke(matcher.group(2))){
                            System.err.println("the company is blocked becuse of not paying loans ");
                        }
                        else this.controller.openingCompaniesCurrentAccount(matcher.group(1),matcher.group(2),Long.parseLong(matcher.group(3)));
                    }

                    else if(( matcher = Algorithm.OPENSAVINGACCOUNT.inputMatcher(input)).find()){
                        if(this.isPersonBlocke(Long.parseLong(matcher.group(2)))){
                            System.err.println("the person is blocked becuse of not paying loans ");
                        }
                        else this.controller.openingSavingAcount(matcher.group(1),Long.parseLong(matcher.group(2)),Long.parseLong(matcher.group(5)),matcher.group(3));
                    }

                    else if(( matcher = Algorithm.OPENSAVINGACCOUNTCOMPANY.inputMatcher(input)).find()){
                        if(this.isCompanyBlocke(matcher.group(2))){
                            System.err.println("the company is blocked becuse of not paying loans ");
                        }
                        else this.controller.openingCompanysSavingAcount(matcher.group(1),matcher.group(2),Long.parseLong(matcher.group(5)),matcher.group(3));
                    }

                    else if(( matcher = Algorithm.CLOSEACCOUNT.inputMatcher(input)).find()){
                        if(this.isPersonBlocke(Long.parseLong(matcher.group(2)))){
                            System.err.println("the company is blocked becuse of not paying loans ");
                        }
                        else this.controller.cloasingAcount(matcher.group(1),Long.parseLong(matcher.group(2)),Long.parseLong(matcher.group(3)));
                    }

                    else if(( matcher = Algorithm.CLOSEACCOUNTCOMPANY.inputMatcher(input)).find()){
                        if(this.isCompanyBlocke(matcher.group(2))){
                            System.err.println("the company is blocked becuse of not paying loans ");
                        }
                        else this.controller.closingCompanyAccount(matcher.group(1),matcher.group(2),Long.parseLong(matcher.group(3)));
                    }

                    else if(( matcher = Algorithm.CHANGECARDPASSWORD.inputMatcher(input)).find()){
                        if(this.isCardBlocke(Long.parseLong(matcher.group(1)))){
                            System.err.println("the card is blocked  ");
                        }
                        else this.controller.changeCardPassworld(Long.parseLong(matcher.group(1)),Integer.parseInt(matcher.group(2)),Integer.parseInt(matcher.group(3)));
                    }

                    else if(( matcher = Algorithm.SETCARDSECONDPASSWORD.inputMatcher(input)).find()){
                        if(this.isCardBlocke(Long.parseLong(matcher.group(1)))){
                            System.err.println("the card is blocked  ");
                        }
                        else this.controller.activateCardSecPassworld(Long.parseLong(matcher.group(1)),Integer.parseInt(matcher.group(2)),Integer.parseInt(matcher.group(3)));
                    }

                    else if(( matcher = Algorithm.CHANGESECONDECARDPASSWORD.inputMatcher(input)).find()){
                        if(this.isCardBlocke(Long.parseLong(matcher.group(1)))){
                            System.err.println("the card is blocked ");
                        }
                        else this.controller.changeCardsecPassworld(Long.parseLong(matcher.group(1)),Integer.parseInt(matcher.group(2)),Integer.parseInt(matcher.group(3)));
                    }

                    else if(( matcher = Algorithm.EXTENDEXPIRATIONDATE.inputMatcher(input)).find()){
                        if(this.isCardBlocke(Long.parseLong(matcher.group(2)))){
                            System.err.println("the card is blocked  ");
                        }
                        else this.controller.extendCardExpirationDate(matcher.group(1),Long.parseLong(matcher.group(2)),Long.parseLong(matcher.group(3)));
                    }

                    else if(( matcher = Algorithm.EXTENDEXPIRATIONDATECOMPANY.inputMatcher(input)).find()){
                        if(this.isCompanyBlocke(matcher.group(2))){
                            System.err.println("the company is blocked ");
                        }
                        else this.controller.extendCompanyCardExpirationDate(matcher.group(1),Long.parseLong(matcher.group(2)),matcher.group(3));
                    }

                    else if(( matcher = Algorithm.DEPOSITMONEY.inputMatcher(input)).find()){
                        if(this.isBankBlocke(matcher.group(1))){
                            System.err.println("the bank is blocked ");
                        }
                        else if(this.isSavingAcountBlocke(Long.parseLong(matcher.group(2)))){
                            System.err.println("the account is blocked ");
                        }
                        else this.controller.depositMoneyToAccount(matcher.group(1),Long.parseLong(matcher.group(2)),Long.parseLong(matcher.group(3)));
                    }

                    else if(( matcher = Algorithm.WITHDRAWMONEY.inputMatcher(input)).find()){

                        if(this.isCardBlocke(Long.parseLong(matcher.group(1)))){
                            System.err.println("the account is blocked ");
                        }
                        else this.controller.withdrawMoneyFromAccount(Long.parseLong(matcher.group(1)),Integer.parseInt(matcher.group(2)),Long.parseLong(matcher.group(3)));
                    }

                    else if(( matcher = Algorithm.WITHDRAWMONEYACCOUNT.inputMatcher(input)).find()){
                        if(this.isBankBlocke(matcher.group(1))){
                            System.err.println("the bank is blocked ");
                        }
                        else if(this.isSavingAcountBlocke(Long.parseLong(matcher.group(2)))){
                            System.err.println("the account is blocked ");
                        }
                        else if(this.isPersonBlocke(Long.parseLong(matcher.group(3)))){
                            System.err.println("the person is blocked becuse of not paying loans ");
                        }
                        else this.controller.withdrawMoneyFromAccount(matcher.group(1),Long.parseLong(matcher.group(2)),Long.parseLong(matcher.group(3)),Long.parseLong(matcher.group(4)));
                    }

                    else if(( matcher = Algorithm.WITHDRAWMONEYCOMPANY.inputMatcher(input)).find()){
                        if(this.isBankBlocke(matcher.group(1))){
                            System.err.println("the bank is blocked ");
                        }
                        else if(this.isSavingAcountBlocke(Long.parseLong(matcher.group(2)))){
                            System.err.println("the account is blocked ");
                        }
                        else if(this.isCompanyBlocke(matcher.group(3))){
                            System.err.println("the person is blocked becuse of not paying loans ");
                        }
                        else this.controller.withdrawMoneyFromAccount(matcher.group(1),Long.parseLong(matcher.group(2)),Long.parseLong(matcher.group(3)),Long.parseLong(matcher.group(4)));
                    }

                    else if(( matcher = Algorithm.GETACCOUNTBALANCE.inputMatcher(input)).find()){
                        if(this.isCardBlocke(Long.parseLong(matcher.group(1)))){
                            System.err.println("the account is blocked ");
                        }
                        else this.controller.getAccountBalance(Long.parseLong(matcher.group(1)),Integer.parseInt(matcher.group(2)));
                    }

                    else if(( matcher = Algorithm.TRANSFERTOCARD.inputMatcher(input)).find()){

                        if(this.isCardBlocke(Long.parseLong(matcher.group(1)))){
                            System.err.println("the account is blocked ");
                        }
                        else if(this.isCardBlocke(Long.parseLong(matcher.group(2)))){
                            System.err.println("the receiver account is blocked ");
                        }
                        else this.controller.transferMoneyFromecard(Long.parseLong(matcher.group(1)),Integer.parseInt(matcher.group(2)),Long.parseLong(matcher.group(3)),Long.parseLong(matcher.group(4)));
                    }

                    else if(( matcher = Algorithm.TRANSFERPERSONTOCOMPANY.inputMatcher(input)).find()){

                        if(this.isSavingAcountBlocke(Long.parseLong(matcher.group(2)))){
                            System.err.println("the account is blocked ");
                        }
                        else  if(this.isSavingAcountBlocke(Long.parseLong(matcher.group(4)))){
                            System.err.println("the receiver account is blocked ");
                        }
                        else if(this.isPersonBlocke(Long.parseLong(matcher.group(2)))){
                            System.err.println("the person is blocked ");
                        }
                        else this.controller.transferMoneyFromAcount(matcher.group(1),Long.parseLong(matcher.group(2)),Long.parseLong(matcher.group(3)),Long.parseLong(matcher.group(4)),Long.parseLong(matcher.group(5)));
                    }

                    else if(( matcher = Algorithm.TRANSFERCOMPANYTOPERSON.inputMatcher(input)).find()){

                        if(this.isSavingAcountBlocke(Long.parseLong(matcher.group(2)))){
                            System.err.println("the account is blocked ");
                        }
                        else  if(this.isSavingAcountBlocke(Long.parseLong(matcher.group(4)))){
                            System.err.println("the receiver account is blocked ");
                        }
                        else if(this.isCompanyBlocke(matcher.group(3))){
                            System.err.println("the receiver account is blocked ");
                        }
                        else this.controller.transferFromCompany(matcher.group(1),Long.parseLong(matcher.group(2)),matcher.group(3),Long.parseLong(matcher.group(4)),Long.parseLong(matcher.group(5)));
                    }

                    else if(( matcher = Algorithm.RECEIVELOAN.inputMatcher(input)).find()){

                        if(this.isBankBlocke(matcher.group(1))){
                            System.err.println("the bank is blocked ");
                        }
                        else  if(this.isPersonBlocke(Long.parseLong(matcher.group(2)))){
                            System.err.println("the person is blocked ");
                        }

                        else this.controller.requsetLoan(matcher.group(1),Long.parseLong(matcher.group(2)),Long.parseLong(matcher.group(3)));
                    }

                    else if(( matcher = Algorithm.RECEIVELOANCOMPANY.inputMatcher(input)).find()){

                        if(this.isBankBlocke(matcher.group(1))){
                            System.err.println("the bank is blocked ");
                        }
                        else  if(this.isCompanyBlocke(matcher.group(2))){
                            System.err.println("the company is blocked ");
                        }

                        else this.controller.requestCompany(matcher.group(1),matcher.group(2),Long.parseLong(matcher.group(3)));
                    }

                    else if(( matcher = Algorithm.PAYOFFLOAN.inputMatcher(input)).find()){

                        if(this.isBankBlocke(matcher.group(1))){
                            System.err.println("the bank is blocked ");
                        }
                        else  if(this.isPersonBlocke(Long.parseLong(matcher.group(2)))){
                            System.err.println("the person is blocked ");
                        }

                        else this.controller.payLoan(matcher.group(1),Long.parseLong(matcher.group(2)),Long.parseLong(matcher.group(3)),true);
                    }

                    else if(( matcher = Algorithm.PAYOFFLOANCOMPANY.inputMatcher(input)).find()){

                        if(this.isBankBlocke(matcher.group(1))){
                            System.err.println("the bank is blocked ");
                        }
                        else  if(this.isCompanyBlocke(matcher.group(2))){
                            System.err.println("the company is blocked ");
                        }

                        else this.controller.payLoanForCompany(matcher.group(1),matcher.group(2),Long.parseLong(matcher.group(3)));
                    }

                    else if(( matcher = Algorithm.GONEXTDAY.inputMatcher(input)).find()){
                        int firstDay=MyDate.currentDay;
                        int firstMonth=MyDate.currentMonth;
                        int firstYear =MyDate.currentYear;
                        MyDate.goNextDay();
                        this.controller.dateTimeForward( firstYear, firstMonth, firstDay);
                    }

                    else if(( matcher = Algorithm.GONEXTMONTH.inputMatcher(input)).find()){
                        int firstDay=MyDate.currentDay;
                        int firstMonth=MyDate.currentMonth;
                        int firstYear =MyDate.currentYear;
                        MyDate.goNextMonth();
                        this.controller.dateTimeForward( firstYear, firstMonth, firstDay);
                    }

                    else if(( matcher = Algorithm.GONEXTYEAR.inputMatcher(input)).find()){
                        int firstDay=MyDate.currentDay;
                        int firstMonth=MyDate.currentMonth;
                        int firstYear =MyDate.currentYear;
                        MyDate.goNextYear();
                        this.controller.dateTimeForward( firstYear, firstMonth, firstDay);
                    }

                    else if(( matcher = Algorithm.GOFORNDAYS.inputMatcher(input)).find()){
                        int firstDay=MyDate.currentDay;
                        int firstMonth=MyDate.currentMonth;
                        int firstYear =MyDate.currentYear;
                        MyDate.goForNDays(Integer.parseInt(matcher.group(1)));
                        this.controller.dateTimeForward( firstYear, firstMonth, firstDay);
                    }

                    else if(( matcher = Algorithm.GOFORNMONTH.inputMatcher(input)).find()){
                        int firstDay=MyDate.currentDay;
                        int firstMonth=MyDate.currentMonth;
                        int firstYear =MyDate.currentYear;
                        MyDate.goForNMonths(Integer.parseInt(matcher.group(1)));
                        this.controller.dateTimeForward( firstYear, firstMonth, firstDay);
                    }

                    else if(( matcher = Algorithm.GOFORNYEAR.inputMatcher(input)).find()){
                        int firstDay=MyDate.currentDay;
                        int firstMonth=MyDate.currentMonth;
                        int firstYear =MyDate.currentYear;
                        MyDate.goForNYears(Integer.parseInt(matcher.group(1)));
                        this.controller.dateTimeForward( firstYear, firstMonth, firstDay);
                    }

                    else if(( matcher = Algorithm.GOTODATE.inputMatcher(input)).find()){
                        int firstDay=MyDate.currentDay;
                        int firstMonth=MyDate.currentMonth;
                        int firstYear =MyDate.currentYear;
                        if(Integer.parseInt(matcher.group(3))<MyDate.currentYear){
                            System.err.println("date is for past you cant go past its not tenet");
                        }else if(Integer.parseInt(matcher.group(3))==MyDate.currentYear&&Integer.parseInt(matcher.group(2))<MyDate.currentMonth){
                            System.err.println("date is for past you cant go past its not tenet");
                        }
                        else if(Integer.parseInt(matcher.group(3))==MyDate.currentYear&&Integer.parseInt(matcher.group(2))==MyDate.currentMonth&&Integer.parseInt(matcher.group(1))==MyDate.currentDay){
                            System.err.println("date is for past you cant go past its not tenet");
                        }else{
                            MyDate.goForDate(Integer.parseInt(matcher.group(1)),Integer.parseInt(matcher.group(2)),Integer.parseInt(matcher.group(3)));
                            this.controller.dateTimeForward( firstYear, firstMonth, firstDay);
                        }
                    }

                    else if(( matcher = Algorithm.SHOWALLBANK.inputMatcher(input)).find()){
                        for (Bank bank : this.controller.centralBank.banks) {
                            System.out.println(bank.getBankName());
                        }
                    }

                    else if(( matcher = Algorithm.SHOWALLPERSON.inputMatcher(input)).find()){
                        for (Person person : this.controller.people) {
                            System.out.println("person name is :"+person.getFirstName()+" "+person.getLastName());
                        }
                    }

                    else if(( matcher = Algorithm.SHOWALLCOMPANIES.inputMatcher(input)).find()){
                        for (Company company: this.controller.companys) {
                            System.out.println("company name is :"+company.getCompanyName());
                        }
                    }

                    else if(( matcher = Algorithm.SHOWALLACOUNT.inputMatcher(input)).find()){
                        for (SavingAcount savingAcount : this.controller.centralBank.allSnavingAcount) {
                            System.out.println("saving account id "+savingAcount.getAcountNumber());
                        }
                        for (CurrentAcount currentAcount : this.controller.centralBank.allCurrentAcount) {
                            System.out.println("current account id "+currentAcount.getAcountNumber());
                        }
                    }

                    else if(( matcher = Algorithm.SHOWALLLOANS.inputMatcher(input)).find()){

                        for (Loan loan : this.controller.centralBank.allLoans) {
                            this.showDwtailLoan(loan);
                        }
                    }

                    else if(( matcher = Algorithm.SHOWACCOUNTSFORPERSON.inputMatcher(input)).find()){
                        Person person=null;
                        for (Person person1 : this.controller.people) {
                            if(person1.getNationalCode()==Long.parseLong(matcher.group(1))){
                                person=person1;
                            }
                        }
                        if(isPersonBlocke(Long.parseLong(matcher.group(1)))){
                            System.err.println("person is blocked");
                        }
                        else{
                            for (SavingAcount savingAcount : person.personSavingAcount) {
                                System.out.println("saving account id is"+savingAcount.getAcountNumber());
                            }
                            for (CurrentAcount currentAcount : person.personCurrentAcount) {
                                System.out.println("current account id is"+currentAcount.getAcountNumber());
                            }
                        }
                    }

                    else if(( matcher = Algorithm.SHOWACCOUNTSFORCOMPANY.inputMatcher(input)).find()){
                        Company company=null;
                        for (Company company1 : this.controller.companys) {
                            if(company1.getCompanyId().equalsIgnoreCase(matcher.group(1))){
                                company=company1;
                            }
                        }
                        if(isCompanyBlocke(matcher.group(1))){
                            System.err.println("company is blocked");
                        }
                        else{
                            for (SavingAcount savingAcount : company.imagineryManager.personSavingAcount) {
                                System.out.println("saving account id is"+savingAcount.getAcountNumber());
                            }
                            for (CurrentAcount currentAcount : company.imagineryManager.personCurrentAcount) {
                                System.out.println("saving account id is"+currentAcount.getAcountNumber());
                            }
                        }
                    }

                    else if(( matcher = Algorithm.SHOWDETAILLOANPERSON.inputMatcher(input)).find()){
                        Person person=null;
                        for (Person person1 : this.controller.people) {
                            if(person1.getNationalCode()==Long.parseLong(matcher.group(1))){
                                person=person1;
                            }
                        }
                        if(isPersonBlocke(Long.parseLong(matcher.group(1)))){
                            System.err.println("person is blocked");
                        }
                        else{
                            for (Loan loan : this.controller.centralBank.allBankMapPersonsLoans.get(person)) {

                                if(person.getNationalCode()==loan.getLoanCatcher().getNationalCode()){
                                    showDwtailLoan(loan);
                                }

                            }

                        }
                    }

                    else if(( matcher = Algorithm.SHOWDETAILLOANCOMPANY.inputMatcher(input)).find()){
                        Company company=null;
                        for (Company company1 : this.controller.companys) {
                            if(company1.getCompanyId().equalsIgnoreCase(matcher.group(1))){
                                company=company1;
                            }
                        }
                        if(isCompanyBlocke(matcher.group(1))){
                            System.err.println("company is blocked");
                        }
                        else{

                                for (Loan loan : this.controller.centralBank.allLoans) {
                                    if(company.imagineryManager.getNationalCode()==loan.getLoanCatcher().getNationalCode()){
                                        showDwtailLoan(loan);
                                    }
                                }

                        }
                    }

                    else if(( matcher = Algorithm.SHOWBANKINTEREST.inputMatcher(input)).find()){
                        if(isBankBlocke(matcher.group(1))){
                            System.err.println("bank is blocked");
                        }
                        else {
                            for (Bank bank : this.controller.centralBank.banks) {
                                if(bank.getBankName().equalsIgnoreCase(matcher.group(1))) System.out.println("bank saving Acount Intrest Percent FOR Long Time"+bank.savingAcountIntrestPercentLongTime+"\n  AND bank saving Acount Intrest Percent FOR Long time "+bank.savingAcountIntrestPercentShortTime);
                            }
                        }
                    }

                    else if(( matcher = Algorithm.SHOWBANKBALANCE.inputMatcher(input)).find()){
                        if(isBankBlocke(matcher.group(1))){
                            System.err.println("bank is blocked");
                        }
                        else {
                            for (Bank bank : this.controller.centralBank.banks) {
                                if(bank.getBankName().equalsIgnoreCase(matcher.group(1))) System.out.println(bank.bankProperty);
                            }
                        }
                    }

                    else if(( matcher = Algorithm.SHOWCENTRALBANKBALANCE.inputMatcher(input)).find()){
                        long centralBankBalance=0;
                        for (Bank bank : this.controller.centralBank.banks) {
                            centralBankBalance+=bank.bankProperty;
                        }
                        System.out.println("central Bank Balance is "+centralBankBalance);
                    }

                    else if(( matcher = Algorithm.SHOWDATE.inputMatcher(input)).find()){
                        System.out.println("today is : "+MyDate.currentYear+"/"+MyDate.currentMonth+"/"+MyDate.currentDay);
                    }

                    else{
                        System.err.println("custome envalid request");
                    }

                }
                catch (NumberFormatException e){
                    System.err.println("pleas enter the correct format of input");
                }

                catch (NullPointerException e){
                    System.err.println("you got the null pointer exception pleas refer to source cod with the cod developer to fix that problems");
                }

                catch (NegativeArraySizeException e){
                    System.err.println("you got the negative size for array exception pleas refer to source cod with the cod developer to fix that problems");
                }

                catch (ArrayIndexOutOfBoundsException e){
                    System.err.println("you got the bounds of array out of array size exception pleas refer to source cod with the cod developer to fix that problems");
                }

                catch (IndexOutOfBoundsException e){
                    System.err.println("you got the bounds of array out of array size exception pleas refer to source cod with the cod developer to fix that problems");
                }



            }
        }

    public boolean isCompanyBlocke(String companyId){
        if(this.controller.companys!=null)
        for (Company company : this.controller.companys) {
            if(company.getCompanyId().equalsIgnoreCase(companyId)&&company.getManager().isBlock){
                System.err.println("the company is block");
                return true;
            }
        }
        return false;
    }
    public boolean isBankBlocke(String bankNAme){
        if(this.controller.centralBank.banks!=null)
            for (Bank bank : this.controller.centralBank.banks) {
                if(bank.getBankProperty()<=0&&bank.getBankName().equalsIgnoreCase(bankNAme)){
                    System.err.println("the bank is block");
                    return true;
                }
            }
        return false;
    }
    public boolean isPersonBlocke(long nationalCod){

        if(this.controller.people!=null)
            for (Person person : this.controller.people) {
                if(person.isBlock&&person.getNationalCode()==nationalCod){
                    System.err.println("the person is block");
                    return true;
                }
            }
        return false;
    }
    public boolean isCardBlocke(long cardId){

        if(this.controller.centralBank.allCurrentAcount!=null)
            for (CurrentAcount currentAcount : this.controller.centralBank.allCurrentAcount) {
                if(currentAcount.getCreditCard().isBlock&&currentAcount.getAcountNumber()==cardId){
                    System.err.println("the card is block");
                    return true;
                }
            }
        return false;
    }
    public boolean isSavingAcountBlocke(long accountId){
        if(this.controller.centralBank.allSnavingAcount!=null)
            for (SavingAcount savingAcount : this.controller.centralBank.allSnavingAcount) {
                if(savingAcount.isBlock&& savingAcount.getAcountNumber()==accountId){
                    System.err.println("the account is block");
                    return true;
                }
            }
        return false;
    }
    public void showDwtailLoan(Loan loan){
        System.out.println("loan amount is : "+loan.getLoanAmount());
        System.out.println("regiven amount is : "+loan.getRegivenAmount());
        System.out.println("catcher of loan is :"+loan.getLoanCatcher().getFirstName()+ " "+loan.getLoanCatcher().getFirstName());
    }
}