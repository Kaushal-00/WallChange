import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        
        ConfigManager config = new ConfigManager();
        WallManager changer = new WallManager();
        CmdManager cmdManager = new CmdManager();

        config.loadOrCreateConfig();
        changer.loadImages(config.getWallpaperFolder());
        changer.setNextWallpaper(config);
        cmdManager.setupCommand(config);
    }
}
