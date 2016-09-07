
package com.datenyc.mom.datenyc.Model.Model.Data;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Result {


    @SerializedName("photos")
    @Expose
    private List<Photo> photos = new ArrayList<Photo>();
    @SerializedName("address_components")
    @Expose
    private List<AddressComponent> addressComponents = new ArrayList<AddressComponent>();
    @SerializedName("formatted_address")
    @Expose
    private String formattedAddress;
    @SerializedName("formatted_phone_number")
    @Expose
    private String formattedPhoneNumber;
    @SerializedName("geometry")
    @Expose
    private Geometry geometry;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("international_phone_number")
    @Expose
    private String internationalPhoneNumber;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("photo_reference")
    @Expose
    private String photoReference;
    @SerializedName("scope")
    @Expose
    private String scope;
    @SerializedName("alt_ids")
    @Expose
    private List<AltId> altIds = new ArrayList<AltId>();
    @SerializedName("rating")
    @Expose
    private double rating;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("reviews")
    @Expose
    private ArrayList<Review> reviews = new ArrayList<Review>();
    @SerializedName("types")
    @Expose
    private List<String> types = new ArrayList<String>();
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("vicinity")
    @Expose
    private String vicinity;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("result")
    @Expose
    private String result;


    /**
     * 
     * @return
     *     The addressComponents
     */
    public List<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    /**
     * 
     * @param addressComponents
     *     The address_components
     */
    public void setAddressComponents(List<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
    }

    /**
     * 
     * @return
     *     The formattedAddress
     */
    public String getFormattedAddress() {
        return formattedAddress;
    }

    /**
     * 
     * @param formattedAddress
     *     The formatted_address
     */
    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    /**
     * 
     * @return
     *     The formattedPhoneNumber
     */
    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }

    /**
     * 
     * @param formattedPhoneNumber
     *     The formatted_phone_number
     */
    public void setFormattedPhoneNumber(String formattedPhoneNumber) {
        this.formattedPhoneNumber = formattedPhoneNumber;
    }

    /**
     * 
     * @return
     *     The geometry
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * 
     * @param geometry
     *     The geometry
     */
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    /**
     *
     * @return
     * The photos
     */
    public List<Photo> getPhotos() {
        return photos;
    }

    /**
     *
     * @param photos
     * The photos
     */
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    /**
     *
     * @return
     *     The photoReference
     */
    public String getPhotoReference() {
        return photoReference;
    }

    /**
     *
     * @param photoReference
     *     The photo_reference
     */
    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }



    /**
     * 
     * @return
     *     The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 
     * @param icon
     *     The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The internationalPhoneNumber
     */
    public String getInternationalPhoneNumber() {
        return internationalPhoneNumber;
    }

    /**
     * 
     * @param internationalPhoneNumber
     *     The international_phone_number
     */
    public void setInternationalPhoneNumber(String internationalPhoneNumber) {
        this.internationalPhoneNumber = internationalPhoneNumber;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The placeId
     */
    public String getPlaceId() {
        return placeId;
    }

    /**
     * 
     * @param placeId
     *     The place_id
     */
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    /**
     * 
     * @return
     *     The scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * 
     * @param scope
     *     The scope
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * 
     * @return
     *     The altIds
     */
    public List<AltId> getAltIds() {
        return altIds;
    }

    /**
     * 
     * @param altIds
     *     The alt_ids
     */
    public void setAltIds(List<AltId> altIds) {
        this.altIds = altIds;
    }

    /**
     * 
     * @return
     *     The rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * 
     * @param rating
     *     The rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * 
     * @return
     *     The reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * 
     * @param reference
     *     The reference
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * 
     * @return
     *     The reviews
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * 
     * @param reviews
     *     The reviews
     */
    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * 
     * @return
     *     The types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * 
     * @param types
     *     The types
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The vicinity
     */
    public String getVicinity() {
        return vicinity;
    }

    /**
     * 
     * @param vicinity
     *     The vicinity
     */
    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    /**
     * 
     * @return
     *     The website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * 
     * @param website
     *     The website
     */
    public void setWebsite(String website) {
        this.website = website;
    }


    public String getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(String result) {
        this.result = result;
    }


}
