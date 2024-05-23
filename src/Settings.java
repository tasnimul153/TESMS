public class Settings {
    private static int MAX_STUDENTS = 50;
    private static int MAX_TEACHERS = 50;
    private static int MAX_ADMINISTRATORS = 50;
    private static int MAX_ENROLLMENTS = 50;
    private static int DISPLAY_LENGTH = 100;
    public static int sidePadding = 5;

    // Getter methods
    public static int getMAX_STUDENTS() {
        return MAX_STUDENTS;
    }

    public static int getMAX_TEACHERS() {
        return MAX_TEACHERS;
    }

    public static int getMAX_ADMINISTRATORS() {
        return MAX_ADMINISTRATORS;
    }

    public static int getMAX_ENROLLMENTS() {
        return MAX_ENROLLMENTS;
    }

    public static int getDISPLAY_LENGTH() {
        return DISPLAY_LENGTH;
    }

    // Setter methods
    public static boolean setMAX_STUDENTS(int range) {
        if (range > 0) {
            MAX_STUDENTS = range;
            return true;
        }
        return false;
    }

    public static boolean setMAX_TEACHERS(int range) {
        if (range > 0) {
            MAX_TEACHERS = range;
            return true;
        }
        return false;
    }

    public static boolean setMAX_ADMINISTRATORS(int range) {
        if (range > 0) {
            MAX_ADMINISTRATORS = range;
            return true;
        }
        return false;
    }

    public static boolean setMAX_ENROLLMENTS(int range) {
        if (range > 0) {
            MAX_ENROLLMENTS = range;
            return true;
        }
        return false;
    }

    public static boolean setDISPLAY_LENGTH(int length) {
        if (length >= 30 && length <= 100) {
            DISPLAY_LENGTH = length;
            return true;
        }
        return false;
    }
}

