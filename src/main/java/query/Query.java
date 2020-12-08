package query;

import model.User;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.GeoRadiusParam;
import schema.CsvSchema;
import setup.DatabaseUtility;

import java.util.*;

public class Query {

    /**
     * Retrieve the ID of restaurants in N km radius from user position,
     * with distance from closest to farthest
     */
    private static List<GeoRadiusResponse> findRestaurantIdInRadius(Jedis redisConnection, User userGPS,
                                                                    double radius, GeoUnit unit, GeoRadiusParam param) {
        return redisConnection.georadius(DatabaseUtility.buildKeyGeoSpatialIndex(), userGPS.getLongitude(),
                userGPS.getLatitude(), radius, unit, param);
    }

    protected static void topNNearestRestaurantInNKmRadius(Jedis redisConnection, User userGPS,
                                                           double radius, GeoUnit unit, GeoRadiusParam param) {

        //Retrieve the ID of restaurants in N km radius from user position, with distance from closest to farthest
        List<GeoRadiusResponse> listOfRestaurant = findRestaurantIdInRadius(redisConnection, userGPS, radius, unit, param);

        int numberOfRestaurant = 0;

        //For each ID, retrieve the hash data of the restaurant and the set cuisines
        for (GeoRadiusResponse geospatialData : listOfRestaurant) {
            Map<String, String> restaurantData = redisConnection.hgetAll(DatabaseUtility.buildKeyHashRestaurant(geospatialData.getMemberByString()));
            Set<String> cuisines = redisConnection.smembers(restaurantData.get(CsvSchema.Cuisines.toString()));
            numberOfRestaurant++;
            PrintQueryResult.printRestaurant(restaurantData, cuisines, numberOfRestaurant, geospatialData);
        }

        if (numberOfRestaurant == 0) {
            System.out.println("Empty result");
        }
    }


    protected static void topNNearestRestaurantInNkKmRadiusSortedByAggregateVote(Jedis redisConnection, User userGPS,
                                                                                 double radius, GeoUnit unit, GeoRadiusParam param) {

        //Retrieve the ID of restaurants in N km radius from user position, with distance from closest to farthest
        List<GeoRadiusResponse> listOfGeoSpatialDataRestaurant = findRestaurantIdInRadius(redisConnection, userGPS, radius, unit, param);

        ArrayList<Map<String, String>> listOfRestaurant = new ArrayList<>();

        //For each geospatial result, retrieve the restaurant info and the distance from user
        for (GeoRadiusResponse geospatialData : listOfGeoSpatialDataRestaurant) {
            Map<String, String> restaurantData = redisConnection.hgetAll(DatabaseUtility.buildKeyHashRestaurant(geospatialData.getMemberByString()));
            restaurantData.put(CsvSchema.Cuisines.toString(), redisConnection.smembers(restaurantData.get(CsvSchema.Cuisines.toString())).toString());
            restaurantData.put("Distance", String.valueOf(Math.round(geospatialData.getDistance())));
            listOfRestaurant.add(restaurantData);
        }

        //Sorting of result by aggregate rating from higher to lower rate
        listOfRestaurant.sort(Comparator.comparingDouble(o -> Double.parseDouble(o.get(CsvSchema.Aggregate_rating.toString()))));
        Collections.reverse(listOfRestaurant);

        //Print result
        int numberOfRestaurant = 0;
        for (Map<String, String> restaurant : listOfRestaurant) {
            numberOfRestaurant++;
            PrintQueryResult.printRestaurantAggregateRating(restaurant, numberOfRestaurant);
        }

        if (numberOfRestaurant == 0) {
            System.out.println("Empty result");
        }
    }

    protected static void restaurantInNKmRadiusWithRequiredScore(Jedis redisConnection, User userGPS,
                                                                 double radius, GeoUnit unit, GeoRadiusParam param, String ratingRequest) {

        //Retrieve the ID of restaurants in N km radius from user position, with distance from closest to farthest
        List<GeoRadiusResponse> listOfGeoSpatialDataRestaurant = findRestaurantIdInRadius(redisConnection, userGPS, radius, unit, param);

        int numberOfRestaurant = 0;

        //For each ID, retrieve the hash data of the restaurant
        for (GeoRadiusResponse geospatialData : listOfGeoSpatialDataRestaurant) {
            Map<String, String> restaurantData = redisConnection.hgetAll(DatabaseUtility.buildKeyHashRestaurant(geospatialData.getMemberByString()));
            //If rating of restaurant is equals to 'Excellent', print the restaurant data
            if (restaurantData.get(CsvSchema.Rating_text.toString()).trim().equalsIgnoreCase(ratingRequest)) {
                restaurantData.put("Distance", String.valueOf(Math.round(geospatialData.getDistance())));
                numberOfRestaurant++;
                PrintQueryResult.printRestaurantWithRequiredRating(restaurantData, numberOfRestaurant);
            }
        }

        if (numberOfRestaurant == 0) {
            System.out.println("Empty result");
        }
    }

    /**
     * This query prints the list of restaurant in 500 km radius from user with 'Good' or 'Very Good' rating,
     * that have the average cost grater than 80$, with table booking
     */
    protected static void restaurantInNKmRadiusWithMultipleFilter(Jedis redisConnection, User userGPS,
                                                                  double radius, GeoUnit unit, GeoRadiusParam param, double minCost, Set<String> ratingRequest) {

        //Retrieve the ID of restaurants in N km radius from user position, with distance from closest to farthest
        List<GeoRadiusResponse> listOfGeoSpatialDataRestaurant = findRestaurantIdInRadius(redisConnection, userGPS, radius, unit, param);

        int numberOfRestaurant = 0;

        //For each ID, retrieve the hash data of the restaurant
        for (GeoRadiusResponse geospatialData : listOfGeoSpatialDataRestaurant) {
            Map<String, String> restaurantData = redisConnection.hgetAll(DatabaseUtility.buildKeyHashRestaurant(geospatialData.getMemberByString()));

            //If rating of restaurant is equals to 'Good' or 'Very Good'
            //and average cost is grater than 50
            //and has table booking, print the result
            if ((ratingRequest.contains(restaurantData.get(CsvSchema.Rating_text.toString())))
                    && (Double.parseDouble(restaurantData.get(CsvSchema.Average_Cost_for_two.toString())) > minCost)
                    && (Boolean.parseBoolean(restaurantData.get(CsvSchema.Has_Table_booking.toString())))
            ) {
                restaurantData.put("Distance", String.valueOf(Math.round(geospatialData.getDistance())));
                numberOfRestaurant++;
                PrintQueryResult.printRestaurantWithMultipleFilter(restaurantData, numberOfRestaurant);
            }
        }

        if (numberOfRestaurant == 0) {
            System.out.println("Empty result");
        }
    }

    /**
     * This query prints the list of restaurant of italian cuisine in 150km radius
     * with online delivery
     * with price range grater than 3
     * sorted by aggregate rating, from higher to lower
     */
    protected static void restaurantInNKmRadiusWithItalianCuisine(Jedis redisConnection, User userGPS,
                                                                  double radius, GeoUnit unit, GeoRadiusParam param, double minCostRange, String cuisine) {

        //Retrieve the ID of restaurants in N km radius from user position, with distance from closest to farthest
        List<GeoRadiusResponse> listOfGeoSpatialDataRestaurant = findRestaurantIdInRadius(redisConnection, userGPS, radius, unit, param);

        ArrayList<Map<String, String>> listOfRestaurant = new ArrayList<>();

        //For each geospatial result, retrieve the restaurant info and the distance from user
        for (GeoRadiusResponse geospatialData : listOfGeoSpatialDataRestaurant) {
            Map<String, String> restaurantData = redisConnection.hgetAll(DatabaseUtility.buildKeyHashRestaurant(geospatialData.getMemberByString()));
            restaurantData.put(CsvSchema.Cuisines.toString(), redisConnection.smembers(restaurantData.get(CsvSchema.Cuisines.toString())).toString());

            if (restaurantData.get(CsvSchema.Cuisines.toString()).contains(cuisine) &&
                    Double.parseDouble(restaurantData.get(CsvSchema.Price_range.toString())) > minCostRange
                    && Boolean.parseBoolean(restaurantData.get(CsvSchema.Has_Online_delivery.toString()))) {
                restaurantData.put("Distance", String.valueOf(Math.round(geospatialData.getDistance())));
                listOfRestaurant.add(restaurantData);
            }
        }

        //Sorting of result by aggregate rating from higher to lower rate
        listOfRestaurant.sort(Comparator.comparingDouble(o -> Double.parseDouble(o.get(CsvSchema.Aggregate_rating.toString()))));
        Collections.reverse(listOfRestaurant);

        //Print result
        int numberOfRestaurant = 0;
        for (Map<String, String> restaurant : listOfRestaurant) {
            numberOfRestaurant++;
            PrintQueryResult.printRestaurantWithItalianCuisines(restaurant, numberOfRestaurant);
        }

        if (numberOfRestaurant == 0) {
            System.out.println("Empty result");
        }
    }
}
