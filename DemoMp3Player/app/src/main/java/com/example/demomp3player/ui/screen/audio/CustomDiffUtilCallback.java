package com.example.demomp3player.ui.screen.audio;

import android.support.v7.util.DiffUtil;

import com.example.demomp3player.data.model.AudioModel;

import java.util.List;

class CustomDiffUtilCallback<T extends AudioModel> extends DiffUtil.Callback {
    private final List<T> mOldItems;
    private final List<T> mNewItems;

    public CustomDiffUtilCallback(List<T> oldItems, List<T> newItems) {
        this.mOldItems = oldItems;
        this.mNewItems = newItems;
    }

    @Override
    public int getOldListSize() {
        return mOldItems.size();
    }

    @Override
    public int getNewListSize() {
        return mNewItems.size();
    }

    @Override
    public boolean areItemsTheSame(int first, int second) {
        T oldItem = mOldItems.get(first),
                newItem = mNewItems.get(second);
     //   Log.d(TAG, "areItemsTheSame: " + oldItem.equals(newItem));
        return oldItem.getData().equals(newItem.getData());
    }

    @Override
    public boolean areContentsTheSame(int first, int second) {
        T oldItem = mOldItems.get(first),
                newItem = mNewItems.get(second);
        return oldItem.equals(newItem);
    }

}
