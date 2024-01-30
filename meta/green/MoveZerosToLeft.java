public class MoveZerosToLeft {
    public static void moveZerosToLeft(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int insertionPos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                swap(nums, i, insertionPos);
                insertionPos++;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 2, 0, 4, 3, 0};
        moveZerosToLeft(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
