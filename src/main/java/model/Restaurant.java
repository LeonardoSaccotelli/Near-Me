package model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Restaurant {
    private String restaurantID;
    private String restaurantName;
    private GeolocationData geolocation;
    private String[] cuisines;
    private float averageCostForTwo;
    private boolean hasTableBooking;
    private boolean hasOnlineDelivery;
    private boolean isDeliveringNow;
    private int priceRange;
    private Rating rating;

    public Restaurant(String restaurantID, String restaurantName,
                      GeolocationData geolocation, String[] cuisines,
                      float averageCostForTwo, String hasTableBooking,
                      String hasOnlineDelivery, String isDeliveringNow,
                      int priceRange, Rating rating) {
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
        this.geolocation = geolocation;
        this.cuisines = cuisines;
        this.averageCostForTwo = averageCostForTwo;
        this.hasTableBooking = hasTableBooking.trim().equalsIgnoreCase("yes");
        this.hasOnlineDelivery = hasOnlineDelivery.trim().equalsIgnoreCase("yes");
        this.isDeliveringNow = isDeliveringNow.trim().equalsIgnoreCase("yes");
        this.priceRange = priceRange;
        this.rating = rating;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public GeolocationData getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(GeolocationData geolocation) {
        this.geolocation = geolocation;
    }

    public String[] getCuisines() {
        return cuisines;
    }

    public void setCuisines(String[] cuisines) {
        this.cuisines = cuisines;
    }

    public float getAverageCostForTwo() {
        return averageCostForTwo;
    }

    public void setAverageCostForTwo(float averageCostForTwo) {
        this.averageCostForTwo = averageCostForTwo;
    }

    public boolean isHasTableBooking() {
        return hasTableBooking;
    }

    public void setHasTableBooking(boolean hasTableBooking) {
        this.hasTableBooking = hasTableBooking;
    }

    public boolean isHasOnlineDelivery() {
        return hasOnlineDelivery;
    }

    public void setHasOnlineDelivery(boolean hasOnlineDelivery) {
        this.hasOnlineDelivery = hasOnlineDelivery;
    }

    public boolean isDeliveringNow() {
        return isDeliveringNow;
    }

    public void setDeliveringNow(boolean deliveringNow) {
        isDeliveringNow = deliveringNow;
    }

    public int getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(int priceRange) {
        this.priceRange = priceRange;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return restaurantID.trim().equalsIgnoreCase(that.restaurantID.trim());
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantID);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantID='" + restaurantID + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", geolocation=" + geolocation +
                ", cuisines=" + Arrays.toString(cuisines) +
                ", averageCostForTwo=" + averageCostForTwo +
                ", hasTableBooking=" + hasTableBooking +
                ", hasOnlineDelivery=" + hasOnlineDelivery +
                ", isDeliveringNow=" + isDeliveringNow +
                ", priceRange=" + priceRange +
                ", rating=" + rating +
                '}';
    }
}
