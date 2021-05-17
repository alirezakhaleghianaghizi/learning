package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Loan {
    private Person loanCatcher;
    private long loanAmount;
    private long regivenAmount;
    private int interestPercent;
    private int penaltyPercent;
    public HashMap<MyDate, Long> monthlyPayment;

    public Loan(Person loanCatcher, long loanAmount, int interestPercent, int penaltyPercent) {
        this.loanCatcher = loanCatcher;
        this.loanAmount = loanAmount;
        this.interestPercent = interestPercent;
        this.penaltyPercent = penaltyPercent;
        this.regivenAmount=0;
        this.monthlyPayment=new HashMap<>();
    }

    public void setLoanCatcher(Person loanCatcher) {
        this.loanCatcher = loanCatcher;
    }

    public void setLoanAmount(long loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setRegivenAmount(long regivenAmount) {
        this.regivenAmount = regivenAmount;
    }

    public void setInterestPercent(int interestPercent) {
        this.interestPercent = interestPercent;
    }

    public void setPenaltyPercent(int penaltyPercent) {
        this.penaltyPercent = penaltyPercent;
    }

    public void setMonthlyPayment(HashMap<MyDate, Long> monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public Person getLoanCatcher() {
        return loanCatcher;
    }

    public long getLoanAmount() {
        return loanAmount;
    }

    public long getRegivenAmount() {
        return regivenAmount;
    }

    public int getInterestPercent() {
        return interestPercent;
    }

    public int getPenaltyPercent() {
        return penaltyPercent;
    }

    public HashMap<MyDate, Long> getMonthlyPayment() {
        return monthlyPayment;
    }
}
