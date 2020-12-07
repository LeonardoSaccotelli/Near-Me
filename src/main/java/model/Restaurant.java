package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Restaurant {
    private final String restaurantID;
    private final String restaurantName;

    private final float latitude;
    private final float longitude;
    private final String city;
    private final String address;
    private final String locality;
    private final ArrayList<String> cuisines;
    private final double averageCostForTwo;
    private final boolean hasTableBooking;
    private final boolean hasOnlineDelivery;
    private final boolean isDeliveringNow;
    private final int priceRange;
    private final double aggregateRating;
    private final String ratingText;
    private final int numberOfVotes;

    public Restaurant(String restaurantID, String restaurantName, float latitude,
                      float longitude, String city, String address, String locality,
                      String[] cuisines, double averageCostForTwo, String hasTableBooking,
                      String hasOnlineDelivery, String isDeliveringNow, int priceRange,
                      double aggregateRating, String ratingText, int numberOfVotes) {
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.address = address;
        this.locality = locality;
        this.cuisines = new ArrayList<>();
        Collections.addAll(this.cuisines, cuisines);
        this.averageCostForTwo = averageCostForTwo;
        this.hasTableBooking = hasTableBooking.trim().equalsIgnoreCase("yes");
        this.hasOnlineDelivery = hasOnlineDelivery.trim().equalsIgnoreCase("yes");
        this.isDeliveringNow = isDeliveringNow.trim().equalsIgnoreCase("yes");
        this.priceRange = priceRange;
        this.aggregateRating = aggregateRating;
        this.ratingText = ratingText;
        this.numberOfVotes = numberOfVotes;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getLocality() {
        return locality;
    }

    public ArrayList<String> getCuisines() {
        return cuisines;
    }

    public double getAverageCostForTwo() {
        return averageCostForTwo;
    }

    public boolean isHasTableBooking() {
        return hasTableBooking;
    }

    public boolean isHasOnlineDelivery() {
        return hasOnlineDelivery;
    }

    public boolean isDeliveringNow() {
        return isDeliveringNow;
    }

    public int getPriceRange() {
        return priceRange;
    }

    public double getAggregateRating() {
        return aggregateRating;
    }

    public String getRatingText() {
        return ratingText;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant temp = (Restaurant) o;
        return restaurantID.trim().equalsIgnoreCase(temp.restaurantID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantID);
    }

    @Override
    public String toString() {
        return "temp{" +
                "restaurantID='" + restaurantID + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", locality='" + locality + '\'' +
                ", cuisines=" + cuisines.toString() +
                ", averageCostForTwo=" + averageCostForTwo +
                ", hasTableBooking=" + hasTableBooking +
                ", hasOnlineDelivery=" + hasOnlineDelivery +
                ", isDeliveringNow=" + isDeliveringNow +
                ", priceRange=" + priceRange +
                ", aggregateRating=" + aggregateRating +
                ", ratingText='" + ratingText + '\'' +
                ", numberOfVotes=" + numberOfVotes +
                '}';
    }
}
