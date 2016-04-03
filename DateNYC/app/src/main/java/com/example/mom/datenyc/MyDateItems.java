package com.example.mom.datenyc;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Ra on 3/21/16.
 */
public class MyDateItems implements Parcelable{

    public final static String MY_ITEMS= "myItems";
    private String price;
    private String location;
    private String venueType;
    private String funActivity;
    private String cuisine;
    private String restaurant;
    private String address;
    private String rating;
    private String funAddress;
    private String funRating;
    private double lat;
    private double lon;
    private String placeId;
    private String phoneNumber;



    public MyDateItems(){

    }

    protected MyDateItems(Parcel in) {
        price = in.readString();
        location = in.readString();
        venueType = in.readString();
        funActivity = in.readString();
        cuisine= in.readString();
        restaurant= in.readString();
        address=in.readString();
        rating=in.readString();
        funAddress=in.readString();
        funRating=in.readString();
        lat=in.readDouble();
        lon=in.readDouble();
        placeId=in.readString();
        phoneNumber=in.readString();

    }

    public static final Creator<MyDateItems> CREATOR = new Creator<MyDateItems>() {
        @Override
        public MyDateItems createFromParcel(Parcel in) {
            return new MyDateItems(in);
        }

        @Override
        public MyDateItems[] newArray(int size) {
            return new MyDateItems[size];
        }
    };

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVenueType() {
        return venueType;
    }

    public void setVenueType(String venueType) {
        this.venueType = venueType;
    }

    public String getFunActivity() {
        return funActivity;
    }

    public String getFormattedFunActivity()throws UnsupportedEncodingException{
        return URLEncoder.encode(funActivity, "UTF-8");
    }

    public void setFunActivity(String funActivity) {
        this.funActivity = funActivity;
    }

    public String getFormattedCuisine() throws UnsupportedEncodingException {
        return URLEncoder.encode(cuisine, "UTF-8");


    }

  public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFunAddress() {
        return funAddress;
    }

    public void setFunAddress(String funAddress) {
        this.funAddress = funAddress;
    }

    public String getFunRating() {
        return funRating;
    }

    public void setFunRating(String funRating) {
        this.funRating = funRating;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(price);
        dest.writeString(location);
        dest.writeString(venueType);
        dest.writeString(funActivity);
        dest.writeString(cuisine);
        dest.writeString(restaurant);
        dest.writeString(address);
        dest.writeString(rating);
        dest.writeString(funAddress);
        dest.writeString(funRating);
        dest.writeDouble(lat);
        dest.writeDouble(lon);
        dest.writeString(placeId);
        dest.writeString(phoneNumber);
    }
}
