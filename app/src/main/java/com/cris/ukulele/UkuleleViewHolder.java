package com.cris.ukulele;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by crisp_000 on 24-02-2018.
 */

public class UkuleleViewHolder extends RecyclerView.ViewHolder {

    TextView mUkeIcon;
    TextView mUkeArtist;
    TextView mUkeTitle;
    TextView mUkeDetails;
    RelativeLayout mLayout;

    UkuleleViewHolder(View itemView) {
        super(itemView);

        mUkeIcon = itemView.findViewById(com.cris.ukulele.R.id.tvIcon);
        mUkeArtist = itemView.findViewById(com.cris.ukulele.R.id.tvArtist);
        mUkeTitle = itemView.findViewById(com.cris.ukulele.R.id.tvSongTitle);
        mUkeDetails = itemView.findViewById(com.cris.ukulele.R.id.tvSongDetails);
        mLayout = itemView.findViewById(com.cris.ukulele.R.id.layout);
    }
}
