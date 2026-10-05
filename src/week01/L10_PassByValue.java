package week01;

import java.util.Arrays;

/**
 * W01-3 数组与方法 · 四、Java 只有值传递
 * 调用方法时，形参拿到的是实参的一份副本：
 * 基本类型复制的是值本身，引用类型复制的是地址。
 *
 * 调用 modify(arr) 时的内存：
 *
 *        栈                                堆
 *   main 的栈帧
 *   ┌───────────────┐
 *   │ arr  = 0x100 ─┼──────────┐
 *   └───────────────┘          ▼
 *   modify 的栈帧          0x100: [1, 2, 3]
 *   ┌───────────────┐          ▲
 *   │ nums = 0x100 ─┼──────────┘     nums 是 arr 的副本，地址相同
 *   └───────────────┘
 *
 *   nums[0] = 100          → 顺着地址改的是堆里同一个数组，main 里看得到
 *   nums = new int[]{...}  → 只把 nums 改成 0x200，arr 还是 0x100，main 里看不到
 */
public class L10_PassByValue {

    public static void main(String[] args) {
        // 1. 基本类型：复制值，方法里怎么改都影响不到外面
        int n = 1;
        change(n);
        System.out.println(n);                                  // 1

        int a = 1, b = 2;
        swap(a, b);
        System.out.println(a + " " + b);                        // 1 2，交换失败

        // 2. 数组：复制的是地址，两个变量指向堆里同一个数组
        int[] arr = {1, 2, 3};
        modify(arr);
        System.out.println(Arrays.toString(arr));               // [100, 2, 3]，通过地址改到了同一个数组

        // 3. 在方法里让形参指向一个新数组：只改了副本里的地址，外面不受影响
        reassign(arr);
        System.out.println(Arrays.toString(arr));               // [100, 2, 3]

        // 4. 交换数组里的两个元素：可以，因为改的是堆里的数组
        swap(arr, 0, 2);
        System.out.println(Arrays.toString(arr));               // [3, 2, 100]

        // 5. String：不可变，str + "!" 会生成新对象再赋给形参，相当于情况 3
        String s = "hi";
        changeStr(s);
        System.out.println(s);                                  // hi

        // 6. StringBuilder：可变，append 改的是同一个对象，相当于情况 2
        StringBuilder sb = new StringBuilder("hi");
        append(sb);
        System.out.println(sb);                                 // hi!
        resetBuilder(sb);
        System.out.println(sb);                                 // hi!，形参指向新对象，外面不受影响

        // 7. 想让方法「改」外面的基本类型变量：用返回值
        n = increase(n);
        System.out.println(n);                                  // 2
    }

    static void change(int x) {
        x = 100;
    }

    static void swap(int x, int y) {
        int t = x;
        x = y;
        y = t;
    }

    static void modify(int[] nums) {
        nums[0] = 100;
    }

    static void reassign(int[] nums) {
        nums = new int[]{7, 8, 9};
        nums[0] = -1;
    }

    static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    static void changeStr(String str) {
        str = str + "!";
    }

    static void append(StringBuilder builder) {
        builder.append("!");
    }

    static void resetBuilder(StringBuilder builder) {
        builder = new StringBuilder("new");
    }

    static int increase(int x) {
        return x + 1;
    }
}
