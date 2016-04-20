
package com.datenyc.mom.datenyc.Theatre.TicketmasterAPI;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Dates {

    @SerializedName("start")
    @Expose
    private Start start;
    @SerializedName("end")
    @Expose
    private End end;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("displayOptions")
    @Expose
    private DisplayOptions displayOptions;
    @SerializedName("status")
    @Expose
    private Status status;

    /**
     * 
     * @return
     *     The start
     */
    public Start getStart() {
        return start;
    }

    /**
     * 
     * @param start
     *     The start
     */
    public void setStart(Start start) {
        this.start = start;
    }

    /**
     * 
     * @return
     *     The end
     */
    public End getEnd() {
        return end;
    }

    /**
     * 
     * @param end
     *     The end
     */
    public void setEnd(End end) {
        this.end = end;
    }

    /**
     * 
     * @return
     *     The timezone
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * 
     * @param timezone
     *     The timezone
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    /**
     * 
     * @return
     *     The displayOptions
     */
    public DisplayOptions getDisplayOptions() {
        return displayOptions;
    }

    /**
     * 
     * @param displayOptions
     *     The displayOptions
     */
    public void setDisplayOptions(DisplayOptions displayOptions) {
        this.displayOptions = displayOptions;
    }

    /**
     * 
     * @return
     *     The status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

}
