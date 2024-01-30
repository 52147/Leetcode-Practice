// 第二题 输入是一个数组和数字n，如果数组里正好有1到n的‍‌‍‍‌‍‌‍‍‌‍‌‌‌‍‌‍‍‌‌整数输出true否则false
public class ArrayChecker {
    public static boolean contains1ToN(int[] array, int n) {
        // 使用一个布尔数组来跟踪找到的数字
        boolean[] found = new boolean[n];

        for (int value : array) {
            // 只处理1到n范围内的数字
            if (value >= 1 && value <= n) {
                // 标记找到的数字
                found[value - 1] = true;
            }
        }

        // 检查是否所有数字都被找到了
        for (boolean b : found) {
            if (!b) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 2, 4, 5}; // 示例数组
        int n = 5; // 检查1到5的整数

        boolean result = contains1ToN(array, n);
        System.out.println("Array contains all numbers from 1 to " + n + ": " + result);
    }
}
