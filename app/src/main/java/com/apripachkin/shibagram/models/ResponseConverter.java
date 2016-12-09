package com.apripachkin.shibagram.models;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.apripachkin.shibagram.constants.Constants;
import com.apripachkin.shibagram.models.flickr.FlickrResponse;
import com.apripachkin.shibagram.models.flickr.Photo;
import com.apripachkin.shibagram.models.px500.PX500Photo;
import com.apripachkin.shibagram.models.px500.PX500Response;
import com.apripachkin.shibagram.models.shibaphoto.pojo.ShibaLikedPhotoPojo;
import com.apripachkin.shibagram.models.shibaphoto.pojo.ShibaPhotoPojo;
import com.apripachkin.shibagram.models.shibaphoto.viewmodel.ShibaPhoto;
import com.apripachkin.shibagram.models.shibaphoto.viewmodel.ShibaPhotoHeader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by apripachkin on 1/5/16.
 */
public class ResponseConverter {
    private static Random rnd = new Random();
    private static SimpleDateFormat flickrDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat px500DateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");

    public static ShibaPhoto convertLikedPojoToViewModel(ShibaLikedPhotoPojo convertModel) {
        String authorUsername = convertModel.getAuthorUsername();
        String description = convertModel.getDescription();
        String imageUrl = convertModel.getImageUrl();
        String serviceType = convertModel.getServiceType();
        long timePosted = convertModel.getTimePosted();
        boolean liked = convertModel.isLiked();
        String authorUrl = convertModel.getAuthorUrl();
        String shareImgUrl = convertModel.getShareImgUrl();
        return getShibaPhoto(authorUsername, description, imageUrl, shareImgUrl, serviceType, liked, timePosted, authorUrl);
    }

    public static ShibaPhoto convertPojoToViewModel(ShibaPhotoPojo convertModel) {
        String authorUsername = convertModel.getAuthorUsername();
        String description = convertModel.getDescription();
        String imageUrl = convertModel.getImageUrl();
        String serviceType = convertModel.getServiceType();
        boolean liked = convertModel.isLiked();
        long timePosted = convertModel.getTimePosted();
        String authorUrl = convertModel.getAuthorUrl();
        String shareImgUrl = convertModel.getShareImgUrl();
        return getShibaPhoto(authorUsername, description, imageUrl, shareImgUrl, serviceType, liked, timePosted, authorUrl);
    }

    @NonNull
    private static ShibaPhoto getShibaPhoto(String authorUsername, String description, String imageUrl, String shareImgUrl, String serviceType, boolean liked, long timePosted, String authorUrl) {
        long headerId = rnd.nextLong();
        ShibaPhotoHeader shibaPhotoHeader = new ShibaPhotoHeader(String.valueOf(headerId), authorUsername, timePosted);
        return new ShibaPhoto(shibaPhotoHeader, imageUrl, shareImgUrl, authorUsername, description, timePosted, serviceType, authorUrl, String.valueOf(headerId), liked);
    }

    public static ShibaLikedPhotoPojo convertModelToLiked(ShibaPhotoPojo convertModel) {
        ShibaLikedPhotoPojo model = new ShibaLikedPhotoPojo();
        model.setAuthorUsername(convertModel.getAuthorUsername());
        model.setDescription(convertModel.getDescription());
        model.setTimePosted(convertModel.getTimePosted());
        model.setImageUrl(convertModel.getImageUrl());
        model.setServiceType(convertModel.getServiceType());
        model.setLiked(convertModel.isLiked());
        model.setAuthorUrl(convertModel.getAuthorUrl());
        return model;
    }


    public List<ShibaPhotoPojo> convertFlickrResponse(FlickrResponse response) {
        List<ShibaPhotoPojo> result = new ArrayList<>();
        for (Photo entry : response.getPhotos().getPhoto()) {
            String user = entry.getOwnername();
            String owner = entry.getOwner();
//            String authorUrl = Constants.FLICKR_BASE_URL + Constants.FLICKR_PROFILE + owner;
            String imageUrl = Constants.FLICKR_BASE_URL + Constants.FLICKR_PHOTOS + owner + "/" + entry.getId();
            String urlO = entry.getUrlO();
//            String urlL = entry.getUrlL();
//            String urlZ = entry.getUrlZ();
            String urlM = entry.getUrlM();
            long dateUpload = convertFlickrTime(entry.getDatetaken());
            String description = checkDescription(entry.getTitle());
            result.add(createShibaPhotoPojo(urlM, urlO, user, description, dateUpload, Constants.SERVICE_TYPE_FLICKR, imageUrl));
        }
        return result;
    }

    public List<ShibaPhotoPojo> convert500PXresponse(PX500Response response) {
        List<ShibaPhotoPojo> result = new ArrayList<>();
        for (PX500Photo px500Photo : response.getPX500Photos()) {
            String url = px500Photo.getImages().get(1).getUrl();
            String shareImgUrl = px500Photo.getImageUrl();
            String authorUrl = Constants.PX500_BASE_URL + px500Photo.getUrl();
            String username = px500Photo.getUser().getUsername();
            String description = checkDescription(px500Photo.getDescription());
            long px500Time = convertPX500Time(px500Photo.getCreatedAt());
            result.add(createShibaPhotoPojo(url, shareImgUrl, username, description, px500Time, Constants.SERVICE_TYPE_500PX, authorUrl));
        }
        return result;
    }

    private ShibaPhotoPojo createShibaPhotoPojo(String url, String shareImgUrl, String user, String description, long date, String serviceType, String authorUrl) {
        ShibaPhotoPojo shibaPhotoPojo = new ShibaPhotoPojo();
        shibaPhotoPojo.setAuthorUsername(user);
        shibaPhotoPojo.setDescription(description);
        shibaPhotoPojo.setImageUrl(url);
        shibaPhotoPojo.setShareImgUrl(shareImgUrl);
        shibaPhotoPojo.setTimePosted(date);
        shibaPhotoPojo.setServiceType(serviceType);
        shibaPhotoPojo.setAuthorUrl(authorUrl);
        return shibaPhotoPojo;
    }

    private String checkDescription(String description) {
        if (TextUtils.isEmpty(description)) {
            return description = "No description available";
        }
        return description;
    }

    private long convertFlickrTime(String time) {
        try {
            return flickrDateFormat.parse(time).getTime();
        } catch (ParseException e) {
            return new Date().getTime();
        }
    }

    private long convertPX500Time(String time) {
        try {
            return px500DateFormat.parse(time).getTime();
        } catch (ParseException e) {
            return new Date().getTime();
        }
    }

}
