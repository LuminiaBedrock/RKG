package clubdev.text;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import clubdev.utils.FileUtils;

public class PackDescription {
    
    public final static String manifest = 
            """
            {
                "format_version": 2,
                "header": {
                    "name": "{name}",
                    "description": \"https://github.com/ClubDevelopment/rkg\",
                    "version": [{version}],
                    "min_engine_version": [1, 16, 0],
                    "uuid": "{uuid_1}"
                },
                "modules": [
                    {
                        "description": \"https://github.com/ClubDevelopment/rkg\",
                        "type": "resources",
                        "version": [{version}],
                        "uuid": "{uuid_2}"
                    }
                ]
            }
            """;

    public static String getManifest() {
        return manifest
            .replace("{name}", getRandomName())
            .replace("{version}", getRandomVersion())
            .replace("{uuid_1}", UUID.randomUUID().toString())
            .replace("{uuid_2}", UUID.randomUUID().toString());
    }

    public static String getRandomName() {
        String url = "https://raw.githubusercontent.com/ClubDevelopment/rkg/main/pack_names.txt";
        ArrayList<String> names = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                names.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names.get(new Random().nextInt(names.size()));
    }

    public static String getRandomVersion() {
        String version = String.format("%d, %d, %d", (int) (Math.random() * 4), (int) (Math.random() * 4), (int) (Math.random() * 4));
        return version;
    }

    public static void downloadRandomIcon(File output) {
        int random = new Random().nextInt(getIconCount());
        String url = String.format("https://raw.githubusercontent.com/ClubDevelopment/rkg/main/icons/pack_icon_%s.png", random);
        try {
            FileUtils.download(url, output);
        } catch (IOException e) {
            // ignore
        }
    }

    public static int getIconCount() {
        try {
            URLConnection connection = new URL("https://raw.githubusercontent.com/ClubDevelopment/rkg/main/icons/count").openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = reader.readLine();
            reader.close();
            return Integer.parseInt(line);
        } catch(IOException e) {
            // ignore
        }
        return 0;
    }
}
