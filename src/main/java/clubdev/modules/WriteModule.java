package clubdev.modules;

import clubdev.Main;
import clubdev.text.TextFormat;
import clubdev.utils.FileUtils;
import clubdev.utils.Utils;

import java.io.File;
import java.util.Scanner;

public class WriteModule {
    
    public WriteModule(Scanner scanner) {
        System.out.println("Enter the number of times:");
        Utils.printArrow();
        
        int count = scanner.nextInt();
        
        if (count == 0) {
            return;  
        }

        Main.startedIn = System.currentTimeMillis();

        if (!Main.tempFolder.exists()) {
            Main.tempFolder.mkdir();
        }
                

        System.out.println("Writing...");
                
        for (int i = 0; i < count; i++) {
        
            File output = new File(Main.tempFolder + "/" + Utils.randomString(10));
            if (!output.exists()) {
                output.mkdir();
            }
        
            int fileCount = 15;
        
            for (int i1 = 0; i1 < fileCount; i1++) {
                File writeFile = new File(output + "/" + Utils.randomString(5) + ".txt");
                FileUtils.writeFile(writeFile, "aaaaaaaaaaa".repeat(1000 * 1000));
                System.out.println(TextFormat.WHITE + " Written " + (i1 + 1) + " of " + fileCount + ".");   
            }

            System.out.println(TextFormat.GREEN + "[" + (i + 1) + "/" + count + "] Done." + TextFormat.RESET);
        }

        Utils.printElapsedTime();
        System.out.println("Press Enter to continue...");
        
        scanner.nextLine();
        scanner.nextLine();
    }
}
