package Task3;

public class BankAccount {
    private double balance;

    public BankAccount() {
    }

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void withdraw(double amount) {
        if(amount > balance) {
            System.out.println("Insufficient balance");
            return;
        }
        this.balance -= amount;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

}
