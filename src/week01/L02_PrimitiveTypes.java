package week01;

/**
 * W01-2 基本语法 · 一、8 种基本数据类型
 * 运行：IDEA 里点 main 左边的绿色三角
 */
public class L02_PrimitiveTypes {

    // 成员变量（静态变量）有默认值，局部变量没有
    static int defaultInt;
    static double defaultDouble;
    static boolean defaultBoolean;
    static char defaultChar;

    public static void main(String[] args) {
        // 1. 各类型的范围：不用背数字，直接用包装类常量
        System.out.println("byte   : " + Byte.MIN_VALUE + " ~ " + Byte.MAX_VALUE + "，" + Byte.BYTES + " 字节");
        System.out.println("short  : " + Short.MIN_VALUE + " ~ " + Short.MAX_VALUE + "，" + Short.BYTES + " 字节");
        System.out.println("int    : " + Integer.MIN_VALUE + " ~ " + Integer.MAX_VALUE + "，" + Integer.BYTES + " 字节");
        System.out.println("long   : " + Long.MIN_VALUE + " ~ " + Long.MAX_VALUE + "，" + Long.BYTES + " 字节");
        System.out.println("float  : " + Float.MIN_VALUE + " ~ " + Float.MAX_VALUE + "，" + Float.BYTES + " 字节");
        System.out.println("double : " + Double.MIN_VALUE + " ~ " + Double.MAX_VALUE + "，" + Double.BYTES + " 字节");
        System.out.println("char   : " + (int) Character.MIN_VALUE + " ~ " + (int) Character.MAX_VALUE + "，" + Character.BYTES + " 字节");
        // 注意：Float.MIN_VALUE / Double.MIN_VALUE 是「最小的正数」，不是最小的负数

        // 2. 默认值（只有成员变量和数组元素才有）
        System.out.println("int 默认值: " + defaultInt);                 // 0
        System.out.println("double 默认值: " + defaultDouble);           // 0.0
        System.out.println("boolean 默认值: " + defaultBoolean);         // false
        System.out.println("char 默认值的编码: " + (int) defaultChar);    // 0，即 '\u0000'
        // int local;
        // System.out.println(local);   // ❌ 编译错误：variable local might not have been initialized

        // 3. 字面量写法
        long big = 2147483648L;          // 超出 int 范围必须加 L；不加 L 报 integer number too large
        float pi = 3.14f;                // 小数默认是 double，赋给 float 必须加 f
        int hex = 0x1F;                  // 十六进制 31
        int bin = 0b101;                 // 二进制 5
        int million = 1_000_000;         // 下划线只是为了好读
        char zh = '中';                   // char 是 2 字节，能存一个中文字符
        System.out.println(big + " " + pi + " " + hex + " " + bin + " " + million + " " + zh);

        // 4. 整数溢出：不报错，从最大值绕到最小值
        System.out.println(Integer.MAX_VALUE + 1);       // -2147483648
        int left = 2_000_000_000, right = 2_100_000_000;
        System.out.println((left + right) / 2);          // 负数，溢出了
        System.out.println(left + (right - left) / 2);   // 2050000000，正确的求中点写法
        try {
            Math.addExact(Integer.MAX_VALUE, 1);         // 需要检测溢出时用 xxxExact
        } catch (ArithmeticException e) {
            System.out.println("溢出被检测到: " + e.getMessage());   // integer overflow
        }
    }
}
