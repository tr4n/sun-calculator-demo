package com.example.demomp3player.ui.screen.audio;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demomp3player.R;
import com.example.demomp3player.data.model.AudioModel;
import com.example.demomp3player.service.PlayAudioService;
import com.example.demomp3player.ui.BaseActivity;
import com.example.demomp3player.util.Constant;

import java.util.List;

public class AudioListActivity extends BaseActivity implements AudioContract.View, AudioAdapter.ItemClickListener {


    private static final int REQUEST_PERMISSION = 1;
    private static final String[] PERMISSIONS = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
    private RecyclerView mRecyclerAudio;
    private ImageView mImageStatus;
    private TextView mTextTitle, mTextArtist;
    private AudioAdapter mAudioAdapter;
    private AudioPresenter mAudioPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void initComponent() {
        mAudioPresenter = new AudioPresenter(this);
        mRecyclerAudio = findViewById(R.id.recycler_audio);
        mImageStatus = findViewById(R.id.image_status);
        mTextTitle = findViewById(R.id.text_playing_title);
        mTextArtist = findViewById(R.id.text_playing_artist);

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
        mImageStatus.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
        mTextTitle.setText(audioModel.getTitle());
        mTextArtist.setText(audioModel.getArtist());

    }

    @Override
    public void onShowAudios(List<AudioModel> audioModels) {
        mAudioAdapter.updateData(audioModels);
    }


    @Override
    public void onItemClicked(AudioModel audioModel) {
        final String message = audioModel.getTitle() + "-" + audioModel.getArtist();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        onDisplayPlayingAudio(audioModel);
        Intent intent = new Intent(AudioListActivity.this, PlayAudioService.class);
        intent.putExtra(Constant.AUDIO_MESSAGE, audioModel);
        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAudioPresenter.onSelfCheckPermissions(PERMISSIONS))
            mAudioPresenter.start();
    }
}
