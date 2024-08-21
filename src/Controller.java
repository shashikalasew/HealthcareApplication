import java.util.*;

public class Controller {

    //empty ArrayList
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Patient> patients = new ArrayList<>();
    static int NUMBER_OF_SLOTS = 5;

    //adding a new doctor
    public static void addDoctor(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter doctor's ID");
        int ID = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter doctor's name");
        String Name = scanner.nextLine();

        System.out.println("Enter doctor's birthday(yyyy-mm-dd)");
        String birthday = scanner.nextLine();
        Date Birthday = java.sql.Date.valueOf(birthday);

        System.out.println("Enter doctor's specialization");
        String Specialization = scanner.nextLine();

        System.out.println("Enter doctor's contact number");
        String Contact = scanner.nextLine();

        Doctor doc = new Doctor(ID, Name, Birthday, Specialization, Contact);
        doctors.add(doc);
        System.out.println("Doctor added successfully!\n");
    }

    //view all doctors
    public static void viewAllDoctors(){
        if(doctors.isEmpty()){
            System.out.println("No Doctors Available at this moment");
        }
        else{
            for(Doctor doctor:doctors){
                System.out.println("Doctor's Name: " + doctor.getName() + ", Specialization: " + doctor.getSpecialization());
            }
        }
    }

    //Adding doctor availability
    public static void addAvailability(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the doctor ID you want to add availability: ");
        int ID = scanner.nextInt();
        scanner.nextLine();

        Doctor selectedDoc = null;
        for(Doctor doc:doctors){
            if(doc.getID() == ID){
                selectedDoc = doc;
            }
        }

        if(selectedDoc == null){
            System.out.println("Doctor not found!");
            return;
        }

        System.out.println("Enter doctor's available date");
        String availability = scanner.nextLine();
        Date AvailableDate = java.sql.Date.valueOf(availability);

        selectedDoc.availabilities.add(AvailableDate);
        System.out.println("Availability added successfully..\n");

    }

    //adding a new doctor
    public static void addPatient(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter patient's ID");
        String ID = scanner.nextLine();

        System.out.println("Enter patient's name");
        String Name = scanner.nextLine();

        System.out.println("Enter patient's contact number");
        String Contact = scanner.nextLine();

        Patient patient = new Patient(ID, Name, Contact);
        patients.add(patient);
        System.out.println("Patient added successfully!\n");
    }

    //Book an appointment
    public static void makeAppointment(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter doctor's ID you want to make an appointment: ");
        int ID = scanner.nextInt();
        scanner.nextLine();

        Doctor selectedDoc = null;
        for(Doctor doc:doctors){
            if(doc.getID() == ID){
                selectedDoc = doc;
            }
        }

        if(selectedDoc == null){
            System.out.println("Doctor not found!");
            return;
        }

        System.out.println("Enter the patient ID: ");
        String Id = scanner.nextLine();

        Patient selectedPatient = null;
        for(Patient patient:patients){
            if(Objects.equals(patient.getID(), Id)){
                selectedPatient = patient;
            }
        }

        if(selectedPatient == null){
            System.out.println("Patient not found!");
            return;
        }

        System.out.println("Enter appointment date");
        String appointmentDate = scanner.nextLine();
        Date AppointmenteDate = java.sql.Date.valueOf(appointmentDate);

        Boolean isAvailable = checkAvailability(selectedDoc, AppointmenteDate);
        if(!isAvailable){
            System.out.println("Doctor is not available on the selected date!");
            return;
        }

        String appointmentTime = getTimeForBooking(selectedDoc, AppointmenteDate);
        if (appointmentTime == null){
            System.out.println("All the slots are filled on this day!");
            return;
        }

        System.out.println("Enter appointment type: (Press G for General R for Referral");
        char appointmentType = scanner.next().charAt(0);
        scanner.nextLine();
        Appointment appointment;
        if(appointmentType == 'R' || appointmentType == 'r'){
            System.out.println("Enter referral doctor's ID: ");
            int refDocID = scanner.nextInt();
            scanner.nextLine();

            Doctor referraldDoctor = null;
            for(Doctor doc:doctors){
                if(doc.getID() == refDocID){
                    referraldDoctor = doc;
                }
            }

            if(referraldDoctor == null){
                System.out.println("Referral Doctor not found!");
                return;
            }

            System.out.println("Leave a note");
            String note = scanner.nextLine();

            appointment = new ReferralAppointments(selectedDoc, selectedPatient, AppointmenteDate, appointmentTime, referraldDoctor, note);

            System.out.println("Enter referral doctor's note: ");
            String referralDocNote = scanner.nextLine();
            ((ReferralAppointments) appointment).setReferralDoctorNotes(referralDocNote);

        } else if (appointmentType == 'G' || appointmentType == 'g') {
            System.out.println("Leave a note");
            String note = scanner.nextLine();

            appointment = new GeneralAppointments(selectedDoc, selectedPatient, note, AppointmenteDate, appointmentTime);
        }
        else {
            System.out.println("Invalid appointment type!");
            return;
        }

        selectedDoc.setAppointment(appointment, AppointmenteDate);
        System.out.println("Appointment added successfully..\n");
        System.out.println("Appointment Date: " + AppointmenteDate + "  Appointment Time: " + appointmentTime);

    }

    //Helping method 1 for makeAppointment() method
    private static boolean checkAvailability(Doctor selectedDoctor, Date dateOfBooking){
        for (Date day : selectedDoctor.availabilities){
             if (day.equals(dateOfBooking)){
                 return true;
             }
        }
        return false;
    }

    //Helping method 2 for makeAppointment() method
    private static String getTimeForBooking(Doctor selectedDoctor, Date dateOfBooking){
        for (Map.Entry<Date, ArrayList<Appointment>> appointment : selectedDoctor.allAppointments.entrySet()){
            if (appointment.getKey().equals(dateOfBooking)){
                int numberOfSlots = appointment.getValue().size();
                if (numberOfSlots > NUMBER_OF_SLOTS-1 ){
                    return null;
                }
                System.out.println("We can make a booking..");
                int time = 17 + appointment.getValue().size();
                String setTime = time + " : 00";
                return setTime;
            }
        }
        return "17 : 00";
    }


    //view selected doctor's bookings
    public static void viewDoctorBookings(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter doctor's ID you want to view bookings: ");
        int ID = scanner.nextInt();
        scanner.nextLine();

        Doctor selectedDoc = null;
        for(Doctor doc:doctors){
            if(doc.getID() == ID){
                selectedDoc = doc;
            }
        }

        if(selectedDoc == null){
            System.out.println("Doctor not found!");
            return;
        }

        HashMap<Date, ArrayList<Appointment>> AllAppointments = selectedDoc.allAppointments;
        if(AllAppointments.isEmpty()){
            System.out.println("No bookings found for this doctor");
        }
        else{
            System.out.println("Appointments for Doctor ID " + ID + ":");
            for(Map.Entry<Date, ArrayList<Appointment>> allAppointments : AllAppointments.entrySet()){
                Date date = allAppointments.getKey();
                ArrayList<Appointment> appointments = allAppointments.getValue();
                for(Appointment appointment: appointments){
                    System.out.println("Patient: " + appointment.getPatient().getName() + ", Date: " + date + ", Time: " + appointment.getTime());
                }
            }
        }
    }
}
