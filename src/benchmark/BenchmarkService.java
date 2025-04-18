package benchmark;

import AVL.AVL;
import BST.BST;
import MinHeap.MinHeap;
import interfaces.PostcodeManager;

import java.util.*;

public class BenchmarkService {

    private static final String[] FILES = {
            "1000_London_Postcodes.txt",
            "2000_London_Postcodes.txt",
            "4000_London_Postcodes.txt",
            "8000_London_Postcodes.txt",
            "16000_London_Postcodes.txt"
    };

    // Runs insert benchmark and returns the time in milliseconds
    public static long runInsert(PostcodeManager structure, List<String> postcodes) {
        return Benchmark.timeInsert(structure, postcodes);
    }

    // Runs search benchmark and returns the time in milliseconds
    public static long runSearch(PostcodeManager structure, List<String> queries) {
        return Benchmark.timeSearch(structure, queries);
    }

    // Runs delete benchmark and returns the time in milliseconds
    public static long runDelete(PostcodeManager structure, List<String> postcodes) {
        return Benchmark.timeDelete(structure, postcodes);
    }

    // Runs full benchmark across all files and data structures
    public static void runFullBenchmarkAcrossFiles() throws Exception {
        Map<String, List<Long>> insertResults = new LinkedHashMap<>();
        Map<String, List<Long>> searchResults = new LinkedHashMap<>();
        Map<String, List<Long>> deleteResults = new LinkedHashMap<>(); // NEW: delete results

        String[] structures = { "BST", "AVL", "MinHeap" };

        // Initialize result lists for each structure
        for (String structure : structures) {
            insertResults.put(structure, new ArrayList<>());
            searchResults.put(structure, new ArrayList<>());
            deleteResults.put(structure, new ArrayList<>());
        }

        // Used for collecting structured lines of result outputs
        Map<String, List<String>> summaryTable = new LinkedHashMap<>();

        // Iterate over each structure and perform benchmarks for all files
        for (String structure : structures) {
            List<String> lines = new ArrayList<>();
            lines.add("=============================================================");
            lines.add("Running Benchmarks for: " + structure);
            lines.add("=============================================================");

            for (String file : FILES) {
                List<String> postcodes = BenchmarkUtils.loadPostcodes("inputFiles/" + file);
                int n = postcodes.size();

                // Instantiate correct data structure implementation
                PostcodeManager structureInstance;
                if (structure.equals("BST"))
                    structureInstance = new BST();
                else if (structure.equals("AVL"))
                    structureInstance = new AVL();
                else
                    structureInstance = new MinHeap(n);

                // Perform insert, search, and delete benchmark
                long insertTime = Benchmark.timeInsert(structureInstance, postcodes);
                long searchTime = Benchmark.timeSearch(structureInstance, postcodes);
                long deleteTime = Benchmark.timeDelete(structureInstance, postcodes); // NEW

                // Store results
                insertResults.get(structure).add(insertTime);
                searchResults.get(structure).add(searchTime);
                deleteResults.get(structure).add(deleteTime);

                lines.add(String.format("File: %-25s | Size: %5d | Insert: %4d ms | Search: %4d ms | Delete: %4d ms",
                        file, n, insertTime, searchTime, deleteTime));
            }
            summaryTable.put(structure, lines);
        }

        // Print all benchmark summaries grouped by structure
        for (String structure : structures) {
            for (String line : summaryTable.get(structure)) {
                System.out.println(line);
            }
            System.out.println();
        }

        // Output doubling ratio analysis for insert, search, and delete
        printDoublingRatios("Insert", insertResults);
        printDoublingRatios("Search", searchResults);
        printDoublingRatios("Delete", deleteResults);
    }

    // Calculates and prints T(2n)/T(n) ratios for insert/search/delete timings
    private static void printDoublingRatios(String label, Map<String, List<Long>> resultMap) {
        System.out.println("=============================================================");
        System.out.println(label + " Time Doubling Ratios (T(2n)/T(n))");
        System.out.println("=============================================================");

        for (Map.Entry<String, List<Long>> entry : resultMap.entrySet()) {
            String structure = entry.getKey();
            List<Long> times = entry.getValue();
            System.out.println("\n" + structure + " " + label + " Times:");
            for (int i = 0; i < times.size(); i++) {
                System.out.printf("T(%-5s) = %4d ms", FILES[i].replace("_London_Postcodes.txt", ""), times.get(i));
                if (i > 0) {
                    long prev = times.get(i - 1);
                    long curr = times.get(i);
                    if (prev == 0) {
                        System.out.print("  | Ratio: undefined (prev = 0)");
                    } else {
                        double ratio = (double) curr / prev;
                        System.out.printf("  | Ratio: %.2f", ratio);
                    }
                }
                System.out.println();
            }
        }
    }
}

// This class is responsible for executing all benchmark operations across different
// data structures (BST, AVL, MinHeap) and postcode datasets.
// It delegates time measurement to the Benchmark class and handles output formatting
// and doubling ratio calculation.

