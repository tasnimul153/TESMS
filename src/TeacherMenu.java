import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TeacherMenu {
    static Map <Integer, String> courseOption = new HashMap<>();
    public static int displayCurrentCourses(Teacher teacher) {
        int i = 1;
        for (Map.Entry<String, Map<String, Teacher>> entry : LocalDatabase.courses.entrySet()) {
            for (Map.Entry<String, Teacher> course : entry.getValue().entrySet()) {
                if (course.getValue().getId() == teacher.getId()) {
                    OutputFormatter.textLeft(i + ". " + entry.getKey() + ": " + course.getKey());
                    courseOption.put(i++, entry.getKey());
                }
            }
        }
        OutputFormatter.textLeft("0. Go back");
        return i-1;
    }
    public static void teacherMenu(Teacher teacher) throws ParseException {
        Scanner input = new Scanner(System.in);
        while (true) {
            OutputFormatter.clearScreen();
            OutputFormatter.header("The Enhanced Student Management System", teacher, "TEACHER");
            OutputFormatter.blankLine();
            OutputFormatter.orderedList(new String[]{"View Profile", "Edit Profile", "Current Courses", "Take Attendance", "Provide Grade", "Logout"});
            OutputFormatter.blankLine();
            OutputFormatter.line();
            OutputFormatter.optionTitleLeft("Choose an option: ");

            int choice = Integer.parseInt(input.nextLine());

            switch (choice) {
                case 1:
                    // View Profile
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String dob = dateFormat.format(teacher.getDob());
                    OutputFormatter.clearScreen();
                    OutputFormatter.header("The Enhanced Student Management System", teacher, "View Profile", "TEACHER");
                    OutputFormatter.blankLine();
                    OutputFormatter.unorderedList( new String[] {
                            "Name: " + teacher.getName(),
                            "Employee ID: " + teacher.getId(),
                            "Date of Birth: " + dob,
                            "Contact: " + teacher.getContact()
                    });
                    OutputFormatter.blankLine();
                    OutputFormatter.blankLine();
                    OutputFormatter.textMiddle("Press Enter to continue");
                    OutputFormatter.line();
                    // wait for press enter to continue
                    input.nextLine();
                    break;
                case 2:
                    // Edit Profile
                    OutputFormatter.clearScreen();
                    OutputFormatter.header("The Enhanced Student Management System", teacher, "Edit Profile", "TEACHER");
                    OutputFormatter.blankLine();
                    String newName = OutputFormatter.inputTextLeft("Enter new name: ");
                    teacher.setName(newName);
                    OutputFormatter.successText("Profile Updated Successfully");
                    break;
                case 3:
                    // Current Courses
                    while(true) {
                        OutputFormatter.clearScreen();
                        OutputFormatter.header("The Enhanced Student Management System", teacher, "Current Courses", "TEACHER");
                        OutputFormatter.blankLine();
                        displayCurrentCourses(teacher);
                        OutputFormatter.blankLine();
                        OutputFormatter.line();
                        int chosenCourseIndex = Integer.parseInt(OutputFormatter.inputTextLeft("Press 0 and Enter: "));
                        if (chosenCourseIndex == 0) { break; } else { OutputFormatter.errorText("Invalid Input"); }
                    }
                    break;
                case 4:
                    while(true) {
                        // Take Attendance
                        OutputFormatter.clearScreen();
                        OutputFormatter.header("The Enhanced Student Management System", teacher, "Take Attendance", "TEACHER");
                        OutputFormatter.blankLine();

                        // Display current courses taken by the teacher
                        int i = displayCurrentCourses(teacher);
                        OutputFormatter.blankLine();
                        OutputFormatter.line();
                        int chosenCourseIndex = Integer.parseInt(OutputFormatter.inputTextLeft("Choose a course: "));
                        String chosenCourseId = courseOption.get(chosenCourseIndex);

                        if (chosenCourseIndex == 0) { break; }
                        if (chosenCourseIndex > i || chosenCourseIndex < 0) {
                            OutputFormatter.errorText("Invalid choice. Please try again.");
                            continue;
                        }

                        // Give attendace to the student for the chosen course
                        while(true) {
                            OutputFormatter.clearScreen();
                            OutputFormatter.header("The Enhanced Student Management System", teacher, "Take Attendance", "TEACHER");
                            OutputFormatter.blankLine();
                            OutputFormatter.blankLine();
                            OutputFormatter.textMiddle("Students registered for " + chosenCourseId + ":");
                            OutputFormatter.line();
                            OutputFormatter.twoColumnText("Student", "Attendance");
                            OutputFormatter.line();
                            OutputFormatter.blankLine();

                            // Display students registered for the chosen course
                            i = 1;
                            for (Map.Entry<Integer, Person> entry : LocalDatabase.users.entrySet()) {
                                if (entry.getValue() instanceof Student) {
                                    Student student = (Student) entry.getValue();
                                    if (student.getCourses().contains(chosenCourseId)) {
                                        String formattedDate = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
                                        String attendanceKey = student.getId() + "-" + chosenCourseId + "-" + formattedDate;
                                        String attendanceStatus = "-";
                                        if (LocalDatabase.attendaces.containsKey(attendanceKey)) {
                                            attendanceStatus = LocalDatabase.attendaces.get(attendanceKey).get(formattedDate) ? "P" : "A";
                                        }
                                        OutputFormatter.twoColumnText(i++ + ". " + student.getName() + " (" + student.getId() + ")", attendanceStatus);
                                    }
                                }
                            }
                            i--;
                            OutputFormatter.blankLine();
                            OutputFormatter.textMiddle("Press 0 and Enter to go back");
                            OutputFormatter.blankLine();
                            OutputFormatter.line();

                            int studentIndex = Integer.parseInt(OutputFormatter.inputTextLeft("Choose a student: "));
                            if (studentIndex == 0) { break; }
                            if (studentIndex > i || studentIndex < 0) {
                                OutputFormatter.errorText("Invalid choice. Please try again.");
                                continue;
                            }

                            int j = 1;
                            for (Map.Entry<Integer, Person> entry : LocalDatabase.users.entrySet()) {
                                if (entry.getValue() instanceof Student) {
                                    Student student = (Student) entry.getValue();
                                    if (student.getCourses().contains(chosenCourseId)) {
                                        if (j == studentIndex) {
                                            OutputFormatter.clearScreen();
                                            OutputFormatter.header("The Enhanced Student Management System", teacher, "Take Attendance", "TEACHER");
                                            OutputFormatter.blankLine();
                                            OutputFormatter.blankLine();
                                            OutputFormatter.textMiddle("Student: " + student.getName() + " (" + student.getId() + ")");
                                            OutputFormatter.blankLine();
                                            OutputFormatter.textMiddle("Course: " + chosenCourseId);
                                            OutputFormatter.blankLine();
                                            String attendanceKey = student.getId() + "-" + chosenCourseId + "-" + new SimpleDateFormat("MM-dd-yyyy").format(new Date());
                                            String attendanceStatus = "-";
                                            if (LocalDatabase.attendaces.containsKey(attendanceKey)) {
                                                attendanceStatus = LocalDatabase.attendaces.get(attendanceKey).get(chosenCourseId) ? "P" : "A";
                                            }
                                            OutputFormatter.textMiddle("Attendance: " + attendanceStatus);
                                            OutputFormatter.blankLine();
                                            OutputFormatter.line();
                                            OutputFormatter.blankLine();
                                            OutputFormatter.textMiddle("Press P for Present, A for Absent and Enter to submit");
                                            OutputFormatter.line();
                                            String attendance = OutputFormatter.inputTextLeft("Enter attendance: ");
                                            boolean isPresent = attendance.equalsIgnoreCase("P");
                                            DailyAttendance dailyAttendance = new DailyAttendance(student.getId(), chosenCourseId, new Date(), isPresent);
                                            dailyAttendance.recordAttendance();
                                        }
                                        j++;
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 5:
                    // Provide Grade
                    while(true) {
                        OutputFormatter.clearScreen();
                        OutputFormatter.header("The Enhanced Student Management System", teacher, "Provide Grade", "TEACHER");
                        OutputFormatter.blankLine();
                        OutputFormatter.blankLine();
                        OutputFormatter.textLeft("Courses taken by you:");
                        OutputFormatter.blankLine();
                        int i = 1;
                        for (Map.Entry<String, Map<String, Teacher>> entry : LocalDatabase.courses.entrySet()) {
                            for (Map.Entry<String, Teacher> course : entry.getValue().entrySet()) {
                                if (course.getValue().getId() == teacher.getId()) {
                                    OutputFormatter.textLeft(i + ". " + entry.getKey() + ": " + course.getKey());
                                    courseOption.put(i++, entry.getKey());
                                }
                            }
                        }
                        i--;
                        OutputFormatter.blankLine();
                        OutputFormatter.textMiddle("Press 0 and Enter to go back");
                        OutputFormatter.line();
                        int chosenCourseIndex = Integer.parseInt(OutputFormatter.inputTextLeft("Choose a course: "));
                        if (chosenCourseIndex == 0) {
                            break;
                        } else if (chosenCourseIndex > i || chosenCourseIndex < 0) {
                            OutputFormatter.errorText("Invalid choice. Please try again.");
                            continue;
                        }

                        String chosenCourseId = courseOption.get(chosenCourseIndex);

                        OutputFormatter.clearScreen();
                        OutputFormatter.header("The Enhanced Student Management System", teacher, "Provide Grade", "TEACHER");
                        OutputFormatter.blankLine();
                        OutputFormatter.blankLine();
                        OutputFormatter.textMiddle("Students registered for " + chosenCourseId);
                        OutputFormatter.line();
                        OutputFormatter.twoColumnText("Student", "Grade");
                        OutputFormatter.line();
                        OutputFormatter.blankLine();

                        i = 1;
                        for (Map.Entry<Integer, Person> entry : LocalDatabase.users.entrySet()) {
                            if (entry.getValue() instanceof Student student) {
                                if (student.getCourses().contains(chosenCourseId)) {
                                    String date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
                                    String gradeKey = student.getId() + "-" + chosenCourseId + "-" + date;
                                    Map<String, String> gradeMap = LocalDatabase.grades.get(gradeKey);
                                    String grade = (gradeMap != null) ? gradeMap.getOrDefault(date, "Not graded") : "Not graded";
                                    OutputFormatter.twoColumnText(i++ + ". " + student.getName() + " (" + student.getId() + ")", grade);
                                }
                            }
                        }
                        OutputFormatter.blankLine();
                        OutputFormatter.textMiddle("Press 0 and Enter to go back");
                        OutputFormatter.blankLine();
                        OutputFormatter.line();

                        int studentIndex = Integer.parseInt(OutputFormatter.inputTextLeft("Choose a student: "));
                        if (studentIndex == 0) {
                            break;
                        } else if (studentIndex > i || studentIndex < 0) {
                            OutputFormatter.errorText("Invalid choice. Please try again.");
                            continue;
                        }

                        String date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());

                        int j = 1;
                        for (Map.Entry<Integer, Person> entry : LocalDatabase.users.entrySet()) {
                            if (entry.getValue() instanceof Student student) {
                                if (student.getCourses().contains(chosenCourseId)) {
                                    if (j == studentIndex) {
                                        OutputFormatter.clearScreen();
                                        OutputFormatter.header("The Enhanced Student Management System", teacher, "Provide Grade", "TEACHER");
                                        OutputFormatter.blankLine();
                                        OutputFormatter.blankLine();
                                        OutputFormatter.textMiddle("Student: " + student.getName() + " (" + student.getId() + ")");
                                        OutputFormatter.blankLine();
                                        OutputFormatter.textMiddle("Course: " + chosenCourseId);
                                        OutputFormatter.blankLine();
                                        String gradeKey = student.getId() + "-" + chosenCourseId + "-" + date;
                                        Map<String, String> gradeMap = LocalDatabase.grades.get(gradeKey);
                                        String grade = (gradeMap != null) ? gradeMap.getOrDefault(date, "Not graded") : "Not graded";
                                        OutputFormatter.textMiddle("Grade: " + grade);
                                        OutputFormatter.blankLine();
                                        OutputFormatter.line();

                                        try {
                                            int number = Integer.parseInt(OutputFormatter.inputTextLeft("Enter grade (0-100) in numbers: "));
                                            GradeManager gradeManager = new GradeManager(GradeManager.UserAccessPermissionType.TEACHER);
                                            gradeManager.inputGrade(student.getId(), chosenCourseId, number);
                                        } catch (Exception e) {
                                            OutputFormatter.errorText("Something went wrong! Please try again.");
                                        }
                                    }
                                    j++;
                                }
                            }
                        }
                    }
                    break;
                case 6:
                    /*
                     *       Logout
                     * */
                    OutputFormatter.clearScreen();
                    OutputFormatter.header("The Enhanced Student Management System", teacher, "Logout", "STUDENT");
                    OutputFormatter.blankLine();
                    OutputFormatter.successText("You have been logged out successfully.");
                    OutputFormatter.blankLine();
                    OutputFormatter.line();
                    OutputFormatter.autoPause(1000);
                    break;
                default:
                    OutputFormatter.errorText("Invalid choice. Please try again.");
            }
            if (choice == 6) {
                break;
            }
        }
    }
}