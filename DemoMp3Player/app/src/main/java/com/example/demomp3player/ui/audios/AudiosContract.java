package com.example.demomp3player.ui.audios;

import com.example.demomp3player.BasePresenter;
import com.example.demomp3player.BaseView;
import com.example.demomp3player.data.model.AudioModel;

import java.util.List;

interface AudiosContract {
    interface View extends BaseView<Presenter> {
        void showExternalAudios(List<AudioModel> audioModelList);

        void showCurrentAudioTab(AudioModel audioModel);

        void hideCurrentAudioTab();

        void updateCurrentAudioStatus(int status);

    }

    interface Presenter extends BasePresenter {
        boolean isPermissionGranted(String[] permissions);

        void loadExternalAudios();

        void onBindService();

        void updateCurrentAudioTab();

        void onStartAudio(AudioModel audioModel);

        void onControlPlayer();

        void onStopAudio();

        void onUnbindService();

        void onDestroyService();

    }
}
