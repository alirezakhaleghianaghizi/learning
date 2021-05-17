package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Algorithm {
    ADDPERSON("^(?i)\\s*add\\s+person\\s+(\\w+)\\s+(\\w+)\\s+(\\d{4})\\s+(\\d{2})\\s+(\\d{2})\\s*$"),
    ADDCOMPANY("^(?i)\\s*add\\s+company\\s+(\\w+)\\s+(\\d+)\\s*$"),
    ADDBANK("^(?i)\\s*add\\s+BANK\\s+(\\W+)\\s*$"),
    ADDBANKWITHINITIAL("^(?i)\\s*add\\s+BANK\\s+(\\W+)\\s+(\\d+)\\s*$"),
    SETBANKINCOMEPERCENT("^(?i)\\s*ls\\s+-n\\s*$"),
    SETBANKINTERESTPERCENT("^(?i)\\s*order\\s+(\\d+)\\s+-c\\s+(\\d+\\.?\\d*)\\s+(\\w+)\\s*$"),
    INCREASEBANKBALANCE("^(?i)\\s*order\\s+-d\\s+(\\d+)\\s*$"),
    OPENCURRENTACCOUNT("^(?i)\\s*logout\\s*$"),
    OPENCURRENTACCOUNTCOMPANY("^(?i)\\s*ls\\s+-o\\s*$"),
    OPENSAVINGACCOUNT("^(?i)\\s*checkout\\s+(\\d+)\\s*$"),
    OPENSAVINGACCOUNTCOMPANY("^(?i)\\s*ls\\s+-ho\\s*$"),
    CLOSEACCOUNT("^(?i)\\s*add\\s+-n\\s+(\\w+)\\s+-c\\s+(\\d+\\.?\\d*)\\s+(\\w+)\\s+-sp\\s+(\\d+\\.?\\d*)\\s+-bp\\s+(\\d+\\.?\\d*)\\s*$"),
    CLOSEACCOUNTCOMPANY("^(?i)\\s*remove\\s+-c\\s+(\\w+)\\s*$"),
    CHANGECARDPASSWORD("^(?i)\\s*edit\\s+(\\d+)\\s+-n\\s+(\\w+)\\s*$"),
    SETCARDSECONDPASSWORD("^(?i)\\s*edit\\s+(\\d+)\\s+-n\\s+(\\w+)\\s+-c\\s+(\\d+\\.?\\d*)\\s*$"),
    CHANGESECONDECARDPASSWORD("^(?i)\\s*edit\\s+(\\d+)\\s+-sp\\s+(\\d+\\.?\\d*)\\s+-bp\\s+(\\d+\\.?\\d*)\\s+-c\\s+(\\d+\\.?\\d*)\\s*$"),
    EXTENDEXPIRATIONDATE("^(?i)\\s*calc\\s+-p\\s*$"),
    EXTENDEXPIRATIONDATECOMPANY("^(?i)\\s*calc\\s+-p\\s+(\\d+)\\s*$"),
    DEPOSITMONEY("^(?i)\\s*calc\\s+-s\\s*$"),
    WITHDRAWMONEY("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    WITHDRAWMONEYACCOUNT("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    WITHDRAWMONEYCOMPANY("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    GETACCOUNTBALANCE("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    TRANSFERTOCARD("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    TRANSFERCOMPANYTOCOMPANY("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    TRANSFERCOMPANYTOPERSON("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    TRANSFERPERSONTOCOMPANY("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    RECEIVELOAN("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    RECEIVELOANCOMPANY("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    PAYOFFLOAN("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    PAYOFFLOANCOMPANY("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    GONEXTDAY("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    GONEXTMONTH("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    GONEXTYEAR("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    GOFORNDAYS("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    GOFORNMONTH("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    GOFORNYEAR("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    GOTODATE("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    SHOWALLBANK("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    SHOWALLPERSON("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    SHOWALLCOMPANIES("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    SHOWALLACOUNT("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    SHOWALLLOANS("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    SHOWACCOUNTSFORPERSON("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    SHOWACCOUNTSFORCOMPANY("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    SHOWDETAILLOANPERSON("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    SHOWDETAILLOANCOMPANY("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    SHOWBANKINTEREST("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    SHOWDATE("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    SHOWCENTRALBANKBALANCE("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),
    SHOWBANKBALANCE("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$"),        ;
    final Pattern inputPattern;
    Algorithm(String s) {
        this.inputPattern=Pattern.compile(s);
    }
    public Matcher inputMatcher(String input){

        return this.inputPattern.matcher(input);
    }
}
