package leetcode.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1. 两数之和（Easy）
 * https://leetcode.cn/problems/two-sum/
 *
 * 思路：哈希表，用空间换时间。
 * 遍历到 nums[i] 时，真正要找的是 need = target - nums[i] 有没有出现过。
 * 用 Map 记录「已经见过的数 → 它的下标」，把「找 need」从 O(n) 降到 O(1)。
 *
 * 关键：先查后放。
 * - 保证不会用同一个元素两次：nums = [3,2,4], target = 6 时，不会错误返回 [0,0]
 * - 重复元素也能处理：nums = [3,3], target = 6 时，第二个 3 能找到第一个 3
 *
 * 时间 O(n)：只遍历一次，每次哈希表查找和插入平均 O(1)
 * 空间 O(n)：最坏情况下哈希表存 n - 1 个元素
 */
public class LC0001_TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();   // 面向接口编程：左边声明为 Map
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];                 // 只算一次
            Integer j = seen.get(need);                  // 只查一次；必须用 Integer，查不到时是 null
            if (j != null) {
                return new int[]{j, i};
            }
            seen.put(nums[i], i);                        // 先查后放
        }
        return new int[0];                               // 题目保证有解，走不到这里；不返回 null，避免调用方空指针
    }

    public static void main(String[] args) {
        LC0001_TwoSum s = new LC0001_TwoSum();
        System.out.println(Arrays.toString(s.twoSum(new int[]{2, 7, 11, 15}, 9)));  // [0, 1]
        System.out.println(Arrays.toString(s.twoSum(new int[]{3, 2, 4}, 6)));       // [1, 2]，不是 [0, 0]
        System.out.println(Arrays.toString(s.twoSum(new int[]{3, 3}, 6)));          // [0, 1]
        System.out.println(Arrays.toString(s.twoSum(new int[]{-1, -2, -3}, -5)));   // [1, 2]，负数也可以
        System.out.println(Arrays.toString(s.twoSum(new int[]{1, 2}, 10)));         // []，无解
    }
}

/*
 * 面试怎么讲：
 * 1. 先说暴力解：两层循环枚举所有组合，O(n^2) 时间、O(1) 空间。
 * 2. 再优化：瓶颈在「找 target - x 有没有出现过」，用哈希表把这一步降到 O(1)，
 *    整体 O(n) 时间、O(n) 空间，即用空间换时间。
 *
 * 常见追问：
 * - 为什么先查后放？先放再查，当 target = 2 * nums[i] 时会找到自己，比如 [3,2,4], 6 会返回 [0,0]。
 * - 数组有序时能不能 O(1) 空间？可以，用左右双指针：和小了左指针右移，和大了右指针左移（第 2 周双指针）。
 *   本题数组无序，先排序会打乱下标，要先把 (值, 原下标) 一起排序，整体 O(n log n)。
 * - 要返回所有满足条件的下标对？Map 的 value 改成 List<Integer> 存同一个值的所有下标，
 *   找到时和列表里每个下标配对，不要提前 return。
 */
