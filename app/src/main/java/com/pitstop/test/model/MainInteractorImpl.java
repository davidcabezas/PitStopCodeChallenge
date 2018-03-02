package com.pitstop.test.model;

import com.pitstop.test.network.APIService;
import com.pitstop.test.network.APIUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by david on 27/2/18.
 */

public class MainInteractorImpl implements MainInteractor {

    private APIService mAPIService;

    @Override
    public Single<Boolean> isDatabaseEmpty() {

        boolean isEmpty;

        ArrayList<Location> listLocation = (ArrayList<Location>) Location.listAll(Location.class);

        if (listLocation != null && listLocation.size() > 0) {
            isEmpty = false;
        } else {
            isEmpty = true;
        }

        return Single.just(isEmpty);

    }

    @Override
    public Single<List<Location>> getSightseeingLocationsData() {

        mAPIService = APIUtils.getAPIService();

        return mAPIService.getSightseeingLocationsData();

    }

    @Override
    public Single<List<Location>> retrieveSightseeingLocationsData() {

        return Single.create(emitter -> {

            ArrayList<Location> listLocation = (ArrayList<Location>) Location.listAll(Location.class);

            if (listLocation == null && listLocation.size() == 0) {
                emitter.onError(new Exception("No data found"));
            }

            for (Location location : listLocation) {

                Hours hours = Hours.findById(Hours.class, location.getId());

                location.setHours(hours);

            }

            emitter.onSuccess(listLocation);

        });

    }

    @Override
    public void persistData(ArrayList<Location> listLocations) {

        for (Location location : listLocations) {

            // First save the Location object and remember its ID
            long locationId = location.save();

            // Then save the Hours object with its Location associated ID
            Hours hours = new Hours(locationId, location.getHours());
            long hoursId = hours.save();

            // Finally add the stored Hours object's ID to the Location object
            location.setHoursId(hoursId);
            location.save();

        }

    }

}
