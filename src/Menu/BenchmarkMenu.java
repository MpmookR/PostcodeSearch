package Menu;

import BST.BST;
import AVL.AVL;
import MinHeap.MinHeap;
import interfaces.PostcodeManager;
import benchmark.Benchmark;
import benchmark.BenchmarkUtils;

import java.util.*;

public class BenchmarkMenu {

    private final Scanner scanner = new Scanner(System.in);
    private List<String> postcodes = new ArrayList<>();
    private List<String> searchSample = new ArrayList<>();
    private PostcodeManager structure;
    private String structureName = "None";
    private String loadedFilename = "None";

    public void showMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Benchmarking Menu ===");
            System.out.println("Current file: " + loadedFilename);
            System.out.println("Current structure: " + structureName);
            System.out.println("-----------------------------------------------");
            System.out.println("1. Load postcode file");
            System.out.println("2. Choose structure to test (BST, AVL, MinHeap)");
            System.out.println("3. Run insert benchmark");
            System.out.println("4. Run search benchmark");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
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
                    System.out.println("Exiting benchmarking menu...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void loadPostcodeFile() {
        System.out.println("Select a postcode file to load:");
        System.out.println("1. 1000_London_Postcodes.txt");
        System.out.println("2. 2000_London_Postcodes.txt");
        System.out.println("3. 4000_London_Postcodes.txt");
        System.out.println("4. 8000_London_Postcodes.txt");
        System.out.println("5. 16000_London_Postcodes.txt");

        String[] files = {
                "1000_London_Postcodes.txt",
                "2000_London_Postcodes.txt",
                "4000_London_Postcodes.txt",
                "8000_London_Postcodes.txt",
                "16000_London_Postcodes.txt"
        };

        System.out.print("Enter your choice: ");
        String input = scanner.nextLine().trim();

        try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < files.length) {
                loadedFilename = files[index];
                postcodes = BenchmarkUtils.loadPostcodes("inputFiles/" + loadedFilename);
                searchSample = BenchmarkUtils.getSample(postcodes, 100);
                System.out.println("Loaded " + postcodes.size() + " postcodes.");
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
        String input = scanner.nextLine().trim();

        switch (input) {
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
                    System.out.println("Please load a postcode file first — MinHeap needs to know how big to be.");
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
        if (!checkReady())
            return;

        long time = Benchmark.timeInsert(structure, postcodes);
        System.out.println("Insert Benchmark (" + structureName + ", " + loadedFilename + "): " + time + " ms");
    }

    private void runSearchBenchmark() {
        if (!checkReady())
            return;

        long time = Benchmark.timeSearch(structure, searchSample);
        System.out.println("Search Benchmark (" + structureName + ", 100 samples): " + time + " ms");
    }

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
