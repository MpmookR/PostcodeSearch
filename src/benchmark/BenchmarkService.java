package benchmark;

import AVL.AVL;
import BST.BST;
import MinHeap.MinHeap;
import interfaces.PostcodeManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BenchmarkService {

    private static final String[] FILES = {
            "1000_London_Postcodes.txt",
            "2000_London_Postcodes.txt",
            "4000_London_Postcodes.txt",
            "8000_London_Postcodes.txt",
            "16000_London_Postcodes.txt"
    };

    // Individual Benchmark Runners

    // Runs count benchmark and returns the time in milliseconds
    public static long runCount(PostcodeManager structure) {
        return Benchmark.timeCount(structure);
    }

    public static long runInsert(PostcodeManager structure, List<String> postcodes) {
        return Benchmark.timeInsert(structure, postcodes);
    }

    public static long runSearch(PostcodeManager structure, List<String> queries) {
        return Benchmark.timeSearch(structure, queries);
    }

    public static long runDelete(PostcodeManager structure, List<String> postcodes) {
        return Benchmark.timeDelete(structure, postcodes);
    }

    public static long runInOrder(PostcodeManager structure) {
        return Benchmark.timeInOrder(structure);
    }

    // Runs full benchmark across all files and data structures

    public static void runFullBenchmarkAcrossFiles() throws Exception {
        Map<String, List<Long>> insertResults = new LinkedHashMap<>();
        Map<String, List<Long>> searchResults = new LinkedHashMap<>();
        Map<String, List<Long>> deleteResults = new LinkedHashMap<>();
        Map<String, List<Long>> countResults = new LinkedHashMap<>();
        Map<String, List<Long>> inOrderResults = new LinkedHashMap<>();

        String[] structures = { "BST", "AVL", "MinHeap" };

        for (String structure : structures) {
            insertResults.put(structure, new ArrayList<>());
            searchResults.put(structure, new ArrayList<>());
            deleteResults.put(structure, new ArrayList<>());
            countResults.put(structure, new ArrayList<>());
            inOrderResults.put(structure, new ArrayList<>());
        }

        Map<String, List<String>> summaryTable = new LinkedHashMap<>();

        for (String structure : structures) {
            List<String> lines = new ArrayList<>();
            lines.add("=============================================================");
            lines.add("Running Benchmarks for: " + structure);
            lines.add("=============================================================");

            for (String file : FILES) {
                List<String> postcodes = loadPostcodes("inputFiles/" + file);
                int n = postcodes.size();

                PostcodeManager instance = switch (structure) {
                    case "BST" -> new BST();
                    case "AVL" -> new AVL();
                    default -> new MinHeap(n);
                };

                long insertTime = Benchmark.timeInsert(instance, postcodes);
                long searchTime = Benchmark.timeSearch(instance, postcodes);
                long countTime = Benchmark.timeCount(instance);
                long inOrderTime = Benchmark.timeInOrder(instance);
                long deleteTime = Benchmark.timeDelete(instance, postcodes);

                insertResults.get(structure).add(insertTime);
                searchResults.get(structure).add(searchTime);
                countResults.get(structure).add(countTime);
                inOrderResults.get(structure).add(inOrderTime);
                deleteResults.get(structure).add(deleteTime);

                lines.add(String.format(
                        "File: %-25s | Size: %5d | Insert: %4d ms | Search: %4d ms | Count: %4d ms | InOrder: %4d ms | Delete: %4d ms",
                        file, n, insertTime, searchTime, countTime, inOrderTime, deleteTime));
            }

            summaryTable.put(structure, lines);
        }

        for (String structure : structures) {
            for (String line : summaryTable.get(structure)) {
                System.out.println(line);
            }
            System.out.println();
        }

        printDoublingRatios("Insert", insertResults);
        printDoublingRatios("Search", searchResults);
        printDoublingRatios("Count", countResults);
        printDoublingRatios("InOrder", inOrderResults);
        printDoublingRatios("Delete", deleteResults);
    }

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

    public static List<String> loadPostcodes(String filePath) throws IOException {
        List<String> postcodes = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.isBlank()) {
                postcodes.add(line.trim()); // Remove any leading/trailing whitespace
            }
        }
        reader.close();
        return postcodes;
    }
}