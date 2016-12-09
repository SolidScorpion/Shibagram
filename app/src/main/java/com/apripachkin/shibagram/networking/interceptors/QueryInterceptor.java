package com.apripachkin.shibagram.networking.interceptors;


import java.util.Map;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by Anton Pripachkin on 27.12.2015.
 */
public abstract class QueryInterceptor implements Interceptor {

    protected Request getRequest(Request original, Map<String, String> queryParams) {
        HttpUrl.Builder httpUrlBuilder = original.url().newBuilder();
        Set<String> keys = queryParams.keySet();
        for (String key : keys) {
            httpUrlBuilder.addQueryParameter(key, queryParams.get(key));
        }
        HttpUrl httpUrl = httpUrlBuilder.build();
        return original.newBuilder().url(httpUrl).build();
    }

}
