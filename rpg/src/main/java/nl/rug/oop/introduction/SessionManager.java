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
                    this.gameSession = quickLoad();
                    break;
                case 5:
                    save(""); //TODO
                    break;
                case 6:
                    load(""); //TODO
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

    private void save (String filename) {
        try {
            checkDefaultDir(); //creates directory if it doesn't previously exist
            FileOutputStream fos = new FileOutputStream(DEFAULT_DIRECTORY + "/" + filename + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.gameSession);
            System.out.println("Successfully saved as \"" + filename + "\".ser");
            oos.flush();
            fos.close();

        } catch (Exception e) {
            // TODO Improve error handling
            e.printStackTrace();
        }
    }

    private void quickSave() {
        save(DEFAULT_NAME);
    }

    private GameSession load(String filename){

        try {
            FileInputStream fis = new FileInputStream(DEFAULT_DIRECTORY  + "/" + filename + ".ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            System.out.println("Successfully loaded \"" + filename + "\".ser from folder");
            return (GameSession) ois.readObject();
        } catch (Exception e) {
            // TODO Improve error handling
            e.printStackTrace();
            return this.gameSession; //returns current session if fails to load new session
        }
    }

    private GameSession quickLoad(){
        return load(DEFAULT_NAME);
    }

}