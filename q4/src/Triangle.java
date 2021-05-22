import java.util.Scanner;

public class Triangle {
    Point point1;
    Point point2;
    Point point3;
    double arga;
boolean isformatOk;
    public Triangle() {
        this.point1 = new Point();
        this.point2 = new Point();
        this.point3 = new Point();
        this.isformatOk=true;
        this.arga=0;
    }

    public static Triangle create(Scanner scanner){
        Triangle triangle=new Triangle();
        try {
            triangle.point1.name=scanner.next();
            triangle.point1.x=Double.parseDouble(scanner.next());
            triangle.point1.y=Double.parseDouble(scanner.next());
            triangle.point1.z=Double.parseDouble(scanner.next());
            triangle.point2.name=scanner.next();
            triangle.point2.x=Double.parseDouble(scanner.next());
            triangle.point2.y=Double.parseDouble(scanner.next());
            triangle.point2.z=Double.parseDouble(scanner.next());
            triangle.point3.name=scanner.next();
            triangle.point3.x=Double.parseDouble(scanner.next());
            triangle.point3.y=Double.parseDouble(scanner.next());
            triangle.point3.z=Double.parseDouble(scanner.next());
            return triangle;
        }
        catch (NumberFormatException e){
            triangle.isformatOk=false;
            return triangle;
        }

    }


    public String c(){
        double x1=point1.x-point2.x;
        double x2=point1.y-point2.y;
        double x3=point1.z-point2.z;
        return this.rounding(Math.sqrt(Math.pow(x1,2)+Math.pow(x2,2)+Math.pow(x3,2)));
    }
    public String argA(){
        double x1=point1.x-point2.x;
        double x2=point1.y-point2.y;
        double x3=point1.z-point2.z;
        double c=Math.pow(x1,2)+Math.pow(x2,2)+Math.pow(x3,2);
        double x=point1.x-point3.x;
        double y=point1.y-point3.y;
        double z=point1.z-point3.z;
        double b=Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2);
        double x4=point3.x-point2.x;
        double x5=point3.y-point2.y;
        double x6=point3.z-point2.z;
        double a=Math.pow(x4,2)+Math.pow(x5,2)+Math.pow(x6,2);
        this.arga=Math.acos((b+c-a)/Math.sqrt(b*c)/2)*180/Math.PI;
        return this.rounding(arga);
    }
    public String p(){
        double x1=point1.x-point2.x;
        double x2=point1.y-point2.y;
        double x3=point1.z-point2.z;
        double c=Math.pow(x1,2)+Math.pow(x2,2)+Math.pow(x3,2);
        double x=point1.x-point3.x;
        double y=point1.y-point3.y;
        double z=point1.z-point3.z;
        double b=Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2);
        double x4=point3.x-point2.x;
        double x5=point3.y-point2.y;
        double x6=point3.z-point2.z;
        double a=Math.pow(x4,2)+Math.pow(x5,2)+Math.pow(x6,2);
        a=Math.sqrt(a);
        b=Math.sqrt(b);
        c=Math.sqrt(c);
        return this.rounding(a+b+c);
    }
    public double p1(){
        double x1=point1.x-point2.x;
        double x2=point1.y-point2.y;
        double x3=point1.z-point2.z;
        double c=Math.pow(x1,2)+Math.pow(x2,2)+Math.pow(x3,2);
        double x=point1.x-point3.x;
        double y=point1.y-point3.y;
        double z=point1.z-point3.z;
        double b=Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2);
        double x4=point3.x-point2.x;
        double x5=point3.y-point2.y;
        double x6=point3.z-point2.z;
        double a=Math.pow(x4,2)+Math.pow(x5,2)+Math.pow(x6,2);
        a=Math.sqrt(a);
        b=Math.sqrt(b);
        c=Math.sqrt(c);
        return a+b+c;
    }
    public String rounding(double x){
        String s1;
        s1=String.format("%.2f",x);
        if(s1.indexOf(".")==-1){
            s1+=".00";
        }
        if(s1.equalsIgnoreCase("-0.00")){
            s1="0.00";
        }
        return s1;
    }
    public String area(){
        double x1=point1.x-point2.x;
        double x2=point1.y-point2.y;
        double x3=point1.z-point2.z;
        double c=Math.pow(x1,2)+Math.pow(x2,2)+Math.pow(x3,2);
        double x=point1.x-point3.x;
        double y=point1.y-point3.y;
        double z=point1.z-point3.z;
        double b=Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2);
        double x4=point3.x-point2.x;
        double x5=point3.y-point2.y;
        double x6=point3.z-point2.z;
        double a=Math.pow(x4,2)+Math.pow(x5,2)+Math.pow(x6,2);
        a=Math.sqrt(a);
        b=Math.sqrt(b);
        c=Math.sqrt(c);
        double p=this.p1()/2;
        double s=Math.sqrt(p*(p-a)*(p-b)*(p-c));
        return this.rounding(s);
    }
}
