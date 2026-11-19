import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final Clinic clinic = new Clinic();
    private static boolean running = true;

    public static void main(String[] args) {
        while (running) {
            System.out.println("\n===== HEALTH CONSULTATION SCHEDULING SYSTEM =====");
            System.out.println("1. Guest");
            System.out.println("2. Admin");
            System.out.println("3. Exit");
            int choice = readInt("Choose an option: ", 1, 3);

            switch (choice) {
                case 1:
                    registerPatient();
                    running = false;
                    break;
                case 2:
                    Admin admin = new Admin("Admin");
                    if (admin.login(input)) {
                        admin.displayMenu();
                        adminPanel();
                    }
                    break;
                case 3:
                    running = false;
                    System.out.println("Thank you for using the system.");
                    break;
            }
        }
        input.close();
    }

    private static void registerPatient() {
        System.out.println("\n===== PATIENT REGISTRATION =====");
        System.out.print("Full name: ");
        String name = input.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Name cannot be empty. Enter full name: ");
            name = input.nextLine().trim();
        }

        int age = readInt("Age: ", 1, 100);
        String contact = readDigits("Contact number: ");

        System.out.println("\nSelect sex:");
        System.out.println("1. Male");
        System.out.println("2. Female");
        int sexChoice = readInt("Choose: ", 1, 2);
        String sex = sexChoice == 1 ? "Male" : "Female";

        Patient patient = new Patient(name, age, contact, sex);
        clinic.displayAvailableDates();

        int dateChoice = readInt("\nSelect a date: ", 1, 12);
        Appointment appointment = clinic.bookAppointment(patient, dateChoice - 1);

        if (appointment != null) {
            appointment.displayConfirmation();
        } else {
            System.out.println("Unable to book appointment. The selected date may be full.");
        }
    }

    private static void adminPanel() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. View Appointment Schedules");
            System.out.println("2. Logout");
            int choice = readInt("Choose an option: ", 1, 2);

            switch (choice) {
                case 1:
                    clinic.displaySchedules();
                    break;
                case 2:
                    loggedIn = false;
                    System.out.println("Admin logged out.");
                    break;
            }
        }
    }

    private static int readInt(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            try {
                int number = Integer.parseInt(input.nextLine().trim());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println("Enter a number from " + min + " to " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static String readDigits(String message) {
        while (true) {
            System.out.print(message);
            String number = input.nextLine().trim();
            if (number.matches("[0-9]+")) {
                return number;
            }
            System.out.println("Contact number must contain digits only.");
        }
    }
}
