public class Sisuo {
   static String s1 = "ff";
    static String s2 = "ff";
    public static void main(String[] args) throws InterruptedException {
        ff();
        short s = 1;
        s = (short) (s+1);//强制类型转换


    }
    public static void ff() throws InterruptedException {
        synchronized (s1){

            Thread.sleep(3000);
            ff2();
            String s = "ss";
            System.out.println("锁定s1");
            Integer i = 99;
            int j = 99;
            i.equals(j);

        }
    }

    public static void ff2(){
        synchronized (s2){
            System.out.println("锁定s2");
        }
    }

}
