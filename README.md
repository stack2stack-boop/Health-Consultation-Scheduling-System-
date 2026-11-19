# Health-Consultation-Scheduling-System-

Description: "A Java-based Health Consultation Scheduling System that allows patients to register and schedule health consultations online. It provides appointment date selection, automated time assignments, unique appointment codes, and an admin interface for viewing and managing appointment schedules. Developed using Object-Oriented Programming (OOP) concepts."

# Object-Oriented Programming Concepts Applied

The following are the detailed documentation of how the OOP principles were used throughout the program:

### *Encapsulation*

```java
private String name;

public String getName() {
    return name;
}

```
In User.java, encapsulation is applied by keeping the variable name private and allowing access through the public getName() method. This protects the user's information from direct modification outside the class.

```java
private int age;
private String contactNumber;
private String sex;

public int getAge() {
    return age;
}

public String getContactNumber() {
    return contactNumber;
}

public String getSex() {
    return sex;
}
```

These encapsulated patient information from Patient.java can only be accessed through getter methods. This keeps important patient details protected while allowing other classes to retrieve the necessary information.
```java
private final String username = "admin";
private final String password = "passpass";
```

In Admin.java, the username and password are declared private, preventing other classes from directly accessing or modifying the administrator's login credentials.
```java
private Patient patient;
private LocalDate date;
private LocalTime startTime;
private LocalTime endTime;
private LocalTime recommendedArrival;
private String appointmentCode;

public Patient getPatient() {
    return patient;
}
```

In Appointment.java, encapsulation is applied by keeping appointment details private and providing a public getter for accessing the associated patient. This protects the appointment's internal information from direct modification.
```java
private final LinkedHashMap<LocalDate, Appointment[]> schedules = new LinkedHashMap<>();
private final ArrayList<String> usedCodes = new ArrayList<>();
private final SecureRandom random = new SecureRandom();
```

Lastly, in Clinic.java, the private fields manage appointment schedules, previously generated codes, and random code generation. These cannot be directly accessed outside the class, protecting the system's internal scheduling data.
### *Inheritance*

```java
public abstract class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void displayMenu();
}
```

The User.java serves as the parent class that contains common properties and methods shared by different types of users in the system. It provides the basic structure that other classes can inherit.

```java
public class Patient extends User {

    public Patient(String name, int age, String contactNumber, String sex) {
        super(name);
        this.age = age;
        this.contactNumber = contactNumber;
        this.sex = sex;
    }
}
```

In Patient.java, inheritance is applied by extending the User class. Through `extends User`, the Patient class inherits the name property and getName() method from its parent class. The `super(name)` constructor is used to pass the patient's name to the parent class.

```java
public class Admin extends User {

    public Admin(String name) {
        super(name);
    }
}
```

Similarly, in Admin.java, inheritance is applied by extending the same User parent class. This allows the Admin class to inherit common user information and methods without having to create them again.

Both Patient.java and Admin.java use the User class as their foundation, allowing the system to organize different user types while reusing common code.
### *Abstraction*

```java
public abstract class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void displayMenu();
}
```

In User.java, abstraction is applied by declaring the User class as an abstract class. It provides a common structure for different types of users without requiring the parent class to define how each user menu should work. The abstract displayMenu() method must be implemented by its child classes.

```java
@Override
public void displayMenu() {
    System.out.println("Guest patient access.");
}
```

In Patient.java, the abstract displayMenu() method from the User class is implemented to provide the appropriate menu behavior for guest patients.

```java
@Override
public void displayMenu() {
    System.out.println("\nWelcome, Admin.");
}
```

Similarly, in Admin.java, the same abstract method is implemented to display the administrator's welcome message.

Through abstraction, the system uses the User class as a general foundation while allowing Patient.java and Admin.java to provide their own specific implementations.

### *Polymorphism*

```java
public abstract class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void displayMenu();
}
```

In User.java, polymorphism is supported through the displayMenu() method, which is declared as an abstract method. This allows different child classes to provide their own versions of the same method.

```java
@Override
public void displayMenu() {
    System.out.println("Guest patient access.");
}
```

In Patient.java, the displayMenu() method is overridden to display a message specific to guest patients.

```java
@Override
public void displayMenu() {
    System.out.println("\nWelcome, Admin.");
}
```

Similarly, in Admin.java, the same displayMenu() method is overridden to display a message specific to administrators.

Through polymorphism, both Patient.java and Admin.java use the same inherited method name but execute different implementations depending on the object. This allows the system to provide different behaviors for different types of users while maintaining a common method structure.

### ***Program Structure***
Main Class(Guest Booking)

    HEALTH CONSULTATION SCHEDULING SYSTEM
    - 1.Guest
    - 2.Admin
    - 3.Exit
    - Choose an option: 1
Patient Class

    Patient Registration:
    - Full Name: Juan Dela Cruz
    - Age: 28
    - Contact Number: 09171234567

    Select Sex:
    - 1.Male
    - 2.Female
    - Choose: 1
Appointment Class

    Available Appointment Dates:
     - 1. Friday, November 20, 2026 | Available - 0/30
     - 2. Saturday, November 21, 2026 | Available - 0/30
     - 3. Sunday, November 22, 2026 | Available - 0/30
     - 4. Monday, November 23, 2026 | Available - 0/30
     - 5. Tuesday, November 24, 2026 | Available - 0/30
     - 6. Wednesday, November 25, 2026 | Available - 0/30
     - 7. Thursday, November 26, 2026 | Available - 0/30
     - 8. Friday, November 27, 2026 | Available - 0/30
     - 9. Saturday, November 28, 2026 | Available - 0/30
     - 10. Sunday, November 29, 2026 | Available - 0/30
     - 11. Monday, November 30, 2026 | Available - 0/30
     - 12. Tuesday, December 01, 2026 | Available - 0/30

     Select a date: 1
Clinic Class

    Appointment Confirmation
     - Patient Name: Juan Dela Cruz
     - Age: 28
     - Contact Number: 09171234567
     - Sex: Male
     - Appointment Date: November 20, 2026
     - Appointment Time: 6:00 AM - 6:28 AM
     - Recommended Arrival: 6:00 AM
     - Appointment Code: HC-8K2P-9X4M
     - Status: SCHEDULED

     Please save your appointment code.

Main Class(Admin)

    HEALTH CONSULTATION SCHEDULING SYSTEM
    - 1.Guest
    - 2.Admin
    - 3.Exit
    - Choose an option: 2

    - Admin username: admin
    - Admin password: passpass
    - Login successful.

    Welcome Admin
Admin Class

    Admin Menu
    - 1. View Appointment Schedules
    - 2. Logout
    - Choose an option: 1
Appointment Class

    - Date: November 20, 2026
    - No appointments.

    - Date: November 21, 2026
    - No appointments.
Admin Menu

    - 1. View Appointment Schedules
    - 2. Logout
    - Choose an option: 2
    - Admin logged out.
    
# How to Run it

1. Download the Source Code: On the main repository page, click the green <> Code button and select Download ZIP.

2. Extract the Files: Locate the downloaded ZIP file on your computer and extract it into a regular folder.

3. Open in VS Code: Launch Visual Studio Code, go to File > Open Folder... in the top-left menu, and select the extracted Health-Consultation-          Scheduling-System--main folder.

4. Run the Application: Open Main.java, click Run in the top menu, and select Start Debugging (or press F5). Once the debugger finishes loading, the    system will launch and run as intended.


# How to Navigate the Program
1. Main Menu

When the program starts, you’ll see:

1. Guest
2. Admin
3. Exit

Type the number of your choice and press Enter. Invalid input (letters or numbers out of range) will ask you to try again.

2.1 Guest: Booking an Appointment

Choose 1. Guest and fill in the registration form:

Full name: cannot be left empty.
Age: a number from 1 to 100.
Contact number: digits only (no spaces, dashes, or +).
Sex: enter 1 for Male or 2 for Female.

Next, you’ll see the next 12 available dates, starting from the day after tomorrow. Each date shows how many of its 30 slots are taken (for example, 5/30) and whether it’s Available or FULL.

Enter the number of the date you want (1 to 12).
The system automatically assigns the next free time slot for that day. Slots are 28 minutes long, starting at 6:00 AM.
A confirmation screen shows your details, appointment date and time, recommended arrival time (10 minutes early, except for the 6:00 AM slot), your appointment code (format HC-XXXX-XXXX), and your status.

Save your appointment code. 

2.2 Admin: Viewing Schedules

Choose 2. Admin and log in:

Username: admin
Password: passpass
You get 3 attempts. After the third failed attempt, you return to the main menu.

Once logged in, the Admin Menu appears:

1. View Appointment Schedules
2. Logout
 - View Appointment Schedules lists every date, with each patient’s name, time slot, and status (SCHEDULED or COMPLETED). Dates with no bookings show “No appointments.”
Logout returns you to the main menu.
4. Exit

Choose 3. Exit from the main menu to close the program.




    
