package com.example.demomp3player.data.source.remote;

import android.support.annotation.NonNull;

import com.example.demomp3player.data.model.AudioModel;
import com.example.demomp3player.data.model.OnDataLoadedCallback;

import java.util.List;

public interface AudioDataSource {

    boolean getExternalAudios(@NonNull OnDataLoadedCallback callback);

}
