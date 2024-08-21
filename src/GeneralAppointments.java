import java.util.Date;

public class GeneralAppointments extends Appointment{

    private String patientNotes;

    public GeneralAppointments(Doctor doctor, Patient patient, String notes, Date date, String time){
        super(doctor, patient, date, time);
        this.patientNotes = notes;
    }

    public String getPatientNotes() {
        return patientNotes;
    }

    public void setPatientNotes(String patientNotes) {
        this.patientNotes = patientNotes;
    }
}
