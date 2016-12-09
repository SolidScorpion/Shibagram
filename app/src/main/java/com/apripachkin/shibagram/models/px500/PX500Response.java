package com.apripachkin.shibagram.models.px500;

/**
 * Created by apripachkin on 12/24/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PX500Response {

    @SerializedName("current_page")
    @Expose
    private Integer currentPage;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_items")
    @Expose
    private Integer totalItems;
    @SerializedName("photos")
    @Expose
    private List<PX500Photo> PX500Photos = new ArrayList<PX500Photo>();

    /**
     * @return The currentPage
     */
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage The current_page
     */
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return The totalPages
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     * @param totalPages The total_pages
     */
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * @return The totalItems
     */
    public Integer getTotalItems() {
        return totalItems;
    }

    /**
     * @param totalItems The total_items
     */
    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    /**
     * @return The PX500Photos
     */
    public List<PX500Photo> getPX500Photos() {
        return PX500Photos;
    }

    /**
     * @param PX500Photos The PX500Photos
     */
    public void setPX500Photos(List<PX500Photo> PX500Photos) {
        this.PX500Photos = PX500Photos;
    }

}
