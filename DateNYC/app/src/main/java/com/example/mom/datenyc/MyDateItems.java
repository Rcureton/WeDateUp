package com.example.mom.datenyc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ra on 3/21/16.
 */
public class MyDateItems implements Parcelable{

    public final static String MY_ITEMS= "myItems";
    private String price;
    private String location;
    private String venueType;
    private String funActivity;


    public MyDateItems(){

    }

    protected MyDateItems(Parcel in) {
        price = in.readString();
        location = in.readString();
        venueType = in.readString();
        funActivity = in.readString();
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

    public void setFunActivity(String funActivity) {
        this.funActivity = funActivity;
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
    }
}
