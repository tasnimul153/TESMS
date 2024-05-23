import java.util.Date;

public abstract class Person {
    private int Id;
    private String Name;
    private Date Dob;
    private String Contact;
    private String Password;

    // Constructor to initialize the person object
    public Person(int Id, String Name, Date Dob, String Contact, String Password) {
        setId(Id);
        setName(Name);
        setDob(Dob);
        setContact(Contact);
        setPassword(Password);
    }

    protected abstract boolean validate();

    // Getters and Setters
    public int getId() {
        return Id;
    }
    public String getName() {
        return Name;
    }
    public Date getDob() {
        return Dob;
    }
    public String getContact() {
        return Contact;
    }
    public void setId(int Id) {
        if (Id <= 0) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        this.Id = Id;
    }

    public void setName(String Name) {
        if (Name == null || Name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.Name = Name;
    }

    public void setDob(Date Dob) {
        if (Dob == null || Dob.after(new Date())) {
            throw new IllegalArgumentException("Date of Birth cannot be null or in the future");
        }
        this.Dob = Dob;
    }

    public void setContact(String Contact) {
        if (Contact == null || Contact.trim().isEmpty()) {
            throw new IllegalArgumentException("Contact cannot be null or empty");
        }
        this.Contact = Contact;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty() || password.length() < 5) {
            OutputFormatter.errorText("Invalid password", "Password cannot be null or empty and must be at least 5 characters long");
            throw new IllegalArgumentException("Password cannot be null or empty and must be at least 5 characters long");
        }

        if (!password.matches(".*[a-z].*")) {
            OutputFormatter.errorText("Invalid password", "Password must contain at least one small letter");
            throw new IllegalArgumentException("Password must contain at least one small letter");
        }

        if (!password.matches(".*[A-Z].*")) {
            OutputFormatter.errorText("Invalid password", "Password must contain at least one capital letter");
            throw new IllegalArgumentException("Password must contain at least one capital letter");
        }

        if (!password.matches(".*\\d.*")) {
            OutputFormatter.errorText("Invalid password", "Password must contain at least one number");
            throw new IllegalArgumentException("Password must contain at least one number");
        }

        this.Password = password.hashCode() + "";
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }
}
