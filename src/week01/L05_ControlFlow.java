package week01;

/**
 * W01-2 基本语法 · 四、流程控制
 */
public class L05_ControlFlow {

    public static void main(String[] args) {
        // 1. if：条件必须是 boolean，if (1) 编译不过
        int score = 85;
        if (score >= 90) {
            System.out.println("优秀");
        } else if (score >= 60) {
            System.out.println("及格");
        } else {
            System.out.println("不及格");
        }

        // 2. 循环
        int[] arr = {3, 1, 4};
        for (int i = 0; i < arr.length; i++) {            // 经典 for，能拿到下标
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        for (int v : arr) {                               // 增强 for，相当于 Python 的 for v in arr
            System.out.print(v + " ");
        }
        System.out.println();
        int n = 0;
        while (n < 3) {
            n++;
        }
        do {                                              // 至少执行一次
            n--;
        } while (n > 10);
        System.out.println("n = " + n);                   // 2

        // 3. 传统 switch：没有 break 会穿透
        // 自测题 3：x = 3 时输出 ABC
        int x = 3;
        switch (x) {
            case 3:
                System.out.print("A");
            case 4:
                System.out.print("B");
            default:
                System.out.print("C");
        }
        System.out.println();                             // ABC

        // 改成新式 switch：不穿透，只输出 A
        switch (x) {
            case 3 -> System.out.print("A");
            case 4 -> System.out.print("B");
            default -> System.out.print("C");
        }
        System.out.println();                             // A

        // 4. 新式 switch 表达式：返回值、多个 case、yield
        String day = "WED";
        int type = switch (day) {
            case "SAT", "SUN" -> 0;
            case "MON", "TUE", "WED", "THU", "FRI" -> 1;
            default -> {
                System.out.println("未知: " + day);
                yield -1;                                 // 代码块里用 yield 返回，不是 return
            }
        };
        System.out.println(day + " -> " + type);          // WED -> 1

        // switch 支持：byte short char int 及包装类、String、enum
        // 不支持：long float double boolean
        // long L = 1; switch (L) { ... }   // ❌ selector type long is not allowed

        // 5. break / continue，带标签跳出多层循环
        outer:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 1) {
                    continue outer;                       // 直接进入外层下一次
                }
                if (i == 2) {
                    break outer;                          // 直接结束两层循环
                }
                System.out.println(i + "," + j);          // 0,0 和 1,0
            }
        }
    }
}
