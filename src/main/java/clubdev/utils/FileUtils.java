package clubdev.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import clubdev.text.TextFormat;

public class FileUtils {
    
    public static void writeFile(File file, String text) {
        try {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(TextFormat.RED + "Не удалось создать новый файл: " + file.getName() + ": " + e.getMessage() + TextFormat.RESET);
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(TextFormat.RED + "Ошибка записи файла " + file.getName() + ": " + e.getMessage() + TextFormat.RESET);
        }
    }


    public static void copyFile(File source, File output) throws IOException {
        if (source.exists()) {
            if (source.isDirectory()) {
                if (!output.exists()) {
                    output.mkdir();
                }
                String[] files = source.list();
                for (String file : files) {
                    File sourceFile = new File(source, file);
                    File outputFile = new File(output, file);
                    copyFile(sourceFile, outputFile);
                }
            } else {
                Files.copy(source.toPath(), output.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }


    public static void download(String url, File output) throws IOException {
        URL downloadUrl = new URL(url);
        URLConnection connection = downloadUrl.openConnection();
    
        try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(output))) {
    
            byte[] data = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(data, 0, data.length)) != -1) {
                out.write(data, 0, bytesRead);
            }
        }
    }

}
