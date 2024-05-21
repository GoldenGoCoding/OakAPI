import java.io.Console;
import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {
    private static final Console console = System.console();
    private static final Scanner scanner = new Scanner(System.in);

    // ANSI escape codes for colors
    private static final String RESET = "\033[0m";
    private static final String WHITE = "\033[37m";
    private static final String BLACK = "\033[40m";
    private static final String GREEN = "\033[32m";
    private static final String RED = "\033[31m";
    private static final String YELLOW = "\033[33m";
    private static final String CYAN = "\033[36m";

    public static void out(String s) {
        System.out.print(s);
    }

    public static String in(String s) {
        out(s);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        String[] colors = {
                WHITE, // 0 fgColor
                BLACK, // 1 bgColor
                GREEN, // 2 positiveColor
                RED,   // 3 negativeColor
                YELLOW, // 4 warnColor
                RED,    // 5 fileColor
                GREEN   // 6 dirColor
        };
        String path, cmd, version = "INCOMPLETE";
        String[] e;
        String[] pE;

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("linux")) {
            path = System.getProperty("user.home");
        } else if (os.contains("win")) {
            path = System.getenv("HOMEDRIVE") + System.getenv("HOMEPATH");
        } else {
            path = System.getProperty("user.home");
        }

        System.out.print(CYAN);
        out("""
                ╔════════════════╗
                ║ WELCOME TO OAK ║
                ╚════════════════╝

                
                """);
        System.out.print(colors[0]);
        out("╗\n");

        while (true) {
            System.out.print(colors[0]);
            cmd = in("║ " + path.replace(":", ":").replace("\\", "~") + "~ ");
            e = cmd.split(" ");
            pE = path.split("\\\\");

            if (cmd.equals(" ")) {
                // Do nothing
            } else if (cmd.equals("")) {
                // Do nothing
            } else if (cmd.startsWith("dir")) {
                File directory = new File(path);
                File[] dirs = directory.listFiles(File::isDirectory);
                File[] files = directory.listFiles(File::isFile);
                if (dirs != null) {
                    for (File item : dirs) {
                        if (!item.exists()) {
                            break;
                        }
                        System.out.print(colors[6]);
                        System.out.println("╠ " + item.getName());
                    }
                }
                if (files != null) {
                    for (File item : files) {
                        System.out.print(colors[5]);
                        System.out.println("╠ " + item.getName());
                    }
                }
            } else if (cmd.startsWith("\\")) {
                File newPath = new File(path + cmd);
                if (newPath.exists()) {
                    path = newPath.getPath();
                } else {
                    System.out.print(colors[3]);
                    out("╒ \"" + path + cmd + "\"\n");
                    System.out.print(colors[4]);
                    out("╘ Does not exist. Please check your spelling and try again\n");
                }
            } else if (cmd.startsWith(".")) {
                if (!path.equals("C:\\")) {
                    int n = 0;
                    for (int i = 0; i < cmd.length(); i++) {
                        if (!cmd.substring(i, i + 1).equals("\\")) {
                            n++;
                        } else {
                            break;
                        }
                    }
                    int l = 0;
                    for (int i = 0; i < pE.length - 1; i++) {
                        l += pE[i].length();
                    }
                    path = path.substring(0, l + 2);
                }
            } else if (cmd.startsWith("cls") || cmd.startsWith("clear")) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } else {
                System.out.print(colors[4]);
                out(String.format("\n\"%s\" IS NOT RECOGNIZED AS A COMMAND, SCRIPT, OR PLUGIN\n\nPLEASE VERIFY SPELLING OR INSTALL PLUGIN BY RUNNING \"install %s\"\n\n", e[0], e[0]));
            }
        }
    }
}