package com.pitstop.test.view;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pitstop.test.R;
import com.pitstop.test.global.Constants;
import com.pitstop.test.global.GlideApp;
import com.pitstop.test.model.Location;
import com.pitstop.test.presenter.LocationItemPresenter;
import com.pitstop.test.presenter.LocationItemPresenterImpl;
import com.pitstop.test.utils.AppUtils;
import com.pitstop.test.view.base.BaseActivity;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by david on 28/2/18.
 */

public class LocationItemActivity extends BaseActivity implements LocationItemView {

    private static final int ZOOM = 15;

    private LocationItemPresenter presenter;

    @BindView(R.id.sliding_layout)
    SlidingUpPanelLayout slidingUpPanelLayout;

    @BindView(R.id.map)
    MapView mMapView;

    @BindView((R.id.location_image))
    ImageView image;

    @BindView(R.id.location_description)
    TextView description;

    @BindView(R.id.location_web)
    TextView web;

    @BindView(R.id.textView_state)
    TextView state;

    @BindView(R.id.textView_monday)
    TextView monday;
    @BindView(R.id.textView_tuesday)
    TextView tuesday;
    @BindView(R.id.textView_wednesday)
    TextView wednesday;
    @BindView(R.id.textView_thursday)
    TextView thursday;
    @BindView(R.id.textView_friday)
    TextView friday;
    @BindView(R.id.textView_saturday)
    TextView saturday;
    @BindView(R.id.textView_sunday)
    TextView sunday;

    private Location mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_item);

        presenter = new LocationItemPresenterImpl(this);

        setUnBinder(ButterKnife.bind(this));

        if (getIntent().hasExtra(Constants.LOCATION_ITEM)) {

            mLocation = (Location) getIntent().getSerializableExtra(Constants.LOCATION_ITEM);

            initializeViewComponents();

        } else {

            // Notify that an unexpected error occurred
            this.onError(R.string.error_occurred);

            finish();

        }

    }

    private void initializeViewComponents() {

        slidingUpPanelLayout.setPanelHeight(getResources().getDisplayMetrics().heightPixels / 3);
        slidingUpPanelLayout.setAnchorPoint(0.4f);
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);

        slidingUpPanelLayout.setScrollableView(findViewById(R.id.scrollView_details));

        getSupportActionBar().setTitle(mLocation.getTitle());

        if (URLUtil.isValidUrl(mLocation.getImage())) {

            GlideApp.with(this)
                    .load(mLocation.getImage())
                    .thumbnail(Glide.with(this).load(R.drawable.loading))
                    .error(Glide.with(this).load(R.drawable.img_not_available2))
                    .into(image);

        }

        description.setText(mLocation.getDescription());

        web.setText(Html.fromHtml("<a href=\"" + mLocation.getUrl() + "\">" + mLocation.getUrl() + "</a>"));
        web.setMovementMethod(LinkMovementMethod.getInstance());

        // Set OPEN or CLOSED text and color
        int stateText = AppUtils.getCurrentState(mLocation.getHours());
        int stateColor = R.color.red;

        if (stateText == R.string.open) {

            stateColor = R.color.green;

        }

        state.setText(stateText);
        state.setTextColor(getResources().getColor(stateColor));

        monday.setText(getResources().getString(R.string.monday) + ": " + mLocation.getHours().getMonday());
        tuesday.setText(getResources().getString(R.string.tuesday) + ": " + mLocation.getHours().getTuesday());
        wednesday.setText(getResources().getString(R.string.wednesday) + ": " + mLocation.getHours().getWednesday());
        thursday.setText(getResources().getString(R.string.thursday) + ": " + mLocation.getHours().getThursday());
        friday.setText(getResources().getString(R.string.friday) + ": " + mLocation.getHours().getFriday());
        saturday.setText(getResources().getString(R.string.saturday) + ": " + mLocation.getHours().getSaturday());
        sunday.setText(getResources().getString(R.string.sunday) + ": " + mLocation.getHours().getSunday());

        // Set the Map's marker
        try {
            Geocoder geocoder = new Geocoder(this);
            List<Address> addressList = geocoder.getFromLocationName(
                    mLocation.getAddress(), 5);
            if (addressList != null && addressList.size() > 0) {

                double lat = addressList.get(0).getLatitude();
                double lng = addressList.get(0).getLongitude();

                mMapView.addMarker(lat, lng, mLocation.getAddress(), ZOOM);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {

        presenter = null;

        image = null;

        slidingUpPanelLayout.removeAllViews();

        mMapView.removeAllViews();

        mMapView.onDestroy();

        mLocation = null;

        super.onDestroy();

    }

    @Override
    protected void onResume() {

        super.onResume();

    }

    @Override
    protected void onPause() {

        super.onPause();

    }

}
