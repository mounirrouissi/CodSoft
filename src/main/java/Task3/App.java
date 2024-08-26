package Task3;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello To Task 3");
        BankAccount account = new BankAccount(1000);
        ATM atm = new ATM(account);
        atm.run();

    }

}
