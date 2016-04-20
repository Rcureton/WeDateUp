
package com.datenyc.mom.datenyc.Theatre.TicketmasterAPI;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Page {

    @SerializedName("size")
    @Expose
    private int size;
    @SerializedName("totalElements")
    @Expose
    private int totalElements;
    @SerializedName("totalPages")
    @Expose
    private int totalPages;
    @SerializedName("number")
    @Expose
    private int number;

    /**
     * 
     * @return
     *     The size
     */
    public int getSize() {
        return size;
    }

    /**
     * 
     * @param size
     *     The size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 
     * @return
     *     The totalElements
     */
    public int getTotalElements() {
        return totalElements;
    }

    /**
     * 
     * @param totalElements
     *     The totalElements
     */
    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    /**
     * 
     * @return
     *     The totalPages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * 
     * @param totalPages
     *     The totalPages
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * 
     * @return
     *     The number
     */
    public int getNumber() {
        return number;
    }

    /**
     * 
     * @param number
     *     The number
     */
    public void setNumber(int number) {
        this.number = number;
    }

}
