package com.pitstop.test.view.base;

import android.support.annotation.StringRes;

/**
 * Created by david on 1/3/18.
 */

public interface BaseView {

    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(@StringRes int resId);

    void showMessage(String message);

}
