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

/*
Part 1: Linear Probing
Inserting key 14 at index 4
empty empty empty empty 14 empty empty empty empty empty 

Inserting key 17 at index 7
empty empty empty empty 14 empty empty 17 empty empty 

Inserting key 18 at index 8
empty empty empty empty 14 empty empty 17 18 empty 

Inserting key 3 at index 3
empty empty empty 3 14 empty empty 17 18 empty 

Inserting key 8 at index 9
empty empty empty 3 14 empty empty 17 18 8 

Inserting key 1 at index 1
empty 1 empty 3 14 empty empty 17 18 8 

Inserting key 18 at index 0
18 1 empty 3 14 empty empty 17 18 8 

Inserting key 11 at index 2
18 1 11 3 14 empty empty 17 18 8 

Inserting key 13 at index 5
18 1 11 3 14 13 empty 17 18 8 

Inserting key 20 at index 6
18 1 11 3 14 13 20 17 18 8 

Part 2: Double Hashing
Inserting key 2 at index 2
empty empty 2 empty empty empty empty empty empty empty empty empty empty empty empty empty empty empty empty empty 

Inserting key 12 at index 12
empty empty 2 empty empty empty empty empty empty empty empty empty 12 empty empty empty empty empty empty empty 

Inserting key 22 at index 8
empty empty 2 empty empty empty empty empty 22 empty empty empty 12 empty empty empty empty empty empty empty 

Inserting key 32 at index 15
empty empty 2 empty empty empty empty empty 22 empty empty empty 12 empty empty 32 empty empty empty empty 

Inserting key 42 at index 9
empty empty 2 empty empty empty empty empty 22 42 empty empty 12 empty empty 32 empty empty empty empty 

Inserting key 52 at index 16
empty empty 2 empty empty empty empty empty 22 42 empty empty 12 empty empty 32 52 empty empty empty 

Inserting key 62 at index 3
empty empty 2 62 empty empty empty empty 22 42 empty empty 12 empty empty 32 52 empty empty empty 

Inserting key 72 at index 17
empty empty 2 62 empty empty empty empty 22 42 empty empty 12 empty empty 32 52 72 empty empty 

Inserting key 82 at index 4
empty empty 2 62 82 empty empty empty 22 42 empty empty 12 empty empty 32 52 72 empty empty 

Inserting key 92 at index 18
empty empty 2 62 82 empty empty empty 22 42 empty empty 12 empty empty 32 52 72 92 empty 

Inserting key 14 at index 14
empty empty 2 62 82 empty empty empty 22 42 empty empty 12 empty 14 32 52 72 92 empty 

Inserting key 17 at index 1
empty 17 2 62 82 empty empty empty 22 42 empty empty 12 empty 14 32 52 72 92 empty 

Inserting key 18 at index 7
empty 17 2 62 82 empty empty 18 22 42 empty empty 12 empty 14 32 52 72 92 empty 

Inserting key 3 at index 11
empty 17 2 62 82 empty empty 18 22 42 empty 3 12 empty 14 32 52 72 92 empty 

Inserting key 8 at index 0
8 17 2 62 82 empty empty 18 22 42 empty 3 12 empty 14 32 52 72 92 empty 

Inserting key 1 at index 13
8 17 2 62 82 empty empty 18 22 42 empty 3 12 1 14 32 52 72 92 empty 

Inserting key 18 at index 10
8 17 2 62 82 empty empty 18 22 42 18 3 12 1 14 32 52 72 92 empty 

Inserting key 11 at index 6
8 17 2 62 82 empty 11 18 22 42 18 3 12 1 14 32 52 72 92 empty 

Inserting key 13 at index 19
8 17 2 62 82 empty 11 18 22 42 18 3 12 1 14 32 52 72 92 13 

Inserting key 20 at index 5
8 17 2 62 82 20 11 18 22 42 18 3 12 1 14 32 52 72 92 13 

Final hash table after double hashing:
8 17 2 62 82 20 11 18 22 42 18 3 12 1 14 32 52 72 92 13 
*/
