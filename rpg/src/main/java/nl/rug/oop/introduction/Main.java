package nl.rug.oop.introduction;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in); //Scanner for input

        int choice = 0;

        while(true){
            System.out.println("What do you want to do?");
            System.out.println("(0)\tLook around");
            choice = in.nextInt();

            if(choice == 0){
                break;
            }
            else if(choice == 1){
                break;
            }
        }
        
    }
}