package com.example.demomp3player.ui.screen.audio;

import com.example.demomp3player.BasePresenter;
import com.example.demomp3player.BaseView;
import com.example.demomp3player.data.model.AudioModel;

import java.util.List;

interface AudioContract {
    interface View extends BaseView<Presenter> {
        void onDisplayPlayingAudio(AudioModel audioModel);
        void onShowAudios(List<AudioModel> audioModelList);


    }

    interface Presenter extends BasePresenter{
        boolean onSelfCheckPermissions(String[] permissions);
        boolean onGetPlayingAudio();

    }
}
