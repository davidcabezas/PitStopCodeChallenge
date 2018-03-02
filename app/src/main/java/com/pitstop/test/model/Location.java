package com.pitstop.test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.pitstop.test.global.Constants;

import java.io.Serializable;

/**
 * Created by david on 27/2/18.
 */

public class Location extends SugarRecord implements Serializable {

    @SerializedName(Constants.TITLE)
    @Expose
    private String title;
    @SerializedName(Constants.DESCRIPTION)
    @Expose
    private String description;
    @SerializedName(Constants.ADDRESS)
    @Expose
    private String address;
    @SerializedName(Constants.HOURS)
    @Expose
    @Ignore
    private Hours hours;
    private long hoursId; // This ID connects Location object with its corresponding Hours object in DB
    @SerializedName(Constants.IMAGE)
    @Expose
    private String image;
    @SerializedName(Constants.URL)
    @Expose
    private String url;

    public Location() {
    }

    public Location(String title, String description, String address, Hours hours, String image, String url) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.hours = hours;
        this.image = image;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Hours getHours() {
        return hours;
    }

    public void setHours(Hours hours) {
        this.hours = hours;
    }

    public long getHoursId() {
        return hoursId;
    }

    public void setHoursId(long hoursId) {
        this.hoursId = hoursId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
