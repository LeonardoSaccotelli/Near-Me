package model;

import java.util.Objects;
import java.util.UUID;

public class User {
    private final String userID;
    private final double latitude;
    private final double longitude;

    public User(float longitude, float latitude) {
        this.userID = UUID.randomUUID().toString();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getUserID() {
        return userID;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID.trim().equalsIgnoreCase(user.userID.trim());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID);
    }
}
