package com.wyn.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.wyn.activity.DetailActivity;
import com.wyn.activity.R;

/**
 * Created by nancy on 15-8-27.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RelatedPhotosViewHolder>{

    private LayoutInflater mInflater;
    private Context mContext;

    public class RelatedPhotosViewHolder extends RecyclerView.ViewHolder{

        public RelatedPhotosViewHolder(View itemView) {
            super(itemView);
            circleImageView = (ImageView)itemView.findViewById(R.id.imageview);
            btn = (Button)itemView.findViewById(R.id.btn_subscribe);
        }

        public ImageView circleImageView;
        public Button btn;
    }
    public RecyclerViewAdapter(Context context){
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }
    @Override
    public RecyclerViewAdapter.RelatedPhotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_recyclerview,null);
        return new RelatedPhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RelatedPhotosViewHolder holder, int position) {
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(mContext, DetailActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }

}
