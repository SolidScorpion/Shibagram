package com.apripachkin.shibagram.constants;

/**
 * Created by apripachkin on 12/17/15.
 */
public class ApiKeys {
    public static final String FLICKR_SECRET = "fbd7334f9afae3bf";
    public static final String FLICKR_BASE_URL = "https://api.flickr.com/services/rest/";
    public static final String PX_500_SECRET = "ogYLirYbe3LHMxCpR1YeoV49Qkf6pqDcnVtOhBQD";
    public static final String PX_500_BASE_URL = "https://api.500px.com";

    static {
        System.loadLibrary("keys");
    }
}
