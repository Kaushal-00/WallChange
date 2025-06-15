# **WallChange:**

- Automatic Wallpaper Rotator for Windows.
- **WallChange** is a lightweight Java application that allows users to automatically cycle through wallpapers using any of the following three methods:
    - Manually running the Java program
    - Using a terminal command - `wallchange` 
    - Pressing a keyboard shortcut - `CTRL+ALT+W.`

---

## **Featurs:**

- Change wallpapers automatically in sequence
- Remembers the last used wallpaper using `config.txt`
- Custom folder path support
- Terminal command support - `wallchange`
- Global keyboard shortcut support - `CTRL+ALT+W`
- Supports `.jpg`, `.jpeg`, `.png`, and `.bmp` images
- Adds `.bat` file to use command from terminal
- Creates `.lnk` shortcut on Desktop with hotkey support
- Lightweight and works offline

---

## **Initial Setup:**

- Run the program for the first time:
- Enter the path to your wallpaper folder when prompted (e.g., `C:\WallPapers`).
- The program will:
    - Create a `config.txt` file to store settings
    - Change the wallpaper to the next image
    - Set up terminal command (e.g., `wallchange`)
    - Create a Desktop shortcut with the hotkey `CTRL+ALT+W`

---

## **Project Structure:**

```
WallChange/
│
├── Main.java              # Main program logic
├── ConfigManager.java     # Handles saving/loading config file
├── WallManager.java       # Loads images and changes wallpaper
├── CmdManager.java        # Creates terminal command (.bat file)
├── ShortcutManager.java   # Creates desktop shortcut with hotkey
├── config.txt             # Automatically generated on first run
```

---

## **How It Works:**

- Wallpapers are loaded from the user-defined folder.
- The config.txt file stores:
    - folder – path to wallpaper images
    - index – tracks the current wallpaper
    - command – terminal command name
    - shortcut – keyboard hotkey (`CTRL+ALT+W`)
- The `.ps1` script and `.bat` files enable command and shortcut execution.

---

## **Usage:**

- Run manually:
    - java Main
- Run using command in terminal:
    - `wallchange22`
- Run using shortcut:
    - Press `CTRL+ALT+W`

---

## **Notes:**

- Shortcut may not work if:
    - `.lnk` file is moved or deleted
    - Hotkey conflicts with another system/app shortcut
- You can manually re-run the program to reconfigure everything.

---