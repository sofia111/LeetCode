package dongtaiguihua;

public class D {

    public static void main(String[] args) {
        System.out.println(sss());
    }

    public static int sss() {
        try {
            int i = 5 / 0;
        } catch (Exception e) {
            return 1;
        }
        finally {
            return 0;
        }
    }

}
