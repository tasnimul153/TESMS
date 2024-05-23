import java.util.Scanner;

public class OutputFormatter {

    public static void autoPause(int miliseconds) {
        try {
            Thread.sleep(miliseconds); // 5 seconds
        } catch (InterruptedException e) {
            System.err.println("Error while pausing: " + e.getMessage());
        }
    }

    public static void clearScreen() {
        String os = System.getProperty("os.name").toLowerCase();

        try {
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void warningText (String text) {
        System.out.print("|");
        // Took (padding + length of text) spaces = x spaces
        for(int i = 0; i < Settings.sidePadding; i++)
            System.out.print(" ");
        System.out.print("\033[0;33m");
        System.out.print(text);
        System.out.print("\033[0;37m");
        // print remainning spaces to fillup the display - 1
        // We are subtracting 1 because we are printing the | at the end which takes 1 space
        // E.g. |  FAHIM     X   50 - (3 + x [in this case length of FAHIM (5)]) spaces + '|'
        // So we are printing in this format:

        // |  FAHIM                                              |

        for (int i = 0; i < Settings.getDISPLAY_LENGTH() - text.length() - Settings.sidePadding - 2; i++) {
            System.out.print(" ");
        }
        System.out.println("|");
    }

    public static void textLeft (String text) {
        System.out.print("|");
        // Took (padding + length of text) spaces = x spaces
        for(int i = 0; i < Settings.sidePadding; i++)
            System.out.print(" ");
        System.out.print(text);
        // print remainning spaces to fillup the display - 1
        // We are subtracting 1 because we are printing the | at the end which takes 1 space
        // E.g. |  FAHIM     X   50 - (3 + x [in this case length of FAHIM (5)]) spaces + '|'
        // So we are printing in this format:

        // |  FAHIM                                              |

        for (int i = 0; i < Settings.getDISPLAY_LENGTH() - text.length() - Settings.sidePadding - 2; i++) {
            System.out.print(" ");
        }
        System.out.println("|");
    }

    public static String inputTextLeft(String text) {
        String str = "";
        Scanner input = new Scanner(System.in);
        do {
            for(int i = 0; i < Settings.sidePadding+1; i++)
                System.out.print(" ");
            System.out.print(text);
            str = input.nextLine();
            if (str.isEmpty()) {
                for(int i = 0; i < Settings.sidePadding+1; i++)
                    System.out.print(" ");
                System.out.print("\033[0;31m");
                System.out.print("Input cannot be empty. Please try again.\n");
                System.out.print("\033[0;37m");
            }
        } while (str.isEmpty());
        return str;
    }

    public static void textMiddle(String text) {
        int preTextSpaces = ((Settings.getDISPLAY_LENGTH() - text.length()) / 2) - 1;
        int postTextSpaces = Settings.getDISPLAY_LENGTH() - text.length() - preTextSpaces - 2;

        // So, the formula becomes: '|' + ' ' * preTextSpaces + text + ' ' * postTextSpaces + '|'
        // E.g. |        FAHIM         |

        System.out.print("|");
        for(int i = 0; i < preTextSpaces; i++)
            System.out.print(" ");
        System.out.print(text);
        for(int i = 0; i < postTextSpaces; i++)
            System.out.print(" ");
        System.out.println("|");
    }

    public static void successText(String text) {
        int preTextSpaces = ((Settings.getDISPLAY_LENGTH() - text.length()) / 2) - 1;
        int postTextSpaces = Settings.getDISPLAY_LENGTH() - text.length() - preTextSpaces - 2;

        // So, the formula becomes: '|' + ' ' * preTextSpaces + text + ' ' * postTextSpaces + '|'
        // E.g. |        Error         |
        clearScreen();
        header("The Enhanced Student Management System");
        for(int i = 0; i < 2; i++) { blankLine(); }
        // change color to red
        System.out.print("|");
        for(int i = 0; i < preTextSpaces; i++)
            System.out.print(" ");
        System.out.print("\033[0;32m");
        System.out.print(text);
        System.out.print("\033[0;37m");
        for(int i = 0; i < postTextSpaces; i++)
            System.out.print(" ");
        System.out.println("|");
        blankLine();
        line();
        autoPause(1000);
        clearScreen();
    }

    public static void loadingScreen() {
        for(int j = 0; j < 2; j++) {
            clearScreen();
            header("The Enhanced Student Management System");
            for(int i = 0; i < 2; i++) { blankLine(); }
            textMiddle("Loading."); blankLine(); line();
            autoPause(300);

            clearScreen();
            header("The Enhanced Student Management System");
            for(int i = 0; i < 2; i++) { blankLine(); }
            textMiddle("Loading.."); blankLine(); line();
            autoPause(300);

            clearScreen();
            header("The Enhanced Student Management System");
            for(int i = 0; i < 2; i++) { blankLine(); }
            textMiddle("Loading..."); blankLine(); line();
            autoPause(300);
        }
    }

    public static void errorText(String text) {
        int preTextSpaces = ((Settings.getDISPLAY_LENGTH() - text.length()) / 2) - 1;
        int postTextSpaces = Settings.getDISPLAY_LENGTH() - text.length() - preTextSpaces - 2;

        // So, the formula becomes: '|' + ' ' * preTextSpaces + text + ' ' * postTextSpaces + '|'
        // E.g. |        Error         |
        clearScreen();
        header("The Enhanced Student Management System");
        for(int i = 0; i < 2; i++) { blankLine(); }
        // change color to red
        System.out.print("|");
        for(int i = 0; i < preTextSpaces; i++)
            System.out.print(" ");
        System.out.print("\033[0;31m");
        System.out.print(text);
        System.out.print("\033[0;37m");
        for(int i = 0; i < postTextSpaces; i++)
            System.out.print(" ");
        System.out.println("|");
        blankLine();
        line();
        autoPause(1000);
    }

    public static void errorText(String text1, String text2) {
        int preTextSpaces = ((Settings.getDISPLAY_LENGTH() - text1.length()) / 2) - 1;
        int postTextSpaces = Settings.getDISPLAY_LENGTH() - text1.length() - preTextSpaces - 2;
        int preTextSpaces2 = ((Settings.getDISPLAY_LENGTH() - text2.length()) / 2) - 1;
        int postTextSpaces2 = Settings.getDISPLAY_LENGTH() - text2.length() - preTextSpaces2 - 2;

        // So, the formula becomes: '|' + ' ' * preTextSpaces + text + ' ' * postTextSpaces + '|'
        // E.g. |        Error         |
        clearScreen();
        header("The Enhanced Student Management System");
        for(int i = 0; i < 2; i++) { blankLine(); }
        System.out.print("|");
        for(int i = 0; i < preTextSpaces; i++)
            System.out.print(" ");
        System.out.print("\033[0;31m");
        System.out.print(text1);
        System.out.print("\033[0;37m");
        for(int i = 0; i < postTextSpaces; i++)
            System.out.print(" ");
        System.out.println("|");
        blankLine();
        line();
        autoPause(1000);

        clearScreen();
        header("The Enhanced Student Management System");
        for(int i = 0; i < 2; i++) { blankLine(); }
        System.out.print("|");
        for(int i = 0; i < preTextSpaces; i++)
            System.out.print(" ");
        System.out.print("\033[0;31m");
        System.out.print(text1);
        System.out.print("\033[0;37m");
        for(int i = 0; i < postTextSpaces; i++)
            System.out.print(" ");
        System.out.println("|");
        System.out.print("|");
        for(int i = 0; i < preTextSpaces2; i++)
            System.out.print(" ");
        System.out.print("\033[0;31m");
        System.out.print(text2);
        System.out.print("\033[0;37m");
        for(int i = 0; i < postTextSpaces2; i++)
            System.out.print(" ");
        System.out.println("|");
        blankLine(); line();
        autoPause(1000);
    }

    public static void textRight(String text) {
        // E.g. |           FAHIM  |
        int preTextSpaces = Settings.getDISPLAY_LENGTH() - text.length() - 1;
        int postTextSpaces = Settings.getDISPLAY_LENGTH() - preTextSpaces - Settings.sidePadding - 1;
        System.out.print("|");
        for(int i = 0; i < preTextSpaces; i++)
            System.out.print(" ");
        System.out.print(text);
        for(int i = 0; i < postTextSpaces; i++)
            System.out.print(" ");
        System.out.println("|");
    }

    public static void twoColumnText(String text1, String text2) {
        int totalTextLength = text1.length() + text2.length();
        int textBetweenSpace = Settings.getDISPLAY_LENGTH() - totalTextLength - 2 - (Settings.sidePadding * 2);
        System.out.print("|");
        for(int i = 0; i < Settings.sidePadding; i++)
            System.out.print(" ");
        System.out.print(text1);
        for(int i = 0; i < textBetweenSpace; i++)
            System.out.print(" ");
        System.out.print(text2);
        for(int i = 0; i < Settings.sidePadding; i++)
            System.out.print(" ");
        System.out.println("|");
    }

    public static void optionTitleLeft(String text) {
        for(int i = 0; i < Settings.sidePadding + 1; i++) {
            System.out.print(" ");
        }
        System.out.print(text);
    }

    public static void line() {
        for(int i = 0; i < Settings.getDISPLAY_LENGTH(); i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void blankLine() {
        System.out.print("|");
        for(int i = 0; i < Settings.getDISPLAY_LENGTH() - 2; i++) {
            System.out.print(" ");
        }
        System.out.println("|");
    }

    private static void title(String title) {
        line();
        textMiddle(title);
        line();
    }

    public static void header(String header) {
        title(header);
    }

    public static void header(String header, Person person, String displayCharacter) {
        title(header);
        twoColumnText(person.getName(), displayCharacter);
    }

    public static void header(String header, Person person, String currentFolder, String displayCharacter) {
        title(header);
        twoColumnText(person.getName() + " > " + currentFolder, displayCharacter);
    }

    public static void orderedList(String[] list) {
        for(int i = 0; i < list.length; i++) {
            textLeft((i + 1) + ". " + list[i]);
        }
    }

    public static void orderedList(int[] list) {
        for(int i = 0; i < list.length; i++) {
            textLeft((i + 1) + ". " + list[i]);
        }
    }

    public static void unorderedList(String[] list) {
        for(int i = 0; i < list.length; i++) {
            textLeft("* " + list[i]);
        }
    }

    public static void unorderedList(int[] list) {
        for(int i = 0; i < list.length; i++) {
            textLeft("* " + list[i]);
        }
    }
}