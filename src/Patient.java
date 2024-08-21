public class Patient extends Person {
    private String ID;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    //Constructor
    public Patient(String id, String name, String contact){
        super(name, contact);
        this.ID = id;
    }

    public char getPatientType(String id){
        this.ID = id;

        if(id.startsWith("T")){
            return 'T';
        }
        else {
            return 'D';
        }
    }
}
