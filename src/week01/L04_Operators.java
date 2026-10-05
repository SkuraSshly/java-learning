package week01;

/**
 * W01-2 基本语法 · 三、运算符
 */
public class L04_Operators {

    public static void main(String[] args) {
        // 1. 自增：i++ 先用后加，++i 先加后用
        int i = 5;
        i = i++;              // 旧值 5 先压栈 → i 自增为 6 → 把栈里的 5 赋回 i
        System.out.println(i);                            // 5

        int a = 5;
        int b = a++ + ++a;    // 5（之后 a=6） + 7（a 先变 7） = 12
        System.out.println(a + " " + b);                  // 7 12

        // 2. 短路：&& || 左边能决定结果就不算右边；& | 两边都算
        int k = 0;
        if (k != 0 && 10 / k > 1) {
            System.out.println("不会执行");
        }
        System.out.println("&& 短路，没有除以 0");
        try {
            if (k != 0 & 10 / k > 1) {
                System.out.println("不会执行");
            }
        } catch (ArithmeticException e) {
            System.out.println("& 不短路: " + e.getMessage());   // / by zero
        }
        String str = null;
        if (str != null && str.length() > 0) {           // 最常见用法：判空
            System.out.println(str);
        }

        // 3. 位运算（5 = 0101，3 = 0011）
        System.out.println(5 & 3);                        // 1  按位与
        System.out.println(5 | 3);                        // 7  按位或
        System.out.println(5 ^ 3);                        // 6  异或
        System.out.println(~5);                           // -6 取反
        System.out.println(1 << 3);                       // 8  左移 = ×2^n
        System.out.println(-8 >> 1);                      // -4 有符号右移，高位补符号位
        System.out.println(-8 >>> 28);                    // 15 无符号右移，高位补 0
        System.out.println(1 << 40);                      // 256！int 移位位数对 32 取模，等于 1 << 8
        System.out.println(1L << 40);                     // 1099511627776

        // 常用技巧
        System.out.println((6 & 1) == 0);                 // true，偶数
        System.out.println(12 & (12 - 1));                // 8，n & (n-1) 去掉最低位的 1（1100 → 1000）
        System.out.println(7 ^ 7);                        // 0，a ^ a = 0
        System.out.println(7 ^ 0);                        // 7，a ^ 0 = a
        System.out.println(37 & (16 - 1));                // 5，n 是 2 的幂时 x & (n-1) == x % n（HashMap 求下标）

        // 4. 三元运算符：两个分支类型不同会统一提升
        int x = 3, y = 9;
        int max = x > y ? x : y;
        System.out.println(max);                          // 9
        Object o = true ? 1 : 2.0;
        System.out.println(o);                            // 1.0，int 被提升成了 double

        // 5. char 参与运算
        char c = 'a';
        System.out.println(c + 1);                        // 98，提升为 int
        System.out.println((char) (c + 1));               // b
        System.out.println("" + c + 1);                   // a1，从左往右先拼成字符串
        System.out.println('z' - 'a');                    // 25，刷题用 c - 'a' 当计数数组下标

        // 6. Math.round 向正无穷舍入，即 floor(x + 0.5)
        System.out.println(Math.round(1.5));              // 2
        System.out.println(Math.round(-1.5));             // -1
        System.out.println(Math.round(-2.5));             // -2
    }
}
