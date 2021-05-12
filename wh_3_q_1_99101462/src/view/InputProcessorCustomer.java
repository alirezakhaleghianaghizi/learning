package view;

import com.google.gson.JsonSyntaxException;
import controller.Controller;
import model.Customer;

import java.util.regex.Matcher;

public class InputProcessorCustomer extends Input{
    public InputProcessorCustomer(Controller controller) {
        super(controller);
    }


    public void run(){
        Matcher matcher;
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

            if(( matcher = Algorithms.LOGIN.inputMatcher(input)).find()&&this.controller.getCurentCoustomerId()==-1){
                //controller.print();
                int id=Integer.parseInt(matcher.group(1));
                int passworld= Integer.parseInt(matcher.group(2));
                if(this.controller.searchingCustomer(id,passworld)==-1){
                    System.out.println("Dont you have Acount ? :) \n pleas sign in or : enter your passworld and email");
                }

                this.fileOperating.jasonWriter(this.controller);
                //controller.print();
            }

            else if(( matcher = Algorithms.SIGNIN.inputMatcher(input)).find()&&this.controller.getCurentCoustomerId()==-1){
                String email=matcher.group(1);
                int passworld=Integer.parseInt(matcher.group(2));
                signing(email,passworld);
                this.fileOperating.jasonWriter(this.controller);
            }

            else if(Algorithms.LISTR.inputMatcher(input).find()){
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

            else if(( matcher = Algorithms.ORDERGOOD.inputMatcher(input)).find()&&this.controller.getCurentCoustomerId()!=-1){
                long goodId =Long.parseLong(matcher.group(1));
                double goodAmount=Double.parseDouble(matcher.group(2));
                String kindeOfAmunt=matcher.group(3);
                long orderingSituation=this.controller.order(goodId,goodAmount,kindeOfAmunt,this.controller.getCurentCoustomerId());
                if(orderingSituation==-1){ System.err.println("ERROR: order not successful"); }
                else { System.out.println("Your order id is = "+orderingSituation); }
                this.fileOperating.jasonWriter(this.controller);
            }

            else if(( matcher = Algorithms.DELETORDER.inputMatcher(input)).find()&&this.controller.getCurentCoustomerId()!=-1){
                long orderId =Long.parseLong(matcher.group(1));
                boolean orderingSituation=this.controller.deletOrder(orderId);
                if(!orderingSituation){ System.err.println("Error deleting order "+orderId+" !"); }
                else { System.out.println("order" +orderId+" was deleted successfully! "); }
                this.fileOperating.jasonWriter(this.controller);
            }

            else if(Algorithms.LOGOUT.inputMatcher(input).find()){
                this.controller.setCurentCoustomerId(-1);
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
