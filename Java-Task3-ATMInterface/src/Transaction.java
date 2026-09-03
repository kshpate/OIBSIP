import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Transaction {

    private final String transactionId;
    private final String type;
    private final double amount;
    private final double balanceAfter;
    private final LocalDateTime dateTime;

    public Transaction(String type, double amount, double balanceAfter) {

        this.transactionId = generateTransactionId();
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.dateTime = LocalDateTime.now();
    }

    // ==================== GETTERS ====================

    public String getTransactionId() {
        return transactionId;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // ==================== TRANSACTION ID ====================

    private String generateTransactionId() {

        return "TXN-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();
    }

    // ==================== DISPLAY ====================

    @Override
    public String toString() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy HH:mm:ss"
                );

        return String.format(
                "ID: %-13s | %-30s | Amount: INR %-10.2f | Balance: INR %-10.2f | %s",
                transactionId,
                type,
                amount,
                balanceAfter,
                dateTime.format(formatter)
        );
    }
}