import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Doctor extends Person {
    private int ID;
    private Date Birthday;
    private String Specialization;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }


    //Arraylist to store doctor available dates
    ArrayList<Date> availabilities = new ArrayList<>();

    //HashMap to store doctor's appointments
    HashMap<Date, ArrayList<Appointment>> allAppointments = new HashMap<>();

    //Constructor
    public Doctor(int id, String name, Date birthday, String specialization, String contact){
        super(name, contact);
        this.ID = id;
        this.Birthday = birthday;
        this.Specialization = specialization;
    }

    public boolean isPhysician(String Specialization){
        this.Specialization = Specialization;
        if(Specialization.endsWith("Physician")){
            return true;
        }
        else{
            return false;
        }
    }

    public void setAppointment(Appointment appointment, Date date){
        ArrayList<Appointment> currentAppointments = this.allAppointments.get(date);
        if(currentAppointments == null){
            ArrayList<Appointment> tempArrayList = new ArrayList<>();
            tempArrayList.add(appointment);
            this.allAppointments.put(date, tempArrayList);
        }
        else{
            currentAppointments.add(appointment);
            this.allAppointments.put(date, currentAppointments);
        }
    }

}
