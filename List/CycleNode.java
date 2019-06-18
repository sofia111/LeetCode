package List;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
*@Author: Sofia
*@Email: feng-sofia@foxmail.com
*@Date: 2019/5/28 21:58
*@Description: 快慢指针判断是否链表是否有循环节点
*/

public class CycleNode {

    public  static ListNode cycleNode(ListNode head){

        ListNode p = head;
        ListNode p2 = head;
        boolean isCycle = false;
        while (p2.next != null && p2.next.next != null){
            p = p.next;
            p2 = p2.next.next;
            if (p == p2 ){
                isCycle = true;
                break;
            }
        }
       if (isCycle){
           ListNode q = head;
           while (p != q){
               p = p.next;
               q = q.next;
           }
           return q;
       }else{
           return null;
       }

    }

    public static void main(String[] args) {

        List<ListNode> a = new ArrayList<ListNode>();

    }

}
