package com.apripachkin.shibagram.networking.interceptors;


import com.apripachkin.shibagram.constants.ApiKeys;
import com.apripachkin.shibagram.constants.Constants;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by apripachkin on 12/29/15.
 */
public class PX500Interceptor extends QueryInterceptor {
    static {
        System.loadLibrary("keys");
    }

    private native String get500PxKey();
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        String host = original.url().host();
        if (ApiKeys.PX_500_BASE_URL.contains(host)) {
            return chain.proceed(getRequest(original, getPX500params()));
        }
        return chain.proceed(original);
    }

    private HashMap<String, String> getPX500params() {
        HashMap<String, String> px500params = new HashMap<>();
        px500params.put(Constants.CONSUMER_KEY, get500PxKey());//ApiKeys.PX_500_KEY);
        px500params.put(Constants.PX500TERM, Constants.PX500TAGS);
        px500params.put(Constants.PX500PERPAGE, Constants.PX500_RESULTS_PER_PAGE);
        px500params.put(Constants.SORT, Constants.PX500_SORT);
        px500params.put(Constants.PX500_IMAGESIZE, Constants.PX500_IMG_SIZES);
        return px500params;
    }
}
