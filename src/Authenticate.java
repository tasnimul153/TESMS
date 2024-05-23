public class Authenticate {
    public static boolean authenticate(int id, String password) {
        if (LocalDatabase.userExists(id)) {
            Person user = LocalDatabase.getUser(id);
            if (user instanceof Student student) {
                return student.getPassword().equals(password);
            } else if (user instanceof Teacher teacher) {
                return teacher.getPassword().equals(password);
            } else if (user instanceof Administrator admin) {
                return admin.getPassword().equals(password);
            }
        }
        OutputFormatter.errorText("User not found", "Please try again");
        return false;
    }

    public static boolean authenticateAdminAccess(CourseManager.UserAccessPermissionType userAccessPermissionType) {
        return userAccessPermissionType == CourseManager.UserAccessPermissionType.ADMIN;
    }

    public static boolean authenticateTeacherAccess(CourseManager.UserAccessPermissionType userAccessPermissionType) {
        return userAccessPermissionType == CourseManager.UserAccessPermissionType.TEACHER;
    }

    public static boolean authenticateStudentAccess(CourseManager.UserAccessPermissionType userAccessPermissionType) {
        return userAccessPermissionType == CourseManager.UserAccessPermissionType.STUDENT;
    }
}
