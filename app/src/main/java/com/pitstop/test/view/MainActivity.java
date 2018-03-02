package com.pitstop.test.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pitstop.test.R;
import com.pitstop.test.view.base.BaseActivity;
import com.pitstop.test.global.Constants;
import com.pitstop.test.model.Location;
import com.pitstop.test.presenter.MainPresenter;
import com.pitstop.test.presenter.MainPresenterImpl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity implements MainView {

    private MainPresenter presenter;

    private Disposable disposable = null;

    @BindView(R.id.main_recyclerview)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUnBinder(ButterKnife.bind(this));

        presenter = new MainPresenterImpl(this);

        presenter.getSightseeingLocations();

    }

    private void fillRecyclerView(ArrayList<Location> locationArrayList) {

        if (mRecyclerView != null) {

            // Setup and fill Recycler View
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setItemViewCacheSize(7);
            mRecyclerView.setDrawingCacheEnabled(true);
            mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            ItemListAdapter adapter = new ItemListAdapter(getApplicationContext(), locationArrayList);

            mRecyclerView.setAdapter(adapter);

            mRecyclerView.setLayoutManager(layoutManager);

            // Responding to RecyclerView's item click
            disposable = adapter.clickEvents.subscribe(location -> {

                presenter.onLocationItemClick(location);

            });

        }

    }

    @Override
    protected void onDestroy() {

        presenter = null;

        if (disposable != null)
            disposable.dispose();

        super.onDestroy();
    }

    @Override
    public void notifyGetSightseeingLocationsData(ArrayList<Location> locationArrayList) {

        if (locationArrayList != null && locationArrayList.size() > 0) {

            fillRecyclerView(locationArrayList);

        }

    }

    @Override
    public void openLocationItemActivity(Location location) {

        Intent intent = new Intent(this, LocationItemActivity.class);
        intent.putExtra(Constants.LOCATION_ITEM, location);
        startActivity(intent);

    }

}
