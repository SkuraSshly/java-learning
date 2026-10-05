package week01;

/**
 * W01-2 基本语法 · 动手练习参考答案
 */
public class L06_SyntaxPractice {

    public static void main(String[] args) {
        // 练习 1：各类型范围见 L02_PrimitiveTypes.java

        // 练习 2：成绩等级。switch 不能写范围，用 score / 10 把分数变成 0~10 的整数
        int score = 85;
        char grade = switch (score / 10) {
            case 10, 9 -> 'A';
            case 8 -> 'B';
            case 7 -> 'C';
            case 6 -> 'D';
            default -> 'F';
        };
        System.out.println(score + " 分 -> " + grade);     // 85 分 -> B

        // 练习 3：1~30，7 的倍数跳过
        for (int i = 1; i <= 30; i++) {
            if (i % 7 == 0) {
                continue;
            }
            if (i % 15 == 0) {                            // 先判断 15，否则会被 3 的分支抢先
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }

        // 练习 4：2 的幂
        int[] tests = {1, 6, 16, 0, -8};
        for (int t : tests) {
            System.out.println(t + " 是 2 的幂吗？" + isPowerOfTwo(t));
        }
        // 1 true，6 false，16 true，0 false，-8 false
    }

    /**
     * 2 的幂的二进制只有一个 1，n & (n - 1) 会去掉最低位的 1，结果为 0 就说明只有一个 1。
     * n > 0 必须先判断：0 & (-1) == 0 会误判；负数也不是 2 的幂。
     */
    static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
