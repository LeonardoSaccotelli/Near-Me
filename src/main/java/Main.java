import query.ExecuteQueriesFromRedis;
import setup.SetupApplication;

public class Main {
    public static void main(String[] args) {
        /*
         * Method to read data from file, checking data
         * and loading data into Redis database.
         * If the set-up ended successfully, the query
         * will be performed.
         * If ended not successfully, application ended.
         * */
        if (SetupApplication.setupApplication()) {
            System.out.println("SET-UP RESULT: SUCCESS");
            System.out.println("===========================================================================");
            ExecuteQueriesFromRedis.executeQueries();

        } else {
            System.out.println("SET-UP RESULT: FAILED\nAPPLICATION STOPPED");
        }
    }
}
