import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WallManager {

    private ArrayList<String> imageList = new ArrayList<>();

    public void loadImages(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File f : files) {
                if (f.isFile() && isImage(f.getName())) {
                    imageList.add(f.getName());
                }
            }
        }
    }

    public void setNextWallpaper(ConfigManager config) throws IOException {
        int index = config.getCurrentIndex();
        if (imageList.isEmpty()) {
            System.out.println("No images found in folder.");
            return;
        }

        String nextImage = imageList.get(index);
        String fullPath = config.getWallpaperFolder() + "\\" + nextImage;

        String command = "powershell -command \"Add-Type -TypeDefinition \\\"using System.Runtime.InteropServices; " +
                "public class Wallpaper { [DllImport(\\\"user32.dll\\\")] public static extern int SystemParametersInfo(int uAction, int uParam, string lpvParam, int fuWinIni); }\\\"; " +
                "[Wallpaper]::SystemParametersInfo(20, 0, '" + fullPath + "', 3)\"";

        ProcessBuilder pb = new ProcessBuilder("powershell", "-command", psCommand);
        pb.start();

        System.out.println("Wallpaper changed to: " + nextImage);

        int newIndex = (index + 1) % imageList.size();
        config.updateIndex(newIndex);
    }

    private boolean isImage(String name) {
        name = name.toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png") || name.endsWith(".bmp");
    }
}
