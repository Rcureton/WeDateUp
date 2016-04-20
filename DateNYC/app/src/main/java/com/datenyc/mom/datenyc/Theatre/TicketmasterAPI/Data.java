
package com.datenyc.mom.datenyc.Theatre.TicketmasterAPI;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Data {

    @SerializedName("_links")
    @Expose
    private com.datenyc.mom.datenyc.Theatre.TicketmasterAPI.Links Links;
    @SerializedName("_embedded")
    @Expose
    private com.datenyc.mom.datenyc.Theatre.TicketmasterAPI.Embedded Embedded;
    @SerializedName("page")
    @Expose
    private Page page;

    /**
     * 
     * @return
     *     The Links
     */
    public com.datenyc.mom.datenyc.Theatre.TicketmasterAPI.Links getLinks() {
        return Links;
    }

    /**
     * 
     * @param Links
     *     The _links
     */
    public void setLinks(com.datenyc.mom.datenyc.Theatre.TicketmasterAPI.Links Links) {
        this.Links = Links;
    }

    /**
     * 
     * @return
     *     The Embedded
     */
    public com.datenyc.mom.datenyc.Theatre.TicketmasterAPI.Embedded getEmbedded() {
        return Embedded;
    }

    /**
     * 
     * @param Embedded
     *     The _embedded
     */
    public void setEmbedded(com.datenyc.mom.datenyc.Theatre.TicketmasterAPI.Embedded Embedded) {
        this.Embedded = Embedded;
    }

    /**
     * 
     * @return
     *     The page
     */
    public Page getPage() {
        return page;
    }

    /**
     * 
     * @param page
     *     The page
     */
    public void setPage(Page page) {
        this.page = page;
    }

}
