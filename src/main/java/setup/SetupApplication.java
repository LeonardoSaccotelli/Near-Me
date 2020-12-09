package setup;

import model.Restaurant;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SetupApplication {
    private static Map<String, Restaurant> listOfRestaurant = new HashMap<>();

    public static boolean setupApplication() {
        System.out.println("APPLICATION SET-UP IN PROGRESS, WAIT!");
        listOfRestaurant = CsvReader.readCsvFile();

        if(listOfRestaurant!= null) {
            return LoadData.loadInRedis(listOfRestaurant);
        }else{
            return false;
        }

    }
}
