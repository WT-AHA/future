package com.aha.algorithm.leetcode;

/**
 * 215. 数组中的第K个最大元素
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/
 */
public class LeetCode215 {

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{2,1}, 2));
    }

    /**
     * 基于快排的方式对数据进行排序 然后取出第 k 大的元素
     * 数据排序方式为 从小到大  那么第k大的元素就是 取出有序数据下标为 len - k 的元素
     *
     * 基于快排每次排序的区间不同 如果 len-k 大于边界值 就取 边界值 + 1 到 len 中排序
     * 反之去 0 边界值-1 中寻找
     * 等于直接返回，就是在快排的情况下优化一下
     */
    public static int findKthLargest(int[] nums, int k) {
//        直接使用 api 也是调用快排的方式
//        Arrays.sort(nums);
//        return nums[nums.length - k];
        int len = nums.length;
        int target = len - k;
        int startIndex = 0;
        int endIndex = len - 1;

        while (true) {
            int i = doublePointerSwap(nums, startIndex, endIndex);
            if (i == target) {
                return nums[i];
            } else if (i < target) {
                startIndex = i + 1;
            } else {
                endIndex = i - 1;
            }
        }

    }

    public static int doublePointerSwap (int[] nums, int startIndex, int endIndex) {

        int left = startIndex;
        int right = endIndex;
        // 以第一个元素作为边界值
        int boundary = nums[startIndex];

        while (left < right) {

            while (left < right && nums[right] >= boundary) {
                right --;
            }
            while (left < right && nums[left] <= boundary) {
                left ++;
            }

            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }

        }

        // 将边界值与指针现在指向的位置进行交换
        nums[startIndex] = nums[right];
        nums[right] = boundary;

        return right;
    }

}
