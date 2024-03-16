package InterviewByCompany.Walmart.OA;

import java.util.Random;

public class User {
    String name;
    String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public static void main(String[] args) {
        User user = new User("Jack", generatePassword());
        System.out.println("User is " + user.name + " and password is " + user.password);
    }

    private static String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        int characterLength = 12; // Length of the generated password
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < characterLength; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }

        return password.toString();
    }
}
