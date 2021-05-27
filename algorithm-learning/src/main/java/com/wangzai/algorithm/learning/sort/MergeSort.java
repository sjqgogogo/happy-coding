package com.wangzai.algorithm.learning.sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MergeSort implements SortDemo {

    @Override
    public int[] sort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int start, int end) {
        if (start == end) {
            return;
        }
        if (start + 1 == end) {
            if (nums[start] > nums[end]) {
                swap(nums, start, end);
            }
            return;
        }
        // 选取中点
        int mid = start + (end - start) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        // 合并两个有序数组
        int[] mergedNums = new int[end - start + 1];
        int left = start, right = mid + 1;
        for (int i = 0; i < mergedNums.length; i++) {
            if (left > mid) {
                mergedNums[i] = nums[right++];
            } else if (right > end) {
                mergedNums[i] = nums[left++];
            } else {
                if (nums[left] < nums[right]) {
                    mergedNums[i] = nums[left++];
                } else {
                    mergedNums[i] = nums[right++];
                }
            }
        }
        for (int i = 0; i < mergedNums.length; i++) {
            nums[start + i] = mergedNums[i];
        }
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] nums = new int[]{0,5,3,2,8,9,0,2};
        log.info("nums before merge sort:{}", nums);
        nums = mergeSort.sort(nums);
        log.info("nums after merge sort:{}", nums);
    }

}
