package dongtaiguihua;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
*@Author: Sofia
*@Email: feng-sofia@foxmail.com
*@Date: 2019/5/6 21:53
*@Description:[给定一个三角形，找出自顶向下的最小路径和
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 *
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
*/
public class MinimumTotal {

    public static void main(String[] args) {

        List<List<Integer>> a = new LinkedList<>();

        a.add(new LinkedList<>(Arrays.asList(2))) ;
        a.add(new LinkedList<>(Arrays.asList(3,4)));
        a.add(new LinkedList<>(Arrays.asList(6,5,7)));
        a.add(new LinkedList<>(Arrays.asList(4,1,8,3)));
        System.out.println(minimumTotal(a));
    }

    public static int minimumTotal(List<List<Integer>> triangle){
        if (triangle.size() == 0 || triangle == null)
            return  0;
        int[] dp = new  int[triangle.size() + 1];

        for (int i =  triangle.size() - 1; i >= 0; i--){
            List<Integer> curTri = triangle.get(i);
            for (int j = 0; j < curTri.size(); j++){
                dp[j] = Math.min(dp[j],dp[j + 1]) + curTri.get(j);
                System.out.println(dp[j]+"---"+dp[j+1]+"--"+j+"---"+curTri.get(j));
            }
        }
        return dp[0];
    }
}
