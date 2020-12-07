package setup;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private final Jedis connection;

    private DatabaseConnection() throws JedisConnectionException {
        String host = "localhost";
        this.connection = new Jedis(host);
    }

    public Jedis getConnection() {
        return this.connection;
    }

    public static DatabaseConnection getInstance() throws JedisConnectionException {
        if (instance == null || !instance.getConnection().isConnected()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
