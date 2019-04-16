package com.example.demomp3player.ui.screen.audio;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.example.demomp3player.data.model.AudioModel;
import com.example.demomp3player.data.source.local.AudioLocalDataSource;
import com.example.demomp3player.data.source.remote.AudioDataSource;
import com.example.demomp3player.util.Constant;

import java.util.List;

public class AudioPresenter implements AudioContract.Presenter {

    private static final int REQUEST_PERMISSION = 1;
    private final AudioContract.View mAudioContractView;
    private final AudioLocalDataSource mAudioLocalDataSource;
    private final Context mContext;


    public AudioPresenter(AudioContract.View mAudioContractView) {
        this.mAudioContractView = mAudioContractView;
        this.mContext = (Context) mAudioContractView;
        this.mAudioLocalDataSource = AudioLocalDataSource.getInstance(mContext);
        mAudioContractView.setPresenter(this);
    }

    @Override
    public void start() {
        loadAudios();
    }

    private void loadAudios() {
        mAudioLocalDataSource.getAudios(new AudioDataSource.LoadAudiosCallback() {
            @Override
            public void onAudiosLoaded(List<AudioModel> audioModels) {
                mAudioContractView.onShowAudios(audioModels);
            }

            @Override
            public void onDataNotAvailable() {
                Toast.makeText(mContext, "Load Audios failed", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public boolean onSelfCheckPermissions(String[] permissions) {
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
    public boolean onGetPlayingAudio() {
        AudioModel audioModel = ((Activity)mContext).getIntent().getExtras().getParcelable(Constant.EXTRA_AUDIO);
        if(audioModel == null) return false;
        mAudioContractView.onDisplayPlayingAudio(audioModel);
        return true;
    }
}
