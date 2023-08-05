import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SystemManager system = new SystemManager();

        try {
            int userSelection = 0;
            while(userSelection != -1){
                System.out.println("Welcome to the Student Record System!");
                System.out.println("What would you like to do ?");

                userSelection = system.getUserInt();
                system.menu(userSelection);
            }
        } catch (InputMismatchException e) {
            System.out.println("error at DO level");
        }

    }
}