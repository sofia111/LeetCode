package dongtaiguihua;

/**
*@Author: Sofia
*@Email: feng-sofia@foxmail.com
*@Date: 2019/5/7 11:22
*@Description: 数组中最大乘积的子序列w
*/
public class MaxProduct {

    public static void main(String[] args) {
//        int[] a = {2,-1,4,5};
//        System.out.println(maxProduct(a));
//        BitSet bitSet = new BitSet();
//    }
//
//
//    public static int maxProduct(int[] nums) {
//
//        int max = Integer.MIN_VALUE, imax = 1, imin = 1; //一个保存最大的，一个保存最小的。
//        for(int i=0; i<nums.length; i++){
//            if(nums[i] < 0){ int tmp = imax; imax = imin; imin = tmp;} //如果数组的数是负数，那么会导致最大的变最小的，最小的变最大的。因此交换两个的值。
//            imax = Math.max(imax*nums[i], nums[i]);
//            imin = Math.min(imin*nums[i], nums[i]);
//
//            max = Math.max(max, imax);
//        }
//        return max;
//    }
        Run1 runner=new Run1();
        Thread th1= new Thread(runner,"新线程");

        Thread th2 = new Thread(runner,"第二个线程");
//区分以下三种情况

        //th1.start();
        //1
        System.out.println("th1.start():");
        th1.start();
        th2.start();
        System.out.println("th1.run():");
        th1.run();

        System.out.println("th2.run()");
        th2.run();
        //2
        System.out.println("runner.run():");
        runner.run();
        //3

    }
}

