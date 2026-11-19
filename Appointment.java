import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Appointment {
    private Patient patient;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime recommendedArrival;
    private String appointmentCode;

    public Appointment(Patient patient, LocalDate date, LocalTime startTime, String appointmentCode) {
        this.patient = patient;
        this.date = date;
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(28);
        this.recommendedArrival = startTime.equals(LocalTime.of(6, 0))
                ? startTime : startTime.minusMinutes(10);
        this.appointmentCode = appointmentCode;
    }

    public String getStatus() {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        if (date.isBefore(today) || (date.equals(today) && !now.isBefore(endTime))) {
            return "COMPLETED";
        }
        return "SCHEDULED";
    }

    public String getTimeRange() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("h:mm a");
        return startTime.format(format) + " - " + endTime.format(format);
    }

    public Patient getPatient() {
        return patient;
    }

    public void displayConfirmation() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mm a");

        System.out.println("\n===== APPOINTMENT CONFIRMATION =====");
        System.out.println("Patient Name: " + patient.getName());
        System.out.println("Age: " + patient.getAge());
        System.out.println("Contact Number: " + patient.getContactNumber());
        System.out.println("Sex: " + patient.getSex());
        System.out.println("Appointment Date: " + date.format(dateFormat));
        System.out.println("Appointment Time: " + getTimeRange());
        System.out.println("Recommended Arrival: " + recommendedArrival.format(timeFormat));
        System.out.println("Appointment Code: " + appointmentCode);
        System.out.println("Status: " + getStatus());
        System.out.println("\nPlease save your appointment code.");
    }
}
