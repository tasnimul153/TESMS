import java.util.Date;

public abstract class Attendance {
    protected int studentId;
    protected String courseId;
    protected Date date;

    public Attendance(int studentId, String courseId, Date date) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.date = date;
    }

    public abstract void recordAttendance();
}
