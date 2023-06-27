package PracticalExperiments.RestAPI;

import java.net.*;
import java.io.*;

public class HttpPostExample {

    public static void main(String[] args) throws Exception {

        String url = "https://example.com/post_endpoint"; // 目标 URL

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // 设置请求方法为 POST
        con.setRequestMethod("POST");

        // 设置请求头部
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        // 设置请求体
        String postData = "param1=value1&param2=value2";
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postData);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // 打印响应结果
        System.out.println(response.toString());

    }
}
