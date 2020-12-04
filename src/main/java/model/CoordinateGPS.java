package model;

import java.util.Objects;

public class CoordinateGPS {
    private String latitude;
    private String longitude;

    public CoordinateGPS(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinateGPS that = (CoordinateGPS) o;
        return latitude.trim().equalsIgnoreCase(that.latitude.trim()) &&
                longitude.trim().equalsIgnoreCase(that.longitude.trim());
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return "CoordinateGPS{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
