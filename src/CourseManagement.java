public interface CourseManagement {
    boolean addCourse(String courseId, String courseName, Teacher teacher);
    boolean updateCourse(String courseId, String newCourseName, Teacher teacher);
    boolean deleteCourse(String courseId);
}
