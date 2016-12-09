package com.apripachkin.shibagram.models.px500;

/**
 * Created by apripachkin on 12/24/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Large {

    @SerializedName("https")
    @Expose
    private String https;

    /**
     * @return The https
     */
    public String getHttps() {
        return https;
    }

    /**
     * @param https The https
     */
    public void setHttps(String https) {
        this.https = https;
    }

}
