import java.util.LinkedHashMap;
import java.util.Map;

public class ATMInventory {

    private final Map<Integer, Integer> notes;

    public ATMInventory() {

        notes = new LinkedHashMap<>();

        // Initial ATM cash
        notes.put(500, 20);
        notes.put(200, 20);
        notes.put(100, 30);
    }

    // ==================== TOTAL CASH ====================

    public int getTotalCash() {

        int total = 0;

        for (Map.Entry<Integer, Integer> entry : notes.entrySet()) {

            total += entry.getKey() * entry.getValue();
        }

        return total;
    }

    // ==================== NOTE COUNT ====================

    public int getNoteCount(int denomination) {

        return notes.getOrDefault(denomination, 0);
    }

    // ==================== CASH AVAILABILITY ====================

    public boolean hasSufficientCash(int amount) {

        return amount <= getTotalCash();
    }

    // ==================== DISPENSE CASH ====================

    public Map<Integer, Integer> dispenseCash(int amount) {

        if (amount <= 0 || amount > getTotalCash()) {
            return null;
        }

        Map<Integer, Integer> dispensedNotes =
                new LinkedHashMap<>();

        int remaining = amount;

        // Try larger denominations first
        for (int denomination : notes.keySet()) {

            int availableNotes = notes.get(denomination);

            int requiredNotes =
                    Math.min(
                            remaining / denomination,
                            availableNotes
                    );

            if (requiredNotes > 0) {

                dispensedNotes.put(
                        denomination,
                        requiredNotes
                );

                remaining -=
                        denomination * requiredNotes;
            }
        }

        // ATM cannot form the requested amount
        if (remaining != 0) {
            return null;
        }

        // Remove dispensed notes from ATM
        for (Map.Entry<Integer, Integer> entry
                : dispensedNotes.entrySet()) {

            int denomination = entry.getKey();
            int count = entry.getValue();

            notes.put(
                    denomination,
                    notes.get(denomination) - count
            );
        }

        return dispensedNotes;
    }

    // ==================== DISPLAY INVENTORY ====================

    public void displayInventory() {

        System.out.println();
        System.out.println("------------- ATM CASH STATUS -------------");

        for (Map.Entry<Integer, Integer> entry : notes.entrySet()) {

            System.out.printf(
                    "INR %d notes : %d%n",
                    entry.getKey(),
                    entry.getValue()
            );
        }

        System.out.println("--------------------------------------------");
        System.out.printf(
                "Total ATM Cash: INR %d%n",
                getTotalCash()
        );
    }
}