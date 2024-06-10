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

        // 循环会在 left = mid 的时候被返回, 循环的镜头是 单个数字
        mergeSort(arr, temp, left, mid);

        // 在循环的镜头 mid + 1 = right，所以也会被返回，循环的尽头 也是单个数字
        mergeSort(arr, temp, mid + 1, right);

        // 此时 right 只比 left 大一个，也就是相当于 两个单个数字进行 合并
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

    /*
    初始数组为 [38, 27, 43, 3]。
    第一次分割后，我们得到两个子数组 [38, 27] 和 [43, 3]。
    继续分割这两个子数组，得到 [38]、[27]、[43] 和 [3]。
    开始合并过程：先合并 [38] 和 [27] 得到 [27, 38]，然后合并 [43] 和 [3] 得到 [3, 43]。
    最后，合并 [27, 38] 和 [3, 43] 得到 [3, 27, 38, 43]。
     */
}

