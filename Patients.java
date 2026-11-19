
public class Patient extends User {
    private int age;
    private String contactNumber;
    private String sex;

    public Patient(String name, int age, String contactNumber, String sex) {
        super(name);
        this.age = age;
        this.contactNumber = contactNumber;
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public void displayMenu() {
        System.out.println("Guest patient access.");
    }
}
