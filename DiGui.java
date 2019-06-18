import java.math.BigInteger;
import java.util.Scanner;

public class DiGui {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int result = 1;
        String s = String.valueOf(result);
        BigInteger answer = new BigInteger(s);
        for (int i = 1; i <= n; i++){
            String X = String.valueOf(i);
            BigInteger v = new BigInteger(X);
            answer = answer.multiply(v);
        }
        System.out.println(answer);
        scanner.close();
    }

        void doSomething () {
              String s="";
            int l = s.length();
        }

}
