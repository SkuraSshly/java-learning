package week01;

/**
 * W01-2 基本语法 · 二、类型转换与精度丢失
 */
public class L03_TypeConversion {

    public static void main(String[] args) {
        // 1. 自动转换（小 → 大）：byte → short → int → long → float → double，char → int
        int i = 10;
        long l = i;
        double d = l;
        System.out.println(l + " " + d);                 // 10 10.0

        // int / long 自动转 float 会悄悄丢精度：float 只有 24 位有效二进制位
        int big = 16777217;                               // 2^24 + 1
        float f = big;
        System.out.println((int) f);                      // 16777216，少了 1

        // 2. 强制转换（大 → 小）：必须显式写 (类型)
        System.out.println((int) 3.9);                    // 3，直接截断，不是四舍五入
        System.out.println((int) -3.9);                   // -3，向 0 截断
        System.out.println((byte) 200);                   // -56，只保留低 8 位：1100 1000 按补码 = 200 - 256

        // 3. 表达式自动提升：byte / short / char 参与运算先提升为 int
        short s = 1;
        // s = s + 1;        // ❌ 编译错误：possible lossy conversion from int to short
        s += 1;              // ✅ 复合赋值自带强转，等价于 s = (short) (s + 1)
        System.out.println(s);                            // 2

        byte b1 = 127;       // ✅ 常量在范围内，可以直接赋值
        // byte b2 = 128;    // ❌ 超出范围
        final int k = 10;
        byte b3 = k;         // ✅ final 常量且值在范围内，编译器也允许
        System.out.println(b1 + " " + b3);

        // 自测题 1：byte a = 10, b = 20; byte c = a + b;  → ❌ 编译错误，a + b 是 int
        byte a = 10, b = 20;
        byte c = (byte) (a + b);                          // 要强转
        System.out.println(c);                            // 30

        // 自测题 2：long x = Integer.MAX_VALUE * 2;  → -2
        // 右边两个 int 先相乘，按 int 溢出成 -2，然后才赋给 long，已经晚了
        long wrong = Integer.MAX_VALUE * 2;
        long right = Integer.MAX_VALUE * 2L;              // 让其中一个是 long，整个乘法按 long 算
        System.out.println(wrong + " " + right);          // -2 4294967294

        // 4. 浮点精度：二进制无法精确表示 0.1
        System.out.println(0.1 + 0.2);                    // 0.30000000000000004
        System.out.println(0.1 + 0.2 == 0.3);             // false
        System.out.println(0.1f == 0.1);                  // false，float 和 double 的近似值不同
        System.out.println(Math.abs(0.1 + 0.2 - 0.3) < 1e-9);   // true，浮点数这样比较
        // 金额：用 BigDecimal（第 3 周）或用 long 按「分」存

        // 5. 整数除法与取余
        System.out.println(1 / 2);                        // 0
        System.out.println(1 / 2.0);                      // 0.5
        System.out.println(7 % 3);                        // 1
        System.out.println(-7 % 3);                       // -1，符号跟被除数（Python 是 2）
        int n = -7;
        System.out.println(n % 2 == 1);                   // false！负奇数 % 2 是 -1
        System.out.println(n % 2 != 0);                   // true，判断奇数这样写
    }
}
