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

/**
 * 新闻评论列表适配器
 */
public class NTListAdapter extends BaseAdapter {

    private ArrayList<CommentBean.DataBean> commentBeans;
    private LayoutInflater inflater;
    public NTListAdapter(Context context, ArrayList<CommentBean.DataBean> list){
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
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_news_comment_list,null);
            viewHolder.author = convertView.findViewById(R.id.news_trend_author);
            viewHolder.content = convertView.findViewById(R.id.news_trend_content);
            viewHolder.time = convertView.findViewById(R.id.news_trend_time);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.author.setText(commentBeans.get(position).getNick_name());
        viewHolder.content.setText(commentBeans.get(position).getContent());
        viewHolder.time.setText(commentBeans.get(position).getRelease_time());
        return convertView;
    }
    private class ViewHolder {
        TextView author;
        public TextView content;
        TextView time;
    }
}
