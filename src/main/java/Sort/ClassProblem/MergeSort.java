package Sort.ClassProblem;

public class MergeSort {

    public void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    // 这个 private merge sort 可以写成 helper function，或者叫作 partition function
     private void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left >= right) {
            return;
        }


        // 不断的将 array 分割
        int mid = left + (right - left) / 2;
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        merge(arr, temp, left, mid, right);
    }

    private void merge(int[] arr, int[] temp, int left, int mid, int right) {
        System.arraycopy(arr, left, temp, left, right - left + 1);

        int i = left;
        int j = mid + 1;
        int k = left;

        // i 从 left 开始, j 从 mid + 1 开始，在 k 上填数字，k 的起点是 left
        // 如果只有两个 数字，那么 left 就等于 mid
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }

        while (i <= mid) {
            arr[k++] = temp[i++];
        }
    }

    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(arr);

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}

