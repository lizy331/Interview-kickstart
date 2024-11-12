package InterviewByCompany.Amazon;

import java.util.Arrays;

public class TruckAndGasStation {

    public int[] calculateDistance(int[] positions, int[][] queries){
        // locate the gasstation of each trcuk to reach
        int[] result = new int[queries.length];

        for(int k = 0;k < queries.length;k++){
            int[] query = queries[k];
            int[] stationToGo = new int[positions.length];
            int aIndex = query[0]-1;
            int bIndex = query[1]-1;

            for(int i = 0;i<positions.length;i++){
                if(i <= aIndex){
                    // goto a stattioin
                    stationToGo[i] = aIndex;
                } else if(i <= bIndex){
                    stationToGo[i] = bIndex;
                }else{
                    stationToGo[i] = positions.length-1;
                }

            }

            // distance = sum: positions[stationToGo[i]] - positions[i]
            int distance = 0;
            for(int i = 0;i<positions.length;i++){
                distance += positions[stationToGo[i]] - positions[i];
            }

            result[k] = distance;

        }

        return result;




    }

    public static void main(String args[]){
        TruckAndGasStation truckAndGasStation = new TruckAndGasStation();

        int[][] queries1 = new int[][]{{2,4}};
        int[] positions = new int[]{3,6,10,15,20};

        System.out.println(Arrays.toString(truckAndGasStation.calculateDistance(positions,queries1))); // reeturn 8
    }
}
