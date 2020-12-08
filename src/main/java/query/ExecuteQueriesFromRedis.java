package query;

import model.User;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.GeoRadiusParam;
import setup.DatabaseUtility;

import java.util.HashSet;
import java.util.Set;

public class ExecuteQueriesFromRedis {
    public static void executeQueries() {
        //Retrieve the RedisConnection Object
        Jedis redisConnection = DatabaseUtility.createJedisConnection();

        //Create a user object with longitude and latitude to simulate the geolocation of user
        User user1 = new User(Double.valueOf(120.95158).floatValue(), Double.valueOf(14.101834).floatValue());

        //QUERY 1
        //This query prints the list of the top 5 restaurant in 50 km radius from user, from closest to farthest
        System.out.println("# Print the list of the top 5 restaurant in 50 km radius from user, from closest to farthest");
        GeoRadiusParam param = new GeoRadiusParam().count(5).withDist().sortAscending();
        Query.topNNearestRestaurantInNKmRadius(redisConnection, user1, 50, GeoUnit.KM, param);
        System.out.println("============================================================================================================");


        //QUERY 2
        //This query prints the list of top 5 restaurant in 100 km radius from user sorted by aggregate vote
        System.out.println("# Print the list of top 5 restaurant in 100 km radius from user sorted by aggregate vote");
        param = new GeoRadiusParam().count(5).withDist();
        Query.topNNearestRestaurantInNkKmRadiusSortedByAggregateVote(redisConnection, user1, 100, GeoUnit.KM, param);
        System.out.println("============================================================================================================");


        //Create a new user with different gps location
        User user2 = new User(Double.valueOf(78.2594998).floatValue(), Double.valueOf(27.53815752).floatValue());

        //QUERY 3
        //This query prints all the restaurant in 50 km radius from user, from closest to farthest, with "Excellent" rating only
        System.out.println("# Print all the restaurant in 50 km radius from user, from closest to farthest, with 'Excellent' rating ");
        param = new GeoRadiusParam().withDist().sortAscending();
        Query.restaurantInNKmRadiusWithRequiredScore(redisConnection, user2, 50, GeoUnit.KM, param, "Excellent");
        System.out.println("============================================================================================================");

        //QUERY 4
        //This query prints the list of restaurant in 500 km radius from user with 'Good' or 'Very Good' rating,
        //that have the average cost grater than 80$, with table booking
        System.out.println("# Print the list of restaurant in 500 km radius from user with 'Good' or 'Very Good' rating,\n" +
                "  that have the average cost grater than 80$, with table booking");
        param = new GeoRadiusParam().withDist().sortAscending();
        Set<String> ratingRequest = new HashSet<>();
        ratingRequest.add("Good");
        ratingRequest.add("Very Good");
        Query.restaurantInNKmRadiusWithMultipleFilter(redisConnection, user2, 500, GeoUnit.KM, param, 80, ratingRequest);
        System.out.println("============================================================================================================");

        //QUERY 5
        //This query prints the list of restaurant of italian cuisine in 150km radius with online delivery with price range grater than 3
        //sorted by aggregate rating
        System.out.println("# This query prints the list of restaurant of italian cuisine in 150km radius with online delivery\n" +
                "  with price range grater than 3 sorted by aggregate rating");
        param = new GeoRadiusParam().withDist();
        Query.restaurantInNKmRadiusWithItalianCuisine(redisConnection, user2, 150, GeoUnit.KM, param, 3, "Italian");
        System.out.println("============================================================================================================");
    }
}
