using System.Runtime.InteropServices;

public class Program
{
    public static void Out(string s)
    {
        Console.Write(s);
    }
    public static string In(string s)
    {
        Console.Write(s);
        string i = Console.ReadLine();
        return i;
    }
    public static void Main(string[] args)
    {
        ConsoleColor[] Colors =
        [
            ConsoleColor.White,  // 0 fgColor
            ConsoleColor.Black,  // 1 bgColor
            ConsoleColor.Green,  // 2 positiveColor
            ConsoleColor.Red,    // 3 negativeColor
            ConsoleColor.Yellow, // 4 warnColor
            ConsoleColor.Red,    // 5 fileColor
            ConsoleColor.Green   // 6 dirColor
        ];
        string path, cmd, version = "v0.0.0";
        string[] e;
        string[] pE;
        if (RuntimeInformation.IsOSPlatform(OSPlatform.Linux))
        {
            path = Environment.SpecialFolder.Personal.ToString();
        } 
        else if (RuntimeInformation.IsOSPlatform(OSPlatform.Windows))
        {
            path = Environment.ExpandEnvironmentVariables("%HOMEDRIVE%%HOMEPATH%");
        } 
        else
        {
            path = Environment.ExpandEnvironmentVariables("HOME");
        }
        
        Console.ForegroundColor = ConsoleColor.Cyan;
        Out($"""
                ╔════════════════════════╗
                ║ WELCOME TO OAK v.0.0.0 ║
                ╚════════════════════════╝


                """);

        while (true)
        {
            Console.ForegroundColor = Colors[0];
            Console.BackgroundColor = Colors[1];
            cmd = In(@"#~" + path.Replace(@":\", ":").Replace(@"\", "~") + "~ ");
            e = cmd.Split(" ");
            pE = path.Split(@"\");

            if (cmd == " ") { }
            else if (cmd == "") { }
            else if (cmd.StartsWith("dir"))
            {
                string[] dirs = Directory.GetDirectories(path);
                string[] files = Directory.GetFiles(path);
                foreach (var item in dirs)
                {
                    Console.ForegroundColor = Colors[6];
                    Console.WriteLine(item.Split(@"\")[item.Split(@"\").Length - 1]);
                }
                foreach (var item in files)
                {
                    Console.ForegroundColor = Colors[5];
                    Console.WriteLine(item.Split(@"\")[item.Split(@"\").Length - 1]);
                }
            }
            else if (cmd.StartsWith(@"\"))
            {
                if (Directory.Exists(path+cmd))
                {
                    path += cmd;
                }
            }
            else if (cmd.StartsWith(@"."))
            {
                int n = 0;
                for (int i = 0;i<cmd.Length;i++)
                {
                    if (cmd[i].ToString()!=@"\") { n++; }
                    if (cmd[i].ToString()==@"\") { break; }
                }
                int l = 0;
                for (int i = 0;i<pE.Length-1;i++)
                {
                    l += pE[i].Length;
                }
                path = path.Substring();
            }
            else if (cmd.StartsWith("cls") || cmd.StartsWith("clear")) { Console.Clear(); }
            else
            {
                Console.ForegroundColor = Colors[4];
                Out($"\n\"{e[0]}\" IS NOT RECOGNIZED AS A COMMAND, SCRIPT, OR PLUGIN\n\nPLEASE VERIFY SPELLING OR INSTALL PLUGIN BY RUNNING \"install {e[0]}\"\n\n");
            }
        }
    }
}