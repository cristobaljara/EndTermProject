package com.cris.ukulele;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;
import java.util.UUID;


/**
 * Created by crisp_000 on 27-02-2018.
 */

public class AddSongActivity extends AppCompatActivity {
    private static final String ARG_SONG_ID = "song_id";

    private UkuleleData mUkuleleData;
    private EditText mTvArtist;
    private EditText mTvSongTitle;
    private EditText mTvSongDetails;
    private Button mSubmit;

    public AddSongActivity() {
    }

    public static Intent newIntent(Context packageContext, UUID songId) {
        Intent intent = new Intent(packageContext, AddSongActivity.class);
        intent.putExtra(ARG_SONG_ID, songId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsong);
        UUID songId = (UUID) getIntent().getSerializableExtra(ARG_SONG_ID);
        mUkuleleData = UkeLab.get(this).getSong(songId);

        mTvArtist = findViewById(R.id.tvArtist);
        mTvSongTitle = findViewById(R.id.tvSongTitle);
        mTvSongDetails = findViewById(R.id.tvSongDetails);
        mTvSongDetails.setTextIsSelectable(true);
        mSubmit = findViewById(R.id.submit);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UkuleleData song = new UkuleleData();
                song.setArtist(mTvArtist.getText().toString());
                song.setTitle(mTvSongTitle.getText().toString());
                song.setTabs(mTvSongDetails.getText().toString());

                UkeLab.get(AddSongActivity.this).addSong(song);
            }
        });
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    private void updateUkuleleData() {

        UkeLab.get(this).updateSong(mUkuleleData);

    }

    private static final String EXTRA_SONG_ID =
            "com.cris.ukulele.songId";

    private ViewPager mViewPager;
    private List<UkuleleData> songList;


}
