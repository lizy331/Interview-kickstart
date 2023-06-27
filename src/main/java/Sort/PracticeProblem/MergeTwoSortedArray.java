package Sort.PracticeProblem;


public class MergeTwoSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m-1;
        int p2 = nums2.length-1;
        int pos = nums1.length-1;

        while(p1>=0 && p2>=0){
            // System.out.println(nums1[p1] + " " + nums2[p2]);
            if(nums1[p1]<=nums2[p2]){
                nums1[pos] = nums2[p2];
                p2--;

            }
            else{
                nums1[pos] = nums1[p1];
                p1--;
            }
            pos--;
        }
        while(p1>=0){
            nums1[pos] = nums1[p1];
            pos--;
            p1--;
        }

        while(p2>=0){
            nums1[pos] = nums2[p2];
            pos--;
            p2--;
        }
    }
}

