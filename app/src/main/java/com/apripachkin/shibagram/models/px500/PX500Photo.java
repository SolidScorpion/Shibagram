package com.apripachkin.shibagram.models.px500;

/**
 * Created by apripachkin on 12/24/15.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PX500Photo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("camera")
    @Expose
    private String camera;
    @SerializedName("lens")
    @Expose
    private String lens;
    @SerializedName("focal_length")
    @Expose
    private String focalLength;
    @SerializedName("iso")
    @Expose
    private String iso;
    @SerializedName("shutter_speed")
    @Expose
    private String shutterSpeed;
    @SerializedName("aperture")
    @Expose
    private String aperture;
    @SerializedName("times_viewed")
    @Expose
    private Integer timesViewed;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("category")
    @Expose
    private Integer category;
    @SerializedName("location")
    @Expose
    private Object location;
    @SerializedName("latitude")
    @Expose
    private Object latitude;
    @SerializedName("longitude")
    @Expose
    private Object longitude;
    @SerializedName("taken_at")
    @Expose
    private Object takenAt;
    @SerializedName("hi_res_uploaded")
    @Expose
    private Integer hiResUploaded;
    @SerializedName("for_sale")
    @Expose
    private Boolean forSale;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("votes_count")
    @Expose
    private Integer votesCount;
    @SerializedName("favorites_count")
    @Expose
    private Integer favoritesCount;
    @SerializedName("comments_count")
    @Expose
    private Integer commentsCount;
    @SerializedName("nsfw")
    @Expose
    private Boolean nsfw;
    @SerializedName("sales_count")
    @Expose
    private Integer salesCount;
    @SerializedName("for_sale_date")
    @Expose
    private Object forSaleDate;
    @SerializedName("highest_rating")
    @Expose
    private Double highestRating;
    @SerializedName("highest_rating_date")
    @Expose
    private String highestRatingDate;
    @SerializedName("license_type")
    @Expose
    private Integer licenseType;
    @SerializedName("converted")
    @Expose
    private Integer converted;
    @SerializedName("collections_count")
    @Expose
    private Integer collectionsCount;
    @SerializedName("crop_version")
    @Expose
    private Integer cropVersion;
    @SerializedName("privacy")
    @Expose
    private Boolean privacy;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("images")
    @Expose
    private List<Image> images = new ArrayList<Image>();
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("positive_votes_count")
    @Expose
    private Integer positiveVotesCount;
    @SerializedName("converted_bits")
    @Expose
    private Integer convertedBits;
    @SerializedName("share_counts")
    @Expose
    private ShareCounts shareCounts;
    @SerializedName("watermark")
    @Expose
    private Boolean watermark;
    @SerializedName("image_format")
    @Expose
    private String imageFormat;
    @SerializedName("licensing_requested")
    @Expose
    private Boolean licensingRequested;
    @SerializedName("user")
    @Expose
    private User user;

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId The user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The camera
     */
    public String getCamera() {
        return camera;
    }

    /**
     * @param camera The camera
     */
    public void setCamera(String camera) {
        this.camera = camera;
    }

    /**
     * @return The lens
     */
    public String getLens() {
        return lens;
    }

    /**
     * @param lens The lens
     */
    public void setLens(String lens) {
        this.lens = lens;
    }

    /**
     * @return The focalLength
     */
    public String getFocalLength() {
        return focalLength;
    }

    /**
     * @param focalLength The focal_length
     */
    public void setFocalLength(String focalLength) {
        this.focalLength = focalLength;
    }

    /**
     * @return The iso
     */
    public String getIso() {
        return iso;
    }

    /**
     * @param iso The iso
     */
    public void setIso(String iso) {
        this.iso = iso;
    }

    /**
     * @return The shutterSpeed
     */
    public String getShutterSpeed() {
        return shutterSpeed;
    }

    /**
     * @param shutterSpeed The shutter_speed
     */
    public void setShutterSpeed(String shutterSpeed) {
        this.shutterSpeed = shutterSpeed;
    }

    /**
     * @return The aperture
     */
    public String getAperture() {
        return aperture;
    }

    /**
     * @param aperture The aperture
     */
    public void setAperture(String aperture) {
        this.aperture = aperture;
    }

    /**
     * @return The timesViewed
     */
    public Integer getTimesViewed() {
        return timesViewed;
    }

    /**
     * @param timesViewed The times_viewed
     */
    public void setTimesViewed(Integer timesViewed) {
        this.timesViewed = timesViewed;
    }

    /**
     * @return The rating
     */
    public Double getRating() {
        return rating;
    }

    /**
     * @param rating The rating
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     * @return The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return The category
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * @param category The category
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * @return The location
     */
    public Object getLocation() {
        return location;
    }

    /**
     * @param location The location
     */
    public void setLocation(Object location) {
        this.location = location;
    }

    /**
     * @return The latitude
     */
    public Object getLatitude() {
        return latitude;
    }

    /**
     * @param latitude The latitude
     */
    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    /**
     * @return The longitude
     */
    public Object getLongitude() {
        return longitude;
    }

    /**
     * @param longitude The longitude
     */
    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }

    /**
     * @return The takenAt
     */
    public Object getTakenAt() {
        return takenAt;
    }

    /**
     * @param takenAt The taken_at
     */
    public void setTakenAt(Object takenAt) {
        this.takenAt = takenAt;
    }

    /**
     * @return The hiResUploaded
     */
    public Integer getHiResUploaded() {
        return hiResUploaded;
    }

    /**
     * @param hiResUploaded The hi_res_uploaded
     */
    public void setHiResUploaded(Integer hiResUploaded) {
        this.hiResUploaded = hiResUploaded;
    }

    /**
     * @return The forSale
     */
    public Boolean getForSale() {
        return forSale;
    }

    /**
     * @param forSale The for_sale
     */
    public void setForSale(Boolean forSale) {
        this.forSale = forSale;
    }

    /**
     * @return The width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * @param width The width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * @return The height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * @param height The height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * @return The votesCount
     */
    public Integer getVotesCount() {
        return votesCount;
    }

    /**
     * @param votesCount The votes_count
     */
    public void setVotesCount(Integer votesCount) {
        this.votesCount = votesCount;
    }

    /**
     * @return The favoritesCount
     */
    public Integer getFavoritesCount() {
        return favoritesCount;
    }

    /**
     * @param favoritesCount The favorites_count
     */
    public void setFavoritesCount(Integer favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    /**
     * @return The commentsCount
     */
    public Integer getCommentsCount() {
        return commentsCount;
    }

    /**
     * @param commentsCount The comments_count
     */
    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    /**
     * @return The nsfw
     */
    public Boolean getNsfw() {
        return nsfw;
    }

    /**
     * @param nsfw The nsfw
     */
    public void setNsfw(Boolean nsfw) {
        this.nsfw = nsfw;
    }

    /**
     * @return The salesCount
     */
    public Integer getSalesCount() {
        return salesCount;
    }

    /**
     * @param salesCount The sales_count
     */
    public void setSalesCount(Integer salesCount) {
        this.salesCount = salesCount;
    }

    /**
     * @return The forSaleDate
     */
    public Object getForSaleDate() {
        return forSaleDate;
    }

    /**
     * @param forSaleDate The for_sale_date
     */
    public void setForSaleDate(Object forSaleDate) {
        this.forSaleDate = forSaleDate;
    }

    /**
     * @return The highestRating
     */
    public Double getHighestRating() {
        return highestRating;
    }

    /**
     * @param highestRating The highest_rating
     */
    public void setHighestRating(Double highestRating) {
        this.highestRating = highestRating;
    }

    /**
     * @return The highestRatingDate
     */
    public String getHighestRatingDate() {
        return highestRatingDate;
    }

    /**
     * @param highestRatingDate The highest_rating_date
     */
    public void setHighestRatingDate(String highestRatingDate) {
        this.highestRatingDate = highestRatingDate;
    }

    /**
     * @return The licenseType
     */
    public Integer getLicenseType() {
        return licenseType;
    }

    /**
     * @param licenseType The license_type
     */
    public void setLicenseType(Integer licenseType) {
        this.licenseType = licenseType;
    }

    /**
     * @return The converted
     */
    public Integer getConverted() {
        return converted;
    }

    /**
     * @param converted The converted
     */
    public void setConverted(Integer converted) {
        this.converted = converted;
    }

    /**
     * @return The collectionsCount
     */
    public Integer getCollectionsCount() {
        return collectionsCount;
    }

    /**
     * @param collectionsCount The collections_count
     */
    public void setCollectionsCount(Integer collectionsCount) {
        this.collectionsCount = collectionsCount;
    }

    /**
     * @return The cropVersion
     */
    public Integer getCropVersion() {
        return cropVersion;
    }

    /**
     * @param cropVersion The crop_version
     */
    public void setCropVersion(Integer cropVersion) {
        this.cropVersion = cropVersion;
    }

    /**
     * @return The privacy
     */
    public Boolean getPrivacy() {
        return privacy;
    }

    /**
     * @param privacy The privacy
     */
    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    /**
     * @return The imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl The image_url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @return The images
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * @param images The images
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The positiveVotesCount
     */
    public Integer getPositiveVotesCount() {
        return positiveVotesCount;
    }

    /**
     * @param positiveVotesCount The positive_votes_count
     */
    public void setPositiveVotesCount(Integer positiveVotesCount) {
        this.positiveVotesCount = positiveVotesCount;
    }

    /**
     * @return The convertedBits
     */
    public Integer getConvertedBits() {
        return convertedBits;
    }

    /**
     * @param convertedBits The converted_bits
     */
    public void setConvertedBits(Integer convertedBits) {
        this.convertedBits = convertedBits;
    }

    /**
     * @return The shareCounts
     */
    public ShareCounts getShareCounts() {
        return shareCounts;
    }

    /**
     * @param shareCounts The share_counts
     */
    public void setShareCounts(ShareCounts shareCounts) {
        this.shareCounts = shareCounts;
    }

    /**
     * @return The watermark
     */
    public Boolean getWatermark() {
        return watermark;
    }

    /**
     * @param watermark The watermark
     */
    public void setWatermark(Boolean watermark) {
        this.watermark = watermark;
    }

    /**
     * @return The imageFormat
     */
    public String getImageFormat() {
        return imageFormat;
    }

    /**
     * @param imageFormat The image_format
     */
    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    /**
     * @return The licensingRequested
     */
    public Boolean getLicensingRequested() {
        return licensingRequested;
    }

    /**
     * @param licensingRequested The licensing_requested
     */
    public void setLicensingRequested(Boolean licensingRequested) {
        this.licensingRequested = licensingRequested;
    }

    /**
     * @return The user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PX500Photo{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", camera='" + camera + '\'' +
                ", lens='" + lens + '\'' +
                ", focalLength='" + focalLength + '\'' +
                ", iso='" + iso + '\'' +
                ", shutterSpeed='" + shutterSpeed + '\'' +
                ", aperture='" + aperture + '\'' +
                ", timesViewed=" + timesViewed +
                ", rating=" + rating +
                ", status=" + status +
                ", createdAt='" + createdAt + '\'' +
                ", category=" + category +
                ", location=" + location +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", takenAt=" + takenAt +
                ", hiResUploaded=" + hiResUploaded +
                ", forSale=" + forSale +
                ", width=" + width +
                ", height=" + height +
                ", votesCount=" + votesCount +
                ", favoritesCount=" + favoritesCount +
                ", commentsCount=" + commentsCount +
                ", nsfw=" + nsfw +
                ", salesCount=" + salesCount +
                ", forSaleDate=" + forSaleDate +
                ", highestRating=" + highestRating +
                ", highestRatingDate='" + highestRatingDate + '\'' +
                ", licenseType=" + licenseType +
                ", converted=" + converted +
                ", collectionsCount=" + collectionsCount +
                ", cropVersion=" + cropVersion +
                ", privacy=" + privacy +
                ", imageUrl='" + imageUrl + '\'' +
                ", images=" + images +
                ", url='" + url + '\'' +
                ", positiveVotesCount=" + positiveVotesCount +
                ", convertedBits=" + convertedBits +
                ", shareCounts=" + shareCounts +
                ", watermark=" + watermark +
                ", imageFormat='" + imageFormat + '\'' +
                ", licensingRequested=" + licensingRequested +
                ", user=" + user +
                '}';
    }
}
