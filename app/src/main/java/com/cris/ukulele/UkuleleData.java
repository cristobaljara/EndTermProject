package com.cris.ukulele;

import java.util.Date;
import java.util.UUID;

/**
 * Created by crisp_000 on 08-02-2018.
 */

public class UkuleleData {
    private UUID mId;
    private String mArtist;
    private String mTitle;
    private String mTabs;
    private Date mCreationDate;

    public UkuleleData() {

        this(UUID.randomUUID());

    }


    public UkuleleData(UUID id){

        mId = id;
        mCreationDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getTitle() {
        return mTitle;
    }


    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTabs() {
        return mTabs;
    }

    public void setTabs(String tabs) {
        mTabs = tabs;
    }

    public Date getCreationDate() {
        return mCreationDate;
    }

    public void setCreationDate(Long creationDate) {
        mCreationDate = new Date(creationDate);
    }
}
