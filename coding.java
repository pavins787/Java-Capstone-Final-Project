
import java.util.ArrayList;
import java.util.Scanner;

public class HospitalPatientSystem {

    static class Patient {
        int patientId;
        String name;
        int age;
        String gender;
        String phone;
        String doctor;
        String date;
        String time;

        Patient(int patientId, String name, int age,
                String gender, String phone, String doctor,
                String date, String time) {
            this.patientId = patientId;
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.phone = phone;
            this.doctor = doctor;
            this.date = date;
            this.time = time;
        }

        void display() {
            System.out.println("-----------------------------------");
            System.out.println("Patient ID : " + patientId);
            System.out.println("Name       : " + name);
            System.out.println("Age        : " + age);
            System.out.println("Gender     : " + gender);
            System.out.println("Phone      : " + phone);
            System.out.println("Doctor     : " + doctor);
            System.out.println("Date       : " + date);
            System.out.println("Time       : " + time);
            System.out.println("-----------------------------------");
        }
    }

    static ArrayList<Patient> patients = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int nextPatientId = 1001;

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n========================================");
            System.out.println(" HOSPITAL PATIENT REGISTRATION SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Register Patient and Book Appointment");
            System.out.println("2. View All Patients");
            System.out.println("3. Search Patient by ID");
            System.out.println("4. Cancel Appointment");
            System.out.println("5. Exit");
            System.out.println("========================================");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    registerPatient();
                    break;

                case 2:
                    viewAllPatients();
                    break;

                case 3:
                    searchPatient();
                    break;

                case 4:
                    cancelAppointment();
                    break;

                case 5:
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);

        sc.close();
    }

    static int readInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            sc.next();
        }

        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    static void registerPatient() {

        System.out.println("\n--- PATIENT REGISTRATION ---");

        System.out.print("Enter patient name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter age: ");
        int age = readInt();

        if (age < 0 || age > 130) {
            System.out.println("Please enter a valid age.");
            return;
        }

        System.out.print("Enter gender: ");
        String gender = sc.nextLine().trim();

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine().trim();

        System.out.println("\nAvailable Doctors:");
        System.out.println("1. Dr. Kumar - General Physician");
        System.out.println("2. Dr. Priya - Cardiologist");
        System.out.println("3. Dr. Arun - Orthopedic");
        System.out.println("4. Dr. Divya - Dermatologist");

        System.out.print("Select doctor (1-4): ");
        int doctorChoice = readInt();

        String doctor;

        switch (doctorChoice) {
            case 1:
                doctor = "Dr. Kumar - General Physician";
                break;
            case 2:
                doctor = "Dr. Priya - Cardiologist";
                break;
            case 3:
                doctor = "Dr. Arun - Orthopedic";
                break;
            case 4:
                doctor = "Dr. Divya - Dermatologist";
                break;
            default:
                System.out.println("Invalid doctor selection.");
                return;
        }

        System.out.print("Enter appointment date (DD-MM-YYYY): ");
        String date = sc.nextLine().trim();

        System.out.print("Enter appointment time (e.g., 10:30 AM): ");
        String time = sc.nextLine().trim();

        // Prevent duplicate appointments for the same doctor,
        // date, and time.
        for (Patient p : patients) {
            if (p.doctor.equals(doctor)
                    && p.date.equalsIgnoreCase(date)
                    && p.time.equalsIgnoreCase(time)) {
                System.out.println(
                    "This doctor already has an appointment at that date and time."
                );
                return;
            }
        }

        Patient patient = new Patient(
            nextPatientId++, name, age, gender, phone,
            doctor, date, time
        );

        patients.add(patient);

        System.out.println("\nAppointment booked successfully!");
        patient.display();
    }

    static void viewAllPatients() {

        System.out.println("\n--- ALL REGISTERED PATIENTS ---");

        if (patients.isEmpty()) {
            System.out.println("No patients registered yet.");
            return;
        }

        for (Patient patient : patients) {
            patient.display();
        }
    }

    static void searchPatient() {

        System.out.print("Enter Patient ID: ");
        int id = readInt();

        for (Patient patient : patients) {
            if (patient.patientId == id) {
                System.out.println("\nPatient found:");
                patient.display();
                return;
            }
        }

        System.out.println("Patient ID not found.");
    }

    static void cancelAppointment() {

        System.out.print("Enter Patient ID to cancel appointment: ");
        int id = readInt();

        for (Patient patient : patients) {
            if (patient.patientId == id) {
                patients.remove(patient);
                System.out.println("Appointment cancelled successfully.");
                return;
            }
        }

        System.out.println("Patient ID not found.");
    }
}
