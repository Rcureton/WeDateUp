
package com.datenyc.mom.datenyc.Theatre.TicketmasterAPI;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Event {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("locale")
    @Expose
    private String locale;
    @SerializedName("promoterId")
    @Expose
    private List<Integer> promoterId = new ArrayList<Integer>();
    @SerializedName("dates")
    @Expose
    private Dates dates;
    @SerializedName("test")
    @Expose
    private boolean test;
    @SerializedName("_links")
    @Expose
    private Links_ Links;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("_embedded")
    @Expose
    private Embedded_ Embedded;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("eventUrl")
    @Expose
    private String eventUrl;

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
     *     The locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * 
     * @param locale
     *     The locale
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * 
     * @return
     *     The promoterId
     */
    public List<Integer> getPromoterId() {
        return promoterId;
    }

    /**
     * 
     * @param promoterId
     *     The promoterId
     */
    public void setPromoterId(List<Integer> promoterId) {
        this.promoterId = promoterId;
    }

    /**
     * 
     * @return
     *     The dates
     */
    public Dates getDates() {
        return dates;
    }

    /**
     * 
     * @param dates
     *     The dates
     */
    public void setDates(Dates dates) {
        this.dates = dates;
    }

    /**
     * 
     * @return
     *     The test
     */
    public boolean isTest() {
        return test;
    }

    /**
     * 
     * @param test
     *     The test
     */
    public void setTest(boolean test) {
        this.test = test;
    }

    /**
     * 
     * @return
     *     The Links
     */
    public Links_ getLinks() {
        return Links;
    }

    /**
     * 
     * @param Links
     *     The _links
     */
    public void setLinks(Links_ Links) {
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
     *     The Embedded
     */
    public Embedded_ getEmbedded() {
        return Embedded;
    }

    /**
     * 
     * @param Embedded
     *     The _embedded
     */
    public void setEmbedded(Embedded_ Embedded) {
        this.Embedded = Embedded;
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

    /**
     * 
     * @return
     *     The eventUrl
     */
    public String getEventUrl() {
        return eventUrl;
    }

    /**
     * 
     * @param eventUrl
     *     The eventUrl
     */
    public void setEventUrl(String eventUrl) {
        this.eventUrl = eventUrl;
    }

}
