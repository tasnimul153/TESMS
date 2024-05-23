import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentMenu {
    public static void studentMenu(Student student) {
        Scanner input = new Scanner(System.in);
        String[] courses = LocalDatabase.courses.keySet().toArray(new String[0]);
        String[] courseNames = new String[LocalDatabase.courses.size()];
        String[] courseInstructors = new String[LocalDatabase.courses.size()];
        while (true) {
            OutputFormatter.clearScreen();
            OutputFormatter.header("The Enhanced Student Management System", student, "Student");
            OutputFormatter.blankLine();
            OutputFormatter.orderedList(new String[] {
                    "View Profile Info",
                    "Edit Profile",
                    "Register for a course",
                    "My Courses",
                    "My Grades & Attendances",
                    "Logout"
            });
            OutputFormatter.blankLine();
            OutputFormatter.line();
            int choice = Integer.parseInt(OutputFormatter.inputTextLeft("Choose an option: "));

            switch (choice) {
                case 1: {
                    /*
                     *       View Profile Info
                     * */

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String dob = dateFormat.format(student.getDob());
                    OutputFormatter.clearScreen();
                    OutputFormatter.header("The Enhanced Student Management System", student, "View Profile Info", "STUDENT");
                    OutputFormatter.blankLine();
                    OutputFormatter.unorderedList(new String[]{
                            "Name: " + student.getName(),
                            "Enrollment: " + student.getEnrollment(),
                            "Date of Birth: " + dob,
                            "Contact: " + student.getContact()
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
                    /*
                     *       Edit Profile
                     * */
                    OutputFormatter.clearScreen();
                    OutputFormatter.header("The Enhanced Student Management System", student, "Edit Profile", "STUDENT");
                    OutputFormatter.blankLine();
                    String newName = OutputFormatter.inputTextLeft("Enter new name: ");
                    student.setName(newName);
                    OutputFormatter.successText("Profile Updated Successfully");
                    break;
                }
                case 3: {
                    /*
                     *       Register for a course
                     * */
                    OutputFormatter.clearScreen();
                    OutputFormatter.header("The Enhanced Student Management System", student, "Register", "INSTRUCTORS");
                    OutputFormatter.blankLine();


                    int i = 1;
                    for(Map.Entry<String, Map<String, Teacher>> entry : LocalDatabase.courses.entrySet()) {
                        String courseId = entry.getKey();
                        for(Map.Entry<String, Teacher> course : entry.getValue().entrySet()) {
                            courseNames[i-1] = course.getKey();
                            courseInstructors[i-1] = course.getValue().getName();
                        }
                        OutputFormatter.twoColumnText(i++ + ". " + courseId + ": " + courseNames[i-2], courseInstructors[i-2]);
                    }

                    OutputFormatter.blankLine();
                    OutputFormatter.line();
                    System.out.println();
                    String chosenCourse = OutputFormatter.inputTextLeft("Choose a course: ");
                    // Check if the student has already registered for 3 courses
                    if (student.getCourses().size() >= 3) {
                        OutputFormatter.errorText("You have already registered for 3 courses.");
                        break;
                    }
                    // If the student has not registered for 3 courses or didn't take this course before
                    // then add the chosen course to the student's list of courses
                    if (!student.getCourses().contains(courses[Integer.parseInt(chosenCourse)-1])) {
                        student.getCourses().add(courses[Integer.parseInt(chosenCourse)-1]);
                        OutputFormatter.successText("You have successfully registered for the course " + courses[Integer.parseInt(chosenCourse)-1] + ".");
                    } else {
                        OutputFormatter.errorText("You have already registered for " + courses[Integer.parseInt(chosenCourse)-1] + " course.");
                    }
                }
                case 4: {
                    /*
                     *       My Courses
                     * */
                    OutputFormatter.clearScreen();
                    OutputFormatter.header("The Enhanced Student Management System", student, "My Courses", "STUDENT");
                    OutputFormatter.blankLine();
                    if (student.getCourses().isEmpty()) {
                        OutputFormatter.errorText("No registered courses.");
                    } else {
                        //OutputFormatter.orderedList(student.getCourses().toArray(new String[0]));
                        int i = 1;
                        for(Map.Entry<String, Map<String, Teacher>> entry : LocalDatabase.courses.entrySet()) {
                            for(Map.Entry<String, Teacher> course : entry.getValue().entrySet()) {
                                courseNames[i-1] = course.getKey();
                                courseInstructors[i-1] = course.getValue().getName();
                            }
                        }
                        for(i = 0; i < student.getCourses().size(); i++) {
                            for(int j = 0; j < courses.length; j++) {
                                if(student.getCourses().get(i).equals(courses[j])) {
                                    OutputFormatter.twoColumnText((i+1) + ". " + student.getCourses().get(i) + " - " + courseNames[j], "Instructor: " + courseInstructors[j]);
                                }
                            }
                        }
                    }
                    OutputFormatter.blankLine();
                    OutputFormatter.blankLine();
                    OutputFormatter.textMiddle("Press Enter to continue");
                    OutputFormatter.line();
                    // wait for press enter to continue
                    input.nextLine();
                    break;
                }
                case 5: {
                    /*
                     *       My grades & attendance
                     * */
                    OutputFormatter.clearScreen();
                    OutputFormatter.header("The Enhanced Student Management System", student, "My Grades & Attendances", "STUDENT");
                    OutputFormatter.blankLine();
                    OutputFormatter.textLeft("Grades:");
                    OutputFormatter.blankLine();
                    for (String course : student.getCourses()) {
                        String date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
                        String gradeKey = student.getId() + "-" + course + "-" + date;
                        String grade;
                        try {
                            grade = LocalDatabase.grades.get(gradeKey).getOrDefault(date, "Not graded");
                        } catch (Exception e) {
                            grade = "Not graded";
                        }
                        OutputFormatter.twoColumnText("Course: " + course, "Grade: " + grade);
                    }
                    OutputFormatter.blankLine();
                    OutputFormatter.blankLine();
                    OutputFormatter.line();
                    OutputFormatter.textLeft("Attendance:");
                    OutputFormatter.blankLine();
                    for (String course : student.getCourses()) {
                        String attendanceKey = student.getId() + "-" + course + "-" + new SimpleDateFormat("MM-dd-yyyy").format(new Date());
                        Map<String, Boolean> attendanceRecord = LocalDatabase.attendaces.get(attendanceKey);
                        if (attendanceRecord != null) {
                            for (Map.Entry<String, Boolean> entry : attendanceRecord.entrySet()) {
                                String date = entry.getKey();
                                String isPresent = entry.getValue() ? "Present" : "Absent";
                                OutputFormatter.twoColumnText("Course: " + course + " [" + date + "]", "Attendance: " + isPresent);
                            }
                        } else {
                            OutputFormatter.twoColumnText("Course: " + course, "Attendance: No record");
                        }
                    }
                    OutputFormatter.blankLine();
                    OutputFormatter.textMiddle("Press Enter to continue");
                    OutputFormatter.line();
                    // wait for press enter to continue
                    input.nextLine();
                    break;
                }
                case 6: {
                    /*
                     *       Logout
                     * */
                    OutputFormatter.clearScreen();
                    OutputFormatter.header("The Enhanced Student Management System", student, "Logout", "STUDENT");
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
            if (choice == 6) {
                break;
            }
        }
    }

    public static void displayProfile(Student student) {
        OutputFormatter.clearScreen();
        OutputFormatter.header("Student Profile");
        OutputFormatter.blankLine();
        OutputFormatter.textLeft(student.toString());
        OutputFormatter.blankLine();
        OutputFormatter.autoPause(2000);
    }
}