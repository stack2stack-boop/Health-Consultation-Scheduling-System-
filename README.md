# Health-Consultation-Scheduling-System-

Description: "A Java-based Health Consultation Scheduling System that allows patients to register and schedule health consultations online. It
provides appointment date selection, automated time assignments, unique appointment codes, and an admin interface for viewing and managing
appointment schedules. Developed using Object-Oriented Programming (OOP) concepts."

# Object-Oriented Programming Concepts Applied

The following are the detailed documentation of how the OOP principles were used throughout the program:

### *Encapsulation*

```java
abstract class User {
    private String name;

    public String getName() {
        return name;
    }
}

```
In User.java, encapsulation is applied by declaring the name field as private and providing controlled read-only access through the public getName()
getter method. This prevents external classes from directly altering a user's name.   

```java
class Patient extends User {
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
}
```

In Patient.java, patient attributes (age, contactNumber, and sex) are declared private and exposed solely through public getter methods. This
protects sensitive patient records from unauthorized modification while allowing classes like Appointment to retrieve details when printing
confirmations.

```java
class Admin extends User {
    private final String username = "admin";
    private final String password = "passpass";
}
```

In Admin.java, username and password are declared private final with no public getters or setters. Credentials remain completely hidden inside the
Admin class and are validated internally via the login(Scanner input) method.

```java
class Appointment {
    private Patient patient;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime recommendedArrival;
    private String appointmentCode;

    public Patient getPatient() {
        return patient;
    }
}
```

In Appointment.java, all booking details (patient, date, startTime, endTime, recommendedArrival, and appointmentCode) are kept private. External
classes can only inspect the appointment through controlled methods such as getPatient(), getTimeRange(), and getStatus().

```java
class Clinic {
    private static final int CAPACITY = 30;
    private static final int TOTAL_DATES = 12;
    private static final int SLOT_DURATION = 28;
    private static final LocalTime OPENING_TIME = LocalTime.of(6, 0);
    private final LinkedHashMap<LocalDate, Appointment[]> schedules = new LinkedHashMap<>();
    private final ArrayList<String> usedCodes = new ArrayList<>();
    private final SecureRandom random = new SecureRandom();
}
```
In Clinic.java, clinic rules, capacity limits, schedule mappings, generated booking codes, and helper methods (countAppointments() and
generateCode()) are all encapsulated as private. Other classes interact with the clinic exclusively through public operations like
displayAvailableDates(), bookAppointment(), and displaySchedules().

```java
public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final Clinic clinic = new Clinic();
    private static boolean running = true;
}
```


### *Inheritance*

```java
abstract class User {
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

User.java serves as the base parent class defining shared state (name) and common behavior (getName()) for all user roles in the system.

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

In Patient.java, inheritance is applied by extending the User class. Through `extends User`, the Patient class inherits the name property and
getName() method from its parent class. The `super(name)` constructor is used to pass the patient's name to the parent class.

```java
class Patient extends User {

    public Patient(String name, int age, String contactNumber, String sex) {
        super(name);
        this.age = age;
        this.contactNumber = contactNumber;
        this.sex = sex;
    }
}
```
In Patient.java, class Patient extends User establishes an IS-A relationship with User. Using super(name), the constructor delegates initialization
of the name field to User, inheriting getName() without duplicating code.

```java
class Admin extends User {

    public Admin(String name) {
        super(name);
    }
}
```
In Admin.java, class Admin extends User similarly inherits the name property and getName() method from User via super(name), allowing both user
types to share a unified superclass.

### *Abstraction*

```java
Java
abstract class User {
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

In User.java, abstraction is achieved by declaring abstract class User alongside the abstract method public abstract void displayMenu();. Because a
generic User cannot be instantiated directly, the parent class defines what every user role must do (displayMenu()) while leaving how it is
displayed to concrete subclasses.

```java
Java
// In Patient.java
@Override
public void displayMenu() {
    System.out.println("Guest patient access.");
}

// In Admin.java
@Override
public void displayMenu() {
    System.out.println("\nWelcome, Admin.");
}
```

Both Patient and Admin fulfill the abstract contract by providing concrete implementations of displayMenu() tailored to their respective roles.

```java
@Override
public void displayMenu() {
    System.out.println("\nWelcome, Admin.");
}
```

### *Polymorphism*

```java
// Parent contract in User.java
public abstract void displayMenu();

// Overridden in Patient.java
@Override
public void displayMenu() {
    System.out.println("Guest patient access.");
}

// Overridden in Admin.java
@Override
public void displayMenu() {
    System.out.println("\nWelcome, Admin.");
}
```

Runtime polymorphism (method overriding) is demonstrated through displayMenu(). Although Patient and Admin share the exact same method signature
inherited from User, invoking displayMenu() on an Admin object executes Admin's welcome prompt ("\nWelcome, Admin."), whereas invoking it on a
Patient object executes Patient's access message ("Guest patient access.")

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


# How to Navigate it
1. Main Menu

When the program starts, you’ll see:

### 1. Guest
### 2. Admin
### 3. Exit

Type the number of your choice and press Enter. Invalid input (letters or numbers out of range) will ask you to try again.

2.1 Guest: Booking an Appointment

Choose 1. Guest and fill in the registration form:

### Full name: cannot be left empty.
### Age: a number from 1 to 100.
### Contact number: digits only (no spaces, dashes, or +).
### Sex: enter 1 for Male or 2 for Female.

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
3. Exit

Choose 3. Exit from the main menu to close the program.

### ***Sample Output***
**1.    GUEST MENU**\
<img width="417" height="111" alt="image" src="https://github.com/user-attachments/assets/b80ee641-a0d2-4396-b7c5-88973b763deb" />

**2.    PATIENT CLASS**\
<img width="417" height="172" alt="image" src="https://github.com/user-attachments/assets/cbcac246-2836-4e41-9651-70ac60062d4b" />

**3.    APPOINTMENT CLASS**\
<img width="417" height="234" alt="image" src="https://github.com/user-attachments/assets/07f5ca62-ba0a-44ef-a357-9f84bde4aeaf" />

**4.    CLINIC CONFIRMATION**\
<img width="328" height="219" alt="c44f907b-7d0e-445d-8084-fdfcc0e4be90" src="https://github.com/user-attachments/assets/f22bd143-0209-4257-938b-36445cec216f" />

**1.    ADMIN MENU**\
<img width="399" height="213" alt="image" src="https://github.com/user-attachments/assets/0485d64f-4647-4607-9ef1-444aa9982fdd" />

**2. APPOINTMENT CLASS**\
<img width="329" height="722" alt="2916fb89-73ad-436a-9067-68f902466b23" src="https://github.com/user-attachments/assets/f5e011ab-f323-4315-80fe-97e64ebc6c77" />

**3. CLINIC CONFIRMATION**\
<img width="414" height="218" alt="3b059226-a391-4047-89e6-047ee14ee3cb" src="https://github.com/user-attachments/assets/c4c2a03b-13ce-4387-9306-c530d6ecb075" />

### ***Author and Acknoelegdement***
This program is created by **"The RatbuKillas"** from IT-2108 in compliance with the final project requirement in the course Object-Oriented Programming.

**Lead Programmer:** \
Arce, King Mizael B.

**Members:**\
Onde, Gian Simon B.\
Gutierrez, Lester D.\
Gaylan, Mark Louie R.

Special thanks to **Mr. Dominic Miko Valdez** in guiding these young programmers through introducing OOP concepts which are necessary in completing this project.

### ***Future Enhancements***
Future developers can transition the system from a text-based console interface to a clean graphical user interface (GUI), making date selection and slot booking much easier to navigate. Incorporating database storage or file handling would also ensure that patient records and schedules are preserved permanently rather than resetting when the program closes. Furthermore, expanding the platform to support appointment cancellation via booking codes, multiple doctor profiles, and administrative search tools will make the scheduling workflow smoother, faster, and more adaptable to real clinic operations.



















    
