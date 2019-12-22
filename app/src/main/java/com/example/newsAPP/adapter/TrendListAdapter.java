package com.example.newsAPP.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aspsine.irecyclerview.IViewHolder;
import com.example.newsAPP.R;
import com.example.newsAPP.bean.CommentBean;
import com.example.newsAPP.bean.TrendBean;

import java.util.ArrayList;

public class TrendListAdapter extends RecyclerView.Adapter<TrendListAdapter.ViewHolder> {
    private final String TAG = TrendListAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<TrendBean.DataBean> beans;
    private TrendListAdapter.OnItemClickListener mOnItemClickListener;

    public TrendListAdapter(Context context, ArrayList<TrendBean.DataBean> commentBeans){
        mContext = context;
        beans = commentBeans;
    }

    @Override
    public TrendListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = View.inflate(mContext,R.layout.item_news_comment_list,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrendListAdapter.ViewHolder holder, int position) {
        TrendBean.DataBean bean = beans.get(position);
        holder.item_comment_public_author.setText(bean.getNick_name());
        holder.item_comment_publish_time.setText(bean.getRelease_time());
        holder.comment_show_content.setText(bean.getContent());
        holder.comment_news_about.setText(bean.getTitle());
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    class ViewHolder extends IViewHolder{
        public TextView item_comment_public_author;
        public TextView item_comment_publish_time;
        public TextView comment_show_content;
        public TextView comment_news_about;


        public ViewHolder(View itemView) {
            super(itemView);
            item_comment_public_author = (TextView) itemView.findViewById(R.id.item_comment_public_author);
            item_comment_publish_time = (TextView) itemView.findViewById(R.id.item_comment_publish_time);
            comment_show_content = (TextView) itemView.findViewById(R.id.comment_show_content);
            comment_news_about = (TextView) itemView.findViewById(R.id.comment_news_about);
        }

    }

    public void setOnItemClickListener(TrendListAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View v,int position);
    }
}
