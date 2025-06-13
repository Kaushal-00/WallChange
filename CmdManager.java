import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CmdManager {

    public void setupCommand(ConfigManager config) {
        
        try {

            String command = config.getCommand();
            String currentDir = System.getProperty("user.dir");

            String commandContent = "@echo off\n"
                + "cd /d \"" + currentDir + "\"\n"
                + "java Main\n";

            String batPath = System.getenv("USERPROFILE") + "\\AppData\\Local\\Microsoft\\WindowsApps\\" + command + ".bat";

            File batFile = new File(batPath);
            if (!batFile.exists()) {
                FileWriter writer = new FileWriter(batFile);
                writer.write(commandContent);
                writer.close();

                System.out.println("You can use `" + command + "` in terminal to change wallpaper.");
            } else {
                System.out.println("You can use `" + command + "` in terminal to change wallpaper.");
            }

        } catch (IOException e) {
            System.out.println("Failed to create command: " + e.getMessage());
        }
    }
}
