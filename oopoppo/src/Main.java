import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static Scanner scan=new Scanner(System.in);
    public static void main(String[] args) {
        HashMap<String,Integer> map=new HashMap<>();
        Groops groooh =new Groops();
        groooh.Scan();
        groooh.Element();
        groooh.Cunter();
        //System.out.println(groooh.elements);
        groooh.sort();
        System.out.print(groooh.finalAnswer);
    }
}
class Groops {
    String chemical;
    HashMap<Integer,String> elements =new HashMap<>();
    HashMap<String ,Integer> finalElements=new HashMap<>();
    String finalAnswer=new String();
    void Scan(){
        chemical=Main.scan.nextLine();
    }
    void Element(){
        Pattern patternName1 = Pattern.compile("[A-Z][a-z]?");
        Matcher matcherName1 = patternName1.matcher(chemical);
        int i=0;
        while (matcherName1.find()){
            if(!elements.containsKey(matcherName1)){
                elements.put(i,matcherName1.group() );
                i++;
            }
        }
    }
    void Cunter(){

        for (int i = 0; i < elements.size(); i++) {
            finalElements.put(elements.get(i),0 );
            int []tedad=new int [10000];
            int whichTedad=0;
            tedad[0]=1;
            if(elements.get(i).length()==2){

                for (int j = chemical.length()-1; j >=0 ; j--) {

                    if(chemical.charAt(j)==')'){
                        //System.out.println("ok1");
                        whichTedad++;
                        if(j==chemical.length()-1||(chemical.charAt(j+1)<48||chemical.charAt(j+1)>57)) tedad[whichTedad]=1;
                        else {
                            int k=j+1;
                            while (k<chemical.length()&&chemical.charAt(k)>=48&&chemical.charAt(k)<=57){
                                k++;
                            }
                            tedad[whichTedad]=Integer.parseInt(chemical.substring(j+1,k));
                        }
                        tedad[whichTedad]*=tedad[whichTedad-1];
                    }
                    else if(chemical.charAt(j)=='('){
                        whichTedad--;
                    }
                    else if(j<chemical.length()-1&&chemical.substring(j,j+2).equals(elements.get(i)) ){
                        int zarib=1;
                        if(j==chemical.length()-2||(chemical.charAt(j+2)<48||chemical.charAt(j+2)>57)) zarib=1;
                        else {
                            int k=j+2;
                            while (k<chemical.length()&&chemical.charAt(k)>=48&&chemical.charAt(k)<=57){
                                k++;
                            }
                            zarib=Integer.parseInt(chemical.substring(j+2,k));
                        }
                        finalElements.put(elements.get(i),finalElements.get(elements.get(i))+tedad[whichTedad]*zarib );
                    }
                }
            }
            else if(elements.get(i).length()==1){
                for (int j = chemical.length()-1; j >=0 ; j--) {
                    if(chemical.charAt(j)==')'){
                        whichTedad++;
                        if(j==chemical.length()-1||(chemical.charAt(j+1)<48||chemical.charAt(j+1)>57)) tedad[whichTedad]=1;
                        else {
                            int k=j+1;
                            while (k<chemical.length()&&chemical.charAt(k)>=48&&chemical.charAt(k)<=57){
                                k++;
                            }
                            tedad[whichTedad]=Integer.parseInt(chemical.substring(j+1,k));
                        }
                        tedad[whichTedad]*=tedad[whichTedad-1];
                    }
                    else if(chemical.charAt(j)=='('){
                        whichTedad--;
                    }
                    else if(chemical.substring(j,j+1).equals(elements.get(i))&& j<chemical.length()-1&&!((int) chemical.charAt(j+1) >=97)){
                        int zarib=1;
                        if(j==chemical.length()-1||(chemical.charAt(j+1)<48||chemical.charAt(j+1)>57)) zarib=1;
                        else {
                            int k=j+1;
                            while (k<chemical.length()&&chemical.charAt(k)>=48&&chemical.charAt(k)<=57){
                                k++;
                            }
                            zarib=Integer.parseInt(chemical.substring(j+1,k));
                        }
                        finalElements.put(elements.get(i),finalElements.get(elements.get(i))+tedad[whichTedad]*zarib );
                    }
                    else if(chemical.substring(j,j+1).equals(elements.get(i))&& j==chemical.length()-1){
                        int zarib=1;
                        if(j==chemical.length()-1||(chemical.charAt(j+1)<48||chemical.charAt(j+1)>57)) zarib=1;
                        else {
                            int k=j+1;
                            while (k<chemical.length()&&chemical.charAt(k)>=48&&chemical.charAt(k)<=57){
                                k++;
                            }
                            zarib=Integer.parseInt(chemical.substring(j+1,k));
                        }
                        finalElements.put(elements.get(i),finalElements.get(elements.get(i))+tedad[whichTedad]*zarib );
                    }
                }
            }
        }
    }
    void sort(){
        String []elements_number =new  String [elements.size()];
        for (int i = 0; i < elements.size(); i++) {
            if(finalElements.get(elements.get(i))!=1)
                elements_number[i]= elements.get(i)+finalElements.get(elements.get(i)).toString();
            else{
                elements_number[i]= elements.get(i);
            }
        }
        int i, j;
        String temp;
        boolean swapped;
        for (i = 0; i < finalElements.size() - 1; i++)
        {
            swapped = false;
            for (j = 0; j < elements.size() - i - 1; j++)
            {
                if ((int)elements_number[j].charAt(0) >(int) elements_number[j + 1].charAt(0))
                {
                    temp = elements_number[j];
                    elements_number[j] = elements_number[j + 1];
                    elements_number[j + 1] = temp;

                    swapped = true;
                }
                else if ((int)elements_number[j].charAt(0) ==(int) elements_number[j + 1].charAt(0))
                {
                    if ((int)elements_number[j].charAt(1) >(int) elements_number[j + 1].charAt(1))
                    {
                        temp = elements_number[j];
                        elements_number[j] = elements_number[j + 1];
                        elements_number[j + 1] = temp;

                        swapped = true;
                    }
                }
            }
            if (swapped == false)
                break;
        }
        finalAnswer="";
        for (int k = 0; k <elements.size() ; k++) {
            if(!finalAnswer.contains(elements_number[k]))
                finalAnswer+=elements_number[k];
        }
    }

}