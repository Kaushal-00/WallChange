import java.io.*;
import java.util.Scanner;

public class ConfigManager {

    private final String configFile = "config.txt";
    private String wallpaperFolder;
    private int currentIndex;
    private String command = "wallchange";
    private String shortcutKey = "CTRL+ALT+W";

    public void loadOrCreateConfig() throws IOException {
        File file = new File(configFile);

        if (!file.exists()) {

            Scanner sc = new Scanner(System.in);

            System.out.println("Enter the path to your wallpaper folder:");
            String inputPath = sc.nextLine().trim();

            if (inputPath.startsWith("\"") && inputPath.endsWith("\"")) {
                inputPath = inputPath.substring(1, inputPath.length() - 1);
            }

            wallpaperFolder = inputPath;
            currentIndex = 0;

            saveConfig();

        } else {

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                
                if (line.startsWith("folder=")) {
                    wallpaperFolder = line.substring("folder=".length());
                } else if (line.startsWith("index=")) {
                    currentIndex = Integer.parseInt(line.substring("index=".length()));
                } else if (line.startsWith("command=")) {
                    command = line.substring("command=".length());
                } else if (line.startsWith("shortcut=")) {
                    shortcutKey = line.substring("shortcut=".length());
                }
            }

            br.close();
        }
    }

    public void saveConfig() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(configFile));

        bw.write("folder=" + wallpaperFolder + "\n");
        bw.write("index=" + currentIndex + "\n");
        bw.write("command=" + command + "\n");
        bw.write("shortcut=" + shortcutKey + "\n");

        bw.close();
    }

    public String getWallpaperFolder() {
        return wallpaperFolder;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void updateIndex(int newIndex) throws IOException {
        this.currentIndex = newIndex;
        saveConfig();
    }

    public String getCommand() {
        return command;
    }

    public String getShortcutKey() {
        return shortcutKey;
    }
}