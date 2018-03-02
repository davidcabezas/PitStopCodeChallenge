package com.pitstop.test.model;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by david on 27/2/18.
 */

public interface MainInteractor {

    Single<Boolean> isDatabaseEmpty();

    /**
     * Get data from the server (only called in 1st use)
     *
     */
    Single<List<Location>> getSightseeingLocationsData();

    /**
     * Retrieve the already stored data
     *
     */
    Single<List<Location>> retrieveSightseeingLocationsData();

    void persistData(ArrayList<Location> locationsList);

}
