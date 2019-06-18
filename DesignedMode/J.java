package DesignedMode;

public class J{

    static int x= 10;
    static {x+=5;}


    static {x/=3;}
    public static void main(String[] args) {
        System.out.println("x="+x);
    }

}
//public abstract class J{
//
//    int ip;
//    public J(){}
//    static void g(){}
//}
