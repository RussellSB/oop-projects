package nl.rug.oop.introduction;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SessionManager {

    private GameSession gameSession;
    private static final String DEFAULT_NAME = "default"; // for the quicksave / quickload functionality
    private static final String DEFAULT_DIRECTORY = "Savegames"; // the directory in which the save files will be

    SessionManager(GameSession gameSession){
        this.gameSession = gameSession;
    }

    private int mainMenu() {
        Scanner in = new Scanner(System.in); //Scanner for input
        int input;

        gameSession.getPlayer().printPlayerStats();

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
            input = in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Input must be a number");
            input = -10000;
            in.nextLine();
        }

        return input;
    }

    void play() {
        boolean quit = false;

        do {
            if(!gameSession.getPlayer().stillAlive()){
                System.out.println("You've ceased to exist. Better luck next time!");
                break;
            }

            switch (this.mainMenu()) {
                case 0:
                    gameSession.getPlayer().getCurrentRoom().inspect();
                    break;
                case 1:
                    gameSession.getPlayer().getCurrentRoom().interactWithDoors(gameSession.getPlayer());
                    break;
                case 2:
                    gameSession.getPlayer().getCurrentRoom().interactWithNPCs(gameSession.getPlayer());
                    break;
                case 3:
                    quickSave();
                    break;
                case 4:
                    if(checkSavegames() == 0){ //proceeds only if there exists save files
                        this.gameSession = quickLoad();
                    }
                    break;
                case 5:
                    slowSave(DEFAULT_NAME); //TODO
                    break;
                case 6:
                    if(checkSavegames() == 0){ //proceeds only if there exists save files
                        printSavegames();
                        this.gameSession = slowLoad(DEFAULT_NAME); //TODO
                    }
                    break;
                case -1:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (!quit);

        System.out.println("Bye-bye!");
    }

    private void checkDefaultDir(){
        File serializedDir = new File(DEFAULT_DIRECTORY);
        if (!serializedDir.exists()){
            serializedDir.mkdir();
        }
    }

    private void save (String filepath) {
        try {
            checkDefaultDir(); //creates directory if it doesn't previously exist
            FileOutputStream fos = new FileOutputStream(filepath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.gameSession);
            oos.flush();
            fos.close();

        } catch (Exception e) {
            // TODO Improve error handling
            e.printStackTrace();
        }
    }

    private void slowSave(String filename){
        System.out.println("Saving as \"" + filename + ".ser\" in folder " + DEFAULT_DIRECTORY);
        save(DEFAULT_DIRECTORY + "/" + filename + ".ser");
    }

    private void quickSave() {
        System.out.println("Quick saving!");
        save(DEFAULT_DIRECTORY + "/" + DEFAULT_NAME + ".ser");
    }

    private GameSession load(String filepath){

        try {
            FileInputStream fis = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (GameSession) ois.readObject();
        } catch (Exception e) {
            // TODO Improve error handling
            e.printStackTrace();
            return this.gameSession; //returns current session if fails to load new session
        }
    }

    private GameSession slowLoad(String filename){
        System.out.println("Loading \"" + filename + ".ser\" from folder " + DEFAULT_DIRECTORY);
        return load(DEFAULT_DIRECTORY  + "/" + filename + ".ser");
    }

    private GameSession quickLoad(){
        System.out.println("Quick loading!");
        return load(DEFAULT_DIRECTORY + "/" + DEFAULT_NAME + ".ser");
    }

    private int checkSavegames(){
        checkDefaultDir();
        File folder = new File(DEFAULT_DIRECTORY);
        File[] listOfFiles = folder.listFiles();

        if(listOfFiles.length == 0){
            System.out.println("There are no game files saved!");
            return -1;
        }else{
            return 0;
        }
    }

    private void printSavegames(){
        checkDefaultDir();
        File folder = new File(DEFAULT_DIRECTORY);
        File[] listOfFiles = folder.listFiles();

        System.out.println("Game files saved: ");
        for(File file : listOfFiles){
            System.out.println(file.getName());
        }
    }

}