# Health-Consultation-Scheduling-System-

Description: "A Java-based Health Consultation Scheduling System that allows patients to register and schedule health consultations online. It
provides appointment date selection, automated time assignments, unique appointment codes, and an admin interface for viewing and managing
appointment schedules. Developed using Object-Oriented Programming (OOP) concepts."

# Problem being addressed
In the present time the need for health consultation takes time to set it up, one must go directly in the clinic to list their information and wait
until their turn.  There's no guarantee what time you're gonna be called but with the help of our Health Consultation Scheduling System, users may
schedule their health consultation remotely, after setting it up, users are gonna receive a date and a recommended time to show-up.  

# Project Objectives
The Health Consultation Scheduling System aims to provide a convenient and efficient way for patients to arrange their health consultations without
having to visit the clinic personally while it also aims to provide clinics with a much more efficient and organized system. 

# Target Users 
The primary target users of the application are "Patients", who use the system to input their information, schedule health consultations, and receive
their consultation date and recommended arrival time, and "Clinics", which use it to monitor patient information and organize appointment schedules.

# Main Feature 
1. User Access & Admin Login - Users can access the system as Guest or Admin. Guest is intended for patients, while Admin access is password
   protected and requires the correct clearance.
2. Appointment Scheduling - Patients pick from 12 available dates (up to 30 slots per day), and the system automatically gives them the next 28
   minute time slot starting at 6:00 AM, plus a recommended arrival time.
3. Booking Code & Receipt - The system creates a unique appointment code (HC-XXXX-XXXX) and shows a confirmation summary of the booking.
4. Appointment Management - Allows the clinic administrator to view, organize, and manage patient appointments.
5. Schedule & Status Viewing - Admins can view all scheduled patients per date and see if each appointment is still SCHEDULED or already COMPLETED
   based on the current time. 

# Technologies Used:
##### Programming Language: Java (JDK)
##### Java Standard Libraries:
      - java.util.Scanner — reads user input from the console.
      - java.util.ArrayList, java.util.LinkedHashMap, & java.util.Map — stores and manages daily appointment schedules and used booking codes.
      - java.time.LocalDate, java.time.LocalTime, & java.time.format.DateTimeFormatter — handles date generation, 28-minute time slot intervals,
      recommended arrival times, and real-time appointment status checks.
      - java.security.SecureRandom — generates unique, randomized appointment codes (HC-XXXX-XXXX).
##### IDE / Code Editor: Visual Studio Code (VS Code) & CodeChum(Sometimes)
##### Version Control & Repository Hosting: Git & GitHub

# Team Members & Roles

### Lead Programmer: Arce, King Mizael
### Documentation: Gaylan, Mark Louie 
### Team Leader: Gutierrez, Lester
### Design Programmer: Onde, Gian Simon




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

``` java
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

3. Open in VS Code: Launch Visual Studio Code, go to File > Open Folder... in the top-left menu, and select the extracted Health-Consultation-
4.    Scheduling-System--main folder.

5. Run the Application: Open Main.java, click Run in the top menu, and select Start Debugging (or press F5). Once the debugger finishes loading, the
6.    system will launch and run as intended.


# How to Navigate it

### 1. Main Menu
When the program starts, you will see the following options:
1. **Guest**
2. **Admin**
3. **Exit**

Type the number of your choice and press **Enter**. Any invalid input (letters or numbers outside `1–3`) will prompt you to try again.

---

### 2. Guest: Booking an Appointment
Select **`1. Guest`** and complete the patient registration form:
* **Full Name:** Cannot be left empty.
* **Age:** Enter a number from `1` to `100`.
* **Contact Number:** Digits only (no spaces, dashes, or `+`).
* **Sex:** Enter `1` for **Male** or `2` for **Female**.

**Selecting a Date & Receiving Confirmation:**
1. The system displays the next **12 available dates** (starting tomorrow), showing how many of the **30 daily slots** are taken (e.g., `5/30`) and
2. whether each date is `Available` or `FULL`.
3. Enter the number of your preferred date (`1` to `12`).
4. The system automatically assigns the next open **28-minute slot** starting at **6:00 AM**.
5. A confirmation screen displays your patient details, appointment date and time, recommended arrival time (**10 minutes early**, except for the
6. `6:00 AM` slot), appointment status, and your unique booking code (`HC-XXXX-XXXX`).

> **Note:** Make sure to save your appointment code for reference.

---

### 3. Admin: Viewing Schedules
Select **`2. Admin`** and enter the administrator credentials:
* **Username:** `admin`
* **Password:** `passpass`

*(You have **3 login attempts**. After three failed attempts, the system returns you to the Main Menu.)*

Once logged in, the **Admin Menu** appears:
* **`1. View Appointment Schedules`** — Lists all 12 dates along with each booked patient's name, assigned time range, and status (`SCHEDULED` or
* `COMPLETED`). Dates with no bookings display `"No appointments."`
* **`2. Logout`** — Logs out of the admin session and returns to the Main Menu.

---

### 4. Exit
Select **`3. Exit`** from the Main Menu to close the program.

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
<img width="399" height="213" alt="image" 
src="https://github.com/user-attachments/assets/0485d64f-4647-4607-9ef1-444aa9982fdd" />

**2. APPOINTMENT CLASS**\
<img width="329" height="722" alt="2916fb89-73ad-436a-9067-68f902466b23" src="https://github.com/user-attachments/assets/f5e011ab-f323-4315
   80fe97e64ebc6c77" />

**3. CLINIC CONFIRMATION**\
<img width="414" height="218" alt="3b059226-a391-4047-89e6-047ee14ee3cb" src="https://github.com/user-attachments/assets/c4c2a03b-13ce-4387-9306
   c530d6ecb075" />

# Project Limitations
1. Console-Based - The system will operate through a text-based interface or the terminal and will not include a graphical user interface (GUI). 

2. File Handling - The system does not use or utilize the File Handling method, as such any information stored in the system when its being used will be lost after closing the system. 

3. No Medical Records Management - This system only runs to collect basic patient information and the reason for consultation. It does not store diagnoses, prescriptions, or medical histories.

4. Fixed Appointment Duration - Every consultation is assigned a fixed duration of 28 minutes. The system does not support longer or shorter consultations.

### ***Future Enhancements***
Future developers can transition the system from a text-based console interface to a clean graphical user interface (GUI), making date selection and
slot booking much easier to navigate. Incorporating database storage or file handling would also ensure that patient records and schedules are
preserved permanently rather than resetting when the program closes. Furthermore, expanding the platform to support appointment cancellation via
booking codes, multiple doctor profiles, and administrative search tools will make the scheduling workflow smoother, faster, and more adaptable to
real clinic operations.

### ***Author and Acknoelegdement***
This program is created by **"Little Saint Hames "** from IT-2108 in compliance with the final project requirement in the course Object-Oriented
Programming.


Special thanks to **Mr. Dominic Miko Valdez** in guiding these young programmers through introducing OOP concepts which are necessary in completing
this project.
