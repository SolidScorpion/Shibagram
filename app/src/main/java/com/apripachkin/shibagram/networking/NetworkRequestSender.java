package com.apripachkin.shibagram.networking;


import android.util.Log;

import com.apripachkin.shibagram.constants.Constants;
import com.apripachkin.shibagram.models.ResponseConverter;
import com.apripachkin.shibagram.models.flickr.FlickrResponse;
import com.apripachkin.shibagram.models.px500.PX500Response;
import com.apripachkin.shibagram.models.shibaphoto.pojo.ShibaPhotoPojo;
import com.apripachkin.shibagram.networking.services.FlickrService;
import com.apripachkin.shibagram.networking.services.PX500Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkRequestSender {
    public static final String KEY_FLICKR_CURRENT_PAGE = "networkrequest_current_flickr_key";
    public static final String KEY_TOTAL_FLICKR_PAGES = "networkrequest_total_flickr_pages_key";
    public static final String KEY_500PX_CURRENT_PAGE = "networkrequest_current_500px_key";
    public static final String KEY_500PX_TOTAL_PAGES = "networkrequest_total_500px_key";
    private static final String TAG = NetworkRequestSender.class.getSimpleName();
    private static NetworkRequestSender instance;
    private ResponseConverter converter = new ResponseConverter();
    private FlickrService flickrService;
    private PX500Service px500Service;
    private int numOfRequests = 0;
    private List<ShibaPhotoPojo> cacheData = new ArrayList<>();
    private int currentFlickrPage;
    private int totalFlickrPages;

    private int current500pxPage;
    private int total500PxPages;

    private NetworkRequestSender() {
    }

    public static NetworkRequestSender getInstance() {
        if (instance == null) {
            instance = new NetworkRequestSender();
        }
        return instance;
    }

    public int getCurrentFlickrPage() {
        return currentFlickrPage;
    }

    public void setCurrentFlickrPage(int currentFlickrPage) {
        this.currentFlickrPage = currentFlickrPage;
    }

    public int getTotalFlickrPages() {
        return totalFlickrPages;
    }

    public void setTotalFlickrPages(int totalFlickrPages) {
        this.totalFlickrPages = totalFlickrPages;
    }

    public int getCurrent500pxPage() {
        return current500pxPage;
    }

    public void setCurrent500pxPage(int current500pxPage) {
        this.current500pxPage = current500pxPage;
    }

    public int getTotal500PxPages() {
        return total500PxPages;
    }

    public void setTotal500PxPages(int total500PxPages) {
        this.total500PxPages = total500PxPages;
    }

    public void sendBatchRequest(NetworkRequestCallback callback) {
        sendFlickrRequest(Constants.DEFAULT_PAGE, callback);
        sendPX500Request(Constants.DEFAULT_PAGE, callback);
    }

    public void sendFlickrRequest(int page, final NetworkRequestCallback callback) {
        if (flickrService == null) {
            flickrService = ServiceCreator.createService(FlickrService.class);
        }
        numOfRequests++;
        Call<FlickrResponse> photos = flickrService.getPhotos(page);
        photos.enqueue(new Callback<FlickrResponse>() {
            @Override
            public void onResponse(Call<FlickrResponse> call, Response<FlickrResponse> response) {
                numOfRequests--;
                FlickrResponse body = response.body();
                if (body != null) {
                    totalFlickrPages = Integer.parseInt(body.getPhotos().getPages());
                    currentFlickrPage = body.getPhotos().getPage();
                    List<ShibaPhotoPojo> collection = converter.convertFlickrResponse(body);
                    deliverResults(collection, callback);
                    Log.d(TAG, "onResponse() called with: total flickr pages: " + totalFlickrPages + " current page: " + currentFlickrPage);
                } else {
                    deliverResults(Collections.<ShibaPhotoPojo>emptyList(), callback);
                }
            }

            @Override
            public void onFailure(Call<FlickrResponse> call, Throwable t) {
                numOfRequests--;
                callback.onFailure(t);
                deliverResults(null, callback);
            }
        });
    }

    public void loadMoreData(NetworkRequestCallback callback) {
        Log.d(TAG, "loadMoreData() called with: callback = [" + callback + "]");
        loadNext500PxPage(callback);
        loadNextFlickrPage(callback);
    }

    public void loadNext500PxPage(NetworkRequestCallback callback) {
        Log.d(TAG, "loadNext500PxPage: current500PxPage: " + current500pxPage + " total pages: " + total500PxPages);
        if (current500pxPage == 0 || total500PxPages == 0) {
            return;
        }
        if (current500pxPage > total500PxPages) {
            callback.onFailure(new IllegalArgumentException("No more data to load from 500px"));
            return;
        }
        int page = ++current500pxPage;
        sendPX500Request(page, callback);
    }

    public void loadNextFlickrPage(NetworkRequestCallback callback) {
        Log.d(TAG, "loadNextFlickrPage: currentFlickrPage: " + currentFlickrPage + " total pages: " + totalFlickrPages);
        if (currentFlickrPage == 0 || totalFlickrPages == 0) {
            return;
        }
        if (currentFlickrPage > totalFlickrPages) {
            callback.onFailure(new IllegalArgumentException("No more data to load from flickr"));
            return;
        }
        int page = ++currentFlickrPage;
        sendFlickrRequest(page, callback);
    }

    public void sendPX500Request(int page, final NetworkRequestCallback callback) {
        if (px500Service == null) {
            px500Service = ServiceCreator.createService(PX500Service.class);
        }
        numOfRequests++;
        Call<PX500Response> photos = px500Service.getPhotos(page);
        photos.enqueue(new Callback<PX500Response>() {
            @Override
            public void onResponse(Call<PX500Response> call, Response<PX500Response> response) {
                PX500Response body = response.body();
                numOfRequests--;
                if (body != null) {
                    current500pxPage = body.getCurrentPage();
                    total500PxPages = body.getTotalPages();
                    List<ShibaPhotoPojo> shibaPhotos = converter.convert500PXresponse(body);
                    deliverResults(shibaPhotos, callback);
                    Log.d(TAG, "onResponse() called with: total 500px pages: " + total500PxPages + " current page: " + current500pxPage);
                } else {
                    deliverResults(Collections.<ShibaPhotoPojo>emptyList(), callback);
                }
            }

            @Override
            public void onFailure(Call<PX500Response> call, Throwable t) {
                numOfRequests--;
                callback.onFailure(t);
                deliverResults(null, callback);
            }
        });
    }

    private void deliverResults(List<ShibaPhotoPojo> results, NetworkRequestCallback callback) {
        Log.d(TAG, "deliverResults() called with: results = [" + results + "], callback = [" + callback + "]");
        if (results != null) {
            cacheData.addAll(results);
        }
        if (numOfRequests == 0) {
            callback.onDataDelivered(cacheData);
        }
    }

    public void clearCacheData() {
        cacheData.clear();
    }

    public interface NetworkRequestCallback {
        void onDataDelivered(List<ShibaPhotoPojo> data);

        void onFailure(Throwable throwable);
    }
}
