package HackerRank.TEKFullStack;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FoodAPI {

    public static void getFood(String url) throws IOException {
//        String url = "https://example.com/post_endpoint"; // 目标 URL

        url = url + "Miami" + "&estimated_cost=" + 150;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // 设置请求方法为 POST
        con.setRequestMethod("GET");


        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // 打印响应结果
        System.out.println(response.toString());

        // 对返回的 response result 提取需要的的 variable
        // 可以建立一个 model
    }

    public static void main(String[] args) throws IOException {
        getFood("https://jsonmock.hackerrank.com/api/food_outlets?city=");
    }
}
