package com.pitstop.test.view;

import com.pitstop.test.view.base.BaseView;
import com.pitstop.test.model.Location;

import java.util.ArrayList;

/**
 * Created by david on 27/2/18.
 */

public interface MainView extends BaseView {

    void notifyGetSightseeingLocationsData(ArrayList<Location> locationArrayList);

    void openLocationItemActivity(Location location);

}
