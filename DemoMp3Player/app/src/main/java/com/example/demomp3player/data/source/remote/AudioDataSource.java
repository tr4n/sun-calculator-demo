package com.example.demomp3player.data.source.remote;

import android.support.annotation.NonNull;

import com.example.demomp3player.data.model.AudioModel;

import java.util.List;

public interface AudioDataSource {

    interface LoadAudiosCallback {

        void onAudiosLoaded(List<AudioModel> audioModels);

        void onDataNotAvailable();
    }

    List<AudioModel> getAudios(@NonNull LoadAudiosCallback callback);




}
