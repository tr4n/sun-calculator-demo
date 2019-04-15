package com.example.demomp3player.data.source.local;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.demomp3player.data.model.AudioModel;
import com.example.demomp3player.data.source.remote.AudioDataSource;

import java.util.ArrayList;
import java.util.List;

public class AudioLocalDataSource implements AudioDataSource {

    private static final String TAG = "AudioLocalDataSource";
    private final Context mContext;
    private static  AudioLocalDataSource instance;


    private AudioLocalDataSource(Context context){
        this.mContext = context;
    }
    public static AudioLocalDataSource getInstance(Context context) {
        if (instance == null) {
                instance = new AudioLocalDataSource(context);
        }
        return instance;
    }

    @Override
    public List<AudioModel> getAudios(@NonNull LoadAudiosCallback callback) {
        List<AudioModel> audioModelList = new ArrayList<>();
        ContentResolver contentResolver = mContext.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        Cursor cursor = contentResolver.query(uri, null, selection, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {

                    int titleIndex = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                    int artistIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                    int durationIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
                    int dataIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);

                   AudioModel audioModel = new AudioModel(
                            cursor.getString(titleIndex),
                            cursor.getString(artistIndex),
                            cursor.getLong(durationIndex),
                            cursor.getString(dataIndex)
                    );
                    Log.d(TAG, "getAudioList: " + audioModel);
                    audioModelList.add(audioModel);

                } while (cursor.moveToNext());

            }
            cursor.close();
            callback.onAudiosLoaded(audioModelList);
            return audioModelList;
        }
        callback.onDataNotAvailable();
        return null;
    }

}
