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

/**
 * 动态列表适配器
 */
public class TrendListAdapter extends RecyclerView.Adapter<TrendListAdapter.ViewHolder> {
    private final String TAG = TrendListAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<TrendBean.DataBean> beans;
    private TrendListAdapter.OnItemClickListener mOnItemClickListener;

    public TrendListAdapter(Context context, ArrayList<TrendBean.DataBean> trendBeans){
        mContext = context;
        beans = trendBeans;
    }

    @Override
    public TrendListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = View.inflate(mContext,R.layout.item_trend,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TrendListAdapter.ViewHolder holder, int position) {
        //if (position >= 0){
            TrendBean.DataBean bean = beans.get(position);
            String name = bean.getNick_name();
            String time = bean.getRelease_time();
            String content = bean.getContent();
            String title = bean.getTitle();
            holder.item_comment_public_author.setText(name);
            holder.item_comment_publish_time.setText(time);
            holder.comment_show_content.setText(content);
            //不是对于新闻评论的动态不显示相关新闻
            if (title.equals("null")) {
                holder.comment_news_about.setVisibility(View.GONE);
            }
            else {
                holder.comment_news_about.setText(title);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // IRecyclerView的Adapter会默认多出两个头部View，需要减去2个position
                    int pos = holder.getIAdapterPosition();
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(holder.itemView, pos);
                    }
                }
            });
        //}
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
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
