import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Scanner;
import java.util.Date;

public class AdministratorMenu {
    public static void administratorMenu(Administrator administrator) throws ParseException {
        Scanner input = new Scanner(System.in);
        while (true) {
            OutputFormatter.clearScreen();
            OutputFormatter.header("The Enhanced Student Management System", administrator, "Administrator");
            OutputFormatter.blankLine();
            OutputFormatter.orderedList(new String[]{
                    "View Profile",
                    "Edit Profile",
                    "Add User",
                    "View Users",
                    "Update User",
                    "Remove User",
                    "Settings",
                    "Logout"
            });
            OutputFormatter.blankLine();
            OutputFormatter.line();
            int choice = Integer.parseInt(OutputFormatter.inputTextLeft("Choose an option: "));

            switch (choice) {
                case 1: {
                    // View Profile
                    OutputFormatter.clearScreen();
                    OutputFormatter.header("The Enhanced Student Management System", administrator, "View Profile", "ADMINISTRATOR");
                    OutputFormatter.blankLine();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String dob = dateFormat.format(administrator.getDob());
                    OutputFormatter.unorderedList(new String[]{
                            "Name: " + administrator.getName(),
                            "Administrator ID: " + administrator.getId(),
                            "Date of Birth: " + dob,
                            "Contact: " + administrator.getContact()
                    });
                    OutputFormatter.blankLine();
                    OutputFormatter.blankLine();
                    OutputFormatter.textMiddle("Press Enter to continue");
                    OutputFormatter.line();
                    // wait for press enter to continue
                    input.nextLine();
                    break;
                }
                case 2: {
                    // Edit Profile
                    OutputFormatter.clearScreen();
                    OutputFormatter.header("The Enhanced Student Management System", administrator, "Edit Profile", "ADMINISTRATOR");
                    OutputFormatter.blankLine();
                    String newName = OutputFormatter.inputTextLeft("Enter new name: ");
                    administrator.setName(newName);
                    OutputFormatter.successText("Profile Updated Successfully");
                    break;
                }
                case 3: {
                    // Add User
                    OutputFormatter.clearScreen();
                    OutputFormatter.header("The Enhanced Student Management System", administrator, "Add User", "ADMINISTRATOR");
                    OutputFormatter.blankLine();
                    String[] userTypes = {"Student", "Teacher", "Administrator", "Go Back"};
                    OutputFormatter.orderedList(userTypes);
                    OutputFormatter.blankLine();
                    OutputFormatter.line();
                    String st = OutputFormatter.inputTextLeft("Choose a user type to add: ");
                    // if st is not a number
                    if (!st.matches("\\d+")) {
                        OutputFormatter.errorText("Invalid choice. Please try again.");
                        break;
                    }
                    int userTypeChoice = Integer.parseInt(st);
                    if (userTypeChoice == 4) {
                        break;
                    } else if (userTypeChoice < 1 || userTypeChoice > 3) {
                        OutputFormatter.errorText("Invalid choice. Please try again.");
                        break;
                    }
                    OutputFormatter.blankLine();
                    int id = Integer.parseInt(OutputFormatter.inputTextLeft("Enter ID: "));
                    String name = OutputFormatter.inputTextLeft("Enter Name: ");
                    String dob = OutputFormatter.inputTextLeft("Enter Date of Birth (dd/MM/yyyy): ");
                    // dob to date object no formatting require
                    Date dobDate = new Date(dob);
                    String contact = OutputFormatter.inputTextLeft("Enter Contact: ");
                    String password = OutputFormatter.inputTextLeft("Enter Password: ");
                    switch (userTypeChoice) {
                        case 1: {
                            // Add Student
                            int enrollment = Integer.parseInt(OutputFormatter.inputTextLeft("Enter Enrollment: "));
                            Student newStudent = new Student(id, name, dobDate, contact, password, enrollment);
                            LocalDatabase.addUser(newStudent);
                            OutputFormatter.successText("Student Added Successfully");
                            break;
                        }
                        case 2: {
                            // Add Teacher
                            int employeeId = Integer.parseInt(OutputFormatter.inputTextLeft("Enter Employee ID: "));
                            String department = OutputFormatter.inputTextLeft("Enter Department: ");
                            Teacher newTeacher = new Teacher(id, name, dobDate, contact, password, employeeId, department);
                            LocalDatabase.addUser(newTeacher);
                            OutputFormatter.successText("Teacher Added Successfully");
                            break;
                        }
                        case 3: {
                            // Add Administrator
                            Administrator newAdmin = new Administrator(id, name, dobDate, contact, password);
                            LocalDatabase.addUser(newAdmin);
                            OutputFormatter.successText("Administrator Added Successfully");
                            break;
                        }
                        default:
                            OutputFormatter.errorText("Invalid choice. Please try again.");
                    }
                    break;
                }
                case 4: {
                    // View Users
                    OutputFormatter.clearScreen();
                    OutputFormatter.header("The Enhanced Student Management System", administrator, "View Users", "ADMINISTRATOR");
                    OutputFormatter.blankLine();
                    for (Map.Entry<Integer, Person> entry : LocalDatabase.users.entrySet()) {
                        OutputFormatter.twoColumnText("ID: " + entry.getKey() + " Name: " + entry.getValue().getName(), "Type: " + entry.getValue().getType());
                    }
                    OutputFormatter.blankLine();
                    OutputFormatter.textMiddle("Press Enter to continue");
                    OutputFormatter.line();
                    // wait for press enter to continue
                    input.nextLine();
                    break;
                }
                case 5: {
                    // Update User
                    OutputFormatter.clearScreen();
                    OutputFormatter.header("The Enhanced Student Management System", administrator, "Update User", "ADMINISTRATOR");
                    OutputFormatter.blankLine();
                    for (Map.Entry<Integer, Person> entry : LocalDatabase.users.entrySet()) {
                        OutputFormatter.twoColumnText("ID: " + entry.getKey() + " Name: " + entry.getValue().getName(), "Type: " + entry.getValue().getType());
                    }
                    OutputFormatter.line();
                    int id = Integer.parseInt(OutputFormatter.inputTextLeft("Enter the ID of the user to update: "));
                    if (LocalDatabase.userExists(id)) {
                        Person user = LocalDatabase.getUser(id);
                        String newName = OutputFormatter.inputTextLeft("Enter new name: ");
                        user.setName(newName);
                        LocalDatabase.updateUser(user);
                        OutputFormatter.successText("User Updated Successfully");
                    } else {
                        OutputFormatter.errorText("User not found. Please try again.");
                    }
                    break;
                }
                case 6: {
                    // Remove User
                    OutputFormatter.clearScreen();
                    OutputFormatter.header("The Enhanced Student Management System", administrator, "Remove User", "ADMINISTRATOR");
                    OutputFormatter.blankLine();
                    for (Map.Entry<Integer, Person> entry : LocalDatabase.users.entrySet()) {
                        OutputFormatter.twoColumnText("ID: " + entry.getKey() + " Name: " + entry.getValue().getName(), "Type: " + entry.getValue().getType());
                    }
                    OutputFormatter.line();
                    int id = Integer.parseInt(OutputFormatter.inputTextLeft("Enter the ID of the user to remove: "));
                    if (LocalDatabase.userExists(id)) {
                        LocalDatabase.removeUser(id);
                        OutputFormatter.successText("User Removed Successfully");
                    } else {
                        OutputFormatter.errorText("User not found. Please try again.");
                    }
                    break;
                }
                case 7: {
                    // Settings
                    OutputFormatter.clearScreen();
                    OutputFormatter.header("The Enhanced Student Management System", administrator, "Settings", "ADMINISTRATOR");
                    OutputFormatter.blankLine();
                    OutputFormatter.orderedList(new String[]{
                            "Change Maximum Students",
                            "Change Maximum Teachers",
                            "Change Maximum Administrators",
                            "Change Maximum Enrollments",
                            "Change Display Length",
                            "Go Back"
                    });
                    OutputFormatter.blankLine();
                    OutputFormatter.line();
                    int settingsChoice = Integer.parseInt(OutputFormatter.inputTextLeft("Choose an option: "));
                    switch (settingsChoice) {
                        case 1: {
                            // Change Maximum Students
                            int newMaxStudents = Integer.parseInt(OutputFormatter.inputTextLeft("Enter new maximum students: "));
                            Settings.setMAX_STUDENTS(newMaxStudents);
                            OutputFormatter.successText("Maximum students updated successfully");
                            break;
                        }
                        case 2: {
                            // Change Maximum Teachers
                            int newMaxTeachers = Integer.parseInt(OutputFormatter.inputTextLeft("Enter new maximum teachers: "));
                            Settings.setMAX_TEACHERS(newMaxTeachers);
                            OutputFormatter.successText("Maximum teachers updated successfully");
                            break;
                        }
                        case 3: {
                            // Change Maximum Administrators
                            int newMaxAdmins = Integer.parseInt(OutputFormatter.inputTextLeft("Enter new maximum administrators: "));
                            Settings.setMAX_ADMINISTRATORS(newMaxAdmins);
                            OutputFormatter.successText("Maximum administrators updated successfully");
                            break;
                        }
                        case 4: {
                            // Change Maximum Enrollments
                            int newMaxEnrollments = Integer.parseInt(OutputFormatter.inputTextLeft("Enter new maximum enrollments: "));
                            Settings.setMAX_ENROLLMENTS(newMaxEnrollments);
                            OutputFormatter.successText("Maximum enrollments updated successfully");
                            break;
                        }
                        case 5: {
                            // Change Display Length
                            int newDisplayLength = Integer.parseInt(OutputFormatter.inputTextLeft("Enter new display length: "));
                            Settings.setDISPLAY_LENGTH(newDisplayLength);
                            OutputFormatter.successText("Display length updated successfully");
                            break;
                        }
                        case 6: {
                            // Go Back
                            break;
                        }
                        default:
                            OutputFormatter.errorText("Invalid choice. Please try again.");
                    }
                    break;
                }
                case 8: {
                    // Logout
                    OutputFormatter.clearScreen();
                    OutputFormatter.header("The Enhanced Student Management System", administrator, "Logout", "ADMINISTRATOR");
                    OutputFormatter.blankLine();
                    OutputFormatter.successText("You have been logged out successfully.");
                    OutputFormatter.blankLine();
                    OutputFormatter.line();
                    OutputFormatter.autoPause(1000);
                    break;
                }
                default:
                    OutputFormatter.errorText("Invalid choice. Please try again.");
            }
            if (choice == 8) {
                break;
            }
        }
    }
}