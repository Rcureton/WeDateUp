
package com.datenyc.mom.datenyc.Theatre.TicketmasterAPI;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Range {

    @SerializedName("localStartDate")
    @Expose
    private String localStartDate;
    @SerializedName("localEndDate")
    @Expose
    private String localEndDate;

    /**
     * 
     * @return
     *     The localStartDate
     */
    public String getLocalStartDate() {
        return localStartDate;
    }

    /**
     * 
     * @param localStartDate
     *     The localStartDate
     */
    public void setLocalStartDate(String localStartDate) {
        this.localStartDate = localStartDate;
    }

    /**
     * 
     * @return
     *     The localEndDate
     */
    public String getLocalEndDate() {
        return localEndDate;
    }

    /**
     * 
     * @param localEndDate
     *     The localEndDate
     */
    public void setLocalEndDate(String localEndDate) {
        this.localEndDate = localEndDate;
    }

}
