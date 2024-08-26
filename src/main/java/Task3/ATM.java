package Task3;

import java.util.Scanner;

public class ATM {
    private BankAccount account;
    private Scanner scanner = new Scanner(System.in);

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void displayMenu() {
        System.out.println("\n===== ATM Menu =====");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void run() {
        while (true) {
            displayMenu();
            System.out.print("Enter your choice (1-4): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void checkBalance() {
        System.out.println("Your balance is: $" + account.getBalance());
    }

    private synchronized void deposit() {
        System.out.print("Enter the deposit amount: $");
        double amount = scanner.nextDouble();
        account.deposit(amount);
        System.out.println("Deposit successful. Your new balance is: $" + account.getBalance());
    }

    private synchronized void withdraw() {
        System.out.print("Enter the withdrawal amount: $");
        double amount = scanner.nextDouble();
        if (amount > account.getBalance()) {
            System.out.println("Insufficient balance.");
        }
    }
}