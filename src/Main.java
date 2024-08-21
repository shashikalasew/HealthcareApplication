import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean MainExit = false;
        while (!MainExit) {
            System.out.println("If you are a hospital administrator please press 1, If you are a patient please press 2, Press 3 to exit");
            int MainNum = scanner.nextInt();

            if (MainNum == 1) {
                administrator();
            } else if (MainNum == 2) {
                patient();
            } else if (MainNum == 3) {
                MainExit = true;
            } else {
                System.out.println("Invalid Input");
            }
        }

        /*
        Doctor Ishara = new Doctor();
        System.out.println(Ishara.isPhysician("NeuroPhysician"));

        Patient Shashi = new Patient();
        System.out.println(Shashi.getPatientType("D-001"));
        */
        }

    //Administrator menu
    public static void administrator(){
        Scanner scanner = new Scanner(System.in);

        boolean SubExit1 = false;
        while(!SubExit1) {
            System.out.println("Press 1 to add a doctor, Press 2 to add a doctor availability, and Press 3 to exit");

            int Num1 = scanner.nextInt();
            switch (Num1) {
                case 1:
                    Controller.addDoctor();
                    break;
                case 2:
                    Controller.addAvailability();
                    break;
                case 3:
                    SubExit1 = true;
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    //Patient menu
    public static void patient(){
        Scanner scanner = new Scanner(System.in);

        boolean SubExit2 = false;
        while(!SubExit2) {
            System.out.println("Press 1 to view doctors, Press 2 to book an appointment, Press 3 to view a selected doctor's bookings, Press 4 to register patient and Press 5 to exit ");

            int Num2 = scanner.nextInt();
            switch (Num2) {
                case 1:
                    Controller.viewAllDoctors();
                    break;
                case 2:
                    Controller.makeAppointment();
                    break;
                case 3:
                    Controller.viewDoctorBookings();
                    break;
                case 4:
                    Controller.addPatient();
                    break;
                case 5:
                    SubExit2 = true;
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }
    }