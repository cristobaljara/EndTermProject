package com.cris.ukulele;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

/**
 * Created by crisp_000 on 08-02-2018.
 */

public class UkuleleAdapter extends RecyclerView.Adapter<UkuleleViewHolder> {

    private List<UkuleleData> mUkuleleData;
    private Context mContext;

    public UkuleleAdapter(Context mContext, List<UkuleleData> mUkuleleData) {
        this.mUkuleleData = mUkuleleData;
        this.mContext = mContext;
    }

    @Override
    public UkuleleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.cris.ukulele.R.layout.recyclerview_uke_item,
                parent, false);
        return new UkuleleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UkuleleViewHolder holder, int position) {
        holder.mUkeIcon.setText(mUkuleleData.get(position).getArtist().substring(0, 1));
        holder.mUkeArtist.setText(mUkuleleData.get(position).getArtist());
        holder.mUkeTitle.setText(mUkuleleData.get(position).getTitle());
        holder.mUkeDetails.setText(mUkuleleData.get(position).getTabs());
        Random mRandom = new Random();
        final int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
        ((GradientDrawable) holder.mUkeIcon.getBackground()).setColor(color);

        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, DetailActivity.class);
                mIntent.putExtra("sender", holder.mUkeArtist.getText().toString());
                mIntent.putExtra("title", holder.mUkeTitle.getText().toString());
                mIntent.putExtra("details", holder.mUkeDetails.getText().toString());
                mIntent.putExtra("icon", holder.mUkeIcon.getText().toString());
                mIntent.putExtra("colorIcon", color);
                mContext.startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUkuleleData.size();
    }
}

