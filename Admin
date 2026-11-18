
import java.util.Scanner;

public class Admin extends User {
    private final String username = "admin";
    private final String password = "passpass";

    public Admin(String name) {
        super(name);
    }

    public boolean login(Scanner input) {
        for (int attempt = 1; attempt <= 3; attempt++) {
            System.out.print("\nAdmin username: ");
            String enteredUsername = input.nextLine();
            System.out.print("Admin password: ");
            String enteredPassword = input.nextLine();

            if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
                System.out.println("Login successful.");
                return true;
            }

            System.out.println("Incorrect username or password.");
            System.out.println("Attempts remaining: " + (3 - attempt));
        }

        System.out.println("Maximum login attempts reached.");
        return false;
    }

    @Override
    public void displayMenu() {
        System.out.println("\nWelcome, Admin.");
    }
}
