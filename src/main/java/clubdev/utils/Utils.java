package clubdev.utils;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import clubdev.Main;
import clubdev.text.TextFormat;

public class Utils {
    
    public final static String title =
            """
                {cyan}██████╗ {red}██╗  ██╗{green} ██████╗ {reset} 
                {cyan}██╔══██╗{red}██║ ██╔╝{green}██╔════╝ {reset}
                {cyan}██████╔╝{red}█████╔╝ {green}██║  ███╗{reset}
                {cyan}██╔══██╗{red}██╔═██╗ {green}██║   ██║{reset}
                {cyan}██║  ██║{red}██║  ██╗{green}╚██████╔╝{reset}
                {cyan}╚═╝  ╚═╝{red}╚═╝  ╚═╝{green} ╚═════╝ {reset}
                
                {white}Resource Pack Killer Gen{reset}
            """;

    public static String randomString(int length) {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32).substring(0, length);
    }


    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            // ignore
        }
    }

    public static void printArrow() {
        System.out.print(TextFormat.GREEN + "> " + TextFormat.RESET);
    }

    public static void printElapsedTime() {
        long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - Main.startedIn);
        long mseconds = System.currentTimeMillis() - Main.startedIn;
        System.out.println("The process was completed in " + (seconds != 0 ? seconds + " s" : mseconds + " ms") + ".");
    } 

    public static void printTitle() {
        System.out.println(
            title.replace("{cyan}", TextFormat.CYAN)
                 .replace("{red}", TextFormat.RED)
                 .replace("{green}", TextFormat.GREEN)
                 .replace("{reset}", TextFormat.RESET)
                 .replace("{white}", TextFormat.WHITE)
        );
    }
}
