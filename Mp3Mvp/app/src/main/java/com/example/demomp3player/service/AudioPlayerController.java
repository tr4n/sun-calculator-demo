package com.example.demomp3player.service;

import com.example.demomp3player.data.model.AudioModel;

public interface AudioPlayerController {

    void create();

    void start();

    void pause();

    void stop();

    void play();

    AudioModel getCurrent();

    int getStatus();
}