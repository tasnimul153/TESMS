public interface GradeManagement {
    boolean inputGrade(int studentId, String courseId, int grade);
    boolean updateGrade(int studentId, String courseId, int newGrade);
    boolean deleteGrade(int studentId, String courseId);
}
