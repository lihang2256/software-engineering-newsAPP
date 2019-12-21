package com.example.newsAPP.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.newsAPP.R;
import com.example.newsAPP.bean.CommentBean;

import java.util.ArrayList;

public class TCListAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<CommentBean> commentBeans;
    private LayoutInflater inflater;
    public TCListAdapter(Context context, ArrayList<CommentBean> list){
        mContext = context;
        commentBeans = list;
    }

    @Override
    public int getCount() {
        return commentBeans.size();
    }

    @Override
    public Object getItem(int position) {
        if (commentBeans == null) {
            return null;
        }
        return commentBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TCListAdapter.ViewHolder viewHolder = new TCListAdapter.ViewHolder();
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_trend_comment_list,null);
            viewHolder.author = convertView.findViewById(R.id.trend_comment_author);
            viewHolder.content = convertView.findViewById(R.id.trend_comment_content);
            viewHolder.time = convertView.findViewById(R.id.news_trend_time);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (TCListAdapter.ViewHolder) convertView.getTag();
        }
//            viewHolder.author.setText(commentBeans.get(position).getAuthor());
//            viewHolder.content.setText(commentBeans.get(position).getContent());
        return convertView;
    }
    private class ViewHolder {
        public TextView author;
        public TextView content;
        public TextView time;
    }
}
