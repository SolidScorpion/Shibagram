package com.apripachkin.shibagram.models.shibaphoto.pojo;


import io.realm.RealmObject;

/**
 * Created by Antony on 04.08.2016.
 */
public class ShibaLikedPhotoPojo extends RealmObject {
    private String imageUrl;
    private String shareImgUrl;
    private String authorUsername;
    private String description;
    private String serviceType;
    private String authorUrl;
    private long timePosted;
    private boolean liked = false;

    public String getShareImgUrl() {
        return shareImgUrl;
    }

    public void setShareImgUrl(String shareImgUrl) {
        this.shareImgUrl = shareImgUrl;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(long timePosted) {
        this.timePosted = timePosted;
    }

    @Override
    public String toString() {
        return "ShibaLikedPhotoPojo{" +
                "imageUrl='" + imageUrl + '\'' +
                ", authorUsername='" + authorUsername + '\'' +
                ", description='" + description + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", timePosted=" + timePosted +
                '}';
    }
}
