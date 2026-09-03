import java.util.HashMap;
import java.util.Map;

public class Bank {

    private final Map<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    // ---------------- ACCOUNT MANAGEMENT ----------------

    public void addAccount(Account account) {

        if (account == null) {
            return;
        }

        accounts.put(account.getAccountNumber(), account);
    }

    public Account findAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public boolean accountExists(String accountNumber) {
        return accounts.containsKey(accountNumber);
    }

    // ---------------- AUTHENTICATION ----------------

    public Account authenticate(String accountNumber, String pin) {

        Account account = accounts.get(accountNumber);

        if (account != null && account.verifyPin(pin)) {
            return account;
        }

        return null;
    }

    // ---------------- TRANSFER ----------------

    public boolean transfer(
            String senderAccountNumber,
            String receiverAccountNumber,
            double amount) {

        Account sender = findAccount(senderAccountNumber);
        Account receiver = findAccount(receiverAccountNumber);

        if (sender == null || receiver == null) {
            return false;
        }

        return sender.transferTo(receiver, amount);
    }

    // ---------------- ACCOUNT COUNT ----------------

    public int getAccountCount() {
        return accounts.size();
    }
}