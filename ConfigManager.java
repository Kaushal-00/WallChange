import java.io.*;
import java.util.Scanner;

public class ConfigManager {

    private final String configFile = "config.txt";
    private String wallpaperFolder;
    private int currentIndex;

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
            wallpaperFolder = br.readLine();
            currentIndex = Integer.parseInt(br.readLine());

            br.close();
        }
    }

    public void saveConfig() throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(configFile));
        
        bw.write(wallpaperFolder + "\n");
        bw.write(String.valueOf(currentIndex));
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
}
