package InterviewByCompany.Walmart.OA;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.util.stream.Stream;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class cleanTxtFiles {
    static Path path = Paths.get("/Users/liziyang/workplace/leetcode/leetcode/src/main/java/InterviewByCompany/Walmart/OA/sample.txt");

    // ^ 代表 negation，也就是说 这个 regular expression 代表着 match 任何 不是 a-z 或者 A-Z 的character
    static String regex = "[^a-zA-Z]";

    // Fill logic to traverse all files and directories recursively
    public static FileVisitResult visitFile(Path file) throws IOException {
        if (file.getFileName().toString().endsWith(".txt")) {
            try (Stream<String> lines = Files.lines(file)) {
                // Process each line and remove non-alphabet characters
                // 这里的 replaceAll("") 的意义在于 将所有 match 到的（即不是 lower case letter 和 upper case letter） 替换成 “”
                String modifiedContent = lines.map(line -> Pattern.compile(regex).matcher(line).replaceAll("")).reduce("", (a, b) -> a + b + "\n");

                // Write the modified content back to the file
                Files.write(file, modifiedContent.getBytes());
            }
        }
        return FileVisitResult.CONTINUE;
    }

    public static void main(String[] args) throws IOException {
        cleanTxtFiles cleaner = new cleanTxtFiles();
        // Invoke the method to traverse and clean .txt files in the folder
//        Files.walkFileTree(cleaner.path, cleaner);
        visitFile(path);
    }
}

