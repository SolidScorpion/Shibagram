package com.apripachkin.shibagram.models.px500;

/**
 * Created by apripachkin on 12/24/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShareCounts {

    @SerializedName("facebook")
    @Expose
    private Integer facebook;
    @SerializedName("twitter")
    @Expose
    private Integer twitter;
    @SerializedName("pinterest")
    @Expose
    private Integer pinterest;

    /**
     * @return The facebook
     */
    public Integer getFacebook() {
        return facebook;
    }

    /**
     * @param facebook The facebook
     */
    public void setFacebook(Integer facebook) {
        this.facebook = facebook;
    }

    /**
     * @return The twitter
     */
    public Integer getTwitter() {
        return twitter;
    }

    /**
     * @param twitter The twitter
     */
    public void setTwitter(Integer twitter) {
        this.twitter = twitter;
    }

    /**
     * @return The pinterest
     */
    public Integer getPinterest() {
        return pinterest;
    }

    /**
     * @param pinterest The pinterest
     */
    public void setPinterest(Integer pinterest) {
        this.pinterest = pinterest;
    }

}
