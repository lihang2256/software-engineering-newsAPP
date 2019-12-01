package com.example.newsAPP.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aspsine.irecyclerview.IViewHolder;
import com.example.newsAPP.R;
import com.example.newsAPP.bean.CommentBean;

import org.w3c.dom.Comment;

import java.util.ArrayList;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder> {
    private final String TAG = CommentListAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<CommentBean> mCommentBeans;
    private CommentListAdapter.OnItemClickListener mOnItemClickListener;

    public CommentListAdapter(Context context, ArrayList<CommentBean> commentBeans){
        mContext = context;
        mCommentBeans = commentBeans;
    }

    @Override
    public CommentListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = View.inflate(mContext,R.layout.item_comment_list,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentListAdapter.ViewHolder holder, int position) {
        CommentBean commentBean = mCommentBeans.get(position);
        //add something
    }

    @Override
    public int getItemCount() {
        return mCommentBeans.size();
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

    public void setOnItemClickListener(CommentListAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View v,int position);
    }
}
