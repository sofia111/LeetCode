package Root;

import java.util.*;

/**
*@Author: Sofia
*@Email: feng-sofia@foxmail.com
*@Date: 2019/5/31 13:58
*@Description: 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * 引用队列，将每层的root放进，值存进list中，将包含每层的值的list放进最终的返回队列中
*/
public class LevelOrder {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()) {
            int count = que.size();
            List<Integer> list = new ArrayList<>();
            while (count > 0) {
                TreeNode node = que.poll();
                list.add(node.val);
                if (node.right != null) {
                    que.add(node.right);
                }
                if (node.left != null) {
                    que.add(node.left);
                }
                count--;
            }
            res.add(list);
        }
        return res;
    }

    //先序深度优先算法，
    public void DFSlervelOrderHelper(TreeNode root,int depth,List<List<Integer>> ans){
        if (root == null){
            return;
        }

        //如果采用中序/后序遍历，需要将if改成while
        if (depth >= ans.size()){
            ans.add(new ArrayList<>());
        }
        ans.get(depth).add(root.val);
        DFSlervelOrderHelper(root.left,depth + 1,ans);
        DFSlervelOrderHelper(root.right, depth+1, ans);
    }
}

