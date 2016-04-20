
package com.datenyc.mom.datenyc.Theatre.TicketmasterAPI;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Links__ {

    @SerializedName("self")
    @Expose
    private Self__ self;

    /**
     * 
     * @return
     *     The self
     */
    public Self__ getSelf() {
        return self;
    }

    /**
     * 
     * @param self
     *     The self
     */
    public void setSelf(Self__ self) {
        this.self = self;
    }

}
