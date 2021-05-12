package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Loan {
    private Person loanCatcher;
    private long loanAmount;
    private long regivenAmount;
    private int interestPercent;
    private int penaltyPercent;
    private HashMap<MyDate, Long> monthlyPayment;

}
