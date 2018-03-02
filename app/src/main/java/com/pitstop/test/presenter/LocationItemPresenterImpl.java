package com.pitstop.test.presenter;

import com.pitstop.test.view.LocationItemView;

/**
 * Created by david on 1/3/18.
 */

/**
 *
 * Presenter ready for possible new stuff in the future
 *
 */ 
public class LocationItemPresenterImpl implements LocationItemPresenter {

    private LocationItemView locationItemView;

    public LocationItemPresenterImpl(LocationItemView locationItemView) {
        this.locationItemView = locationItemView;
    }
}
