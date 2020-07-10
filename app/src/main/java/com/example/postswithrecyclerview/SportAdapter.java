package com.example.postswithrecyclerview;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SportAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "SportAdapter";
    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private Callback mCallback;
    private List<Sport> mSportList;

    public SportAdapter(List<Sport> initialSportList){
        this.mSportList = initialSportList;
    }

    public void setCallback(Callback callback){
        this.mCallback = callback;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        switch (viewType){
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        if(this.mSportList!=null && this.mSportList.size() > 0){
            return VIEW_TYPE_NORMAL;
        }
        else{
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if(this.mSportList != null && this.mSportList.size() > 0){
            return this.mSportList.size();
        }
        else{
            return 0;
        }

    }
    public void addItems(List<Sport> sportList) {
        mSportList.addAll(sportList);
        notifyDataSetChanged();
    }

    public interface Callback{
        void onEmptyViewRetryClick();
    }
    public class ViewHolder extends BaseViewHolder{
        @BindView(R.id.thumbnail)
        ImageView coverImageView;
        @BindView(R.id.newsTitle)
        TextView newsTextView;
        @BindView(R.id.title)
        TextView titleTextView;
        @BindView(R.id.newsInfo)
        TextView infoTextView;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            coverImageView.setImageDrawable(null);
            titleTextView.setText("");
            newsTextView.setText("");
            infoTextView.setText("");
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);

            final Sport mSport = mSportList.get(position);

            if(mSport.getImageUrl() != null){
                Glide.with(itemView.getContext())
                        .load(mSport.getImageUrl())
                        .into(coverImageView);
            }

            if(mSport.getTitle() != null){
                titleTextView.setText(mSport.getTitle());
            }

            if(mSport.getInfo() != null){
                infoTextView.setText(mSport.getInfo());
            }

            if(mSport.getSubtitle() != null){
                newsTextView.setText(mSport.getSubtitle());
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSport.getImageUrl() != null) {
                        try {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.addCategory(Intent.CATEGORY_BROWSABLE);
                            intent.setData(Uri.parse(mSport.getImageUrl()));
                            itemView.getContext().startActivity(intent);
                        } catch (Exception e) {
                            Log.e(TAG, "onClick: Image url is not correct");
                        }
                    }
                }
            });



            }
        }

    public class EmptyViewHolder extends BaseViewHolder{

        @BindView(R.id.tv_message)
        TextView messageTextView;
        @BindView(R.id.buttonRetry)
        TextView retryBtn;
        public EmptyViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            retryBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.onEmptyViewRetryClick();
                }
            });
        }
        @Override
        protected void clear() {

        }
    }


}
