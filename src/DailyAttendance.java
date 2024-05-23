import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;

public class DailyAttendance extends Attendance {
    private boolean isPresent;

    public DailyAttendance(int studentId, String courseId, Date date, boolean present) {
        super(studentId, courseId, date);
        this.isPresent = present;
    }

    public boolean isPresent() {
        return isPresent;
    }

    @Override
    public void recordAttendance() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String dateKey = sdf.format(date);
        String mapKey = studentId + "-" + courseId + "-" + dateKey;
        Map<String, Boolean> dailyAttendance = LocalDatabase.attendaces.getOrDefault(mapKey, new HashMap<>());
        dailyAttendance.put(dateKey, isPresent);
        LocalDatabase.attendaces.put(mapKey, dailyAttendance);
        Person person = LocalDatabase.users.get(studentId);
        if (person != null) {
            OutputFormatter.successText("Attendance recorded for " + person.getName() + " on " + dateKey);
        } else {
            OutputFormatter.errorText("Failed to record attendance. No user found with ID: " + studentId);
        }
    }
}
