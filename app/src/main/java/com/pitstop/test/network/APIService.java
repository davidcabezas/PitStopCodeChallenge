package com.pitstop.test.network;

import com.pitstop.test.model.Location;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by david on 27/2/18.
 */

public interface APIService {

    @GET("/bins/1vhe1/")
    Single<List<Location>> getSightseeingLocationsData();

}
