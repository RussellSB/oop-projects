package nl.rug.oop.introduction;

public class Main {
    public static void main(String[] args) {
        GameSession gameSession = new GameSession();
        gameSession.initWorld1();

        GameManager gameManager = new GameManager(gameSession);
        gameManager.play();
    }
}