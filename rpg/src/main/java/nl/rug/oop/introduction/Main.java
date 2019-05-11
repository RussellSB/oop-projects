package nl.rug.oop.introduction;

public class Main {
    public static void main(String[] args) {
        GameSession gameSession = new GameSession();
        gameSession.initWorld1();

        SessionManager sessionManager = new SessionManager(gameSession);
        sessionManager.play();
    }
}