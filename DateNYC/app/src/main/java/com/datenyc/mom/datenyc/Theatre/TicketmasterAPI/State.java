
package com.datenyc.mom.datenyc.Theatre.TicketmasterAPI;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class State {

    @SerializedName("stateCode")
    @Expose
    private String stateCode;

    /**
     * 
     * @return
     *     The stateCode
     */
    public String getStateCode() {
        return stateCode;
    }

    /**
     * 
     * @param stateCode
     *     The stateCode
     */
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

}
