package view;

import com.google.gson.JsonSyntaxException;
import controller.Controller;

import java.util.regex.Matcher;

public class InputProcessorSeller extends Input {

    public InputProcessorSeller(Controller controller) {
        super(controller);
    }


    public void run(){
        Matcher matcher ;
        try{
            this.controller= fileOperating.readFile("goodlist.txt",this.controller);
        }
        catch (JsonSyntaxException e){
            e.printStackTrace();
        }

        if (this.controller==null){
            this.controller=new Controller(-1);
        }
        while (!(input =scanner.nextLine()).equalsIgnoreCase("exit")){

             if(Algorithms.LISTR.inputMatcher(input).find()){
                this.controller.print(this.controller.allListedGoods(this.controller.availibaleGoodsList,this.controller.nonAvailibaleGoodsList));
                this.fileOperating.jasonWriter(this.controller);
            }

            else if(Algorithms.LISTI.inputMatcher(input).find()){
                this.controller.print(this.controller.availibaleGoodsList);
                this.fileOperating.jasonWriter(this.controller);
            }

            else if(Algorithms.LISTN.inputMatcher(input).find()){
                this.controller.print(this.controller.nonAvailibaleGoodsList);
                this.fileOperating.jasonWriter(this.controller);
            }

            else if(Algorithms.LISTO.inputMatcher(input).find()){
                this.controller.nonCheckdList();
                this.fileOperating.jasonWriter(this.controller);
            }

            else if(( matcher = Algorithms.CHECKORDER.inputMatcher(input)).find()){
                long orderId =Long.parseLong(matcher.group(1));
                if(this.controller.check(orderId)) System.out.println("order "+orderId+ "checked .");
                else { System.err.println("chekcing order failed ."); }
                this.fileOperating.jasonWriter(this.controller);
            }

             else if(Algorithms.LISTHO.inputMatcher(input).find()){
                 this.controller.chekedOrders();
                 this.fileOperating.jasonWriter(this.controller);
             }

             else if(( matcher = Algorithms.ADDGOOD.inputMatcher(input)).find()){
                 String goodName=matcher.group(1);
                 double amountOfGood=Double.parseDouble(matcher.group(2));
                 String kindOfAmount = matcher.group(3);
                 double sellPrice=Double.parseDouble(matcher.group(4));
                 double buyPrice=Double.parseDouble(matcher.group(5));
                long goodId= this.controller.addNewGood(goodName,amountOfGood,buyPrice,sellPrice,kindOfAmount);
                if(goodId==-1) System.err.println(goodName +"is already exist");
                else {System.out.println("the "+goodName+ " good cod is "+goodId);}
                 this.fileOperating.jasonWriter(this.controller);
             }

             else if(( matcher = Algorithms.REMOVEGOOD.inputMatcher(input)).find()){
                 long goodId =Long.parseLong(matcher.group(1));
                 if(this.controller.removeGood(goodId)) System.out.println( goodId+ "removed .");
                 else { System.err.println("removing good failed ."); }
                 this.fileOperating.jasonWriter(this.controller);
             }

             else if(( matcher = Algorithms.EDDITGOODAMOUNT.inputMatcher(input)).find()){
                 long goodId =Long.parseLong(matcher.group(1));
                String goodName=matcher.group(2);
                 double goodAmount =Double.parseDouble(matcher.group(3));
                 if(this.controller.editNameCount(goodId,goodName,goodAmount)) System.out.println( goodId+ "edited .");
                 else { System.err.println("editing good failed ."); }
                 this.fileOperating.jasonWriter(this.controller);
             }

             else if(( matcher = Algorithms.EDDITGOODNAME.inputMatcher(input)).find()){
                 long goodId =Long.parseLong(matcher.group(1));
                 String goodName=matcher.group(2);
                 if(this.controller.editName(goodId,goodName)) System.out.println( goodId+ "edited .");
                 else { System.err.println("editing good failed ."); }
                 this.fileOperating.jasonWriter(this.controller);
             }

             else if(( matcher = Algorithms.EDDITGOODSELLBUYAMOUNT.inputMatcher(input)).find()){
                 long goodId =Long.parseLong(matcher.group(1));
                 double sellPrice=Double.parseDouble(matcher.group(2));
                 double buyPrice=Double.parseDouble(matcher.group(3));
                 double goodAmount =Double.parseDouble(matcher.group(4));
                 if(this.controller.editBuySellCount(goodId,sellPrice,buyPrice,goodAmount)) System.out.println( goodId+ "edited .");
                 else { System.err.println("editing good failed ."); }
                 this.fileOperating.jasonWriter(this.controller);
             }

             else if(Algorithms.CALCULATEPALL.inputMatcher(input).find()){
                 System.out.println(this.controller.allProfit()+" IRR");
                 this.fileOperating.jasonWriter(this.controller);
             }

             else if((matcher =Algorithms.CALCULATEPGOOD.inputMatcher(input)).find()){
                 long goodId=Long.parseLong(matcher.group(1));
                 System.out.println(this.controller.goodProfit(goodId)+" IRR");
                 this.fileOperating.jasonWriter(this.controller);
             }
             else if(Algorithms.CALCULATESALL.inputMatcher(input).find()){
                 this.controller.allSellsRate();
                 this.fileOperating.jasonWriter(this.controller);
             }

             else if((matcher =Algorithms.CALCULATESGOOD.inputMatcher(input)).find()){
                 long goodId=Long.parseLong(matcher.group(1));
                 this.controller.sellRate(goodId);
                 this.fileOperating.jasonWriter(this.controller);
             }

             else{
                System.err.println("custome envalid request");
            }

        }
    }

    private void signing(String email,int passworld){
        this.controller.signing(email,passworld);
    }




}
