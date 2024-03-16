package InterviewByCompany.Walmart.OA;

import java.io.*;
import java.util.*;

public class StringManapulation {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getProperty("OUTPUT_FILE_PATH")));

        // Read input from the user
        String str = bufferedReader.readLine();
        StringBuffer newStr = new StringBuffer(str);

        for (int i = 0; i < str.length(); i++) {
            // Check if the character at "i" is lowercase
            if (Character.isLowerCase(str.charAt(i))) {
                // Convert the character to uppercase
                newStr.setCharAt(i, Character.toUpperCase(str.charAt(i)));
            } else if (Character.isUpperCase(str.charAt(i))) {
                // Convert the character to lowercase
                newStr.setCharAt(i, Character.toLowerCase(str.charAt(i)));
            }
        }

        // Write the result to the output
        bufferedWriter.write(newStr.toString() + "\n");

        bufferedReader.close();
        bufferedWriter.close();
    }
}

