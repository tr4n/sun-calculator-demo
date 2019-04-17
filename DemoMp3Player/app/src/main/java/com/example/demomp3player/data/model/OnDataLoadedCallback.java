package com.example.demomp3player.data.model;

public interface OnDataLoadedCallback<T> {

    void onDataLoaded(T data);

    void onDataNotAvailable();
}