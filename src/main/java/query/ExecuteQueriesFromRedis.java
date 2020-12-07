package query;

import redis.clients.jedis.Jedis;
import setup.DatabaseUtility;

public class ExecuteQueriesFromRedis {
    public static void executeQueries() {
        //Retrieve the RedisConnection Object
        Jedis redisConnection = DatabaseUtility.createJedisConnection();
    }
}
