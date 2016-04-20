
package com.datenyc.mom.datenyc.Theatre.TicketmasterAPI;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Embedded_ {

    @SerializedName("venue")
    @Expose
    private List<Venue_> venue = new ArrayList<Venue_>();
    @SerializedName("categories")
    @Expose
    private List<Category_> categories = new ArrayList<Category_>();
    @SerializedName("attractions")
    @Expose
    private List<Attraction> attractions = new ArrayList<Attraction>();

    /**
     * 
     * @return
     *     The venue
     */
    public List<Venue_> getVenue() {
        return venue;
    }

    /**
     * 
     * @param venue
     *     The venue
     */
    public void setVenue(List<Venue_> venue) {
        this.venue = venue;
    }

    /**
     * 
     * @return
     *     The categories
     */
    public List<Category_> getCategories() {
        return categories;
    }

    /**
     * 
     * @param categories
     *     The categories
     */
    public void setCategories(List<Category_> categories) {
        this.categories = categories;
    }

    /**
     * 
     * @return
     *     The attractions
     */
    public List<Attraction> getAttractions() {
        return attractions;
    }

    /**
     * 
     * @param attractions
     *     The attractions
     */
    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

}
