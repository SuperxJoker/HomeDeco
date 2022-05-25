package com.user.homedeco.model;

import java.util.Objects;

public class Review{
    private String emailKey;
    private String reviewKey;

    @Override
    public String toString() {
        return "Review{" +
                "emailKey='" + emailKey + '\'' +
                ", reviewKey='" + reviewKey + '\'' +
                '}';
    }

    public Review(String emailKey, String reviewKey){
        this.emailKey = emailKey;
        this.reviewKey = reviewKey;
    }

    public Review() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return Objects.equals(getEmailKey(), review.getEmailKey()) && Objects.equals(getReviewKey(), review.getReviewKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmailKey(), getReviewKey());
    }

    public String getEmailKey(){
        return emailKey;
    }

    public void setEmailKey(String emailKey){
        this.emailKey = emailKey;
    }

    public String getReviewKey(){
        return reviewKey;
    }

    public void setReviewKey(String reviewKey){
        this.reviewKey = reviewKey;
    }
}
