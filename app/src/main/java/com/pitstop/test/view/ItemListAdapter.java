package com.pitstop.test.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;

import com.bumptech.glide.Glide;
import com.pitstop.test.R;
import com.pitstop.test.global.GlideApp;
import com.pitstop.test.model.Location;
import com.pitstop.test.utils.AppUtils;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by david on 27/2/18.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private Context context;
    private ArrayList<Location> list;

    PublishSubject<Location> clickSubject = PublishSubject.create();
    Observable<Location> clickEvents = clickSubject;

    public ItemListAdapter(Context context, ArrayList<Location> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_location, parent, false);

        ItemViewHolder holder = new ItemViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        Location location = list.get(position);

        holder.itemView.setOnClickListener(view -> clickSubject.onNext(location));

        if (URLUtil.isValidUrl(location.getImage())) {

            GlideApp.with(context)
                    .load(location.getImage())
                    .thumbnail(Glide.with(context).load(R.drawable.loading))
                    .error(Glide.with(context).load(R.drawable.img_not_available2))
                    .into(holder.image);

        }

        holder.title.setText(location.getTitle());

        holder.description.setText(location.getDescription());

        // This code set the actual state (open/closed) and the corresponding color
        int stateText = AppUtils.getCurrentState(location.getHours());

        int stateColor = R.color.red;

        if (stateText == R.string.open) {

            stateColor = R.color.green;

        }

        holder.state.setText(stateText);
        holder.state.setTextColor(context.getResources().getColor(stateColor));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
