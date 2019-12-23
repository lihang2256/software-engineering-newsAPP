package com.example.newsAPP.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.newsAPP.R;
import com.example.newsAPP.bean.CommentBean;
import com.example.newsAPP.bean.TrendCommentBean;

import java.util.ArrayList;

public class TCListAdapter extends BaseAdapter{

    private ArrayList<TrendCommentBean.CommentListBean> commentBeans;
    private LayoutInflater inflater;
    public TCListAdapter(Context context, ArrayList<TrendCommentBean.CommentListBean> list){
        commentBeans = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        TCListAdapter.ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new TCListAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.item_trend_comment_list,null);
            viewHolder.author = convertView.findViewById(R.id.trend_comment_author);
            viewHolder.content = convertView.findViewById(R.id.trend_comment_content);
            viewHolder.time = convertView.findViewById(R.id.trend_comment_time);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (TCListAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.author.setText(commentBeans.get(position).getNick_name());
        viewHolder.time.setText(commentBeans.get(position).getRelease_time());
        viewHolder.content.setText(commentBeans.get(position).getContent());
        return convertView;
    }
    private class ViewHolder {
        TextView author;
        public TextView content;
        TextView time;
    }
}
