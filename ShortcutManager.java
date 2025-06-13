import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ShortcutManager {

    public void setupShortcut(ConfigManager config) {

        try {

            String shortcutKey = config.getShortcutKey();
            String currentDir = System.getProperty("user.dir");
            String vbsFilePath = currentDir + "\\wallchangeShortcut.vbs";
            String programsDir = System.getenv("APPDATA") + "\\Microsoft\\Windows\\Start Menu\\Programs";
            String lnkFilePath = programsDir + "\\WallChangeShortcut.lnk";

            String vbsContent = "Set oShell = CreateObject(\"WScript.Shell\")\n"
                    + "oShell.Run \"cmd /c cd /d " + currentDir + " && java Main\", 0, false";

            FileWriter vbsWriter = new FileWriter(vbsFilePath);
            vbsWriter.write(vbsContent);
            vbsWriter.close();

            String psCommand = "$WshShell = New-Object -ComObject WScript.Shell;\n"
                    + "$Shortcut = $WshShell.CreateShortcut('" + lnkFilePath + "');\n"
                    + "$Shortcut.TargetPath = 'wscript.exe';\n"
                    + "$Shortcut.Arguments = '\"" + vbsFilePath + "\"';\n"
                    + "$Shortcut.WorkingDirectory = '" + currentDir + "';\n"
                    + "$Shortcut.Hotkey = '" + shortcutKey + "';\n"
                    + "$Shortcut.Save();";

            File tempScript = File.createTempFile("createShortcut", ".ps1");
            java.nio.file.Files.write(tempScript.toPath(), psCommand.getBytes());

            ProcessBuilder pb = new ProcessBuilder("powershell", "-ExecutionPolicy", "Bypass", "-File", tempScript.getAbsolutePath());
            Process process = pb.inheritIO().start();
            process.waitFor();
            
            tempScript.deleteOnExit();

            System.out.println("You can use `" + shortcutKey + "` to change wallpaper.");

        } catch (IOException | InterruptedException e) {
            System.out.println("Failed to create shortcut: " + e.getMessage());
        }
    }
}
