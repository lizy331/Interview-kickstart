package InterviewByCompany.Meta;
import java.util.*;

public class SortKSegmentsArray {

    class Node{
        Node next;
        int val;
        int segment;

        public Node(int v, int s){
            this.val = v;
            this.segment = s;
        }
    }
    public void sortKSegments(int[] nums, int k){

        Node[] nodes = new Node[k];
        Node[] tails = new Node[k];
        int segmentIndex = 0;

        for(int i = 0;i<nums.length;i++){
            if(i > 0 && nums[i] < nums[i-1]){
                segmentIndex++;
            }

            Node newNode = new Node(nums[i],segmentIndex);
            if(nodes[segmentIndex]==null){
                nodes[segmentIndex] = newNode;
            }else{
                tails[segmentIndex].next = newNode;
            }
            tails[segmentIndex] = newNode;
        }

        Queue<Node> minHeap = new PriorityQueue<>((a,b) -> a.val-b.val);
        for(Node node : nodes){
            if(node!=null){
                minHeap.offer(node);
            }
        }

        int writer = 0;
        while(!minHeap.isEmpty()){
            Node node = minHeap.poll();
            nums[writer++] = node.val;
            node = node.next;
            if(node!=null){
                minHeap.offer(node);
            }
        }

        return;
    }

    public void sortKSegments2(int[] nums, int k){
        List<List<Integer>> segments = new ArrayList<>();

        for(int i = 0;i<k;i++){
            segments.add(new ArrayList<>());
        }

        int segmentIndex = 0;
        for(int i = 0;i<nums.length;i++){
            if(i > 0 && nums[i] < nums[i-1]){
                segmentIndex++;
            }
            segments.get(segmentIndex).add(nums[i]);
        }

        Queue<int[]> minHeap = new PriorityQueue<>((a,b) -> a[0] - b[0]);

        for(int i = 0;i<segments.size();i++){
            if(segments.get(i).size()>0){
                minHeap.offer(new int[]{segments.get(i).get(0),i,0}); // value, segmentIndex, position within this segment
            }
        }

        int writer = 0;
        while(!minHeap.isEmpty()){
            int[] info = minHeap.poll();
            int value = info[0];
            int segmentGroup = info[1];
            int position = info[2];
            nums[writer++] = value;

            if(position + 1 < segments.get(segmentGroup).size()){
                minHeap.offer(new int[]{segments.get(segmentGroup).get(position+1),segmentGroup,position+1});
            }

        }

        return;


    }


    public static void main(String[] args){

        int[] testcase1 = new int[]{7,8,9,4,5,6,1,2,3};
        int k1 = 3;

        SortKSegmentsArray solution = new SortKSegmentsArray();
        solution.sortKSegments2(testcase1,k1);
        System.out.println(Arrays.toString(testcase1)); // 1,2,3,4,5,6,7,8,9
    }
}
