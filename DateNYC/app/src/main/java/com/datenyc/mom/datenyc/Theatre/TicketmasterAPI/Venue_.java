
package com.datenyc.mom.datenyc.Theatre.TicketmasterAPI;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Venue_ {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("marketId")
    @Expose
    private List<Integer> marketId = new ArrayList<Integer>();
    @SerializedName("country")
    @Expose
    private Country country;
    @SerializedName("state")
    @Expose
    private State state;
    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("postalCode")
    @Expose
    private String postalCode;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("timeZone")
    @Expose
    private String timeZone;
    @SerializedName("_links")
    @Expose
    private Links__ Links;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The marketId
     */
    public List<Integer> getMarketId() {
        return marketId;
    }

    /**
     * 
     * @param marketId
     *     The marketId
     */
    public void setMarketId(List<Integer> marketId) {
        this.marketId = marketId;
    }

    /**
     * 
     * @return
     *     The country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * 
     * @return
     *     The state
     */
    public State getState() {
        return state;
    }

    /**
     * 
     * @param state
     *     The state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * 
     * @return
     *     The city
     */
    public City getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * 
     * @return
     *     The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     *     The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * 
     * @return
     *     The postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * 
     * @param postalCode
     *     The postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * 
     * @return
     *     The address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     *     The address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * 
     * @return
     *     The timeZone
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * 
     * @param timeZone
     *     The timeZone
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * 
     * @return
     *     The Links
     */
    public Links__ getLinks() {
        return Links;
    }

    /**
     * 
     * @param Links
     *     The _links
     */
    public void setLinks(Links__ Links) {
        this.Links = Links;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

}
