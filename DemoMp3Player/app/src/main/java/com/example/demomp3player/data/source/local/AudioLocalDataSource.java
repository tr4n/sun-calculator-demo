package com.example.demomp3player.data.source.local;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import com.example.demomp3player.data.model.AudioModel;
import com.example.demomp3player.data.model.OnDataLoadedCallback;
import com.example.demomp3player.data.source.remote.AudioDataSource;

import java.util.ArrayList;
import java.util.List;

public class AudioLocalDataSource implements AudioDataSource {

    private static final String TAG = "AudioLocalDataSource";
    private final Context mContext;
    private static AudioLocalDataSource instance;


    private AudioLocalDataSource(Context context) {
        this.mContext = context;
    }

    public static AudioLocalDataSource getInstance(Context context) {
        if (instance == null) {
            instance = new AudioLocalDataSource(context);
        }
        return instance;
    }

    @Override
    public boolean getExternalAudios(@NonNull OnDataLoadedCallback callback) {
        List<AudioModel> audioModels = new ArrayList<>();
        ContentResolver contentResolver = mContext.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        Cursor cursor = contentResolver.query(uri, null, selection, null, null);

        if (null == cursor) {
            callback.onDataNotAvailable();
            return false;
        }

        if (cursor.moveToFirst()) {
            do {

                int titleIndex = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                int artistIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                int dataIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);

                final AudioModel audioModel = new AudioModel(
                        cursor.getString(titleIndex),
                        cursor.getString(artistIndex),
                        cursor.getString(dataIndex)
                );

                audioModels.add(audioModel);

            } while (cursor.moveToNext());

        }
        cursor.close();
        callback.onDataLoaded(audioModels);
        return true;


    }

}
