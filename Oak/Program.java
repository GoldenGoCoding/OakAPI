import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {
    private static final Scanner scanner = new Scanner(System.in);

    public static void Out(String s) {
        System.out.print(s);
    }

    public static String in(String s) {
        Out(s);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        String[] colors = {
            "\u001B[37m", // 0 fgColor (White)
            "\u001B[40m", // 1 bgColor (Black)
            "\u001B[32m", // 2 positiveColor (Green)
            "\u001B[31m", // 3 negativeColor (Red)
            "\u001B[33m", // 4 warnColor (Yellow)
            "\u001B[31m", // 5 fileColor (Red)
            "\u001B[32m"  // 6 dirColor (Green)
        };
        String path;
        String cmd;
        String version = "v0.0.0";
        String[] e;

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("linux")) {
            path = System.getProperty("user.home");
        } else if (os.contains("win")) {
            path = System.getenv("HOMEDRIVE") + System.getenv("HOMEPATH");
        } else {
            path = System.getProperty("user.home");
        }

        Out("\u001B[36m"); // Cyan color
        Out("""
                ╔════════════════════════╗
                ║ WELCOME TO OAK v.0.0.0 ║
                ╚════════════════════════╝


                """);

        while (true) {
            Out(colors[0]); // fgColor
            Out(colors[1]); // bgColor
            cmd = in("#~" + path.replace(":", "~").replace("\\", "~") + "~ ");
            e = cmd.split(" ");
            if (cmd.equals(" ") || cmd.equals("")) {
                // Do nothing
            } else if (cmd.startsWith("dir")) {
                File directory = new File(path);
                File[] dirs = directory.listFiles(File::isDirectory);
                File[] files = directory.listFiles(File::isFile);
                if (dirs != null) {
                    for (File item : dirs) {
                        Out(colors[6]); // dirColor
                        System.out.println(item.getName());
                    }
                }
                if (files != null) {
                    for (File item : files) {
                        Out(colors[5]); // fileColor
                        System.out.println(item.getName());
                    }
                }
            } else if (cmd.startsWith("cls") || cmd.startsWith("clear")) {
                Out("\033[H\033[2J");
                System.out.flush();
            } else {
                Out(colors[4]); // warnColor
                Out(String.format(
                        "\n\"%s\" IS NOT RECOGNIZED AS A COMMAND, SCRIPT, OR PLUGIN\n\nPLEASE VERIFY SPELLING OR INSTALL PLUGIN BY RUNNING \"install %s\"\n\n",
                        e[0], e[0]));
            }
        }
    }
}
