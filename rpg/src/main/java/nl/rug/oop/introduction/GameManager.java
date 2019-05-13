package nl.rug.oop.introduction;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class GameManager {
    // Attributes
    private static final String TITLE_SCREEN_FILE = "TitleScreen.txt";
    private static final String DEFAULT_SAVE_NAME = "QuickSave"; // For the quickSave/quickLoad functionality
    private static final String DEFAULT_SAVE_DIRECTORY = "Savegames"; // The directory in which the save files will be
    private GameSession gameSession;


    // Constructor
    GameManager(GameSession gameSession) {
        this.gameSession = gameSession;
    }


    // Other methods
    private int mainMenu() {
        Scanner in = new Scanner(System.in); // Scanner for input
        int selectedMenuItem;

        System.out.println("\nWhat do you want to do?");
        System.out.println("  (-1) Give up");
        System.out.println("   (0) Spectate the environment");
        System.out.println("   (1) Look for doors");
        System.out.println("   (2) Look for living entities satisfying the seven vital functions");
        System.out.println("   (3) QuickSave");
        System.out.println("   (4) QuickLoad");
        System.out.println("   (5) Save");
        System.out.println("   (6) Load");

        System.out.print("-> ");

        try {
            selectedMenuItem = in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("** Input must be a number **");
            selectedMenuItem = -10000; // Invalid choice
            in.nextLine(); // Discards the rest of the input
        } catch (Exception e) {
            System.out.println("** A weird error occurred: " + e.toString() + " **");
            selectedMenuItem = -10000; // Invalid choice
            in.nextLine(); // Discards the rest of the input
        }

        return selectedMenuItem;
    }

    void play() {
        boolean quit = false;

        printTitleScreen();
        gameSession.getPlayer().printPlayerStats();

        do {
            if (!gameSession.getPlayer().stillAlive()) {
                System.out.println("You've ceased to exist. Better luck next time!");
                break;
            }

            switch (this.mainMenu()) {
                case 0:
                    gameSession.getPlayer().getCurrentRoom().inspect();
                    gameSession.getPlayer().printPlayerStats();
                    break;
                case 1:
                    gameSession.getPlayer().getCurrentRoom().interactWithDoors(gameSession.getPlayer());
                    gameSession.getPlayer().printPlayerStats();
                    break;
                case 2:
                    gameSession.getPlayer().getCurrentRoom().interactWithNPCs(gameSession.getPlayer());
                    gameSession.getPlayer().printPlayerStats();
                    break;
                case 3:
                    quickSave();
                    break;
                case 4:
                    quickLoad();
                    gameSession.getPlayer().printPlayerStats();
                    break;
                case 5:
                    luxuriousSave();
                    break;
                case 6:
                    luxuriousLoad();
                    gameSession.getPlayer().printPlayerStats();
                    break;
                case -1:
                    quit = true;
                    break;
                default:
                    System.out.println("** Invalid choice **");
            }
        } while (!quit);

        System.out.println("\nBye-bye!\n");
    }

    private void printTitleScreen() {
        try {
            FileReader fr = new FileReader(TITLE_SCREEN_FILE);
            BufferedReader br = new BufferedReader(fr);
            String buffer;
            while ((buffer = br.readLine()) != null) {
                System.out.println(buffer);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("** Title screen couldn't be shown: " + e.toString() + " **");
        }
    }

    private void checkDefaultDir() {
        File folder = new File(DEFAULT_SAVE_DIRECTORY);

        if (!folder.exists()) {
            boolean created = folder.mkdir();

            if(!created)
                System.out.println("** Unable to create the default save directory **");
        }
    }

    private void save(String filepath) {
        try {
            FileOutputStream fos = new FileOutputStream(filepath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.gameSession);
            oos.flush();
            fos.close();
            System.out.println("** Game progress saved! **");
        } catch (Exception e) {
            System.out.println("** A weird error occurred: " + e.toString() + " **");
        }
    }

    private void load(String filepath) {
        try {
            FileInputStream fis = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.gameSession =  (GameSession) ois.readObject();
            System.out.println("** Game progress loaded! **");
        } catch (FileNotFoundException e) {
            System.out.println("** ERROR: The file \"" + filepath + "\" doesn't exist! **");
        } catch (Exception e) {
            System.out.println("** A weird error occurred: " + e.toString() + " **");
        }
    }

    private void quickSave() {
        String filepath = DEFAULT_SAVE_DIRECTORY + "/" + DEFAULT_SAVE_NAME + ".ser";

        System.out.println("** Saving game progress in \"" + filepath + "\" **");

        checkDefaultDir();
        save(filepath);
    }

    private void quickLoad() {
        if(!saveGameFilesExist()) {
            System.out.println("** There are no save files! **");
            return;
        }

        String filepath = DEFAULT_SAVE_DIRECTORY + "/" + DEFAULT_SAVE_NAME + ".ser";

        System.out.println("** Loading game progress from \"" + filepath + "\" **");

        load(filepath);
    }

    private void luxuriousSave() {
        Scanner in = new Scanner(System.in); // Scanner for input
        String fileName;
        String filePath;

        System.out.println("Filename?");
        fileName = in.nextLine();

        if(fileName.endsWith(".ser"))
            filePath = DEFAULT_SAVE_DIRECTORY + "/" + fileName;
        else
            filePath = DEFAULT_SAVE_DIRECTORY + "/" + fileName + ".ser";

        System.out.println("** Saving game progress in \"" + filePath + "\" **");

        checkDefaultDir();
        save(filePath);
    }

    private void luxuriousLoad() {
        Scanner in = new Scanner(System.in); // Scanner for input
        int selectedFile;
        String filePath;
        List<File> files = getSaveFilesFromDir();

        if(files.isEmpty()) {
            System.out.println("** There are no save files! **");
            return;
        }

        System.out.println("Which file?  (-1: none)");

        for(int i=0; i<files.size(); i++)
        {
            System.out.println("  (" + i + ") " + files.get(i).toString());
        }

        System.out.print("-> ");

        try {
            selectedFile = in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("** Input must be a number **");
            selectedFile = -1; // Cancel
            in.nextLine(); // Discards the rest of the input
        } catch (Exception e) {
            System.out.println("** An error occurred: " + e.toString() + " **");
            selectedFile = -1; // Cancel
            in.nextLine(); // Discards the rest of the input
        }

        if(selectedFile != -1) {
            filePath = files.get(selectedFile).getPath();
            System.out.println("** Loading game progress from \"" + filePath + "\" **");
            load(filePath);
        }
    }

    private List<File> getSaveFilesFromDir() {
        List<File> saveFiles = new ArrayList<>();

        File folder = new File(DEFAULT_SAVE_DIRECTORY);

        File[] files = folder.listFiles();

        if (files != null)
            for (File file : files)
                if (file.getPath().endsWith(".ser"))
                    saveFiles.add(file);

        return saveFiles;
    }

    private boolean saveGameFilesExist() {
        return !getSaveFilesFromDir().isEmpty();
    }
}