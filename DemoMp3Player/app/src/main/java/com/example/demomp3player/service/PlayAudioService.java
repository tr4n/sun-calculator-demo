package com.example.demomp3player.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.example.demomp3player.R;
import com.example.demomp3player.data.model.AudioModel;
import com.example.demomp3player.ui.screen.audio.AudioListActivity;
import com.example.demomp3player.util.Constant;

import java.io.IOException;

public class PlayAudioService extends Service {

    private static final String TAG = "PlayAudioService";
    private static final String CHANNEL_ID = "com.example.demomp3player";
    private static boolean sIsPlaying = false;
    private static int sCurrentPosition = 0;
    private MediaPlayer mMediaPlayer;
    private static AudioModel sAudio;

    public PlayAudioService() {

    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String action = intent.getExtras().getString(Constant.EXTRA_ACTION);
        switch (action) {
            case Constant.ACTION_SEND_AUDIO:
                AudioModel audioModel =
                        intent.getExtras().getParcelable(Constant.EXTRA_AUDIO);
                if (audioModel != null) {
                    sAudio = audioModel;
                    onPlayAudio(sAudio);
                    onPushNotification(sAudio);

                }
                onSendBroadcastReceiver(sAudio);
                break;
            case Constant.ACTION_GET_AUDIO:
                onSendBroadcastReceiver(sAudio);
                break;
            case Constant.ACTION_PLAY:
                onPlayContinueAudio();
                break;
            case Constant.ACTION_PAUSE:
                onPauseAudio();
                break;
            case Constant.ACTION_CANCEL:
                onDestroy();
                break;
        }

        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) mMediaPlayer.release();
    }

    private void onPushNotification(AudioModel audioModel) {


        Intent notificationIntent = new Intent(this, AudioListActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, 0);

        onCreateNotificationChannel();

        RemoteViews notificationLayout
                = new RemoteViews(getPackageName(), R.layout.notification_audio_player);
        notificationLayout.setTextViewText(R.id.text_title, audioModel.getTitle());
        notificationLayout.setTextViewText(R.id.text_artist, audioModel.getArtist());
        notificationLayout.setImageViewResource(R.id.image_control, R.drawable.ic_music_note_24dp);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(getText(R.string.notification_title))
                .setContentText(getText(R.string.notification_message))
                .setSmallIcon(R.drawable.ic_music_note_24dp)
                .setCustomContentView(notificationLayout)
                .setContentIntent(pendingIntent)
                .setTicker(getText(R.string.ticker_text))
                .setPriority(Notification.DEFAULT_ALL)
                .build();


        startForeground(3, notification);
    }

    private void onPlayAudio(AudioModel audioModel) {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.release();
        }
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setLooping(true);
        try {
            mMediaPlayer.setDataSource(audioModel.getData());
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onPauseAudio() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            sCurrentPosition = mMediaPlayer.getCurrentPosition();
        }
    }

    private void onPlayContinueAudio() {
        if (mMediaPlayer != null) {
            mMediaPlayer.start();
            mMediaPlayer.seekTo(sCurrentPosition);
        }
    }

    private void onCreateNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.setDescription(description);
            notificationChannel.setVibrationPattern(new long[]{ 0 });
            notificationChannel.enableVibration(true);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

        }

    }

    private void onSendBroadcastReceiver(AudioModel audioModel) {
        Intent intent = new Intent();
        intent.putExtra(Constant.EXTRA_AUDIO, audioModel);
        intent.setAction(Constant.ACTION_SEND_AUDIO);
        sendBroadcast(intent);
    }


}
