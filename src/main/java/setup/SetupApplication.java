package setup;

import model.Restaurant;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SetupApplication {
    private static Map<String, Restaurant> listOfRestaurant = new HashMap<>();

    public static void setupApplication(){
        System.out.println("APPLICATION SETUP IN PROGRESS, WAIT!");
        try {
             listOfRestaurant = CsvReader.readCsvFile();
        } catch (IOException e) {
            System.out.println("Error! Unable to read from file.");
            e.printStackTrace();
        }

        LoadData.loadInRedis();
    }
}
