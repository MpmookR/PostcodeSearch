package Menu;

import AVL.AVL;
import BST.BST;
import MinHeap.MinHeap;
import benchmark.BenchmarkService;
import interfaces.PostcodeManager;

import java.util.*;

public class BenchmarkMenu {

    private final Scanner scanner = new Scanner(System.in);
    private List<String> postcodes = new ArrayList<>();
    private PostcodeManager structure;
    private String structureName = "None";
    private String loadedFilename = "None";

    public void showMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Benchmarking Menu ===");
            System.out.println("1. Run full benchmark across all files & structures");
            System.out.println("2. Run benchmark on selected file and structure");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    runFullBenchmarkAll();
                    break;
                case "2":
                    runSelectedBenchmark();
                    break;
                case "3":
                    System.out.println("Exiting benchmarking menu...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void runSelectedBenchmark() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Benchmark on Selected File and Structure ---");
            System.out.println("Current file: " + loadedFilename);
            System.out.println("Current structure: " + structureName);
            System.out.println("-----------------------------------------------");
            System.out.println("1. Load postcode file");
            System.out.println("2. Choose structure to test (BST, AVL, MinHeap)");
            System.out.println("3. Run insert benchmark");
            System.out.println("4. Run search benchmark");
            System.out.println("5. Run delete benchmark");
            System.out.println("6. Run count benchmark");
            System.out.println("7. Run in-order benchmark");
            System.out.println("8. Back to main menu");
            System.out.print("Choose an option: ");
    
            String subChoice = scanner.nextLine().trim();
    
            switch (subChoice) {
                case "1":
                    loadPostcodeFile();
                    break;
                case "2":
                    chooseStructure();
                    break;
                case "3":
                    runInsertBenchmark();
                    break;
                case "4":
                    runSearchBenchmark();
                    break;
                case "5":
                    runDeleteBenchmark();
                    break;
                case "6":
                    runCountBenchmark();
                    break;
                case "7":
                    runInOrderBenchmark();
                    break;
                case "8":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    private void runCountBenchmark() {
        if (!checkReady()) return;
    
        long start = System.nanoTime();
        int count = structure.count();
        long end = System.nanoTime();
    
        System.out.println("\n=============================================================");
        System.out.println("Count Benchmark for " + structureName);
        System.out.println("File: " + loadedFilename);
        System.out.println("-------------------------------------------------------------");
        System.out.printf("Postcode Count: %d%n", count);
        System.out.printf("Time taken: %d ms%n", (end - start) / 1_000_000);
        System.out.println("=============================================================");
    }

    private void loadPostcodeFile() {
        String[] files = {
                "1000_London_Postcodes.txt",
                "2000_London_Postcodes.txt",
                "4000_London_Postcodes.txt",
                "8000_London_Postcodes.txt",
                "16000_London_Postcodes.txt"
        };

        System.out.println("Select a postcode file to load:");
        for (int i = 0; i < files.length; i++) {
            System.out.printf("%d. %s\n", i + 1, files[i]);
        }
        System.out.print("Enter your choice: ");

        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (index >= 0 && index < files.length) {
                loadedFilename = files[index];
                postcodes = BenchmarkService.loadPostcodes("inputFiles/" + loadedFilename);
                System.out.println("Loaded " + postcodes.size() + " postcodes from " + loadedFilename);
            } else {
                System.out.println("Invalid file choice.");
            }
        } catch (Exception e) {
            System.out.println("Failed to load file: " + e.getMessage());
        }
    }

    private void chooseStructure() {
        System.out.println("Choose structure to test:");
        System.out.println("1. BST");
        System.out.println("2. AVL");
        System.out.println("3. MinHeap");
        System.out.print("Enter your choice: ");

        switch (scanner.nextLine().trim()) {
            case "1":
                structure = new BST();
                structureName = "BST";
                break;
            case "2":
                structure = new AVL();
                structureName = "AVL";
                break;
            case "3":
                if (postcodes.isEmpty()) {
                    System.out.println("Please load a postcode file first — MinHeap needs to know the size.");
                    return;
                }
                structure = new MinHeap(postcodes.size());
                structureName = "MinHeap";
                break;
            default:
                System.out.println("Invalid structure choice.");
                structure = null;
                structureName = "None";
        }

        if (structure != null) {
            System.out.println("Structure set to " + structureName);
        }
    }

    private void runInsertBenchmark() {
        if (!checkReady()) return;

        long time = BenchmarkService.runInsert(structure, postcodes);
        System.out.println("\n=============================================================");
        System.out.println("Insert Benchmark for " + structureName);
        System.out.println("File: " + loadedFilename + " | Size: " + postcodes.size());
        System.out.println("-------------------------------------------------------------");
        System.out.printf("Time taken: %d ms%n", time);
        System.out.println("=============================================================");
    }

    private void runSearchBenchmark() {
        if (!checkReady()) return;

        long time = BenchmarkService.runSearch(structure, postcodes);
        System.out.println("\n=============================================================");
        System.out.println("Search Benchmark for " + structureName);
        System.out.println("File: " + loadedFilename + " | Size: " + postcodes.size());
        System.out.println("-------------------------------------------------------------");
        System.out.printf("Time taken: %d ms%n", time);
        System.out.println("=============================================================");
    }

    private void runDeleteBenchmark() {
        if (!checkReady()) return;

        long time = BenchmarkService.runDelete(structure, postcodes);
        System.out.println("\n=============================================================");
        System.out.println("Delete Benchmark for " + structureName);
        System.out.println("File: " + loadedFilename + " | Size: " + postcodes.size());
        System.out.println("-------------------------------------------------------------");
        System.out.printf("Time taken: %d ms%n", time);
        System.out.println("=============================================================");
    }

    private void runInOrderBenchmark() {
        if (!checkReady()) return;
    
        long start = System.nanoTime();
        List<String> sorted = structure.getAllPostcodes();
        long end = System.nanoTime();
    
        System.out.println("\n=============================================================");
        System.out.println("In-Order Benchmark for " + structureName);
        System.out.println("File: " + loadedFilename);
        System.out.println("-------------------------------------------------------------");
        System.out.printf("Sorted postcodes retrieved: %d%n", sorted.size());
        System.out.printf("Time taken: %d ms%n", (end - start) / 1_000_000);
        System.out.println("=============================================================");
    }

    private void runFullBenchmarkAll() {
        try {
            BenchmarkService.runFullBenchmarkAcrossFiles();
        } catch (Exception e) {
            System.out.println("Failed to run full benchmark: " + e.getMessage());
        }
    }

    // Validation helper before running benchmarks
    private boolean checkReady() {
        if (postcodes.isEmpty()) {
            System.out.println("Please load a postcode file first.");
            return false;
        }
        if (structure == null) {
            System.out.println("Please choose a data structure to benchmark.");
            return false;
        }
        return true;
    }
}
