package projects.loginsystem;

import java.util.*;

public class LoginSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FileReader db = new FileReader(); // instance from FileReader

        System.out.print("username: "); // TODO let user create new acc if he hasn't one
        String user = scanner.nextLine(); // input -> username
        System.out.println();
        validUser(user, db); // checks if username is valid // TODO if invalid -> repeat step (ask for username again)

        System.out.print("password: ");
        String password = scanner.nextLine(); // input -> password
        System.out.println();

        if (validPassword(user, password, db)) { // checks if password is valid // TODO if invalid -> repeat step (ask for pw again)
            System.out.println("success, you are logged in "); // success message
        }
    }

    private static void validUser(String user, FileReader db) {
        if (user == null || user.equals("") || user.matches("[ \t]+")) { // checks if user is empty
            System.out.println("empty user, please try again"); // -> error message
            System.exit(0); // system exit
        } else if (!db.containsUser(user)) { // checks if user isn't in db
            System.out.println("user does not exist, please try again "); // -> error message
            System.exit(0); // system exit
        }
    }

    public static boolean validPassword(String user, String password, FileReader db) {
        String pwFromDB = db.getPasswordForUser(user);

        if (pwFromDB == null) { // if pw is null -> error message
            System.out.println("failed to log in [problem in database] please try again ");
            return false;
        } else if (password == null || password.equals("") || password.matches("[ \t]+")) { // if pw is empty -> error message
            System.out.println("failed to log in [empty password] please try again ");
            return false;
        } else if (pwFromDB.equals(password)) { // if pw is correct -> error message
            return true;
        } else if (!pwFromDB.equals(password)) { // if pw is incorrect -> error message
            System.out.println("failed to log in [invalid password] please try again ");
            return false;
        } else { // else unknown error message
            System.out.println("failed to log in [unknown error] please try again ");
            return false;
        }
    }
}

