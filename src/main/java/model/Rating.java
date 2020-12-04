package model;

import java.util.Objects;

public class Rating {
    private float aggregateRating;
    private String ratingText;
    private int numberOfVotes;

    public Rating(float aggregateRating, String ratingText, int numberOfVotes) {
        this.aggregateRating = aggregateRating;
        this.ratingText = ratingText;
        this.numberOfVotes = numberOfVotes;
    }

    public float getAggregateRating() {
        return aggregateRating;
    }

    public void setAggregateRating(float aggregateRating) {
        this.aggregateRating = aggregateRating;
    }

    public String getRatingText() {
        return ratingText;
    }

    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return aggregateRating == rating.aggregateRating &&
                numberOfVotes == rating.numberOfVotes &&
                ratingText.trim().equalsIgnoreCase(rating.ratingText.trim());
    }

    @Override
    public int hashCode() {
        return Objects.hash(aggregateRating, ratingText, numberOfVotes);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "aggregateRating=" + aggregateRating +
                ", ratingText='" + ratingText + '\'' +
                ", numberOfVotes=" + numberOfVotes +
                '}';
    }
}
