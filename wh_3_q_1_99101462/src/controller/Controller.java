package controller;

import model.Bill;
import model.Customer;
import model.Goods;
import view.TabeOfGoods;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Controller {
    public ArrayList<Goods> availibaleGoodsList=new ArrayList<>();
    public ArrayList<Goods> nonAvailibaleGoodsList=new ArrayList<>();
    ArrayList<Bill> billsList=new ArrayList<>();
    HashMap<Long, Date> historyOrder=new HashMap<>();
    TabeOfGoods tabel =new TabeOfGoods();
    public ArrayList<Customer> customers=new ArrayList<>();
    private int curentCoustomerId;

    public Controller(int curentCoustomerId) {
        this.curentCoustomerId = curentCoustomerId;
    }

        public void setCurentCoustomerId(int curentCoustomerId) {
        this.curentCoustomerId = curentCoustomerId;
    }
        public int getCurentCoustomerId() {
        return curentCoustomerId;
    }
        public void print (ArrayList<Goods> listWatchingGoods){
        this.tabel.print_avaliblity(listWatchingGoods);
    }
        public  ArrayList<Goods> allListedGoods(ArrayList<Goods>availibaleGoodsList,ArrayList<Goods>nonAvailibaleGoodsList){
        ArrayList<Goods>allGoods=new ArrayList<>();
        allGoods.addAll(availibaleGoodsList);
        allGoods.addAll(nonAvailibaleGoodsList);
        return allGoods;
    }
        public void setGoodSellProfit(Goods goods ,double amount){
            goods.setAmount(goods.getAmount()-amount);
            goods.setAmountOfOrders(amount+goods.getAmountOfOrders());
            goods.setNumberOfOrders(1+goods.getNumberOfOrders());
            goods.setProfitOfOrders(amount*(goods.getSell()-goods.getBuy())+goods.getProfitOfOrders());
            goods.setSellOfOrders(goods.getSellOfOrders()+amount*goods.getSell());

        }
        public void deletGoodSellProfit(Goods goods ,double amount){
        goods.setAmount(goods.getAmount()+amount);
        goods.setAmountOfOrders(-amount+goods.getAmountOfOrders());
        goods.setNumberOfOrders(-1+goods.getNumberOfOrders());
        goods.setProfitOfOrders(-amount*(goods.getSell()-goods.getBuy())+goods.getProfitOfOrders());
        goods.setSellOfOrders(goods.getSellOfOrders()-amount*goods.getSell());

    }
        public long order(long goodId,double amount,String kindeOfGood,int customerId){
        Bill newBill;
            for (Goods goods : this.availibaleGoodsList) {
                if(goods.getCod()==goodId&&goods.getKindOfAmount().equalsIgnoreCase(kindeOfGood)){
                    if(goods.getAmount()>amount){
                        this.setGoodSellProfit(goods,amount);
                      newBill=new Bill(customerId,amount,goodId);
                    this.billsList.add(newBill);
                        for (Customer customer : this.customers) {
                            if(customer.getId()==customerId){
                                customer.bills.add(newBill);
                                return newBill.id;
                            }
                        }
                    }
                    else if(goods.getAmount()==amount){
                        this.nonAvailibaleGoodsList.add(goods);
                        this.availibaleGoodsList.remove(goods);
                        this.setGoodSellProfit(goods,amount);
                        newBill=new Bill(customerId,amount,goodId);
                        this.billsList.add(newBill);
                        for (Customer customer : this.customers) {
                            if(customer.getId()==customerId){
                                customer.bills.add(newBill);
                                return newBill.id;
                            }
                        }
                    }
                    return -1;
                }
            }
        return -1;
        }
        public boolean deletOrder(long orderId){
            for (Bill bill : this.billsList) {
                if(bill.id==orderId){
                    this.billsList.remove(bill);
                    for (Customer customer : this.customers) {
                        if(customer.getId()==bill.getCustomerCod()){
                            for (Goods goods : this.availibaleGoodsList) {
                                if(goods.getCod()==bill.getGoodCod()) this.deletGoodSellProfit(goods,bill.getAmount());
                            }
                            customer.bills.remove(bill);
                        }
                    }
                    return  true;
                }
            }
        return false;
        }
        public void nonCheckdList(){
            for (Bill bill : billsList) {
                if(!bill.checked){
                    System.out.println("order Id:"+bill.id);
                }
            }
        }
        public boolean check(long billId){
            for (Bill bill : billsList) {
                if(bill.checked!=true){
                    bill.checked=true;
                    this.historyOrder.put(bill.id,new Date());
                   return true;
                }
            }
                return false;
        }
        public void chekedOrders(){
            for (Bill bill : this.billsList) {
                if(bill.checked==true) System.out.println(" order Id:  "+bill.id+"   date of checkd:"+this.historyOrder.get(bill.id));
            }
        }
        public long addNewGood(String name,double amount , double buy,double sell,String kinedOfAmount ){
            for (Goods goods : this.availibaleGoodsList) {
                if(goods.getName().equalsIgnoreCase(name)) return -1;
            }

            Goods newGood=new Goods(name,amount,buy,sell,kinedOfAmount);
            this.availibaleGoodsList.add(newGood);

            return newGood.getCod();
        }
        public boolean removeGood(long Id){
            for (Goods goods : this.availibaleGoodsList) {
                if(goods.getCod()==Id){
                    this.availibaleGoodsList.remove(goods);
                    return true;
                }
            }
            return false;
        }
        public boolean editName(long goodCod,String name){
            for (Goods goods : this.availibaleGoodsList) {
                if(goods.getCod()==goodCod){
                    goods.setName(name);
                    return true;
                }
            }
            for (Goods goods : this.nonAvailibaleGoodsList) {
                if(goods.getCod()==goodCod){
                    goods.setName(name);
                    return true;
                }
            }
        return false;
        }
        public boolean editNameCount(long goodCod,String name,double count){
            for (Goods goods : this.availibaleGoodsList) {
                if(goods.getCod()==goodCod){
                    goods.setName(name);
                    goods.setAmount(count);
                    if(count<=0){
                        nonAvailibaleGoodsList.add(goods);
                        availibaleGoodsList.remove(goods);
                    }
                    return true;
                }
            }
            for (Goods goods : this.nonAvailibaleGoodsList) {
                if(goods.getCod()==goodCod){
                    goods.setName(name);
                    goods.setAmount(count);
                    if(count>0){
                        availibaleGoodsList.add(goods);
                        nonAvailibaleGoodsList.remove(goods);
                    }
                    return true;
                }
            }
            return false;
        }
        public boolean editCount(long goodCod,double count){
            for (Goods goods : this.availibaleGoodsList) {
                if(goods.getCod()==goodCod){
                    goods.setAmount(count);
                    return true;
                }
            }
            for (Goods goods : this.nonAvailibaleGoodsList) {
                if(goods.getCod()==goodCod){
                    goods.setAmount(count);
                    return true;
                }
            }
            return false;
        }
        public boolean editBuySellCount(long goodCod,double sell,double buy,double count){
        if(sell<buy) return false;

            for (Goods goods : this.availibaleGoodsList) {
                if(goods.getCod()==goodCod){
                    goods.setAmount(count);
                    goods.setBuy(buy);
                    goods.setSell(sell);
                    if(count<=0){
                        nonAvailibaleGoodsList.add(goods);
                        availibaleGoodsList.remove(goods);
                    }
                    return true;
                }
            }
            for (Goods goods : this.nonAvailibaleGoodsList) {
                if(goods.getCod()==goodCod){
                    goods.setAmount(count);
                    goods.setBuy(buy);
                    goods.setSell(sell);
                    if(count>0){
                        availibaleGoodsList.add(goods);
                        nonAvailibaleGoodsList.remove(goods);
                    }
                    return true;
                }
            }
        return false;
        }
        public double allProfit(){
        double profits=0;
            for (Goods goods : this.availibaleGoodsList) {
                profits+=goods.getProfitOfOrders();
            }
        return profits;
        }
        public double goodProfit(long goodCod){
            double profits=0;
            for (Goods goods : this.availibaleGoodsList) {
               if(goods.getCod()==goodCod){
                    profits=goods.getProfitOfOrders();
                   return profits;
                }
            }
            return 0;
        }
        public void sellRate(long goodId){
            for (Goods goods : this.availibaleGoodsList) {
                if(goodId==goods.getCod()){
                    System.out.println(goods.getNumberOfOrders()+" orders ,"+goods.getAmountOfOrders()+" "+goods.getKindOfAmount()+" "+goods.getName()+","+goods.getSellOfOrders()+" IRR sell , "+goods.getProfitOfOrders()+" IRR profits");
                    break;
                }
            }
            for (Goods goods : this.nonAvailibaleGoodsList) {
                if(goodId==goods.getCod()){
                    System.out.println(goods.getNumberOfOrders()+" orders ,"+goods.getAmountOfOrders()+" "+goods.getKindOfAmount()+" "+goods.getName()+","+goods.getSellOfOrders()+" IRR sell , "+goods.getProfitOfOrders()+" IRR profits");
                    break;
                }
            }
        }
        public void allSellsRate(){
            for (Goods goods : this.availibaleGoodsList) {
                    System.out.println(goods.getNumberOfOrders()+" orders ,"+goods.getAmountOfOrders()+" "+goods.getKindOfAmount()+" "+goods.getName()+","+goods.getSellOfOrders()+" IRR sell , "+goods.getProfitOfOrders()+" IRR profits");
            }
            for (Goods goods : this.nonAvailibaleGoodsList) {
                    System.out.println(goods.getNumberOfOrders()+" orders ,"+goods.getAmountOfOrders()+" "+goods.getKindOfAmount()+" "+goods.getName()+","+goods.getSellOfOrders()+" IRR sell , "+goods.getProfitOfOrders()+" IRR profits");
            }
        }
        public int searchingCustomer(int id ,int pasworld){
            for (Customer customer : this.customers) {
                if(customer.getId()==id){
                    if(customer.getPassworld()==pasworld){
                        System.out.println("you loged in"+customer.getId());
                        this.curentCoustomerId=customer.getId();
                        return 1;
                    }
                    else {
                        System.out.println("the passworld was wrong");
                        return 0;
                    }

                }
            }
            return -1;
        }
        public void signing(String email, int passworld){
        try{
            this.curentCoustomerId=Math.abs(email.hashCode());
            this.customers.add(new Customer(curentCoustomerId,passworld));
            System.out.println("your acounts Id is "+curentCoustomerId);
        }
        catch (NumberFormatException e){
            this.curentCoustomerId= (int) Math.abs(Math.random());
        }

        }

}
