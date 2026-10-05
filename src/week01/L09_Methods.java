package week01;

/**
 * W01-3 数组与方法 · 三、方法定义、重载、可变参数
 */
public class L09_Methods {

    public static void main(String[] args) {
        // 1. 调用：main 是 static，这里只能直接调用 static 方法
        //    不带 static 的方法属于对象，要先 new 出对象才能调（第 2 周）
        System.out.println(max(3, 9));                          // 9
        System.out.println(isEven(4));                          // true
        printLine(5);                                           // -----

        // 2. 重载：方法名相同，参数列表不同（个数、类型、顺序），和返回值无关
        System.out.println(add(1, 2));                          // 3    调 add(int, int)
        System.out.println(add(1.5, 2.5));                      // 4.0  调 add(double, double)
        System.out.println(add(1, 2, 3));                       // 6    调 add(int, int, int)
        System.out.println(add(1, 2.5));                        // 3.5  没有 (int, double)，1 自动提升为 double

        // 重载匹配的优先级：精确匹配 > 基本类型拓宽 > 自动装箱 > 可变参数
        show(5);                                                // int：精确匹配，优先于 long / Integer / int...
        show(5L);                                               // long
        show(Integer.valueOf(5));                               // Integer
        show();                                                 // 可变参数，长度 0
        show(1, 2);                                             // 可变参数，长度 2
        pick(5);                                                // long：没有 pick(int) 时，int → long 拓宽优先于装箱成 Integer

        // 3. 可变参数：本质就是数组
        System.out.println(sum());                              // 0，传 0 个也行，nums 是空数组，不是 null
        System.out.println(sum(1, 2, 3));                       // 6
        System.out.println(sum(new int[]{4, 5}));               // 9，也可以直接传一个数组
        System.out.println(join("-", "a", "b", "c"));           // a-b-c，可变参数必须放在最后，且只能有一个
        total(1, 2);                                            // 固定参数版：能匹配固定参数时优先，可变参数排最后
        total(1, 2, 3);                                         // 可变参数版

        // 4. return：void 方法也可以用 return; 提前结束
        checkAge(-1);                                           // 年龄不合法
        checkAge(20);                                           // 年龄 20
    }

    // 方法 = 修饰符 返回类型 方法名(参数列表) { 方法体 }
    static int max(int a, int b) {
        return a > b ? a : b;
    }

    static boolean isEven(int n) {
        return n % 2 == 0;
    }

    static void printLine(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    static int add(int a, int b) {
        return a + b;
    }

    static double add(double a, double b) {
        return a + b;
    }

    static int add(int a, int b, int c) {
        return a + b + c;
    }

    // static long add(int a, int b) { return a + b; }
    // ❌ 只有返回值不同不算重载：method add(int,int) is already defined
    // 因为调用 add(1, 2) 时编译器无法根据返回值判断该调哪个

    static void show(int x) {
        System.out.println("int");
    }

    static void show(long x) {
        System.out.println("long");
    }

    static void show(Integer x) {
        System.out.println("Integer");
    }

    static void show(int... xs) {
        System.out.println("可变参数，长度 " + xs.length);
    }

    // static void show(int[] xs) { }
    // ❌ 和 show(int... xs) 冲突：可变参数编译后就是 int[]，两者签名相同

    static void pick(long x) {
        System.out.println("long");
    }

    static void pick(Integer x) {
        System.out.println("Integer");
    }

    static void total(int a, int b) {
        System.out.println("固定参数 (int, int)");
    }

    static void total(int... nums) {
        System.out.println("可变参数，长度 " + nums.length);
    }

    // static void bad(int... a, String s) { }   // ❌ 可变参数必须是最后一个参数
    // static void bad(int... a, int... b) { }   // ❌ 一个方法只能有一个可变参数

    static int sum(int... nums) {
        int total = 0;
        for (int n : nums) {
            total += n;
        }
        return total;
    }

    static String join(String sep, String... parts) {
        return String.join(sep, parts);
    }

    static void checkAge(int age) {
        if (age < 0) {
            System.out.println("年龄不合法");
            return;
        }
        System.out.println("年龄 " + age);
    }
}
