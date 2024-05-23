import java.util.HashMap;
import java.util.Map;

public class CourseManager implements CourseManagement {
    public enum UserAccessPermissionType { STUDENT, TEACHER, ADMIN }
    private UserAccessPermissionType userAccessPermissionType;

    public CourseManager(UserAccessPermissionType userAccessPermissionType) {
        this.userAccessPermissionType = userAccessPermissionType;
    }

    @Override
    public boolean addCourse(String courseId, String courseName, Teacher teacher) {
        if (Authenticate.authenticateAdminAccess(userAccessPermissionType)) {
            LocalDatabase.courses.put(courseId, new HashMap<>(Map.of(courseName, teacher)));
            return true;
        }
        OutputFormatter.errorText("Permission denied", "User does not have permission to add course");
        return false;
    }

    @Override
    public boolean updateCourse(String courseId, String newCourseName, Teacher teacher) {
        if (Authenticate.authenticateAdminAccess(userAccessPermissionType)) {
            if (LocalDatabase.courses.containsKey(courseId)) {
                LocalDatabase.courses.put(courseId, new HashMap<>(Map.of(newCourseName, teacher)));
                return true;
            }
            OutputFormatter.errorText("Course not found", "Please try again");
            return false;
        }
        OutputFormatter.errorText("Permission denied", "User does not have permission to update course");
        return false;
    }

    @Override
    public boolean deleteCourse(String courseId) {
        if (Authenticate.authenticateAdminAccess(userAccessPermissionType)) {
            if (LocalDatabase.courses.containsKey(courseId)) {
                LocalDatabase.courses.remove(courseId);
                return true;
            }
            OutputFormatter.errorText("Course not found", "Please try again");
            return false;
        }
        OutputFormatter.errorText("Permission denied", "User does not have permission to delete course");
        return false;
    }
}
