package com.apripachkin.shibagram.networking.interceptors;


import com.apripachkin.shibagram.constants.ApiKeys;
import com.apripachkin.shibagram.constants.Constants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by apripachkin on 12/29/15.
 */
public class FlickrInterceptor extends QueryInterceptor {
    static {
        System.loadLibrary("keys");
    }

    private native String getFlickrKey();

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        String host = request.url().host();
        if (ApiKeys.FLICKR_BASE_URL.contains(host)) {
            return chain.proceed(getRequest(request, getFlickrParams()));
        }
        return chain.proceed(request);
    }

    private Map<String, String> getFlickrParams() {
        HashMap<String, String> flickrParams = new HashMap<>();
        flickrParams.put(Constants.API_KEY, getFlickrKey());//ApiKeys.FLICKR_KEY);
        flickrParams.put(Constants.TAGS, Constants.FLICKR_TAGS);
        flickrParams.put(Constants.EXTRAS, Constants.FLICKR_EXTRAS);
        flickrParams.put(Constants.FORMAT, Constants.JSON);
        flickrParams.put(Constants.NOJSONCALLBACK, Constants.FLICKR_NOJSON);
        return flickrParams;
    }

}
