package nl.rug.oop.introduction;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Main {
    public static void main(String[] args) {

        GameSession gameSession = new GameSession();
        gameSession.initWorld1();

        SessionManager sessionManager = new SessionManager(gameSession);
        sessionManager.play();

    }

        /*Game game = new Game();

        System.out.println("LETS LOAD THAT SHIT");

        try {
            FileInputStream fis = new FileInputStream("test.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            game = (Game) ois.readObject();
        } catch (Exception e) {
            // TODO Improve error handling
            e.printStackTrace();
        }

        game.play();*/
    //}
}