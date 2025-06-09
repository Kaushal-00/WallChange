import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ConfigManager config = new ConfigManager();
        WallpaperChanger changer = new WallpaperChanger();

        config.loadOrCreateConfig();
        changer.loadImages(config.getWallpaperFolder());
        changer.setNextWallpaper(config);
    }
}
