
package com.datenyc.mom.datenyc.Theatre.TicketmasterAPI;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Start {

    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("localDate")
    @Expose
    private String localDate;
    @SerializedName("localTime")
    @Expose
    private String localTime;

    /**
     * 
     * @return
     *     The dateTime
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * 
     * @param dateTime
     *     The dateTime
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * 
     * @return
     *     The localDate
     */
    public String getLocalDate() {
        return localDate;
    }

    /**
     * 
     * @param localDate
     *     The localDate
     */
    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    /**
     * 
     * @return
     *     The localTime
     */
    public String getLocalTime() {
        return localTime;
    }

    /**
     * 
     * @param localTime
     *     The localTime
     */
    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }

}
