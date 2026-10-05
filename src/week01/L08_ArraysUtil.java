package week01;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * W01-3 数组与方法 · 二、Arrays 工具类
 */
public class L08_ArraysUtil {

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 7};

        // 1. toString / deepToString
        System.out.println(Arrays.toString(arr));               // [5, 2, 9, 1, 7]

        // 2. sort：原地排序，返回值是 void
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        System.out.println(Arrays.toString(sorted));            // [1, 2, 5, 7, 9]
        int[] part = {5, 2, 9, 1, 7};
        Arrays.sort(part, 1, 4);                                // 只排下标 [1, 4)，左闭右开
        System.out.println(Arrays.toString(part));              // [5, 1, 2, 9, 7]
        // 基本类型数组用双轴快排（不稳定），对象数组用 TimSort（稳定，归并 + 插入）

        // 降序：int[] 不能传比较器，要换成 Integer[]
        Integer[] boxed = {5, 2, 9, 1, 7};
        Arrays.sort(boxed, Collections.reverseOrder());
        System.out.println(Arrays.toString(boxed));             // [9, 7, 5, 2, 1]
        Arrays.sort(boxed, (x, y) -> Integer.compare(x, y));    // Lambda 写比较器（第 5、6 周细讲）
        System.out.println(Arrays.toString(boxed));             // [1, 2, 5, 7, 9]
        // 比较器别写 (x, y) -> x - y：x 很大、y 是很小的负数时会溢出
        // Arrays.sort(arr, Collections.reverseOrder());        // ❌ 编译错误，int[] 不行

        String[] words = {"banana", "Apple", "cherry"};
        Arrays.sort(words);
        System.out.println(Arrays.toString(words));             // [Apple, banana, cherry]，按字符编码，大写排在小写前

        // 3. fill
        int[] dp = new int[5];
        Arrays.fill(dp, -1);                                    // 刷题初始化 dp / 访问标记数组常用
        System.out.println(Arrays.toString(dp));                // [-1, -1, -1, -1, -1]
        Arrays.fill(dp, 1, 3, 0);                               // 只填 [1, 3)
        System.out.println(Arrays.toString(dp));                // [-1, 0, 0, -1, -1]

        // 4. copyOf / copyOfRange：返回新数组
        System.out.println(Arrays.toString(Arrays.copyOf(arr, 7)));       // [5, 2, 9, 1, 7, 0, 0]，变长补默认值
        System.out.println(Arrays.toString(Arrays.copyOf(arr, 3)));       // [5, 2, 9]，变短截断
        System.out.println(Arrays.toString(Arrays.copyOfRange(arr, 1, 4))); // [2, 9, 1]，[1, 4)
        // 数组长度固定，「扩容」就是建一个更大的新数组再复制过去，ArrayList 就是这样做的（第 5 周）

        int[] dest = new int[5];
        System.arraycopy(arr, 0, dest, 1, 3);                   // 源数组, 源起点, 目标数组, 目标起点, 个数
        System.out.println(Arrays.toString(dest));              // [0, 5, 2, 9, 0]
        // Arrays.copyOf 底层调用的就是 System.arraycopy（native 方法，很快）

        // 5. equals：比较内容；== 比较是不是同一个数组
        int[] x = {1, 2, 3};
        int[] y = {1, 2, 3};
        System.out.println(x == y);                             // false
        System.out.println(x.equals(y));                        // false！数组没有重写 equals，等同于 ==
        System.out.println(Arrays.equals(x, y));                // true
        int[][] p = {{1}, {2}};
        int[][] q = {{1}, {2}};
        System.out.println(Arrays.equals(p, q));                // false，内层数组按 equals（即 ==）比
        System.out.println(Arrays.deepEquals(p, q));            // true

        // 6. binarySearch：数组必须先排好序，否则结果没有意义
        System.out.println(Arrays.binarySearch(sorted, 7));     // 3
        System.out.println(Arrays.binarySearch(sorted, 6));     // -4，没找到返回 -(插入点) - 1，6 应插在下标 3

        // 7. Arrays.asList 的三个坑
        List<String> list = Arrays.asList("a", "b", "c");
        list.set(0, "z");                                       // 改可以
        try {
            list.add("d");                                      // 增删不行
        } catch (UnsupportedOperationException ex) {
            System.out.println("asList 返回的列表长度固定，不能 add / remove");
        }
        String[] backing = {"a", "b"};
        List<String> view = Arrays.asList(backing);
        view.set(0, "z");
        System.out.println(backing[0]);                         // z，列表和原数组共用一块内存

        int[] nums = {1, 2, 3};
        List<int[]> wrong = Arrays.asList(nums);                // 基本类型数组被当成「一个元素」
        System.out.println(wrong.size());                       // 1
        List<Integer> right = Arrays.asList(1, 2, 3);
        System.out.println(right.size());                       // 3
        // 要能增删的 List：new ArrayList<>(Arrays.asList(...))，集合放到第 5 周讲

        // 8. 求和、最大值：Stream 一行搞定（第 6 周讲），面试手写还是要会用循环
        System.out.println(Arrays.stream(arr).sum());           // 24
        System.out.println(Arrays.stream(arr).max().getAsInt()); // 9
    }
}
