package setup;

import model.Restaurant;
import redis.clients.jedis.Jedis;

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


            }
        } else {
            System.out.println("  We will use the latest available version of the database");
        }
        System.out.println("APPLICATION SET-UP ENDED");
        System.out.println("===========================================================================");
    }
}
