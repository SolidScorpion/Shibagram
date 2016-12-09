package com.apripachkin.shibagram.constants;

/**
 * Created by apripachkin on 12/17/15.
 */
public class Constants {

    public static final String API_KEY = "api_key";
    public static final String TAGS = "tags";
    public static final String FLICKR_TAGS = "柴犬,shiba,shibe, shiba inu";
    public static final String EXTRAS = "extras";
    public static final String FLICKR_EXTRAS = "url_z, url_l, url_o,url_m, date_upload, date_taken,owner_name";
    public static final String FORMAT = "format";
    public static final String JSON = "json";
    public static final String NOJSONCALLBACK = "nojsoncallback";
    public static final String FLICKR_NOJSON = "1";
    public static final String CONSUMER_KEY = "consumer_key";
    public static final String PX500TAGS = "shiba,shibe,柴";
    public static final String PX500TERM = "term";
    public static final String PX500PERPAGE = "rpp";
    public static final String PX500_RESULTS_PER_PAGE = "40";
    public static final String SORT = "sort";
    public static final String PX500_SORT = "created_at";
    public static final String PX500_IMAGESIZE = "image_size";
    public static final String PX500_IMG_SIZES = "4,20,21";
    public static final String FLICKR_BASE_URL = "https://www.flickr.com";
    public static final String FLICKR_PROFILE = "/people/";
    public static final String FLICKR_PHOTOS = "/photos/";
    public static final String PX500_BASE_URL = "https://500px.com";
    public static final int DEFAULT_PAGE = 1;
    public static final int FEEDBACK_EMAIL_REQUEST_CODE = 12312;

    public static final String SERVICE_TYPE_FLICKR = "FLICKR_SERVICE";
    public static final String SERVICE_TYPE_500PX = "500PX_SERVICE";

    public static final String AUTHOR_URL_LINK = "AUTHOR_URL_LINK";
    public static final String DOUBLE_TAP_TUTORIAL = "DOUBLE_TAP_TUTORIAL";

       /*
        Flickr api get profile and picture link:
        https://www.flickr.com/people/{user-id}/ - profile
        https://www.flickr.com/photos/{user-id}/ - photostream
        https://www.flickr.com/photos/{user-id}/{photo-id} - individual photo
     */
}
