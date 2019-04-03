package com.example.sundemocaculator.ui.main;

import android.content.Context;

interface MainContract {

    interface View {
        // Response from Presenter
        void showResult(String result);
        Context getContext();
        void onScrollToEnd();
        void onPrepareClearScreen(boolean state);
    }

    /**
     * Presenter.
     */
    interface Presenter {
        void calculateExpression(String input);
        void onPressDel(String input);
        void onPressC();
        void onPressNumber(String input, int number, boolean reset);
        void onPressSymbol(String input, int index);

    }
}
