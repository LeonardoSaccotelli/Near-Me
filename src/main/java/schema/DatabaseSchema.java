package schema;

public class DatabaseSchema {
    public static final class Restaurant {
        public static final String RESTAURANT = "restaurant";

        public static final class Content {
            public static final String GEO = "geo";
            public static final String INFO = "info";
        }

    }

    public static final class CodeRequest {
        public static final int INFO_REQUEST = 1;
        public static final int GEO_REQUEST = 2;
    }
}
