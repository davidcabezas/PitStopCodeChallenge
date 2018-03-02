package com.pitstop.test.view.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.pitstop.test.R;
import com.pitstop.test.utils.AppUtils;

import butterknife.Unbinder;

/**
 * Created by david on 28/2/18.
 */

public class BaseActivity extends AppCompatActivity implements BaseView {

    private Unbinder mUnBinder;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    protected void onDestroy() {

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public void showLoading() {

        hideLoading();
        mProgressDialog = AppUtils.showLoadingDialog(this);

    }

    @Override
    public void hideLoading() {

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }

    }

    @Override
    public void onError(int resId) {

        showMessage(resId);

    }

    @Override
    public void onError(String message) {

        showMessage(message);

    }

    @Override
    public void showMessage(int resId) {

        showMessage(getString(resId));

    }

    @Override
    public void showMessage(String message) {

        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.error_occurred), Toast.LENGTH_SHORT).show();
        }

    }
}
