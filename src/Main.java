import java.text.ParseException;
import java.util.Scanner;

public class Main {
    static final String[] userType = {"Student", "Teacher", "Administrator", "Exit"};

    public static void main(String[] args) throws ParseException {
        Scanner input = new Scanner(System.in);
        int loginAttempts = 3;
        while(true) {
            if (loginAttempts == 0) {
                OutputFormatter.errorText("Maximum login attempts exceeded. Try again later", "Goodbye!");
                break;
            } else {
                OutputFormatter.clearScreen();
                int choice = titleScreenHome(loginAttempts);

                if(choice < 1 || choice > userType.length) {
                    OutputFormatter.errorText("Invalid Choice", "Please try again");
                    continue;
                } else if (choice == 1) {
                    /*
                     *       Student Login
                     * */

                    titleScreenHome2(userType[0]);

                    int id = Integer.parseInt(OutputFormatter.inputTextLeft("Enter your Student ID: "));
                    String password = OutputFormatter.inputTextLeft("Enter your Password: ").hashCode() + "";

                    OutputFormatter.loadingScreen();

                    // check if the student exists
                    if(Authenticate.authenticate(id, password)) {
                        OutputFormatter.successText("Login Successful");
                        Student student = (Student) LocalDatabase.getUser(id);
                        StudentMenu.studentMenu(student);
                        continue;
                    } else {
                        OutputFormatter.errorText("Invalid Credentials", "Please try again");
                        loginAttempts--;
                    }
                } else if (choice == 2) {
                    /*
                     *       Teacher Login
                     * */

                    titleScreenHome2(userType[1]);

                    int id = Integer.parseInt(OutputFormatter.inputTextLeft("Enter your Teacher ID: "));
                    String password = OutputFormatter.inputTextLeft("Enter your Password: ").hashCode() + "";

                    OutputFormatter.loadingScreen();

                    // check if the teacher exists
                    if(Authenticate.authenticate(id, password)) {
                        OutputFormatter.successText("Login Successful");
                        Teacher teacher = (Teacher) LocalDatabase.getUser(id);
                        TeacherMenu.teacherMenu(teacher);
                    } else {
                        OutputFormatter.errorText("Invalid Credentials", "Please try again");
                        loginAttempts--;
                    }
                } else if (choice == 3) {
                    /*
                     *       Administrator Login
                     * */

                    titleScreenHome2(userType[2]);

                    int id = Integer.parseInt(OutputFormatter.inputTextLeft("Enter your Administrator ID: "));
                    String password = OutputFormatter.inputTextLeft("Enter your Password: ").hashCode() + "";

                    OutputFormatter.loadingScreen();

                    // check if the administrator exists
                    if(Authenticate.authenticate(id, password)) {
                        OutputFormatter.successText("Login Successful");
                        Administrator administrator = (Administrator) LocalDatabase.getUser(id);
                        AdministratorMenu.administratorMenu(administrator);
                    } else {
                        OutputFormatter.errorText("Invalid Credentials", "Please try again");
                        loginAttempts--;
                    }
                } else if (choice == 4){
                    OutputFormatter.clearScreen();
                    OutputFormatter.header("The Enhanced Student Management System");
                    OutputFormatter.textMiddle("Thank you for using the system");
                    OutputFormatter.blankLine();
                    OutputFormatter.textMiddle("Goodbye!");
                    OutputFormatter.blankLine();
                    OutputFormatter.line();
                    break;
                } else {
                    OutputFormatter.errorText("Invalid Choice", "Please try again");
                }
            }
        }
    }

    public static int titleScreenHome(int loginAttempts) {
        OutputFormatter.header("The Enhanced Student Management System");
        OutputFormatter.textLeft("Log in as:");
        if (loginAttempts < 3) {
            OutputFormatter.warningText("You have " + loginAttempts + " attempts remaining to login");
        }
        OutputFormatter.blankLine();
        OutputFormatter.orderedList(userType);
        OutputFormatter.blankLine();
        OutputFormatter.line();
        return Integer.parseInt(OutputFormatter.inputTextLeft("Choose an option: "));
    }

    public static void titleScreenHome2(String userType) {
        OutputFormatter.clearScreen();
        OutputFormatter.header("The Enhanced Student Management System");
        OutputFormatter.textLeft("Log In > " + userType.toUpperCase());
        OutputFormatter.blankLine();
    }
}