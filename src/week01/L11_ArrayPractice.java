package week01;

import java.util.Arrays;

/**
 * W01-3 数组与方法 · 动手练习参考答案
 * 本周验收：不靠 IDE 提示，能用 Java 写出数组排序、找最大值这类小程序。
 * 复习时可以挑几个方法，关掉提示自己默写一遍再对照。
 */
public class L11_ArrayPractice {

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 7};

        // 练习 1：一次遍历同时求最大值和最小值
        int[] mm = maxMin(arr);
        System.out.println("最大值 " + mm[0] + "，最小值 " + mm[1]);   // 最大值 9，最小值 1

        // 练习 2：原地反转数组
        int[] r = arr.clone();
        reverse(r);
        System.out.println(Arrays.toString(r));                 // [7, 1, 9, 2, 5]

        // 练习 3：手写冒泡排序
        int[] s = arr.clone();
        bubbleSort(s);
        System.out.println(Arrays.toString(s));                 // [1, 2, 5, 7, 9]

        // 练习 4：手写二分查找
        System.out.println(binarySearch(s, 7));                 // 3
        System.out.println(binarySearch(s, 6));                 // -1

        // 练习 5：矩阵转置（2 行 3 列 → 3 行 2 列）
        int[][] m = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(Arrays.deepToString(transpose(m)));  // [[1, 4], [2, 5], [3, 6]]

        // 练习 6：可变参数求和与平均分
        System.out.println(sum(90, 85, 77));                    // 252
        System.out.println(average(90, 85, 77));                // 84.0
        System.out.println(average());                          // 0.0

        // 练习 7：手写选择排序
        int[] sel = arr.clone();
        selectionSort(sel);
        System.out.println(Arrays.toString(sel));               // [1, 2, 5, 7, 9]

        // 练习 8：有序数组原地去重，返回去重后的长度
        int[] dup = {1, 1, 2, 3, 3, 3, 5};
        int len = removeDuplicates(dup);
        System.out.println(len + " " + Arrays.toString(Arrays.copyOf(dup, len)));   // 4 [1, 2, 3, 5]

        // 练习 9：统计小写字母出现次数
        int[] freq = letterCount("banana");
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                System.out.print((char) ('a' + i) + "=" + freq[i] + " ");
            }
        }
        System.out.println();                                   // a=3 b=1 n=2
    }

    static int[] maxMin(int[] a) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }
        int max = a[0], min = a[0];           // 用第一个元素初始化；用 0 初始化的话全是负数时会错
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
            if (a[i] < min) {
                min = a[i];
            }
        }
        return new int[]{max, min};           // 一个方法要返回两个值，最简单的办法是返回数组
    }

    static void reverse(int[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {   // 双指针，头尾往中间走
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }

    static void bubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {                // 第 i 轮把第 i 大的数换到末尾
            boolean swapped = false;
            for (int j = 0; j < a.length - 1 - i; j++) {        // 末尾 i 个已经排好，不用再比
                if (a[j] > a[j + 1]) {
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;                        // 一轮没有交换说明已经有序，最好情况 O(n)
            }
        }
    }

    static int binarySearch(int[] a, int target) {
        int left = 0, right = a.length - 1;   // 闭区间 [left, right]
        while (left <= right) {               // 区间里还有元素就继续
            int mid = left + (right - left) / 2;   // 防溢出写法，见 W01-2
            if (a[mid] == target) {
                return mid;
            } else if (a[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    static int[][] transpose(int[][] m) {
        int rows = m.length, cols = m[0].length;
        int[][] t = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                t[j][i] = m[i][j];
            }
        }
        return t;
    }

    static int sum(int... nums) {
        int total = 0;
        for (int n : nums) {
            total += n;
        }
        return total;
    }

    static double average(int... scores) {
        if (scores.length == 0) {
            return 0;
        }
        return (double) sum(scores) / scores.length;   // 先转 double，否则是整数除法；可变参数可以直接传数组
    }

    static void selectionSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {                // 每轮从 [i, n) 里选出最小的，放到 i
            int minIdx = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {                // 每轮最多交换一次，比冒泡交换次数少
                int t = a[i];
                a[i] = a[minIdx];
                a[minIdx] = t;
            }
        }
    }

    static int removeDuplicates(int[] a) {
        if (a.length == 0) {
            return 0;
        }
        int slow = 0;                         // [0, slow] 是去重后的部分
        for (int fast = 1; fast < a.length; fast++) {   // 快慢指针，fast 往前找新数字
            if (a[fast] != a[slow]) {
                slow++;
                a[slow] = a[fast];
            }
        }
        return slow + 1;
    }

    static int[] letterCount(String s) {
        int[] count = new int[26];            // 下标 0~25 对应 a~z，默认值都是 0
        for (char c : s.toCharArray()) {
            count[c - 'a']++;                 // c - 'a' 把字母变成下标，见 W01-2 的 char 运算
        }
        return count;
    }
}
