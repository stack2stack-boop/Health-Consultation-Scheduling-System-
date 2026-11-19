
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Clinic {
    private static final int CAPACITY = 30;
    private static final int TOTAL_DATES = 12;
    private static final int SLOT_DURATION = 28;
    private static final LocalTime OPENING_TIME = LocalTime.of(6, 0);

    private final LinkedHashMap<LocalDate, Appointment[]> schedules = new LinkedHashMap<>();
    private final ArrayList<String> usedCodes = new ArrayList<>();
    private final SecureRandom random = new SecureRandom();

    public Clinic() {
        for (int i = 1; i <= TOTAL_DATES; i++) {
            LocalDate date = LocalDate.now().plusDays(i);
            schedules.put(date, new Appointment[CAPACITY]);
        }
    }

    public void displayAvailableDates() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
        System.out.println("\n===== AVAILABLE APPOINTMENT DATES =====");

        int index = 1;
        for (Map.Entry<LocalDate, Appointment[]> entry : schedules.entrySet()) {
            int booked = countAppointments(entry.getValue());
            String status = booked == CAPACITY ? "FULL" : "Available";

            System.out.println(index + ". " + entry.getKey().format(format)
                    + " | " + status + " - " + booked + "/" + CAPACITY);
            index++;
        }
    }

    public Appointment bookAppointment(Patient patient, int dateIndex) {
        if (dateIndex < 0 || dateIndex >= schedules.size()) {
            return null;
        }

        LocalDate selectedDate = new ArrayList<>(schedules.keySet()).get(dateIndex);
        Appointment[] dailyAppointments = schedules.get(selectedDate);

        int availableSlot = -1;
        for (int i = 0; i < dailyAppointments.length; i++) {
            if (dailyAppointments[i] == null) {
                availableSlot = i;
                break;
            }
        }

        if (availableSlot == -1) {
            System.out.println("This date is fully booked.");
            return null;
        }

        LocalTime appointmentTime = OPENING_TIME.plusMinutes((long) availableSlot * SLOT_DURATION);
        String code = generateCode();

        Appointment appointment = new Appointment(patient, selectedDate, appointmentTime, code);
        dailyAppointments[availableSlot] = appointment;
        return appointment;
    }

    private int countAppointments(Appointment[] appointments) {
        int count = 0;

        for (Appointment appointment : appointments) {
            if (appointment != null) {
                count++;
            }
        }

        return count;
    }

    private String generateCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String code;

        do {
            StringBuilder builder = new StringBuilder("HC-");

            for (int i = 0; i < 4; i++) {
                builder.append(characters.charAt(random.nextInt(characters.length())));
            }

            builder.append("-");

            for (int i = 0; i < 4; i++) {
                builder.append(characters.charAt(random.nextInt(characters.length())));
            }

            code = builder.toString();
        } while (usedCodes.contains(code));

        usedCodes.add(code);
        return code;
    }

    public void displaySchedules() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

        System.out.println("\n===== APPOINTMENT SCHEDULES =====");
        for (Map.Entry<LocalDate, Appointment[]> entry : schedules.entrySet()) {
            System.out.println("\nDate: " + entry.getKey().format(dateFormat));
            Appointment[] appointments = entry.getValue();
            boolean hasAppointments = false;

            for (Appointment appointment : appointments) {
                if (appointment != null) {
                    hasAppointments = true;
                    System.out.println("Patient: " + appointment.getPatient().getName()
                            + " | Time: " + appointment.getTimeRange()
                            + " | Status: " + appointment.getStatus());
                }
            }

            if (!hasAppointments) {
                System.out.println("No appointments.");
            }
        }
    }
}
