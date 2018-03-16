package com.cris.ukulele;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

public class DetailActivity extends AppCompatActivity {

    private static final String ARG_SONG_ID = "song_id";
    private ViewPager mViewPager;
    private UkuleleData mUkuleleData;
    private TextView mIcon;
    private EditText mArtist;
    private EditText mSongTitle;
    private EditText mSongDetails;
    private Button Button;



    public static Intent newIntent(Context packageContext, UUID songId) {
        Intent intent = new Intent(packageContext, DetailActivity.class);
        intent.putExtra(ARG_SONG_ID, songId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.cris.ukulele.R.layout.activity_detail);

        UUID songId = (UUID) getIntent().getSerializableExtra(ARG_SONG_ID);
        mUkuleleData = UkeLab.get(this).getSong(songId);

        mViewPager = findViewById(R.id.ukulele_view_pager);

        mIcon = findViewById(com.cris.ukulele.R.id.tvIcon);
        mArtist = findViewById(com.cris.ukulele.R.id.tvArtist);
        mSongTitle = findViewById(com.cris.ukulele.R.id.tvSongTitle);
        mSongDetails = findViewById(com.cris.ukulele.R.id.tvSongDetails);

        mArtist.setText(mUkuleleData.getArtist());
        mSongTitle.setText(mUkuleleData.getTitle());
        mSongDetails.setText(mUkuleleData.getTabs());

        Button = findViewById(R.id.popupbutton);

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailActivity.this, Pop.class));
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mUkuleleData.setArtist(mArtist.getText().toString());
        mUkuleleData.setTitle(mSongTitle.getText().toString());
        mUkuleleData.setTabs(mSongDetails.getText().toString());


        UkeLab.get(this).updateSong(mUkuleleData);
    }

}
