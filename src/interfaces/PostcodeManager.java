package interfaces;

import java.util.List;

// Common interface for postcode data structures

public interface PostcodeManager {
    //count the number of postcodes in the list
    int count (); 

    //check if a postcode is in the list
    boolean contains(String postcode);

    //add a postcode to the list
    boolean add(String postcode);

    //delete a postcode from the list
    boolean delete(String postcode);

    //output all the postcodes into a text file
    List<String> getAllPostcodes();

}

// defines the basic operations 
// 1. count the number of postcodes in the list,
// 2. check if a postcode is in the list,
// 3. add a postcode to the list,
// 4. delete a postcode from the list,
// 5. output all the postcodes into a text file 1 per line in alphabetical order.
