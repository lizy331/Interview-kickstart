package InterviewByCompany.Apple.FileCopy;

import java.io.*;

public class FileCopy {

    public static void copy(String fromFile, String toFile) throws IOException {
        File source = new File(fromFile);
        File destination = new File(toFile);

        try (InputStream inputStream = new FileInputStream(source);
             OutputStream outputStream = new FileOutputStream(destination)) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }

    public static void main(String[] args) {
        String fromFile = "/Users/liziyang/workplace/leetcode/leetcode/src/main/java/InterviewByCompany/Apple/FileCopy/sourceFile.txt";
        String toFile = "/Users/liziyang/workplace/leetcode/leetcode/src/main/java/InterviewByCompany/Apple/FileCopy/destinationFile.txt";

        try {
            copy(fromFile, toFile);
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.err.println("Error occurred during file copy: " + e.getMessage());
        }
    }
}
