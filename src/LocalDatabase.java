import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalDatabase  {
    public static Map<Integer, Person> users = new HashMap<Integer, Person>() {{
        // Adding a default data to test and demo the application
        put(840231155, new Student(
                840231155,
                "Md Tanzimul Alam Fahim",
                createDate("09/08/2001"),
                "ma0055@bravemail.uncp.edu",
                "F123456f*",
                1)
        );
        put(840231156, new Student(
                840231156,
                "Tazrian Ismail",
                createDate("01/01/2002"),
                "ismail@uncp.edu",
                "S123456s*",
                1)
        );
        put(840231157, new Student(
                840231157,
                "Jhon Doe",
                createDate("02/02/2003"),
                "doe@uncp.edu",
                "S234567s*",
                1)
        );
        put(840231158, new Student(
                840231158,
                "Jessica Jones",
                createDate("03/03/2004"),
                "jones@uncp.edu",
                "S345678s*",
                1)
        );
        put(840231159, new Student(
                840231159,
                "Kyle Rayner",
                createDate("04/04/2005"),
                "rayner@uncp.edu",
                "S456789s*",
                1)
        );
        put(840231160, new Student(
                840231160,
                "Hal Jordan",
                createDate("05/05/2006"),
                "jordan@uncp.edu",
                "S567890s*",
                1)
        );

        put(123457, new Teacher(
                123457,
                "Joong Lyul Lee",
                createDate("06/06/1980"),
                "joonglyul.lee@uncp.edu",
                "T123456t*",
                11225,
                "Computer Science")
        );
        put(123458, new Teacher(
                123458,
                "Anisur Rahman",
                createDate("07/07/1981"),
                "rahman@uncp.edu",
                "T234567t*",
                11226,
                "Mathematics")
        );
        put(123459, new Teacher(
                123459,
                "Prasanth BusiReddyGari",
                createDate("08/08/1982"),
                "prashanth.busireddygari@uncp.edu",
                "T345678t*",
                11227,
                "Physics")
        );
        put(153174, new Administrator(
                153174,
                "Tasnimul Alam",
                createDate("09/09/1970"),
                "admin@uncp.edu",
                "A123456a*")
        );
    }};
    public static Map<String, Map<String, Teacher>> courses = new HashMap<>() {{
        // Adding a default data to test and demo the application
        put("CSC 1850, 800", new HashMap<>() {{
            put("Object Oriented Programming", (Teacher) users.get(123457));
        }});
        put("CYB 4220, 800", new HashMap<>() {{
            put("Cybersecurity Management", (Teacher) users.get(123459));
        }});
        put("CYB 4900, 001", new HashMap<>() {{
            put("Cybersecurity Capstone Project", (Teacher) users.get(123459));
        }});
    }};
    // grades has student id, course id and grade
    public static Map<String, Map<String, String>> grades = new HashMap<>();
    public static Map<String, Map<String, Boolean>> attendaces = new HashMap<>();

    public static Map<String, Map<String, Teacher>> getCourses() { return courses; }

    private static Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static Person getUser(int id) {
        return users.get(id);
    }
    public static boolean userExists(int id) {
        return users.containsKey(id);
    }
    public static void addUser(Person person) {
        users.put(person.getId(), person);
    }
    public static void updateUser(Person person) {
        users.put(person.getId(), person);
    }
    public static void removeUser(int id) {
        users.remove(id);
    }
}
