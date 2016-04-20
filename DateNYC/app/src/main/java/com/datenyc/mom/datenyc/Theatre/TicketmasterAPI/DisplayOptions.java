
package com.datenyc.mom.datenyc.Theatre.TicketmasterAPI;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class DisplayOptions {

    @SerializedName("range")
    @Expose
    private Range range;

    /**
     * 
     * @return
     *     The range
     */
    public Range getRange() {
        return range;
    }

    /**
     * 
     * @param range
     *     The range
     */
    public void setRange(Range range) {
        this.range = range;
    }

}
