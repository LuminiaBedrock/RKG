package clubdev.modules;

import clubdev.Main;
import clubdev.text.ConsoleAnimation;
import clubdev.text.PackDescription;
import clubdev.text.TextFormat;
import clubdev.utils.FileUtils;
import clubdev.utils.Utils;
import clubdev.utils.ZipUtils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ZipModule {
    
    public ZipModule(Scanner scanner) {
        if (!Main.tempFolder.exists()) {
            System.out.println(TextFormat.RED + "The archive could not be created, the folder " + Main.tempFolder.getName() + " was not found." + TextFormat.RESET);
            return;
        }

        Main.startedIn = System.currentTimeMillis();

        
        File zipOutput = new File(Main.outputFolder + "/pack.zip");
        
        if (!Main.outputFolder.exists()) {
            Main.outputFolder.mkdir();
        }

        
        System.out.println("Creating manifest.json.");
        File manifest = new File(Main.tempFolder + "/manifest.json");
        FileUtils.writeFile(manifest, PackDescription.getManifest());

        System.out.println("Downloading pack_icon.png...");
        PackDescription.downloadRandomIcon(new File(Main.tempFolder + "/pack_icon.png"));

        try {
            System.out.println(TextFormat.YELLOW + "Creating an archive..." + TextFormat.RESET);
            
            ConsoleAnimation animation = new ConsoleAnimation();
            Thread animationThread = new Thread(animation);

            animationThread.start();
            System.out.print(TextFormat.YELLOW + "  Creating..." + TextFormat.RESET);

            File dir = new File(".");
            for (File file : dir.listFiles()) {
                String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
                if (extension.equals("jar")) {
                    FileUtils.copyFile(file, new File(Main.tempFolder + "/" + file.getName()));
                }
            }

            ZipUtils.zip(Main.tempFolder, zipOutput);
            FileUtils.copyFile(zipOutput, new File(Main.outputFolder + "/pack.mcpack"));
            animation.stop();

            double archiveSize = (double) zipOutput.length() / (1024 * 1024);
            System.out.println(TextFormat.GREEN + "\rDone. (" + archiveSize + " mb)" + TextFormat.RESET);
            
            Utils.printElapsedTime();
        } catch (IOException e) {
            System.out.println(TextFormat.RED + "The archive could not be created: " + e.getMessage() + TextFormat.RESET);
        }

        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
}
