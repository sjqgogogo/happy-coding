package com.sunjiaqi.algorithm.learning.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class QuickSort implements SortDemo {

    @Override
    public int[] sort(int[] nums) {
        partition(nums, 0, nums.length - 1);
        return nums;
    }

    /**
     * 目标：将数组nums的[start, end]部分进行部分排序，使得存在一个基准数pivot，这个数左边的数都不比他大，这个数右边的数都不小于他
     * @param nums
     * @param start
     * @param end
     */
    private void partition(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }

        // 获取基准数的方法，使用随机的方法会比较稳定，也可以直接取第一个数
        int pivot = getPivot(nums, start, end);

        // 使用双指针法进行排序，一个指针标记比pivot小的坐标范围，一个指针用来遍历数组，遇到小的数就与指针1的数进行交换
        int currentIndex = start;
        for (int i = start; i <= end; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, currentIndex++);
            }
        }
        // 双指针法结束后，currentIndex起右边所有的数都不比pivot小，只需要把pivot交换至currentIndex即可保证右边的数都不比pivot小
        for (int i = currentIndex; i <= end; i++) {
            if (nums[i] == pivot) {
                swap(nums, i, currentIndex);
                break;
            }
        }
        // 此时currentIndex即为所求基准，以currentIndex为界限，分别对左右递归执行partition方法即可
        partition(nums, start, currentIndex - 1);
        partition(nums, currentIndex + 1, end);
    }

    private int getPivot(int[] nums, int start, int end) {
        Random random = new Random();
        int index = random.nextInt(end - start + 1) + start;
        if (index > end || index < start) {
            throw new RuntimeException();
        }
        return nums[index];
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] nums = new int[]{0,5,3,2,8,9,0,2};
        log.info("nums before quick sort:{}", nums);
        nums = quickSort.sort(nums);
        log.info("nums after quick sort:{}", nums);
    }

}
