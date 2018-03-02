package com.pitstop.test.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pitstop.test.R;

/**
 * Created by david on 27/2/18.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView title;
    public TextView description;
    public TextView state;

    public ItemViewHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.cardview_image);
        title = itemView.findViewById(R.id.cardview_title);
        description = itemView.findViewById(R.id.cardview_description);
        state = itemView.findViewById(R.id.cardview_state);
    }
}
