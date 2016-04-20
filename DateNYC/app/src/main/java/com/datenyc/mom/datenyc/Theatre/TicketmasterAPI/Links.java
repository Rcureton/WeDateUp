
package com.datenyc.mom.datenyc.Theatre.TicketmasterAPI;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Links {

    @SerializedName("self")
    @Expose
    private Self self;
    @SerializedName("next")
    @Expose
    private Next next;

    /**
     * 
     * @return
     *     The self
     */
    public Self getSelf() {
        return self;
    }

    /**
     * 
     * @param self
     *     The self
     */
    public void setSelf(Self self) {
        this.self = self;
    }

    /**
     * 
     * @return
     *     The next
     */
    public Next getNext() {
        return next;
    }

    /**
     * 
     * @param next
     *     The next
     */
    public void setNext(Next next) {
        this.next = next;
    }

}
