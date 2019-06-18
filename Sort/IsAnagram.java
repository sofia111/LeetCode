package Sort;

import java.util.Arrays;

/**
*@Author: Sofia
*@Email: feng-sofia@foxmail.com
*@Date: 2019/4/25 20:38
*@Description: 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 *
*/
public class IsAnagram {

    public static boolean isAnagram(String s, String t){

        char[] sChars  = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
//你好
        System.out.println(String.valueOf(sChars));
        return String.valueOf(sChars).equals(String.valueOf(tChars));
    }

    public static void main(String[] args) {
        String s = "hello";
        String t = "elloh";
        System.out.println(isAnagram(s,t));
    }
}
