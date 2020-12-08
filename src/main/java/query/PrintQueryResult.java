package query;

import redis.clients.jedis.GeoRadiusResponse;
import schema.CsvSchema;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class PrintQueryResult {
    protected static void printRestaurant(Map<String, String> restaurant, Set<String> cuisines, int number, GeoRadiusResponse geospatialData) {
        String queryResult = number + ")  Restaurant Name: " + restaurant.get(CsvSchema.Restaurant_Name.toString()) +
                "\n    City: " + restaurant.get(CsvSchema.City.toString()) +
                "\n    Address: " + restaurant.get(CsvSchema.Address.toString()) +
                "\n    Cuisines: " + cuisines.toString() +
                "\n    Price Range: " + restaurant.get(CsvSchema.Price_range.toString()) +
                "\n    Rating: " + restaurant.get(CsvSchema.Rating_text.toString()) +
                "\n    Distance: " + Math.round(geospatialData.getDistance()) + " Km" +
                "\n------------------------------------------------------------------------------------------------------------";

        System.out.println(queryResult);
        printResultQueryOnFile(1, queryResult);
    }

    protected static void printRestaurantAggregateRating(Map<String, String> restaurant, int number) {
        String queryResult = number + ")  Restaurant Name: " + restaurant.get(CsvSchema.Restaurant_Name.toString()) +
                "\n    Address: " + restaurant.get(CsvSchema.Address.toString()) +
                "\n    Cuisines: " + restaurant.get(CsvSchema.Cuisines.toString()) +
                "\n    Price Range: " + restaurant.get(CsvSchema.Price_range.toString()) +
                "\n    Rating: " + restaurant.get(CsvSchema.Aggregate_rating.toString())
                + "\t" + restaurant.get(CsvSchema.Rating_text.toString()) +
                "\n    Distance: " + restaurant.get("Distance") + " Km" +
                "\n------------------------------------------------------------------------------------------------------------";

        System.out.println(queryResult);
        printResultQueryOnFile(2, queryResult);
    }

    protected static void printRestaurantWithRequiredRating(Map<String, String> restaurant, int number) {
        String queryResult = number + ")  Restaurant Name: " + restaurant.get(CsvSchema.Restaurant_Name.toString()) +
                "\n    Rating: " + restaurant.get(CsvSchema.Aggregate_rating.toString())
                + "\t" + restaurant.get(CsvSchema.Rating_text.toString()) +
                "\n    Distance: " + restaurant.get("Distance") + " Km" +
                "\n------------------------------------------------------------------------------------------------------------";

        System.out.println(queryResult);
        printResultQueryOnFile(3, queryResult);
    }

    protected static void printRestaurantWithMultipleFilter(Map<String, String> restaurant, int number) {
        String queryResult = number + ")  Restaurant Name: " + restaurant.get(CsvSchema.Restaurant_Name.toString()) +
                "\n    Rating: " + restaurant.get(CsvSchema.Aggregate_rating.toString())
                + "\t" + restaurant.get(CsvSchema.Rating_text.toString()) +
                "\n    Average cost for two: " + restaurant.get(CsvSchema.Average_Cost_for_two.toString()) + " $" +
                "\n    Has table booking: " + (restaurant.get(CsvSchema.Has_Table_booking.toString()).equalsIgnoreCase("true") ? "Yes" : "No") +
                "\n    Distance: " + restaurant.get("Distance") + " Km" +
                "\n------------------------------------------------------------------------------------------------------------";

        System.out.println(queryResult);
        printResultQueryOnFile(4, queryResult);
    }

    protected static void printRestaurantWithItalianCuisines(Map<String, String> restaurant, int number) {
        String queryResult = number + ")  Restaurant Name: " + restaurant.get(CsvSchema.Restaurant_Name.toString()) +
                "\n    Cuisines: " + restaurant.get(CsvSchema.Cuisines.toString()) +
                "\n    Average cost for two: " + restaurant.get(CsvSchema.Average_Cost_for_two.toString()) + " $" +
                "\t" + "Price range: " + restaurant.get(CsvSchema.Price_range.toString()) +
                "\n    Has online delivery: " + (restaurant.get(CsvSchema.Has_Online_delivery.toString()).equalsIgnoreCase("true") ? "Yes" : "No") +
                "\n    Distance: " + restaurant.get("Distance") + " Km" +
                "\n------------------------------------------------------------------------------------------------------------";

        System.out.println(queryResult);
        printResultQueryOnFile(5, queryResult);
    }

    private static void printResultQueryOnFile(int nQuery, String content) {
        String resourcesPathString = System.getProperty("user.dir") + "/src/main/resources/result_query";
        String filename = "query_" + nQuery + ".txt";
        String file = resourcesPathString + "/" + filename;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.append(content);
            writer.close();
        } catch (IOException e) {
            System.out.println("  Error in writing query result on file");
        }
    }
}
