package leetcode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 49. 字母异位词分组（Medium）
 * https://leetcode.cn/problems/group-anagrams/
 *
 * 思路：字母异位词排序后完全相同，所以用「排好序的字符串」当分组的键。
 * 哈希表：排序后的字符串 → 这一组单词的 List。遍历完把所有 List 拿出来就是答案。
 *
 * 注意：键不能直接用 char[]。数组没有重写 equals / hashCode（见 W01-3），
 * 两个内容相同的 char[] 在 HashMap 里是两个不同的键。要先 new String(chars) 转成字符串。
 *
 * 时间 O(n · k log k)：n 个单词，每个长度最多 k，排序 k log k
 * 空间 O(n · k)：哈希表里存了所有字符
 */
public class LC0049_GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);                           // "eat" → ['a', 'e', 't']
            // computeIfAbsent：键不存在时先放一个新 List 再返回，存在时直接返回已有的 List
            groups.computeIfAbsent(new String(chars), k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(groups.values());
    }

    public static void main(String[] args) {
        LC0049_GroupAnagrams sol = new LC0049_GroupAnagrams();
        System.out.println(sol.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        // 三组：[eat, tea, ate]、[tan, nat]、[bat]，组的顺序不固定（HashMap 不保证顺序，题目允许任意顺序）
        System.out.println(sol.groupAnagrams(new String[]{""}));    // [[]]
        System.out.println(sol.groupAnagrams(new String[]{"a"}));   // [[a]]

        // 验证 char[] 不能当键：内容相同，却是两个键
        Map<char[], Integer> bad = new HashMap<>();
        bad.put("abc".toCharArray(), 1);
        bad.put("abc".toCharArray(), 2);
        System.out.println(bad.size());                   // 2
    }
}

/*
 * 面试怎么讲：
 * 关键是找到「同一组单词共有的特征」当哈希表的键。
 * - 排序后的字符串：O(k log k) 得到键，好写。
 * - 计数当键：用 int[26] 统计每个字母出现次数，再拼成字符串（如 "1#0#0#...#1"）当键，
 *   O(k) 得到键，单词很长时更快。
 *
 * 常见追问：
 * - 为什么不能用 char[] / int[] 直接当键？数组的 equals 和 hashCode 是按地址算的，不按内容。
 * - 如果字符不只是小写字母（比如有中文）？计数法的 int[26] 不够用，排序法照样可用。
 */
