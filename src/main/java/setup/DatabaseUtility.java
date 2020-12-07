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

    public static String buildKeyStructure(int pathRequest) {
        String structureDataBase = "";
        if (pathRequest == 1) {
            structureDataBase = DatabaseSchema.Restaurant.RESTAURANT
                    + ":" + DatabaseSchema.Restaurant.Content.INFO;
        } else if (pathRequest == 2) {
            structureDataBase = DatabaseSchema.Restaurant.RESTAURANT
                    + ":" + DatabaseSchema.Restaurant.Content.GEO;
        }
        return structureDataBase;
    }

}
