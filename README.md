# Health-Consultation-Scheduling-System-

Description: "A Java-based Health Consultation Scheduling System that allows patients to register and schedule health consultations online. It provides appointment date selection, automated time assignments, unique appointment codes, and an admin interface for viewing and managing appointment schedules. Developed using Object-Oriented Programming (OOP) concepts."

## Object-Oriented Programming Concepts Applied

The following are the detailed documentation of how the OOP principles were used throughout the program:

### *Encapsulation*

```java
private String name;

public User(String name) {
    this.name = name;
}

public String getName() {
    return name;
}
```

In User.java, encapsulation is applied by keeping the name variable private and allowing access through the public getName() method. This prevents other classes from directly modifying the user's name.

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

In Patient.java, encapsulation is used to protect the patient's personal information, including age, contact number, and sex. These private variables can only be accessed through their respective public getter methods.

```java
private final String username = "admin";
private final String password = "passpass";
```

In Admin.java, the username and password are declared private, preventing other classes from directly accessing the administrator's login credentials. The login() method handles the verification internally.

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

public String getTimeRange() {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("h:mm a");
    return startTime.format(format) + " - " + endTime.format(format);
}
```

In Appointment.java, encapsulation is applied by keeping appointment information private. Public methods such as getPatient() and getTimeRange() provide controlled access to specific appointment details without exposing the variables directly.

```java
private final LinkedHashMap<LocalDate, Appointment[]> schedules = new LinkedHashMap<>();
private final ArrayList<String> usedCodes = new ArrayList<>();
private final SecureRandom random = new SecureRandom();
```

Lastly, in Clinic.java, encapsulation is applied to the appointment schedules, used appointment codes, and random code generator. These private fields prevent other classes from directly modifying the clinic's internal scheduling data.


