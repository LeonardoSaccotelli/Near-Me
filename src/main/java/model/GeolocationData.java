package model;

import java.util.Objects;

public class GeolocationData {
    private CoordinateGPS gps;
    private String city;
    private String address;
    private String locality;
    private String localityVerbose;

    public GeolocationData(CoordinateGPS gps, String city, String address, String locality, String localityVerbose) {
        this.gps = gps;
        this.city = city;
        this.address = address;
        this.locality = locality;
        this.localityVerbose = localityVerbose;
    }

    public CoordinateGPS getGps() {
        return gps;
    }

    public void setGps(CoordinateGPS gps) {
        this.gps = gps;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getLocalityVerbose() {
        return localityVerbose;
    }

    public void setLocalityVerbose(String localityVerbose) {
        this.localityVerbose = localityVerbose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeolocationData that = (GeolocationData) o;
        return gps.equals(that.gps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gps);
    }

    @Override
    public String toString() {
        return "GeolocationData{" +
                "gps=" + gps +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", locality='" + locality + '\'' +
                ", localityVerbose='" + localityVerbose + '\'' +
                '}';
    }
}
