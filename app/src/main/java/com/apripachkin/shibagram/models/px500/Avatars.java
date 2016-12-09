package com.apripachkin.shibagram.models.px500;

/**
 * Created by apripachkin on 12/24/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Avatars {

    @SerializedName("default")
    @Expose
    private Default _default;
    @SerializedName("large")
    @Expose
    private Large large;
    @SerializedName("small")
    @Expose
    private Small small;
    @SerializedName("tiny")
    @Expose
    private Tiny tiny;

    /**
     * @return The _default
     */
    public Default getDefault() {
        return _default;
    }

    /**
     * @param _default The default
     */
    public void setDefault(Default _default) {
        this._default = _default;
    }

    /**
     * @return The large
     */
    public Large getLarge() {
        return large;
    }

    /**
     * @param large The large
     */
    public void setLarge(Large large) {
        this.large = large;
    }

    /**
     * @return The small
     */
    public Small getSmall() {
        return small;
    }

    /**
     * @param small The small
     */
    public void setSmall(Small small) {
        this.small = small;
    }

    /**
     * @return The tiny
     */
    public Tiny getTiny() {
        return tiny;
    }

    /**
     * @param tiny The tiny
     */
    public void setTiny(Tiny tiny) {
        this.tiny = tiny;
    }

}