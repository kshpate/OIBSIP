import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ATM {

    private final Bank bank;
    private final Scanner scanner;
    private final ATMInventory inventory;

    private Account currentAccount;

    public ATM(Bank bank) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
        this.inventory = new ATMInventory();
    }

    // ==================== START ATM ====================

    public void start() {

        printWelcomeMessage();

        while (true) {

            if (!login()) {
                System.out.println("\nThank you for using our ATM.");
                break;
            }

            showMainMenu();

            logout();

            System.out.print("\nDo you want to use another account? (Y/N): ");
            String choice = scanner.nextLine().trim();

            if (!choice.equalsIgnoreCase("Y")) {
                System.out.println("\nThank you for using our ATM!");
                break;
            }
        }
    }

    // ==================== WELCOME ====================

    private void printWelcomeMessage() {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("          WELCOME TO JAVA BANK ATM");
        System.out.println("==============================================");
    }

    // ==================== LOGIN ====================

    private boolean login() {

        final int MAX_ATTEMPTS = 3;

        System.out.println("\n------------- LOGIN -------------");

        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {

            System.out.print("Enter Account Number: ");
            String accountNumber = scanner.nextLine().trim();

            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine().trim();

            Account account = bank.authenticate(accountNumber, pin);

            if (account != null) {

                currentAccount = account;

                System.out.println("\nLogin successful!");
                System.out.println("Welcome, "
                        + currentAccount.getAccountHolderName() + "!");

                return true;
            }

            int remainingAttempts = MAX_ATTEMPTS - attempt;

            System.out.println("\nInvalid account number or PIN.");

            if (remainingAttempts > 0) {
                System.out.println(
                        "Attempts remaining: " + remainingAttempts
                );
            }
        }

        System.out.println("\nToo many failed login attempts.");

        return false;
    }

    // ==================== MAIN MENU ====================

    private void showMainMenu() {

        while (currentAccount != null) {

            System.out.println();
            System.out.println("==============================================");
            System.out.println("                 MAIN MENU");
            System.out.println("==============================================");
            System.out.println("1. Balance Inquiry");
            System.out.println("2. Cash Deposit");
            System.out.println("3. Cash Withdrawal");
            System.out.println("4. Money Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Change PIN");
            System.out.println("7. ATM Cash Status");
            System.out.println("8. Logout");
            System.out.println("==============================================");

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    showBalance();
                    break;

                case 2:
                    deposit();
                    break;

                case 3:
                    withdraw();
                    break;

                case 4:
                    transfer();
                    break;

                case 5:
                    showTransactionHistory();
                    break;

                case 6:
                    changePin();
                    break;

                case 7:
                    showATMStatus();
                    break;

                case 8:
                    return;

                default:
                    System.out.println(
                            "\nInvalid choice. Please select 1-8."
                    );
            }
        }
    }

    // ==================== BALANCE ====================

    private void showBalance() {

        System.out.println("\n------------- BALANCE -------------");

        System.out.printf(
                "Current Balance: INR %.2f%n",
                currentAccount.getBalance()
        );
    }

    // ==================== DEPOSIT ====================

    private void deposit() {

        System.out.println("\n------------- CASH DEPOSIT -------------");

        double amount = readAmount("Enter amount to deposit: ");

        if (currentAccount.deposit(amount)) {

            System.out.printf(
                    "\nINR %.2f deposited successfully.%n",
                    amount
            );

            System.out.printf(
                    "New Balance: INR %.2f%n",
                    currentAccount.getBalance()
            );

        } else {

            System.out.println(
                    "\nInvalid amount. Deposit must be greater than INR 0."
            );
        }
    }

    // ==================== WITHDRAW ====================

    private void withdraw() {

        System.out.println("\n------------- CASH WITHDRAWAL -------------");

        double amount = readAmount("Enter amount to withdraw: ");

        // ATM only dispenses notes of INR 100, INR 200 and INR 500
        if (amount % 100 != 0) {

            System.out.println(
                    "\nWithdrawal amount must be a multiple of INR 100."
            );

            return;
        }

        if (amount > currentAccount.getBalance()) {

            System.out.println("\nInsufficient account balance.");

            return;
        }

        if (!inventory.hasSufficientCash((int) amount)) {

            System.out.println(
                    "\nATM does not have sufficient cash."
            );

            System.out.printf(
                    "Available ATM Cash: INR %d%n",
                    inventory.getTotalCash()
            );

            return;
        }

        // Try to create the exact combination of notes
        Map<Integer, Integer> dispensedNotes =
                inventory.dispenseCash((int) amount);

        if (dispensedNotes == null) {

            System.out.println(
                    "\nATM cannot dispense the requested amount"
            );

            System.out.println(
                    "using the currently available denominations."
            );

            return;
        }

        // Deduct money from the user's account
        if (currentAccount.withdraw(amount)) {

            System.out.printf(
                    "\nINR %.2f withdrawal successful.%n",
                    amount
            );

            System.out.println("\nCash Dispensed:");

            for (Map.Entry<Integer, Integer> entry :
                    dispensedNotes.entrySet()) {

                System.out.printf(
                        "INR %d x %d%n",
                        entry.getKey(),
                        entry.getValue()
                );
            }

            System.out.printf(
                    "\nRemaining Account Balance: INR %.2f%n",
                    currentAccount.getBalance()
            );

            System.out.printf(
                    "Remaining ATM Cash: INR %d%n",
                    inventory.getTotalCash()
            );

        } else {

            System.out.println(
                    "\nWithdrawal failed."
            );
        }
    }

    // ==================== TRANSFER ====================

    private void transfer() {

        System.out.println("\n------------- MONEY TRANSFER -------------");

        System.out.print("Enter receiver account number: ");

        String receiverAccountNumber =
                scanner.nextLine().trim();

        if (!bank.accountExists(receiverAccountNumber)) {

            System.out.println("\nReceiver account does not exist.");

            return;
        }

        if (receiverAccountNumber.equals(
                currentAccount.getAccountNumber())) {

            System.out.println(
                    "\nYou cannot transfer money to your own account."
            );

            return;
        }

        double amount =
                readAmount("Enter amount to transfer: ");

        if (amount > currentAccount.getBalance()) {

            System.out.println("\nInsufficient balance.");

            return;
        }

        boolean successful = bank.transfer(
                currentAccount.getAccountNumber(),
                receiverAccountNumber,
                amount
        );

        if (successful) {

            System.out.printf(
                    "\nINR %.2f transferred successfully.%n",
                    amount
            );

            System.out.printf(
                    "Remaining Balance: INR %.2f%n",
                    currentAccount.getBalance()
            );

        } else {

            System.out.println(
                    "\nTransfer failed. Please try again."
            );
        }
    }

    // ==================== TRANSACTIONS ====================

    private void showTransactionHistory() {

        System.out.println(
                "\n------------- TRANSACTION HISTORY -------------"
        );

        List<Transaction> transactions =
                currentAccount.getTransactions();

        if (transactions.isEmpty()) {

            System.out.println("No transactions found.");

            return;
        }

        for (int i = 0; i < transactions.size(); i++) {

            System.out.println(
                    (i + 1) + ". " + transactions.get(i)
            );
        }
    }

    // ==================== ATM STATUS ====================

    private void showATMStatus() {

        inventory.displayInventory();
    }

    // ==================== CHANGE PIN ====================

    private void changePin() {

        System.out.println("\n------------- CHANGE PIN -------------");

        System.out.print("Enter current PIN: ");

        String currentPin = scanner.nextLine().trim();

        if (!currentAccount.verifyPin(currentPin)) {

            System.out.println("\nIncorrect current PIN.");

            return;
        }

        System.out.print("Enter new 4-digit PIN: ");

        String newPin = scanner.nextLine().trim();

        if (!isValidPin(newPin)) {

            System.out.println(
                    "\nPIN must contain exactly 4 digits."
            );

            return;
        }

        System.out.print("Confirm new PIN: ");

        String confirmPin = scanner.nextLine().trim();

        if (!newPin.equals(confirmPin)) {

            System.out.println("\nPINs do not match.");

            return;
        }

        if (newPin.equals(currentPin)) {

            System.out.println(
                    "\nNew PIN must be different from the old PIN."
            );

            return;
        }

        currentAccount.changePin(newPin);

        System.out.println("\nPIN changed successfully.");
    }

    // ==================== LOGOUT ====================

    private void logout() {

        if (currentAccount != null) {

            System.out.println(
                    "\nLogging out " +
                    currentAccount.getAccountHolderName() +
                    "..."
            );

            currentAccount = null;
        }
    }

    // ==================== INPUT HELPERS ====================

    private int readInt(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }

    private double readAmount(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {

                double amount = Double.parseDouble(input);

                if (amount > 0) {
                    return amount;
                }

                System.out.println(
                        "Amount must be greater than INR 0."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid amount. Please enter a valid number."
                );
            }
        }
    }

    private boolean isValidPin(String pin) {

        return pin.matches("\\d{4}");
    }
}