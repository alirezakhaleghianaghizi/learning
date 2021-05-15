package model;

public class MyDate {
    public static int currentYear =1400;
    public static int currentMonth =1;
    public static int currentDay =1;
    private int year;
    private int month;
    private int day;

    public MyDate() {
        this.year= currentYear;
        this.month= currentMonth;
        this.day= currentDay;
    }

    public MyDate(int year,int month,int day) {
        this.year=year;
        this.month=month;
        this.day=day;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
    public static void goNextDay(){
        if(currentMonth <7&& currentMonth >=1&& currentDay ==31){
            currentMonth++;
            currentDay =1;
        }
        else if(currentMonth <12&& currentMonth >=7&& currentDay ==30){
            currentMonth++;
            currentDay =1;
        }
        else if(currentMonth ==12&& currentDay ==30&&(currentYear -1399)%4==0){
           currentYear++;
            currentMonth =1;
            currentDay =1;
        }
        else if(currentMonth ==12&& currentDay ==29&&(currentYear -1399)%4!=0){
            currentYear++;
            currentMonth =1;
            currentDay =1;
        }
        else {
            currentDay++;
        }
    }

    public static void goNextMonth() {
        if(currentMonth ==12){
            currentYear++;
            currentMonth =1;
        }
        else {
            currentMonth++;
        }
    }

    public static void goNextYear() {
            currentYear++;
    }

    public static void goForNDays(int nextDays) {
        for (int i = 0; i < nextDays; i++) {
            goNextDay();
        }
    }

    public static void goForNMonths(int nextMonths) {
        for (int i = 0; i < nextMonths; i++) {
            goNextMonth();
        }
    }

    public static void goForNYears(int nextYears) {
        for (int i = 0; i < nextYears; i++) {
            goNextYear();
        }
    }

    public static void goForDate(int dateYear,int dateMonth,int dateDay) {
        currentYear =dateYear;
        currentMonth =dateMonth;
        currentDay =dateDay;
    }

}
