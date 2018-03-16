package com.cris.ukulele;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String EXTRA_SONG_ID = "song_id";

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ImageButton floatButton;
    private UkuleleAdapter mUkuleleAdapter;
    private Button Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.cris.ukulele.R.layout.activity_main);

        mToolbar = findViewById(com.cris.ukulele.R.id.toolbar);
        mToolbar.setTitle(com.cris.ukulele.R.string.app_name);
        mRecyclerView = findViewById(com.cris.ukulele.R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,
                DividerItemDecoration.VERTICAL));

        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        updateUI();

        mRecyclerView.setAdapter(mUkuleleAdapter);

        floatButton = findViewById(R.id.imageButton);
        floatButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                UkuleleData song = new UkuleleData();
                Intent intent = AddSongActivity.newIntent(MainActivity.this, song.getId());
                startActivity(intent);
            }
        });

        Button = findViewById(R.id.popupbutton);

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Pop.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        UkeLab ukeLab = UkeLab.get(this);
        List<UkuleleData> songs = ukeLab.getAllSongs();

        if (mUkuleleAdapter == null) {
            mUkuleleAdapter = new UkuleleAdapter(songs);
            mRecyclerView.setAdapter(mUkuleleAdapter);
        } else {
            mUkuleleAdapter.setSongs(songs);
            mUkuleleAdapter.notifyDataSetChanged();
        }
    }

    private class UkeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private UkuleleData song;
        private TextView mArtistTextView;
        private TextView mTitleTextView;
        private TextView mTabsTextView;


        public UkeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.recyclerview_uke_item, parent, false));
            itemView.setOnClickListener(this);

            mArtistTextView = itemView.findViewById(R.id.tvArtist);
            mTitleTextView = itemView.findViewById(R.id.tvSongTitle);
            mTabsTextView = itemView.findViewById(R.id.tvSongDetails);

        }

        public void bind(UkuleleData ukuleleData) {
            song = ukuleleData;
            mArtistTextView.setText(song.getArtist());
            mTitleTextView.setText(song.getTitle());
            mTabsTextView.setText(song.getTabs());
        }

        @Override
        public void onClick(View view) {

            Intent myIntent = DetailActivity.newIntent(MainActivity.this,song.getId());
            startActivity(myIntent);
        }
    }

    private class UkuleleAdapter extends RecyclerView.Adapter<UkeHolder> {

        private List<UkuleleData> mDataList;

        public UkuleleAdapter(List<UkuleleData> ukuleleData) {
            mDataList = ukuleleData;
        }

        @Override
        public UkeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getBaseContext());
            return new UkeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(UkeHolder holder, int position) {
            UkuleleData ukuleleData = mDataList.get(position);
            holder.bind(ukuleleData);
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }

        public void setSongs(List<UkuleleData> ukuleleData) {
            mDataList = ukuleleData;
        }

    }
}

