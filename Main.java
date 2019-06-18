import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] A = new int[n];
        for (int i= 0; i < n; i++){
            A[i] = scan.nextInt();
        }
        for (int i = 0; i < n; i++){
            System.out.println(method(A[i]));
        }



    }

    public static String method(int a){

        String flag = "S";
        int k = 0;
        int l = 0;
        int p = a;
        while(p!=0){
            l++;
            int i = 0;
            i = p % 10;
            if (i == 0){
                k++;
            }
            else if (a % i == 0) {
                k++;
                flag = "H";
            }
           p/=10;
        }
        if (l == k){
            flag = "G";
        }
        return flag;
    }
}
