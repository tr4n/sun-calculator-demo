package com.example.demomp3player.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.example.demomp3player.R;
import com.example.demomp3player.data.model.AudioModel;
import com.example.demomp3player.ui.screen.audio.AudioListActivity;
import com.example.demomp3player.util.Constant;

import java.io.IOException;
import java.util.Objects;

public class PlayAudioService extends Service  {


    private static final String CHANNEL_ID = "com.example.demomp3player";
    private MediaPlayer mMediaPlayer;

    public PlayAudioService() {

    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        AudioModel mAudioModel =
                Objects.requireNonNull(intent.getExtras()).getParcelable(Constant.AUDIO_MESSAGE);
               playForeground(mAudioModel);
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) mMediaPlayer.release();
    }
    private void playForeground(AudioModel audioModel) {

        playAudio(audioModel);
        createNotificationChannel();
        // Get the layouts to use in the custom notification
        RemoteViews notificationLayout
                = new RemoteViews(getPackageName(), R.layout.notification_audio_player);

        notificationLayout.setTextViewText(R.id.text_title, audioModel.getTitle());
        notificationLayout.setTextViewText(R.id.text_artist, audioModel.getArtist());
        notificationLayout.setImageViewResource(R.id.image_control, R.drawable.ic_music_note_24dp);

        Intent intent = new Intent(this, AudioListActivity.class);

        intent.putExtra(Constant.AUDIO_MESSAGE, audioModel);



        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_music_note_24dp)
                .setCustomContentView(notificationLayout)

                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager =
               (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = builder.build();
        notificationManager.notify(0, notification);

        startForeground(0, notification);
    }

    private void playAudio(AudioModel audioModel) {
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


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

        }

    }




}
