package com.apripachkin.shibagram.networking.services;


import com.apripachkin.shibagram.models.flickr.FlickrResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by apripachkin on 12/23/15.
 */
public interface FlickrService {
    @GET("?method=flickr.photos.search")
    Call<FlickrResponse> getPhotos(@Query("page") int page);
}
