package nl.rug.oop.demoAssignment;

import java.util.Scanner;

/**
 * The main class in which the program starts.
 */
public class Main {

    /**
     * Entry point to our very complex program.
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is your name?");
        String name = scanner.nextLine();

        Greeter greeter = new Greeter(name);

        System.out.println(greeter.sayHello());
        System.out.println("This is a very complex program.");
        System.out.println(greeter.sayGoodBye());
    }
}