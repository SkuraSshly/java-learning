package leetcode.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 128. 最长连续序列（Medium）
 * https://leetcode.cn/problems/longest-consecutive-sequence/
 *
 * 思路：先把所有数放进 HashSet（去重 + O(1) 查找）。
 * 只从「起点」开始往后数：x - 1 不在集合里，x 才是一段连续序列的起点。
 * 从起点 x 一直查 x + 1、x + 2 ... 在不在，数出这一段的长度，更新最大值。
 *
 * 为什么是 O(n)：起点虽然可能有很多个，但每个数只会在「它所在那一段」被往后数到一次，
 * 不是起点的数直接跳过。所以 while 循环在整个过程中总共最多执行 n 次，加上外层遍历 n 次，合计 O(n)。
 *
 * 时间 O(n)，空间 O(n)
 */
public class LC0128_LongestConsecutive {

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
        }

        int maxLen = 0;                                   // 空数组答案是 0，不能从 1 开始
        for (int x : set) {                               // 遍历 set 而不是 nums：重复的数只处理一次
            if (!set.contains(x - 1)) {                   // x 是起点
                int cur = x;
                int curLen = 1;
                while (set.contains(cur + 1)) {
                    cur++;
                    curLen++;                             // 加的是这一段的长度，不是 maxLen
                }
                maxLen = Math.max(maxLen, curLen);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LC0128_LongestConsecutive sol = new LC0128_LongestConsecutive();
        System.out.println(sol.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));           // 4，[1, 2, 3, 4]
        System.out.println(sol.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));   // 9，[0..8]
        System.out.println(sol.longestConsecutive(new int[]{1, 0, 1, 2}));                     // 3，有重复
        System.out.println(sol.longestConsecutive(new int[]{}));                               // 0，空数组
        System.out.println(sol.longestConsecutive(new int[]{1, 2, 0, 10, 11}));                // 3，第二段 [10, 11] 更短
    }
}

/*
 * 第一版的两个 bug（对照用）：
 * 1. while 里写成了 maxLen += 1，curLen 一直是 1。
 *    这样所有段的长度会累加到 maxLen 上：[1, 2, 0, 10, 11] 会得到 1 + 2 + 1 = 4，正确答案是 3。
 * 2. maxLen 初始化为 1，空数组会返回 1，正确答案是 0。
 *
 * 面试怎么讲：
 * - 先说排序：排好序后扫一遍数连续段，O(n log n)。题目要求 O(n)，所以不能排序。
 * - 用 HashSet 做 O(1) 查找，只从起点往后数，每个数最多被数到一次，整体 O(n)。
 */
