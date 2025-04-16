package interfaces;

import java.util.List;

public interface PostcodeManager {
    // Count the number of postcodes
    int count();

    // Search if a postcode exists
    boolean search(String postcode);

    // Insert a postcode
    boolean insert(String postcode);

    // Delete a postcode
    boolean delete(String postcode);

    // Return all postcodes in alphabetical order
    List<String> getAllPostcodes();
}


// defines the basic operations 
// 1. count the number of postcodes in the list,
// 2. check if a postcode is in the list,
// 3. add a postcode to the list,
// 4. delete a postcode from the list,
// 5. output all the postcodes into a text file 1 per line in alphabetical order.
