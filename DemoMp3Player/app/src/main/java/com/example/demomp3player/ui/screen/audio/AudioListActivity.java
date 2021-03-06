package com.example.demomp3player.ui.screen.audio;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demomp3player.R;
import com.example.demomp3player.data.model.AudioModel;
import com.example.demomp3player.service.PlayAudioService;
import com.example.demomp3player.ui.BaseActivity;
import com.example.demomp3player.util.Constant;

import java.util.List;

public class AudioListActivity extends BaseActivity implements AudioContract.View, AudioAdapter.ItemClickListener, View.OnClickListener {


    private static final int REQUEST_PERMISSION = 1;
    private static final String[] PERMISSIONS = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
    private static boolean sIsPlaying = true;
    private RecyclerView mRecyclerAudio;
    private ImageView mImageController, mImageCancel;
    private TextView mTextTitle, mTextArtist;
    private AudioAdapter mAudioAdapter;
    private AudioPresenter mAudioPresenter;
    private ConstraintLayout mConstraintPlayingAudio;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_audio_list;
    }

    @Override
    protected void initComponent() {
        mAudioPresenter = new AudioPresenter(this);
        mRecyclerAudio = findViewById(R.id.recycler_audio);
        mImageController = findViewById(R.id.image_controller);
        mTextTitle = findViewById(R.id.text_playing_title);
        mTextArtist = findViewById(R.id.text_playing_artist);
        mConstraintPlayingAudio = findViewById(R.id.constraint_playing);
        mImageCancel = findViewById(R.id.image_playing_cancel);
    }

    @Override
    protected void initData() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        );
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                this,
                linearLayoutManager.getOrientation()
        );
        mAudioAdapter = new AudioAdapter(this);
        mRecyclerAudio.addItemDecoration(dividerItemDecoration);
        mRecyclerAudio.setLayoutManager(linearLayoutManager);
        mRecyclerAudio.setAdapter(mAudioAdapter);
        mImageController.setOnClickListener(this);
        mImageCancel.setOnClickListener(this);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                finish();
            }
        }
    }

    @Override
    public void setPresenter(AudioContract.Presenter presenter) {
        mAudioPresenter = (AudioPresenter) presenter;
    }


    @Override
    public void onDisplayPlayingAudio(AudioModel audioModel) {
        if (audioModel == null) {
            mConstraintPlayingAudio.setVisibility(View.GONE);
            Intent intent = new Intent(this, PlayAudioService.class);
            stopService(intent);
        } else {
            mConstraintPlayingAudio.setVisibility(View.VISIBLE);
            if (sIsPlaying)
                mImageController.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
            else
                mImageController.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
            mTextTitle.setText(audioModel.getTitle());
            mTextArtist.setText(audioModel.getArtist());
        }


    }

    @Override
    public void onShowAudios(List<AudioModel> audioModels) {
        mAudioAdapter.updateData(audioModels);
    }


    @Override
    public void onItemClicked(AudioModel audioModel) {
        final String message = audioModel.getTitle() + "-" + audioModel.getArtist();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        onSendRequestService(audioModel, Constant.ACTION_SEND_AUDIO);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_controller:
                onSetStatusPlaying(!sIsPlaying);
                break;
            case R.id.image_playing_cancel:
                onDisplayPlayingAudio(null);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAudioPresenter.onSelfCheckPermissions(PERMISSIONS))
            mAudioPresenter.start();
        IntentFilter intentFilter = new IntentFilter(Constant.ACTION_SEND_AUDIO);
        registerReceiver(mAudioReceiver, intentFilter);
        onSendRequestService(null, Constant.ACTION_GET_AUDIO);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(mAudioReceiver);
        super.onPause();
    }

    private void onSendRequestService(AudioModel audioModel, String action) {
        Intent intent = new Intent(AudioListActivity.this, PlayAudioService.class);
        intent.putExtra(Constant.EXTRA_ACTION, action);
        intent.putExtra(Constant.EXTRA_AUDIO, audioModel);
        startService(intent);
    }

    private void onSetStatusPlaying(boolean status) {
        sIsPlaying = status;
        if (status) {
            mImageController.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
            onSendRequestService(null, Constant.ACTION_PLAY);
        } else {
            mImageController.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
            onSendRequestService(null, Constant.ACTION_PAUSE);
        }
    }

    private BroadcastReceiver mAudioReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constant.ACTION_SEND_AUDIO)) {
                AudioModel audioModel = intent.getParcelableExtra(Constant.EXTRA_AUDIO);
                onDisplayPlayingAudio(audioModel);
            }
        }
    };


}
