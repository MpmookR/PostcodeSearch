package benchmark;

import java.io.*;
import java.util.*;


public class BenchmarkUtils {

    //Reads lines from a file and returns them as a List<String>
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

    //Picks a random subset from that list for benchmarking searches
    public static List<String> getSample(List<String> data, int count) {
        Collections.shuffle(data); //Shuffle to simulate random search patterns
        return data.subList(0, Math.min(count, data.size()));
    }
}


