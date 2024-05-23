import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GradeManager implements GradeManagement {
    public enum UserAccessPermissionType { STUDENT, TEACHER, ADMIN }
    private UserAccessPermissionType userAccessPermissionType;

    public GradeManager(UserAccessPermissionType userAccessPermissionType) {
        this.userAccessPermissionType = userAccessPermissionType;
    }

    public String calculateGrade(int grade) {
        if (grade >= 90) {
            return "A";
        } else if (grade >= 80) {
            return "B";
        } else if (grade >= 70) {
            return "C";
        } else if (grade >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    @Override
    public boolean inputGrade(int studentId, String courseId, int grade) {
        if (userAccessPermissionType != UserAccessPermissionType.TEACHER) {
            System.out.println("Unauthorized access attempt. Only teachers can input grades.");
            return false;
        }
        try {
            String date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
            LocalDatabase.grades.put(studentId + "-" + courseId + "-" + date, Map.of(date, calculateGrade(grade)));
            System.out.println("Grade inputted for student: " + studentId + " in course: " + courseId);
            return true;
        } catch (Exception e) {
            System.out.println("An error occurred while inputting grade: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateGrade(int studentId, String courseId, int newGrade) {
        if (userAccessPermissionType != UserAccessPermissionType.TEACHER) {
            System.out.println("Unauthorized access attempt. Only teachers can update grades.");
            return false;
        }
        try {
            String date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
            LocalDatabase.grades.put(studentId + "-" + courseId + "-" + date, Map.of(date, calculateGrade(newGrade)));
            System.out.println("Grade updated for student: " + studentId + " in course: " + courseId);
            return true;
        } catch (Exception e) {
            System.out.println("An error occurred while updating grade: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteGrade(int studentId, String courseId) {
        if (userAccessPermissionType != UserAccessPermissionType.TEACHER) {
            System.out.println("Unauthorized access attempt. Only teachers can delete grades.");
            return false;
        }
        try {
            String date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
            LocalDatabase.grades.remove(studentId + "-" + courseId + "-" + date);
            System.out.println("Grade deleted for student: " + studentId + " in course: " + courseId);
            return true;
        } catch (Exception e) {
            System.out.println("An error occurred while deleting grade: " + e.getMessage());
            return false;
        }
    }
}