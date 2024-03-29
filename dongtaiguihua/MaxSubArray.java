package dongtaiguihua;
/**
*@Author: Sofia
*@Email: feng-sofia@foxmail.com
*@Date: 2019/5/6 21:27
*@Description: 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
*/
public class MaxSubArray {

    public static void main(String[] args) {
        int[] a = {-2,-1,-3,-4,-5,-6};
        System.out.println(maxSubArray(a));

    }

    public static int maxSubArray(int[] nums){

        int rs = nums[0];
        int sum = 0;
        for (int num : nums){
            if (sum > 0)
                sum += num;
            else
                sum = num;
            rs = Math.max(rs,sum);
        }
        return rs;
    }
}
