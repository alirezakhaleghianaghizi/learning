import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Vector3D V1,V2,V3;
        double X,Y,Z;
        X = scanner.nextDouble();
        Y = scanner.nextDouble();
        Z = scanner.nextDouble();
        V1 = new Vector3D(X,Y,Z);
        V2 = new Vector3D(Y,Z,X);
        System.out.println("L= " + V2.getLength());
        V3 = V1.crossProduct(V2);
        System.out.println("CP= " + V3.toString());
        System.out.println("DP= " + V1.dotProduct(V2));
        System.out.println("UV= " + V3.getUnitVector().toString());
    }
}class Vector3D {
    double x;
    double y;
    double z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vector3D(Vector3D V){
        this.x=V.x;
        this.y=V.y;
        this.z=V.y;
    }
    public String getLength(){
        double length;
        length =Math.sqrt(this.x*this.x+this.y*this.y+this.z*this.z);

        return rounding(length);
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

    public Vector3D crossProduct(Vector3D V2){
        Vector3D V3=new Vector3D(0,0,0);
        V3.x=this.y*V2.z-this.z*V2.y;
        V3.y+=this.z*V2.x-this.x*V2.z;
        V3.z+=this.x*V2.y-this.y*V2.x;
        return V3;
    }

    public String  dotProduct(Vector3D V2){
        Vector3D V3=new Vector3D(0,0,0);
        V3.x=this.x*V2.x;
        V3.y+=this.y*V2.y;
        V3.z+=this.z*V2.z;
        return this.rounding(V3.x+V3.y+V3.z);
    }
    public Vector3D getUnitVector(){
        Vector3D V3=new Vector3D(0,0,0);
        double length=Math.sqrt(this.x*this.x+this.y*this.y+this.z*this.z);
        V3.x=this.x/length;
        V3.y+=this.y/length;
        V3.z+=this.z/length;
        return V3;
    }

    @Override
    public String toString() {
        return
                this.rounding(x) +
                        "," + this.rounding(y) +
                        "," + this.rounding(z)
                ;
    }
}
