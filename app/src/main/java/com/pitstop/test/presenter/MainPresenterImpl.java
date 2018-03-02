package com.pitstop.test.presenter;

import com.pitstop.test.R;
import com.pitstop.test.model.Location;
import com.pitstop.test.model.MainInteractor;
import com.pitstop.test.model.MainInteractorImpl;
import com.pitstop.test.view.MainView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by david on 27/2/18.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenterImpl(MainView mainView) {

        this.mainView = mainView;
        this.mainInteractor = new MainInteractorImpl();

    }

    // TODO: This methos must return a List<Location>
    @Override
    public void getSightseeingLocations() {

        mainView.showLoading();

        // TODO: return a List<Location>. Should 'emit' value?
        mainInteractor.isDatabaseEmpty()
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Boolean aBoolean) {

                        // Send results from Service
                        if (aBoolean) {

                            mainInteractor.getSightseeingLocationsData()
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new SingleObserver<List<Location>>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onSuccess(List<Location> locations) {

                                            mainView.hideLoading();
                                            mainView.notifyGetSightseeingLocationsData((ArrayList<Location>) locations);

                                            mainInteractor.persistData((ArrayList<Location>) locations);

                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                            e.printStackTrace();
                                            mainView.hideLoading();
                                            mainView.showMessage(R.string.error_occurred);

                                        }
                                    });

                        } else { // Send results from Database

                            mainInteractor.retrieveSightseeingLocationsData().subscribe(new SingleObserver<List<Location>>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onSuccess(List<Location> list) {

                                    mainView.hideLoading();
                                    mainView.notifyGetSightseeingLocationsData((ArrayList<Location>) list);

                                }

                                @Override
                                public void onError(Throwable e) {

                                    e.printStackTrace();
                                    mainView.hideLoading();
                                    mainView.showMessage(R.string.error_occurred);

                                }
                            });

                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        e.printStackTrace();
                        mainView.hideLoading();
                        mainView.showMessage(R.string.error_occurred);

                    }
                });

    }

    @Override
    public void onLocationItemClick(Location location) {

        mainView.openLocationItemActivity(location);

    }

}
