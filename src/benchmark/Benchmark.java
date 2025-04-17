package benchmark;

import interfaces.PostcodeManager;
import java.util.List;

public class Benchmark {

    //Measures how long it takes to insert a list of postcodes into the given structure
    public static long timeInsert(PostcodeManager structure, List<String> postcodes) {
        long start = System.nanoTime(); // Record start time
        for (String postcode : postcodes) {
            structure.add(postcode); // Insert each postcode using the structure
        }
        long end = System.nanoTime();
        return (end - start) / 1_000_000; // Convert nanoseconds to milliseconds
    }

    //Measures how long it takes to search for a list of postcodes in the given structure
    public static long timeSearch(PostcodeManager structure, List<String> queries) {
        long start = System.nanoTime();
        for (String postcode : queries) {
            structure.contains(postcode); // Check postcode using the structure
        }
        long end = System.nanoTime();
        return (end - start) / 1_000_000;
    }
}

