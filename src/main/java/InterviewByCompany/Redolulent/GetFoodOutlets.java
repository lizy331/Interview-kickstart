package InterviewByCompany.Redolulent;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetFoodOutlets {
    public static List<String> getRelevantFoodOutlets(String city, int maxCost) {
        List<String> relevantOutlets = new ArrayList<>();
        try {
            int pageNumber = 1;
            int totalPages = 1;

            while (pageNumber <= totalPages) {
                String url = "https://jsonmock.hackerrank.com/api/food_outlets?city=" + city + "&page=" + pageNumber;
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("GET");

                if (conn.getResponseCode() == 200) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String responseJson = reader.readLine();
                    JSONObject responseObject = new JSONObject(responseJson);
                    System.out.println(responseObject.toString());

                    int currentPage = responseObject.getInt("page");
                    totalPages = responseObject.getInt("total_pages");

                    JSONArray data = responseObject.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject outlet = data.getJSONObject(i);
                        int estimatedCost = outlet.getInt("estimated_cost");
                        String outletName = outlet.getString("name");

                        if (estimatedCost <= maxCost) {
                            relevantOutlets.add(outletName);
                        }
                    }

                    pageNumber = currentPage + 1;
                } else {
                    // Handle the error response if needed.
                }

                conn.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return relevantOutlets;
    }

    public static void main(String[] args) {
        String city = "Seattle";
        int maxCost = 50;
        List<String> relevantOutlets = getRelevantFoodOutlets(city, maxCost);

        System.out.println(relevantOutlets);
    }
}

