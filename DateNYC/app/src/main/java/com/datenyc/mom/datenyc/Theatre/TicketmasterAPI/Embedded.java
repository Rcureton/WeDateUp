
package com.datenyc.mom.datenyc.Theatre.TicketmasterAPI;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Embedded {

    @SerializedName("events")
    @Expose
    private List<Event> events = new ArrayList<Event>();

    /**
     * 
     * @return
     *     The events
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * 
     * @param events
     *     The events
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

}
