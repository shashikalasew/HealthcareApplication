public abstract class Person {

    private String Name;
    private String Contact;

    public Person(String name, String contact){
        this.Name = name;
        this.Contact = contact;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        this.Contact = contact;
    }
}
