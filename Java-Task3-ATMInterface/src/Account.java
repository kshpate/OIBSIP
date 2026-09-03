import java.util.ArrayList;
import java.util.List;

public class Account {

    private final String accountNumber;
    private final String accountHolderName;

    private String pin;
    private double balance;

    private final List<Transaction> transactions;

    public Account(String accountNumber, String accountHolderName,
                   String pin, double initialBalance) {

        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.pin = pin;
        this.balance = initialBalance;

        this.transactions = new ArrayList<>();
    }

    // ---------------- GETTERS ----------------

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    // ---------------- PIN ----------------

    public boolean verifyPin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public void changePin(String newPin) {
        this.pin = newPin;
    }

    // ---------------- DEPOSIT ----------------

    public boolean deposit(double amount) {

        if (amount <= 0) {
            return false;
        }

        balance += amount;

        transactions.add(
                new Transaction(
                        "DEPOSIT",
                        amount,
                        balance
                )
        );

        return true;
    }

    // ---------------- WITHDRAW ----------------

    public boolean withdraw(double amount) {

        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;

        transactions.add(
                new Transaction(
                        "WITHDRAWAL",
                        amount,
                        balance
                )
        );

        return true;
    }

    // ---------------- TRANSACTION HISTORY ----------------

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    // ---------------- TRANSFER ----------------

    public boolean transferTo(Account receiver, double amount) {

        if (receiver == null || receiver == this) {
            return false;
        }

        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;

        transactions.add(
                new Transaction(
                        "TRANSFER TO " + receiver.getAccountNumber(),
                        amount,
                        balance
                )
        );

        receiver.balance += amount;

        receiver.transactions.add(
                new Transaction(
                        "TRANSFER FROM " + accountNumber,
                        amount,
                        receiver.balance
                )
        );

        return true;
    }
}