package com.apripachkin.shibagram.networking;


import com.apripachkin.shibagram.constants.ApiKeys;
import com.apripachkin.shibagram.networking.interceptors.FlickrInterceptor;
import com.apripachkin.shibagram.networking.interceptors.PX500Interceptor;
import com.apripachkin.shibagram.networking.services.FlickrService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by apripachkin on 12/21/15.
 */
public class ServiceCreator {

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    private static Retrofit.Builder builder = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create());
    private static OkHttpClient.Builder httpClient;

    private ServiceCreator() {
    }

    public static <S> S createService(Class<S> serviceClass) {
        if (isGivenService(serviceClass, FlickrService.class)) {
            builder.baseUrl(ApiKeys.FLICKR_BASE_URL);
        } else {
            builder.baseUrl(ApiKeys.PX_500_BASE_URL);
        }

        return builder.client(getHttpClient()).build().create(serviceClass);
    }

    private static boolean isGivenService(Class serviceClass, Class givenClass) {
        return serviceClass.getSimpleName().equals(givenClass.getSimpleName());
    }

    private static OkHttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = new OkHttpClient.Builder();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            httpClient.interceptors().clear();
            boolean debug = false; //todo remove
            if (debug)
                httpClient.networkInterceptors().add(loggingInterceptor);
            httpClient.networkInterceptors().add(new FlickrInterceptor());
            httpClient.networkInterceptors().add(new PX500Interceptor());

        }
        return httpClient.build();
    }
}
