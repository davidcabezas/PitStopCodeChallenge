package com.pitstop.test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.pitstop.test.global.Constants;

import java.io.Serializable;

/**
 * Created by david on 27/2/18.
 */

public class Hours extends SugarRecord implements Serializable {

    private long locationId; // This ID connects Hours object with its corresponding Location object in DB

    @SerializedName(Constants.MONDAY)
    @Expose
    private String monday;
    @SerializedName(Constants.TUESDAY)
    @Expose
    private String tuesday;
    @SerializedName(Constants.WEDNESDAY)
    @Expose
    private String wednesday;
    @SerializedName(Constants.THURSDAY)
    @Expose
    private String thursday;
    @SerializedName(Constants.FRIDAY)
    @Expose
    private String friday;
    @SerializedName(Constants.SATURDAY)
    @Expose
    private String saturday;
    @SerializedName(Constants.SUNDAY)
    @Expose
    private String sunday;

    public Hours() {
    }

    public Hours(long locationId, String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday) {
        this.locationId = locationId;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public Hours(long locationId, Hours hours) {
        this.locationId = locationId;
        this.monday = hours.getMonday();
        this.tuesday = hours.getTuesday();
        this.wednesday = hours.getWednesday();
        this.thursday = hours.getThursday();
        this.friday = hours.getFriday();
        this.saturday = hours.getSaturday();
        this.sunday = hours.getSunday();
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

}
