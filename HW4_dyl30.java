public class HW4_dyl30 {
    public static void main(String[] args) {
        // Part 1: Linear Probing
        System.out.println("Part 1: Linear Probing");
        int[] keysForLinearProbing = {14, 17, 18, 3, 8, 1, 18, 11, 13, 20};
        int HashTableSize = 10;
        int[] linearProbingTable = new int[HashTableSize];
        java.util.Arrays.fill(linearProbingTable, -1); // Initialize with -1 to indicate empty slots
        for (int key : keysForLinearProbing) {
            insertLinearProbing(linearProbingTable, key);
        }

        // Part 2: Double Hashing
        System.out.println("Part 2: Double Hashing");
        int[] initialKeysForDoubleHashing = {2, 12, 22, 32, 42, 52, 62, 72, 82, 92};
        int[] additionalKeysForDoubleHashing = {14, 17, 18, 3, 8, 1, 18, 11, 13, 20};
        int doubleHashTableSize = 20;
        int[] doubleHashingTable = new int[doubleHashTableSize];
        java.util.Arrays.fill(doubleHashingTable, -1); // Initialize with -1 to indicate empty slots
        for (int key : initialKeysForDoubleHashing) {
            insertDoubleHashing(doubleHashingTable, key, doubleHashTableSize);
        }
        for (int key : additionalKeysForDoubleHashing) {
            insertDoubleHashing(doubleHashingTable, key, doubleHashTableSize);
        }

        // Print final hash table for double hashing
        System.out.println("Final hash table after double hashing:");
        printHashTable(doubleHashingTable);
    }

    // Methods for Part 1: Linear Probing
    private static void insertLinearProbing(int[] table, int key) {
        int index = key % table.length; // Hash function: key mod size of hash table (10)
        while (table[index] != -1) {
            index = (index + 1) % table.length; // Linear probing for next slot
        }
        table[index] = key; // Insert key into the hash table

        // Print the step-by-step progress
        System.out.println("Inserting key " + key + " at index " + index);
        printHashTable(table);
    }

    // Methods for Part 2: Double Hashing
    private static void insertDoubleHashing(int[] table, int key, int tableSize) {
        int index1 = h1(key, tableSize);
        int index2 = h2(key);

        int i = 0;
        int index = index1;

        while (table[index] != -1) {
            i++;
            index = (index1 + i * index2) % tableSize; // Double hashing formula
        }

        table[index] = key; // Insert key into the hash table

        // Print the step of insertion
        System.out.println("Inserting key " + key + " at index " + index);
        printHashTable(table);
    }

    private static int h1(int key, int tableSize) {
        return key % tableSize; // Hash function 1: key mod table size (20)
    }

    private static int h2(int key) {
        return 7 - (key % 7); // Hash function 2: 7 - key mod 7
    }

    // Method to print the hash table
    private static void printHashTable(int[] table) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == -1) {
                System.out.print("empty ");
            } else {
                System.out.print(table[i] + " ");
            }
        }
        System.out.println("\n");
    }
}
