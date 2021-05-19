package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Algorithm {
    ADDPERSON("^(?i)\\s*add\\s+person\\s+(\\w+)\\s+(\\w+)\\s+(\\d+)\\s+(\\d{1,2})\\/(\\d{1,2})\\/(\\d{4})\\s*$"),
    ADDCOMPANY("^(?i)\\s*add\\s+company\\s+(\\w+)\\s+(\\d+)\\s*$"),
    ADDBANK("^(?i)\\s*add\\s+bank\\s+(\\w+)\\s*$"),
    ADDBANKWITHINITIAL("^(?i)\\s*add\\s+BANK\\s+(\\w+)\\s+(\\d+)\\s*$"),
    SETBANKINCOMEPERCENT("^(?i)\\s*set\\s+bank\\s+income\\s+percent\\s+(\\w+)\\s+(\\d{1,2})\\s*$"),
    SETBANKINTERESTPERCENTSHORT("^(?i)\\s*set\\s+bank\\s+interest\\s+percent\\s+(\\w+)\\s+(\\d{1,2})\\s+(short)\\s*$"),
    SETBANKINTERESTPERCENTLONG("^(?i)\\s*set\\s+bank\\s+interest\\s+percent\\s+(\\w+)\\s+(\\d{1,2})\\s+(long)\\s*$"),
    INCREASEBANKBALANCE("^(?i)\\s*increas\\s+bank\\s+balance\\s+(\\w+)\\s+(\\d+)\\s*$"),
    OPENCURRENTACCOUNT("^(?i)\\s*open\\s+current\\s+account\\s+(\\w+)\\s+(\\d+)\\s+(\\d+)\\s*$"),
    OPENCURRENTACCOUNTCOMPANY("^(?i)\\s*open\\s+current\\s+account\\s+(\\w+)\\s+(\\w+)\\s+(\\d+)\\s*$"),
    OPENSAVINGACCOUNT("^(?i)\\s*open\\s+deposit\\s+account\\s+(\\w+)\\s+(\\d+)\\s+(\\w+)-(\\w+)\\s+(\\d+)\\s*$"),
    OPENSAVINGACCOUNTCOMPANY("^(?i)\\s*open\\s+deposit\\s+account\\s+(\\w+)\\s+(\\w+)\\s+(\\w+)-(\\w+)\\s+(\\d+)\\s*$"),
    CLOSEACCOUNT("^(?i)\\s*close\\s+account\\s+(\\w+)\\s+(\\d+)\\s+(\\d+)\\s*$"),
    CLOSEACCOUNTCOMPANY("^(?i)\\s*close\\s+account\\s+(\\w+)\\s+(\\w+)\\s+(\\d+)\\s*$"),
    CHANGECARDPASSWORD("^(?i)\\s*change\\s+card\\s+password\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s*$"),
    SETCARDSECONDPASSWORD("^(?i)\\s*set\\s+card\\s+second\\s+password\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s*$"),
    CHANGESECONDECARDPASSWORD("^(?i)\\s*change\\s+card\\s+second\\s+password\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s*$"),
    EXTENDEXPIRATIONDATE("^(?i)\\s*extend\\s+the\\s+expiration\\s+date\\s+(\\w+)\\s+(\\d+)\\s+(\\d+)\\s*$"),
    EXTENDEXPIRATIONDATECOMPANY("^(?i)\\s*extend\\s+the\\s+expiration\\s+date\\s+(\\w+)\\s+(\\d+)\\s+(\\w+)\\s*$"),
    DEPOSITMONEY("^(?i)\\s*deposit\\s+money\\s+(\\w+)\\s+(\\d+)\\s+(\\d+)\\s*$"),
    WITHDRAWMONEY("^(?i)\\s*withdraw\\s+money\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s*$"),
    WITHDRAWMONEYACCOUNT("^(?i)\\s*withdraw\\s+money\\s+(\\w+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s*$"),
    WITHDRAWMONEYCOMPANY("^(?i)\\s*withdraw\\s+money\\s+(\\w+)\\s+(\\d+)\\s+(\\w+)\\s+(\\d+)\\s*$"),
    GETACCOUNTBALANCE("^(?i)\\s*get\\s+account\\s+balance\\s+(\\d+)\\s+(\\d+)\\s*$"),
    TRANSFERTOCARD("^(?i)\\s*transfer\\s+money\\s+to\\s+another\\s+account\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s*$"),
    TRANSFERCOMPANYTOPERSON("^(?i)\\s*transfer\\s+money\\s+to\\s+another\\s+account\\s+(\\w+)\\s+(\\d+)\\s+(\\w+)\\s+(\\d+)\\s+(\\d+)\\s*$"),
    TRANSFERPERSONTOCOMPANY("^(?i)\\s*transfer\\s+money\\s+to\\s+another\\s+account\\s+(\\w+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s*$"),
    RECEIVELOAN("^(?i)\\s*receive\\s+loan\\s+(\\w+)\\s+(\\d+)\\s+(\\d+)\\s*$"),
    RECEIVELOANCOMPANY("^(?i)\\s*receive\\s+loan\\s+(\\w+)\\s+(\\w+)\\s+(\\d+)\\s*$"),
    PAYOFFLOAN("^(?i)\\s*pay\\s+off\\s+the\\s+loan\\s+(\\w+)\\s+(\\d+)\\s+(\\d+)\\s*$"),
    PAYOFFLOANCOMPANY("^(?i)\\s*pay\\s+off\\s+the\\s+loan\\s+(\\w+)\\s+(\\w+)\\s+(\\d+)\\s*$"),
    GONEXTDAY("^(?i)\\s*go\\s+next\\s+day\\s*$"),
    GONEXTMONTH("^(?i)\\s*go\\s+next\\s+month\\s*$"),
    GONEXTYEAR("^(?i)\\s*go\\s+next\\s+year\\s*$"),
    GOFORNDAYS("^(?i)\\s*go\\s+for\\s+(\\d+)\\s+days\\s*$"),
    GOFORNMONTH("^(?i)\\s*go\\s+for\\s+(\\d+)\\s+months\\s*$"),
    GOFORNYEAR("^(?i)\\s*go\\s+for\\s+(\\d+)\\s+years\\s*$"),
    GOTODATE("^(?i)\\s*go\\s+to\\s+date\\s+(\\d{1,2})\\/(\\d{1,2})\\/(\\d{4})\\s*$"),
    SHOWALLBANK("^(?i)\\s*Show\\s+all\\s+banks\\s*$"),
    SHOWALLPERSON("^(?i)\\s*Show\\s+all\\s+persons\\s*$"),
    SHOWALLCOMPANIES("^(?i)\\s*Show\\s+all\\s+companies\\s*$"),
    SHOWALLACOUNT("^(?i)\\s*Show\\s+all\\s+accounts\\s*$"),
    SHOWALLLOANS("^(?i)\\s*Show\\s+all\\s+loans\\s*$"),
    SHOWACCOUNTSFORPERSON("^(?i)\\s*Show\\s+accounts\\s+for\\s+(\\d+)\\s*$"),
    SHOWACCOUNTSFORCOMPANY("^(?i)\\s*Show\\s+accounts\\s+for\\s+(\\w+)\\s*$"),
    SHOWDETAILLOANPERSON("^(?i)\\s*Show\\s+details\\s+of\\s+the\\s+loan\\s+for\\s+(\\d+)\\s*$"),
    SHOWDETAILLOANCOMPANY("^(?i)\\s*Show\\s+details\\s+of\\s+the\\s+loan\\s+for\\s+(\\w+)\\s*$"),
    SHOWBANKINTEREST("^(?i)\\s*show\\s+bank\\s+interest\\s+(\\w+)\\s*$"),
    SHOWDATE("^(?i)\\s*show\\s+date\\s*$"),
    SHOWCENTRALBANKBALANCE("^(?i)\\s*show\\s+central\\s+bank\\s+balance\\s*$"),
    SHOWBANKBALANCE("^(?i)\\s*show\\s+bank\\s+balance\\s+(\\w+)\\s*$"),        ;


    final Pattern inputPattern;
    Algorithm(String s) {
        this.inputPattern=Pattern.compile(s);
    }
    public Matcher inputMatcher(String input){

        return this.inputPattern.matcher(input);
    }


}
