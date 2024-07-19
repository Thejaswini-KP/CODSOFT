package atminterface;
import java.util.Scanner;

public class ATM {

    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Withdraw Cash");
        System.out.println("2. Deposit Cash");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    public void performTransaction(Scanner scanner) {
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                withdrawCash(scanner);
                break;
            case 2:
                depositCash(scanner);
                break;
            case 3:
                checkBalance();
                break;
            case 4:
                System.out.println("Exiting ATM...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void withdrawCash(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. Please collect your cash.");
        } else {
            System.out.println("Insufficient funds. Please try a lower amount.");
        }
    }

    private void depositCash(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        account.deposit(amount);
        System.out.println("Deposit successful. Your new balance is: " + account.getBalance());
    }

    private void checkBalance() {
        System.out.println("Your current balance is: " + account.getBalance());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount(100000.00); // Initial balance of $1000
        ATM atm = new ATM(account);

        while (true) {
            atm.displayMenu();
            atm.performTransaction(scanner);

            if (scanner.nextLine().toLowerCase().equals("n")) {
                break;
            }
        }

        scanner.close();
        System.out.println("Thank you for using our ATM service.");
    }
}

class BankAccount {

    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }

    public void deposit(double amount) {
        balance += amount;
    }
}
