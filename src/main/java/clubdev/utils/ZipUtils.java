package clubdev.utils;

import java.io.*;
import java.util.zip.*;

public class ZipUtils {
    
    public static void zip(File input, File output) throws IOException {
        FileOutputStream fos = new FileOutputStream(output);
        ZipOutputStream zos = new ZipOutputStream(fos);
        String inputPath = input.getParentFile() != null ? input.getParentFile().getAbsolutePath() : input.getAbsolutePath();
        zipFile(input, zos, inputPath.length() + 1);
        zos.close();
        fos.close();
    }
    
    private static void zipFile(File file, ZipOutputStream zos, int prefixLength) throws IOException {
        zos.setLevel(Deflater.BEST_COMPRESSION);

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                zipFile(f, zos, prefixLength);
            }
        } else {

            FileInputStream fis = new FileInputStream(file);
            String entryName = file.getAbsolutePath().substring(prefixLength);
            ZipEntry entry = new ZipEntry(entryName);

            zos.putNextEntry(entry);
            
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }

            zos.closeEntry();
            fis.close();
        }
    }
}
