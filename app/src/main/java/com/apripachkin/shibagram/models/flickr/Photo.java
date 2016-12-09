package com.apripachkin.shibagram.models.flickr;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("secret")
    @Expose
    private String secret;
    @SerializedName("server")
    @Expose
    private String server;
    @SerializedName("farm")
    @Expose
    private int farm;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("ispublic")
    @Expose
    private int ispublic;
    @SerializedName("isfriend")
    @Expose
    private int isfriend;
    @SerializedName("isfamily")
    @Expose
    private int isfamily;
    @SerializedName("dateupload")
    @Expose
    private long dateupload;
    @SerializedName("datetaken")
    @Expose
    private String datetaken;
    @SerializedName("datetakengranularity")
    @Expose
    private int datetakengranularity;
    @SerializedName("datetakenunknown")
    @Expose
    private int datetakenunknown;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("latitude")
    @Expose
    private int latitude;
    @SerializedName("longitude")
    @Expose
    private int longitude;
    @SerializedName("accuracy")
    @Expose
    private int accuracy;
    @SerializedName("context")
    @Expose
    private int context;
    @SerializedName("url_l")
    @Expose
    private String urlL;
    @SerializedName("height_l")
    @Expose
    private String heightL;
    @SerializedName("width_l")
    @Expose
    private String widthL;
    @SerializedName("url_m")
    @Expose
    private String urlM;
    @SerializedName("height_m")
    @Expose
    private String heightM;
    @SerializedName("width_m")
    @Expose
    private String widthM;
    @SerializedName("url_o")
    @Expose
    private String urlO;
    @SerializedName("height_o")
    @Expose
    private String heightO;
    @SerializedName("width_o")
    @Expose
    private String widthO;
    @SerializedName("url_z")
    @Expose
    private String urlZ;
    @SerializedName("height_z")
    @Expose
    private String heightZ;
    @SerializedName("width_z")
    @Expose
    private String widthZ;
    @SerializedName("ownername")
    @Expose
    private String ownername;

    public String getUrlM() {
        return urlM;
    }

    public void setUrlM(String urlM) {
        this.urlM = urlM;
    }

    public String getHeightM() {
        return heightM;
    }

    public void setHeightM(String heightM) {
        this.heightM = heightM;
    }

    public String getWidthM() {
        return widthM;
    }

    public void setWidthM(String widthM) {
        this.widthM = widthM;
    }

    /**
     * @return The ownername
     */
    public String getOwnername() {
        return ownername;
    }

    /**
     * @param ownername The ownername
     */
    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     *
     * @param owner
     * The owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     *
     * @return
     * The secret
     */
    public String getSecret() {
        return secret;
    }

    /**
     *
     * @param secret
     * The secret
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     *
     * @return
     * The server
     */
    public String getServer() {
        return server;
    }

    /**
     *
     * @param server
     * The server
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     *
     * @return
     * The farm
     */
    public int getFarm() {
        return farm;
    }

    /**
     *
     * @param farm
     * The farm
     */
    public void setFarm(int farm) {
        this.farm = farm;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The ispublic
     */
    public int getIspublic() {
        return ispublic;
    }

    /**
     *
     * @param ispublic
     * The ispublic
     */
    public void setIspublic(int ispublic) {
        this.ispublic = ispublic;
    }

    /**
     *
     * @return
     * The isfriend
     */
    public int getIsfriend() {
        return isfriend;
    }

    /**
     *
     * @param isfriend
     * The isfriend
     */
    public void setIsfriend(int isfriend) {
        this.isfriend = isfriend;
    }

    /**
     *
     * @return
     * The isfamily
     */
    public int getIsfamily() {
        return isfamily;
    }

    /**
     *
     * @param isfamily
     * The isfamily
     */
    public void setIsfamily(int isfamily) {
        this.isfamily = isfamily;
    }

    /**
     *
     * @return
     * The dateupload
     */
    public long getDateupload() {
        return dateupload;
    }

    /**
     *
     * @param dateupload
     * The dateupload
     */
    public void setDateupload(long dateupload) {
        this.dateupload = dateupload;
    }

    /**
     *
     * @return
     * The datetaken
     */
    public String getDatetaken() {
        return datetaken;
    }

    /**
     *
     * @param datetaken
     * The datetaken
     */
    public void setDatetaken(String datetaken) {
        this.datetaken = datetaken;
    }

    /**
     *
     * @return
     * The datetakengranularity
     */
    public int getDatetakengranularity() {
        return datetakengranularity;
    }

    /**
     *
     * @param datetakengranularity
     * The datetakengranularity
     */
    public void setDatetakengranularity(int datetakengranularity) {
        this.datetakengranularity = datetakengranularity;
    }

    /**
     *
     * @return
     * The datetakenunknown
     */
    public int getDatetakenunknown() {
        return datetakenunknown;
    }

    /**
     * @param datetakenunknown The datetakenunknown
     */
    public void setDatetakenunknown(int datetakenunknown) {
        this.datetakenunknown = datetakenunknown;
    }

    /**
     * @return The tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * @param tags The tags
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * @return The latitude
     */
    public int getLatitude() {
        return latitude;
    }

    /**
     * @param latitude The latitude
     */
    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    /**
     * @return The longitude
     */
    public int getLongitude() {
        return longitude;
    }

    /**
     * @param longitude The longitude
     */
    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    /**
     * @return The accuracy
     */
    public int getAccuracy() {
        return accuracy;
    }

    /**
     * @param accuracy The accuracy
     */
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * @return The context
     */
    public int getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(int context) {
        this.context = context;
    }

    /**
     * @return The urlL
     */
    public String getUrlL() {
        return urlL;
    }

    /**
     *
     * @param urlL
     * The url_l
     */
    public void setUrlL(String urlL) {
        this.urlL = urlL;
    }

    /**
     *
     * @return
     * The heightL
     */
    public String getHeightL() {
        return heightL;
    }

    /**
     *
     * @param heightL
     * The height_l
     */
    public void setHeightL(String heightL) {
        this.heightL = heightL;
    }

    /**
     *
     * @return
     * The widthL
     */
    public String getWidthL() {
        return widthL;
    }

    /**
     *
     * @param widthL
     * The width_l
     */
    public void setWidthL(String widthL) {
        this.widthL = widthL;
    }

    /**
     *
     * @return
     * The urlO
     */
    public String getUrlO() {
        return urlO;
    }

    /**
     *
     * @param urlO
     * The url_o
     */
    public void setUrlO(String urlO) {
        this.urlO = urlO;
    }

    /**
     *
     * @return
     * The heightO
     */
    public String getHeightO() {
        return heightO;
    }

    /**
     *
     * @param heightO
     * The height_o
     */
    public void setHeightO(String heightO) {
        this.heightO = heightO;
    }

    /**
     *
     * @return
     * The widthO
     */
    public String getWidthO() {
        return widthO;
    }

    /**
     *
     * @param widthO
     * The width_o
     */
    public void setWidthO(String widthO) {
        this.widthO = widthO;
    }

    /**
     *
     * @return
     * The urlZ
     */
    public String getUrlZ() {
        return urlZ;
    }

    /**
     *
     * @param urlZ
     * The url_z
     */
    public void setUrlZ(String urlZ) {
        this.urlZ = urlZ;
    }

    /**
     *
     * @return
     * The heightZ
     */
    public String getHeightZ() {
        return heightZ;
    }

    /**
     *
     * @param heightZ
     * The height_z
     */
    public void setHeightZ(String heightZ) {
        this.heightZ = heightZ;
    }

    /**
     *
     * @return
     * The widthZ
     */
    public String getWidthZ() {
        return widthZ;
    }

    /**
     * @param widthZ The width_z
     */
    public void setWidthZ(String widthZ) {
        this.widthZ = widthZ;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", secret='" + secret + '\'' +
                ", server='" + server + '\'' +
                ", farm=" + farm +
                ", title='" + title + '\'' +
                ", ispublic=" + ispublic +
                ", isfriend=" + isfriend +
                ", isfamily=" + isfamily +
                ", dateupload='" + dateupload + '\'' +
                ", datetaken='" + datetaken + '\'' +
                ", datetakengranularity=" + datetakengranularity +
                ", datetakenunknown=" + datetakenunknown +
                ", tags='" + tags + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", accuracy=" + accuracy +
                ", context=" + context +
                ", urlL='" + urlL + '\'' +
                ", heightL='" + heightL + '\'' +
                ", widthL='" + widthL + '\'' +
                ", urlO='" + urlO + '\'' +
                ", heightO='" + heightO + '\'' +
                ", widthO='" + widthO + '\'' +
                ", urlZ='" + urlZ + '\'' +
                ", heightZ='" + heightZ + '\'' +
                ", widthZ='" + widthZ + '\'' +
                '}';
    }
}