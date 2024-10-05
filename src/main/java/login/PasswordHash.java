package login;

import java.security.MessageDigest;
import java.util.Map;

public class PasswordHash {
    private static final String FILE_PATH = "D:\\Learning\\JavaMentorship\\PMS\\src\\main";

    public static boolean checkPassword(String username, String password) {
        Map<String, String> users = FileHandler.readUsers("D:\\Learning\\JavaMentorship\\PMS\\src\\main\\resources\\login.txt");
        String passwordHash = hashPassword(password);
        return passwordHash.equals(users.get(username.trim().toLowerCase()));
    }

    public static boolean register(String username, String password) {
        Map<String, String> users = FileHandler.readUsers("D:\\Learning\\JavaMentorship\\PMS\\src\\main\\resources\\login.txt");
        if (users.containsKey(username.trim().toLowerCase())) {
            return false; // Username already exists
        }
        String passwordHash = hashPassword(password);
        FileHandler.writeUser("D:\\Learning\\JavaMentorship\\PMS\\src\\main\\resources\\login.txt", username.trim().toLowerCase(), passwordHash);
        return true;
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
