package com.apripachkin.shibagram.networking.services;


import com.apripachkin.shibagram.models.px500.PX500Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by apripachkin on 12/24/15.
 */
public interface PX500Service {
    @GET("/v1/photos/search")
    Call<PX500Response> getPhotos(@Query("page") int page);
}
