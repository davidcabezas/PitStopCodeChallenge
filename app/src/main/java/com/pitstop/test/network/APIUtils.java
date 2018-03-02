package com.pitstop.test.network;

/**
 * Created by david on 27/2/18.
 */

public class APIUtils {

    private APIUtils() {}

    public static final String BASE_URL = "https://api.myjson.com/";

    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

}
