package week01;

import java.util.Arrays;

/**
 * W01-3 数组与方法 · 一、一维与二维数组
 */
public class L07_Arrays {

    public static void main(String[] args) {
        // 1. 三种创建方式
        int[] a = new int[3];                 // 指定长度，元素是默认值
        int[] b = {1, 2, 3};                  // 静态初始化，只能在声明的同时这样写
        int[] c = new int[]{4, 5, 6};         // 这种可以先声明后赋值：c = new int[]{...}
        // int[] d = new int[3]{1, 2, 3};     // ❌ 指定了长度就不能再给初始值
        // int e[] = {1, 2};                  // C 风格也能编译，但不推荐
        System.out.println(a.length + " " + b.length + " " + c.length);   // 3 3 3，length 是字段，不是方法

        // 2. 默认值：和成员变量的默认值一样
        System.out.println(Arrays.toString(new int[2]));       // [0, 0]
        System.out.println(Arrays.toString(new double[2]));    // [0.0, 0.0]
        System.out.println(Arrays.toString(new boolean[2]));   // [false, false]
        System.out.println(Arrays.toString(new String[2]));    // [null, null]，引用类型默认 null
        char[] chars = new char[1];
        System.out.println((int) chars[0]);                     // 0，即 '\u0000'（直接打印是个看不见的字符）

        // 3. 直接打印数组：得到的是「类型@哈希码」，不是内容
        System.out.println(b);                                  // [I@xxxxxxxx，[I 表示 int 数组
        System.out.println(Arrays.toString(b));                 // [1, 2, 3]

        // 4. 越界：运行时异常，编译器查不出来
        try {
            System.out.println(b[3]);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());                // Index 3 out of bounds for length 3
        }

        // 5. 数组是对象，在堆上；变量里存的是引用（地址）
        int[] alias = b;                      // 没有复制，两个变量指向同一个数组
        alias[0] = 100;
        System.out.println(b[0]);                               // 100
        int[] copy = b.clone();               // 真正复制一份
        copy[0] = 1;
        System.out.println(b[0] + " " + copy[0]);               // 100 1
        System.out.println((b == alias) + " " + (b == copy));   // true false，== 比较的是地址

        // 6. 遍历
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i] + " ");
        }
        System.out.println();                                   // 4 5 6
        for (int v : c) {
            v = 0;                            // v 只是元素的副本，改 v 不会改数组
        }
        System.out.println(Arrays.toString(c));                 // [4, 5, 6]

        // 7. 二维数组：本质是「元素为一维数组的数组」
        int[][] m = new int[2][3];            // 2 行 3 列
        m[1][2] = 9;
        System.out.println(m.length + " " + m[0].length);       // 2 3，行数 和 第 0 行的列数
        System.out.println(Arrays.toString(m));                 // [[I@..., [I@...]，一维的 toString 不够用
        System.out.println(Arrays.deepToString(m));             // [[0, 0, 0], [0, 0, 9]]

        int[][] grid = {{1, 2, 3}, {4, 5, 6}};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();                               // 1 2 3 换行 4 5 6
        }

        // 每行长度可以不同（锯齿数组）
        int[][] jagged = new int[3][];        // 只指定行数，每一行现在是 null
        System.out.println(jagged[0]);                          // null
        jagged[0] = new int[1];
        jagged[1] = new int[]{1, 2};
        jagged[2] = new int[]{1, 2, 3};
        System.out.println(Arrays.deepToString(jagged));        // [[0], [1, 2], [1, 2, 3]]
        // int[][] bad = new int[][3];        // ❌ 必须先指定第一维

        // 二维数组的 clone 是浅拷贝：只复制了「行的引用」，行本身还是共享的
        int[][] shallow = grid.clone();
        shallow[0][0] = 99;
        System.out.println(grid[0][0]);                         // 99，原数组也变了
        int[][] deep = new int[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            deep[i] = grid[i].clone();        // 深拷贝：每一行单独复制
        }
        deep[0][0] = 1;
        System.out.println(grid[0][0]);                         // 99，不受影响

        // 8. 空数组和 null 不一样
        int[] empty = new int[0];
        int[] none = null;
        System.out.println(empty.length);                       // 0
        try {
            System.out.println(none.length);
        } catch (NullPointerException ex) {
            System.out.println("null 数组取 length → NullPointerException");
        }
    }
}
