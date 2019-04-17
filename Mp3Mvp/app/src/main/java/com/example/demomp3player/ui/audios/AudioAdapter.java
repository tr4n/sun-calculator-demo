package com.example.demomp3player.ui.audios;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demomp3player.R;
import com.example.demomp3player.data.model.AudioModel;
import com.example.demomp3player.ui.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;



public class AudioAdapter extends BaseRecyclerViewAdapter<AudioAdapter.AudioViewHolder> {

    private final List<AudioModel> mAudioModels = new ArrayList<>();
    private final ItemClickListener mItemClickListener;

    AudioAdapter(Context mContext) {
        super(mContext);
        mItemClickListener = (ItemClickListener) mContext;
    }


    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View convertView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_audio, viewGroup, false);
        return new AudioViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder audioViewHolder, int i) {
        audioViewHolder.onBindData(mAudioModels.get(i), mItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mAudioModels.size();
    }


    void updateData(List<AudioModel> audioModels) {
        mAudioModels.clear();
        mAudioModels.addAll(audioModels);
        notifyDataSetChanged();
    }


    static class AudioViewHolder extends RecyclerView.ViewHolder  {

        private final TextView mTitle;
        private final TextView mArtist;

        AudioViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.text_title);
            mArtist = itemView.findViewById(R.id.text_artist);

        }

        void onBindData(final AudioModel audioModel, final ItemClickListener mItemClickListener) {
            mTitle.setText(audioModel.getTitle());
            mArtist.setText(audioModel.getArtist());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Log.d(TAG, "onClick: " + audioModel);
                    mItemClickListener.onItemClicked(audioModel);
                }
            });
        }


    }

    public interface ItemClickListener {
        void onItemClicked(AudioModel audioModel);
    }
}
