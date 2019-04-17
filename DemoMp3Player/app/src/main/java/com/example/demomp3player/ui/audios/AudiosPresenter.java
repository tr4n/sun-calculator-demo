package com.example.demomp3player.ui.audios;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;

import android.widget.Toast;

import com.example.demomp3player.R;
import com.example.demomp3player.data.model.AudioModel;
import com.example.demomp3player.data.model.OnDataLoadedCallback;
import com.example.demomp3player.data.source.local.AudioLocalDataSource;

import com.example.demomp3player.service.AudioPlayerService;
import com.example.demomp3player.util.Constant;

import java.util.List;


import static android.content.Context.BIND_AUTO_CREATE;

public class AudiosPresenter implements AudiosContract.Presenter {

    private static final String[] PERMISSIONS = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int REQUEST_PERMISSION = 1;
    private final AudiosContract.View mAudioContractView;
    private final AudioLocalDataSource mAudioLocalDataSource;
    private final Context mContext;
    private static int sAudioStatus = Constant.AUDIO_EMPTY;
    private AudioPlayerService mAudioPlayerService;


    AudiosPresenter(AudiosContract.View mAudioContractView) {
        this.mAudioContractView = mAudioContractView;
        this.mContext = (Context) mAudioContractView;
        this.mAudioLocalDataSource = AudioLocalDataSource.getInstance(mContext);
        mAudioContractView.setPresenter(this);
    }


    @Override
    public boolean isPermissionGranted(String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (mContext.checkSelfPermission(permissions[0])
                    == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(
                        (Activity) mContext,
                        permissions,
                        REQUEST_PERMISSION
                );

                return false;
            }
        }
        return true;
    }

    @Override
    public void start() {
        if (isPermissionGranted(PERMISSIONS)) loadExternalAudios();
    }

    @Override
    public void loadExternalAudios() {
        mAudioLocalDataSource.getExternalAudios(new OnDataLoadedCallback<List<AudioModel>>() {
            @Override
            public void onDataLoaded(List<AudioModel> data) {
                mAudioContractView.showExternalAudios(data);
            }

            @Override
            public void onDataNotAvailable() {
                Toast.makeText(mContext, mContext.getString(R.string.message_data_not_avaiable), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBindService() {
        Intent intent = new Intent(mContext, AudioPlayerService.class);
        mContext.bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    public void updateCurrentAudioTab() {
        if (mAudioPlayerService == null) return;
        sAudioStatus = mAudioPlayerService.getStatus();
        switch (sAudioStatus) {
            case Constant.AUDIO_EMPTY:
                mAudioContractView.hideCurrentAudioTab();
                break;
            case Constant.AUDIO_PLAYING:
                mAudioContractView.showCurrentAudioTab(mAudioPlayerService.getCurrent());
                mAudioContractView.updateCurrentAudioStatus(Constant.AUDIO_PLAYING);
                break;
            case Constant.AUDIO_PAUSE:
                mAudioContractView.showCurrentAudioTab(mAudioPlayerService.getCurrent());
                mAudioContractView.updateCurrentAudioStatus(Constant.AUDIO_PAUSE);
                break;
        }
    }

    @Override
    public void onStartAudio(AudioModel audioModel) {
        sAudioStatus = Constant.AUDIO_PLAYING;
        Intent intent = new Intent(mContext, AudioPlayerService.class);
        intent.putExtra(Constant.EXTRA_AUDIO, audioModel);
        mContext.startService(intent);
    }

    @Override
    public void onControlPlayer() {
        switch (sAudioStatus) {
            case Constant.AUDIO_EMPTY:
                mAudioContractView.showCurrentAudioTab(mAudioPlayerService.getCurrent());
                break;
            case Constant.AUDIO_PLAYING:
                sAudioStatus = Constant.AUDIO_PAUSE;
                mAudioContractView.updateCurrentAudioStatus(Constant.AUDIO_PAUSE);
                mAudioPlayerService.pause();
                break;
            case Constant.AUDIO_PAUSE:
                sAudioStatus = Constant.AUDIO_PLAYING;
                mAudioContractView.updateCurrentAudioStatus(Constant.AUDIO_PLAYING);
                mAudioPlayerService.play();
                break;

        }
    }

    @Override
    public void onStopAudio() {
        sAudioStatus = Constant.AUDIO_EMPTY;
        mAudioPlayerService.stop();
    }


    @Override
    public void onUnbindService() {
        mContext.unbindService(mServiceConnection);

    }


    @Override
    public void onDestroyService() {
        if (sAudioStatus == Constant.AUDIO_EMPTY) {
            Intent intent = new Intent(mContext, AudioPlayerService.class);
            mContext.stopService(intent);
        }
    }


    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AudioPlayerService.PlayAudioBinder binder = (AudioPlayerService.PlayAudioBinder) service;
            mAudioPlayerService = binder.getService();
            updateCurrentAudioTab();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

    };
}