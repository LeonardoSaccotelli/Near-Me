package setup;

import model.Restaurant;
import redis.clients.jedis.Jedis;
import schema.CsvSchema;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LoadData {

    protected static void loadInRedis(Map<String, Restaurant> listOfRestaurant, boolean datasetAvailable) {
        Jedis redisConnection = DatabaseUtility.createJedisConnection();

        if (redisConnection.isConnected() && datasetAvailable) {
            System.out.println("  Updating of database in progress ...");
            //Delete all data in database
            redisConnection.flushDB();

            Set<String> keys = listOfRestaurant.keySet();
            for (String key : keys) {
                Restaurant restaurant = listOfRestaurant.get(key);
                //Create the key for the set where we store the cuisines
                String cuisinesKey = DatabaseUtility.buildKeyCuisinesSet(restaurant.getRestaurantID());

                //Store cuisines in redis set
                for (String cuisine : restaurant.getCuisines()) {
                    redisConnection.sadd(cuisinesKey, cuisine);
                }

                String restaurantKey = DatabaseUtility.buildKeyHashRestaurant(restaurant.getRestaurantID());

                //Store all data in Redis Hash, for cuisines filed we save the key of the set
                //where we have saved the cuisines info
                redisConnection.hset(restaurantKey, createHashRestaurant(restaurant, cuisinesKey));

                //Store geospatial index, for each pair long, lat we save the id of the restaurant
                redisConnection.geoadd(DatabaseUtility.buildKeyGeoSpatialIndex(),
                        restaurant.getLongitude(),
                        restaurant.getLatitude(), restaurant.getRestaurantID());
            }
        } else {
            System.out.println("  We will use the latest available version of the database");
        }
        System.out.println("APPLICATION SET-UP ENDED");
        System.out.println("===========================================================================");
    }


    private static Map<String, String> createHashRestaurant(Restaurant restaurant, String cuisineKey) {
        Map<String, String> restaurantMap = new HashMap<>();
        restaurantMap.put(CsvSchema.Restaurant_ID.toString(), restaurant.getRestaurantID());
        restaurantMap.put(CsvSchema.Restaurant_Name.toString(), restaurant.getRestaurantName());
        restaurantMap.put(CsvSchema.Latitude.toString(), String.valueOf(restaurant.getLatitude()));
        restaurantMap.put(CsvSchema.Longitude.toString(), String.valueOf(restaurant.getLongitude()));
        restaurantMap.put(CsvSchema.City.toString(), restaurant.getCity());
        restaurantMap.put(CsvSchema.Address.toString(), restaurant.getAddress());
        restaurantMap.put(CsvSchema.Locality.toString(), restaurant.getLocality());
        restaurantMap.put(CsvSchema.Cuisines.toString(), cuisineKey);
        restaurantMap.put(CsvSchema.Average_Cost_for_two.toString(), String.valueOf(restaurant.getAverageCostForTwo()));
        restaurantMap.put(CsvSchema.Has_Table_booking.toString(), String.valueOf(restaurant.isHasTableBooking()));
        restaurantMap.put(CsvSchema.Has_Online_delivery.toString(), String.valueOf(restaurant.isHasOnlineDelivery()));
        restaurantMap.put(CsvSchema.Is_delivering_now.toString(), String.valueOf(restaurant.isDeliveringNow()));
        restaurantMap.put(CsvSchema.Price_range.toString(), String.valueOf(restaurant.getPriceRange()));
        restaurantMap.put(CsvSchema.Aggregate_rating.toString(), String.valueOf(restaurant.getAggregateRating()));
        restaurantMap.put(CsvSchema.Rating_text.toString(), restaurant.getRatingText());
        restaurantMap.put(CsvSchema.Votes.toString(), String.valueOf(restaurant.getNumberOfVotes()));

        return restaurantMap;
    }

}
