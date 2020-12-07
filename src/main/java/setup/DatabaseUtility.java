package setup;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import schema.DatabaseSchema;

public class DatabaseUtility {
    public static Jedis createJedisConnection() {
        final int DB_INDEX = 0;
        Jedis redisConnection = null;

        try {
            //Create a connection with redis Database
            redisConnection = DatabaseConnection.getInstance().getConnection();
            //Select a index for your database (0 to 15 slot, one for each db, by config we can change this number)
            redisConnection.select(DB_INDEX);
        } catch (JedisConnectionException e) {
            System.out.println("  Database Connection Creation Failed : " + e.getMessage());
        }
        return redisConnection;
    }

    public static String buildKeyCuisinesSet(String restaurantID) {
        return DatabaseSchema.Restaurant.RESTAURANT + ":" +
                DatabaseSchema.Restaurant.Content.CUISINES + ":" +
                restaurantID;
    }

    public static String buildKeyGeoSpatialIndex() {
        return DatabaseSchema.Restaurant.RESTAURANT + ":" +
                DatabaseSchema.Restaurant.Content.GEO;
    }

    public static String buildKeyHashRestaurant(String restaurantID) {
        return DatabaseSchema.Restaurant.RESTAURANT + ":" +
                DatabaseSchema.Restaurant.Content.INFO + ":" +
                restaurantID;
    }
}
