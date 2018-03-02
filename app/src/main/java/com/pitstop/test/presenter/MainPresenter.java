package com.pitstop.test.presenter;

import com.pitstop.test.model.Location;

/**
 * Created by david on 27/2/18.
 */

public interface MainPresenter {

    void getSightseeingLocations();

    void onLocationItemClick(Location location);

}
