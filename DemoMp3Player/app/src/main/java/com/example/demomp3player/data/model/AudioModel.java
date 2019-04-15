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

    private final String title;
    private final String artist;
    private final long duration;
    private final String data;

    public AudioModel(String title, String artist, long duration, String data) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.data = data;
    }

    private AudioModel(Parcel in) {
        this.title = in.readString();
        this.artist = in.readString();
        this.duration = in.readLong();
        this.data = in.readString();
    }


    @Override
    public String toString() {
        return "{title= " + title + ", data= " + data + "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.artist);
        dest.writeLong(this.duration);
        dest.writeString(this.data);
    }

    public String getTitle() {
        return title;
    }


    public String getArtist() {
        return artist;
    }


    public long getDuration() {
        return duration;
    }


    public String getData() {
        return data;
    }


}
