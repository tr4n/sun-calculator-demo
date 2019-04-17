package com.example.demomp3player.data.model;

import android.os.Parcel;
import android.os.Parcelable;


public class AudioModel implements Parcelable {

    public static final Creator<AudioModel> CREATOR = new Creator<AudioModel>() {
        @Override
        public AudioModel createFromParcel(Parcel source) {
            return new AudioModel(source);
        }

        @Override
        public AudioModel[] newArray(int size) {
            return new AudioModel[size];
        }
    };

    private final String mTitle;
    private final String mArtist;
    private final String mPath;

    public AudioModel(String title, String artist, String data) {
        mTitle = title;
        mArtist = artist;
        mPath = data;
    }

    private AudioModel(Parcel in) {
        mTitle = in.readString();
        mArtist = in.readString();
        mPath = in.readString();
    }


    @Override
    public String toString() {
        return "{title= " + mTitle + ", path= " + mPath + "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mArtist);
        dest.writeString(mPath);
    }

    public String getTitle() {
        return mTitle;
    }


    public String getArtist() {
        return mArtist;
    }


    public String getPath() {
        return mPath;
    }


}
