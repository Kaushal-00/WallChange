import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        
        ConfigManager config = new ConfigManager();
        WallManager changer = new WallManager();
        CmdManager cmdManager = new CmdManager();
        ShortcutManager shortcutManager = new ShortcutManager();

        config.loadOrCreateConfig();
        changer.loadImages(config.getWallpaperFolder());
        changer.setNextWallpaper(config);
        cmdManager.setupCommand(config);
        shortcutManager.setupShortcut(config);
    }
}
