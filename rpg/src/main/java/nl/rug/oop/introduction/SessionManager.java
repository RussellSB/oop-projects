package nl.rug.oop.introduction;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SessionManager {

    private GameSession gameSession;

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
                    System.out.println("NOT FULLY IMPLEMENTED YET"); // TODO
                    quickSave(gameSession);
                    break;
                case 4:
                    System.out.println("NOT IMPLEMENTED YET"); // TODO
                    gameSession = quickLoad(gameSession);
                    break;
                case 5:
                    System.out.println("NOT IMPLEMENTED YET"); // TODO
                    break;
                case 6:
                    System.out.println("NOT IMPLEMENTED YET"); // TODO
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

    private void save (GameSession gameSession, String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gameSession);
            oos.flush();
            fos.close();
        } catch (Exception e) {
            // TODO Improve error handling
            e.printStackTrace();
        }
    }

    private void quickSave(GameSession gameSession) {
        save(gameSession,"test.ser");
    }

    private GameSession load(String filename, GameSession gameSession){

        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (GameSession) ois.readObject();
        } catch (Exception e) {
            // TODO Improve error handling
            e.printStackTrace();
            return gameSession; //returns current session if fails to load new session
        }
    }

    private GameSession quickLoad(GameSession gameSession){
        return load("test.ser", gameSession);
    }

}
