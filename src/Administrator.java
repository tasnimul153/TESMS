import java.util.Date;

public class Administrator extends Person {
    public Administrator(int id, String name, Date dob, String contact, String password) {
        super(id, name, dob, contact, password);
        validate();
    }

    /*  Administrator er niomaboli/validation
     *  Check if the administrator ID is valid:
            The ID should be a positive integer.
     *  Check if the name is valid:
            The name should not be null or empty.
            The name should only contain alphabetic characters and spaces.
     *  Check if the date of birth (Dob) is valid:
            The Dob should not be null.
            The Dob should be a date in the past.
     *  Check if the contact information is valid:
            The contact information should not be null or empty.
            The contact information should be a valid email address.
    * */

    @Override
    protected boolean validate() {
        if (getId() <= 0) {
            OutputFormatter.errorText("Invalid administrator ID", "Administrator ID must be a positive integer");
            throw new IllegalArgumentException("Invalid administrator ID");
        }
        if (getName() == null || getName().isEmpty() || !getName().matches("[a-zA-Z ]+")) {
            OutputFormatter.errorText("Invalid name", "Name cannot be null or empty and should only contain alphabetic characters and spaces");
            throw new IllegalArgumentException("Invalid name");
        }
        if (getDob() == null || getDob().after(new Date())) {
            OutputFormatter.errorText("Invalid date of birth", "Date of birth cannot be null or in the future");
            throw new IllegalArgumentException("Invalid date of birth");
        }
        if (getContact() == null || getContact().isEmpty() || !getContact().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            OutputFormatter.errorText("Invalid contact information", "Contact information cannot be null or empty and should be a valid email address");
            throw new IllegalArgumentException("Invalid contact information");
        }
        return true;
    }
}