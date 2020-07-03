package com.ptg.algorithm;

import java.util.Arrays;
import java.util.logging.Handler;

public class Sort {

    public static void quickSort(int[] arr, int left, int right) {

        if (left < right) {
            int pivot = partition(arr, left, right);
            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }

    }

    /**
     * 排序过程
     *
     * @param arr   待排序数组
     * @param left  待排序数组最小下标
     * @param right 待排序数组最大下标
     * @return 排好序之后基准数的位置下标，方便下次的分区
     */
    public static int partition(int[] arr, int left, int right) {
        int temp = arr[left];//定义基准数，默认为数组的第一个元素
        while (left < right) {//循环执行的条件
            //因为默认的基准数是在最左边，所以首先从右边开始比较进入while循环的判断条件
            //如果当前arr[right]比基准数大，则直接将右指针左移一位，当然还要保证left<right
            while (left < right && arr[right] > temp) {
                right--;
            }
            //跳出循环说明当前的arr[right]比基准数要小，那么直接将当前数移动到基准数所在的位置，并且左指针向右移一位（left++）
            //这时当前数（arr[right]）所在的位置空出，需要从左边找一个比基准数大的数来填充。
            if (left < right) {
                arr[left++] = arr[right];
            }
            //下面的步骤是为了在左边找到比基准数大的数填充到right的位置。
            //因为现在需要填充的位置在右边，所以左边的指针移动，如果arr[left]小于或者等于基准数，则直接将左指针右移一位
            while (left < right && arr[left] <= temp) {
                left++;
            }
            //跳出上一个循环说明当前的arr[left]的值大于基准数，需要将该值填充到右边空出的位置，然后当前位置空出。
            if (left < right) {
                arr[right--] = arr[left];
            }
        }
        //当循环结束说明左指针和右指针已经相遇。并且相遇的位置是一个空出的位置，
        //这时候将基准数填入该位置，并返回该位置的下标，为分区做准备。
        arr[left] = temp;
        return left;
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; i < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        int len = arr.length;
        int minIndex = -1;
        for (int i = 0; i < len - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
        }
    }

    public static void insertionSort(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            // 暂存当前这个元素
            int tmp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] > tmp) { // 前面的元素比当前的元素大
                arr[j] = arr[j - 1]; //前面的元素后移
                j--;
            }
            arr[j] = tmp;
        }
    }

    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
    }

    public static void mergeSort(int[] arr, int start, int end, int[] tmp) {
        if (start < end) {
            int mid = (start + end) >> 1;
            mergeSort(arr, start, mid, tmp);
            mergeSort(arr, mid + 1, end, tmp);
            if (arr[mid] <= arr[mid + 1]) {// 如果数组的这个子区间本身有序，无需合并
                return;
            }
            merge(arr, start, mid, end, tmp);
        }

    }

    private static void merge(int[] arr, int start, int mid, int end, int[] tmp) {
        int i = start;  // i is the start index of left sorted array
        int j = mid + 1;    // j is the start index of right sorted array
        int k = 0;

        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                tmp[k] = arr[i];
                i++;
            } else {
                tmp[k] = arr[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            tmp[k++] = arr[i++];
        }

        while (j <= end) {
            tmp[k++] = arr[j++];
        }

        //tmp拷贝到arr
        k = 0;
        int _start = start;
        while (_start <= end) {
            arr[_start++] = tmp[k++];
        }

    }

    /**
     * @param arr
     * @param k   当前下沉元素的下标
     * @param end [0, end] 是 nums 的有效部分
     */
    public static void adjustHeap(int[] arr, int k, int end) {
        while (2 * k + 1 <= end) {
            int j = 2 * k + 1;
            if (j + 1 <= end && arr[j + 1] > arr[j]) {
                j++;
            }
            if (arr[j] > arr[k]) {
                swap(arr, j, k);
            } else {
                break;
            }
            k = j;
        }
    }

    private static void swap(int[] arr, int j, int k) {
        int temp = arr[j];
        arr[j] = arr[k];
        arr[k] = temp;
    }

    public static void headSort(int[] arr) {
        int len = arr.length;
        for (int i = (len - 1) / 2; i >= 0; i--) {
            adjustHeap(arr, i, len - 1);
        }

        // 循环不变量：区间 [0, i] 堆有序
        for (int i = len - 1; i >= 1; ) {
            // 把堆顶元素（当前最大）交换到数组末尾
            swap(arr, 0, i);
            // 逐步减少堆有序的部分
            i--;
            // 下标 0 位置下沉操作，使得区间 [0, i] 堆有序
            adjustHeap(arr, 0, i);
        }
    }

    public static void shellSort(int[] arr) {
        int len = arr.length;
        int gap = 1;
        while (3 * gap + 1 < len) {
            gap = 3 * gap + 1;
        }

        while (gap >= 1) {
            for (int i = gap; i < len; i++) {
                int tmp = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > tmp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = tmp;
            }
            gap /= 3;
        }

    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 6, 1, 7, 2, 8};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
