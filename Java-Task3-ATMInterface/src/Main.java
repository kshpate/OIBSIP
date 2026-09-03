public class Main {

    public static void main(String[] args) {

        // Create the bank
        Bank bank = new Bank();

        // Create sample accounts
        Account account1 = new Account(
                "1001",
                "Kshitij Patel",
                "1234",
                10000.00
        );

        Account account2 = new Account(
                "1002",
                "Rahul Sharma",
                "5678",
                15000.00
        );

        Account account3 = new Account(
                "1003",
                "Priya Shah",
                "9012",
                20000.00
        );

        // Add accounts to the bank
        bank.addAccount(account1);
        bank.addAccount(account2);
        bank.addAccount(account3);

        // Create and start ATM
        ATM atm = new ATM(bank);

        atm.start();
    }
}