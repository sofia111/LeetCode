package DesignedMode;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
*@Author: Sofia
*@Date: 2019/6/3 20:05
*@Description: 观察者模式，对象之间一对多的依赖，一个对象状态发生改变时，所有依赖她的对象都得到通知并自动更新
*/
public class Observer {
    public static int maxProfit(int[] prices){

//        if (prices.length <= 1)
//            return 0;
//        int min = prices[0],max = 0;
//        for (int i = 1; i < prices.length; i++){
//            max = Math.max(max,prices[i] - min);
//            min = Math.min(min,prices[i]);
//        }
//        return max;
        int max = 0;
        int yestoday = Integer.MAX_VALUE;
        for (int today : prices){
            if (today > yestoday){
                max += today - yestoday;
                yestoday = today;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] a = {7,1,5,3,6,4};
        System.out.println(maxProfit(a));
    }
}
