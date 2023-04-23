package clubdev;

import clubdev.modules.WriteModule;
import clubdev.modules.ZipModule;
import clubdev.text.TextFormat;
import clubdev.utils.Utils;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    
    public static Main instance;

    public static File tempFolder = new File("temp");
    public static File outputFolder = new File("output");

    public static long startedIn = 0L;

    static boolean running = true;

    public Main() {
        instance = this;
    }

    public static void main(String[] args) {

        try {
            if (System.getProperty("os.name").contains("Windows")) {
                if (System.console() == null) {

                    String jarName = new File(Main.class.getProtectionDomain()
                        .getCodeSource()
                        .getLocation()
                        .toURI()
                        .getPath())
                        .getName();

                    ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "start", "cmd.exe", "/c", "java -jar " + jarName);
                    builder.redirectErrorStream(true);
                    builder.start();
                    return;
                }
            }
        } catch (IOException | URISyntaxException e) {
            // ignore
        }

        Scanner scanner = new Scanner(System.in);
        
        while (running) {

            Utils.clearConsole();

            System.out.print("\n");
            Utils.printTitle();

            System.out.println(TextFormat.REVERSED + "Enter the command:");
            System.out.println(TextFormat.WHITE + "1. write - writing a large number of files.");
            System.out.println("2. zip - archive everything.");
            System.out.println("3. exit - exit.");
            Utils.printArrow();

            String command = scanner.nextLine().toLowerCase();

            switch(command) {
                case "write", "1" -> {
                    new WriteModule(scanner);
                    break;
                }

                case "zip", "2" -> {
                    new ZipModule(scanner);
                    break;
                }

                case "exit", "3" -> {
                    running = false;
                }

                default -> {
                    System.out.println("Error: unknown command.");
                    break;
                }
            }

            if (running) {
                System.out.print("Continue the work? (yes/no) ");
                Utils.printArrow();

                String response = scanner.nextLine();

                switch(response) {
                    case "no", "n", "2" -> running = false;
                }
            }
        }

        scanner.close();
    }
}