package setup;

import model.Restaurant;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SetupApplication {
    private static Map<String, Restaurant> listOfRestaurant = new HashMap<>();
    private static boolean datasetAvailable = false;

    public static void setupApplication(){
        System.out.println("APPLICATION SET-UP IN PROGRESS, WAIT!");
        try {
            listOfRestaurant = CsvReader.readCsvFile();
            datasetAvailable = true;
        } catch (IOException e) {
            System.out.println("  Error! Unable to read from file.");
        }

        LoadData.loadInRedis(listOfRestaurant, datasetAvailable);
    }
}
