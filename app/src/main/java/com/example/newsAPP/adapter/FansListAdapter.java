package com.example.newsAPP.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.newsAPP.R;
import com.example.newsAPP.bean.FansBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FansListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<FansBean.DataBean> mContentsList ;
    private Context mContext;
    private FansListAdapter.ContentsDeleteListener mContentsDeleteListener;
    //设置滑动删除按钮是否显示
    private Map<Integer, Integer> visibleDeleteTv;
    //CheckBox选择和未选择
    private Map<Integer, Boolean> selectCb;
    //滑动后的X坐标点
    private int mLastX = 0;
//	private int mLastY = 0;

    public FansListAdapter(Context mContext,List<FansBean.DataBean> mContentsList, FansListAdapter.ContentsDeleteListener mContentsDeleteListener) {
        this.mContext = mContext;
        this.mContentsList = mContentsList;
        this.mContentsDeleteListener = mContentsDeleteListener;
        this.mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        visibleDeleteTv = new HashMap<Integer, Integer>();
        selectCb = new HashMap<Integer, Boolean>();

        if (mContentsList!=null) {
            // 更新界面时,记录为 未选中和滑动删除按钮不可见
            for (int i = 0; i < mContentsList.size(); i++) {
                visibleDeleteTv.put(i, View.GONE);
                selectCb.put(i, false);
            }
        }
    }

    public void updateView(List<FansBean.DataBean> mContentsList) {
        this.mContentsList = mContentsList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(mContentsList!=null) {
            return mContentsList.size();
        }
        return -1;
    }

    @Override
    public Object getItem(int position) {
        return mContentsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final FansListAdapter.HolderView holderView;
        if (convertView == null) {
            holderView = new FansListAdapter.HolderView();
            convertView = mInflater.inflate(R.layout.activity_fans_list_view,
                    null);
            holderView.listSelectCb = (CheckBox) convertView
                    .findViewById(R.id.my_select_cb2);
            holderView.listContentTv = (TextView) convertView
                    .findViewById(R.id.my_content_tv2);
            holderView.listDeleteTv = (TextView) convertView
                    .findViewById(R.id.my_delete_tv2);
            holderView.listRl = (RelativeLayout) convertView
                    .findViewById(R.id.my_rl2);

            convertView.setTag(holderView);
        } else {
            holderView = (FansListAdapter.HolderView) convertView.getTag();
            if (holderView.listSelectCb.isChecked()) {
                holderView.listSelectCb.setChecked(false);
            }

        }
        // 显示内容
        holderView.listContentTv.setText(mContentsList.get(position).getNick_name());

        if (visibleDeleteTv != null) {
            holderView.listDeleteTv
                    .setVisibility(visibleDeleteTv.get(position));
        }
        if (selectCb != null) {
            holderView.listSelectCb.setChecked(selectCb.get(position));
            mContentsDeleteListener.contentsDeleteSelect(position,
                    selectCb.get(position));
        }

        // 处理选择事件
        holderView.listRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visibleDeleteTv.containsValue(View.VISIBLE)) {//可见时，再次单击设置不可见，未选中
                    for (int i = 0; i < getCount(); i++) {
                        visibleDeleteTv.put(i, View.GONE);
                        selectCb.put(i, false);
                        mContentsDeleteListener.contentsDeleteSelect(i, false);
                    }
                    notifyDataSetChanged();
                } else {
                    boolean isChecked = holderView.listSelectCb.isChecked() ? false : true;
                    holderView.listSelectCb.setChecked(isChecked);

                    mContentsDeleteListener.contentsDeleteSelect(position, isChecked);
                }
            }
        });

        holderView.listSelectCb
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        mContentsDeleteListener.contentsDeleteSelect(position,
                                isChecked);
                    }
                });

        holderView.listSelectCb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (visibleDeleteTv.containsValue(View.VISIBLE)) {
                    for (int i = 0; i < getCount(); i++) {
                        visibleDeleteTv.put(i, View.GONE);
                        selectCb.put(i, false);
                        mContentsDeleteListener.contentsDeleteSelect(i,
                                false);
                    }
                    notifyDataSetChanged();
                }
            }
        });

        convertView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final Animation alpha = AnimationUtils.loadAnimation(mContext,
                        R.anim.rotate_left);
                int x = (int) event.getX();
//				int y = (int) event.getY();
                // Log.d(TAG, "x=" + x + "  y=" + y);
                // press down
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    alpha.cancel();
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    alpha.cancel();
                    int deltaX = mLastX - x;
//					int deltaY = mLastY - y;
                    // Log.d(TAG, "deltaX=" + deltaX + ",deltaY=" + deltaY);
                    if (deltaX > 40) {//当滑动距离大于40时，显示该位置的删除按键
                        for (int i = 0; i < getCount(); i++) {
                            visibleDeleteTv.put(i, View.GONE);
                            selectCb.put(i, false);
                            mContentsDeleteListener.contentsDeleteSelect(i, false);
                            if (i == position) {
                                visibleDeleteTv.put(position, View.VISIBLE);
                                selectCb.put(i, true);
                                mContentsDeleteListener.contentsDeleteSelect(i, true);
                                if (visibleDeleteTv.get(position) == View.VISIBLE) {
                                    holderView.listDeleteTv
                                            .startAnimation(alpha);
                                }
                            }
                        }
                        notifyDataSetChanged();
                    }
                    return true;
                } else {// other
                    alpha.cancel();

                }
                mLastX = x;
//				mLastY = y;
                return false;
            }
        });
        holderView.listDeleteTv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Log.d(TAG, "onClick:position=" + position);
                mContentsList.remove(position);
                mContentsDeleteListener.contentDelete(position);
                for (int i = 0; i < mContentsList.size(); i++) {
                    visibleDeleteTv.put(i, View.GONE);
                    selectCb.put(i, false);
                    mContentsDeleteListener.contentsDeleteSelect(i, false);
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    public class HolderView {
        public TextView listContentTv, listDeleteTv;
        public CheckBox listSelectCb;
        public RelativeLayout listRl;
    }

    public void setContentsDeleteListener(
            FansListAdapter.ContentsDeleteListener mContentsDeleteListener) {
        this.mContentsDeleteListener = mContentsDeleteListener;
    }

    /**
     * 用于删除内容的接口
     *
     *
     *
     */
    public interface ContentsDeleteListener {
        /**
         * 根据isChecked 选择和取消选择的指定位置
         * @param position
         * @param isChecked
         */
        public void contentsDeleteSelect(int position, boolean isChecked);
        /**
         * 删除指定位置内容
         * @param position
         */
        public void contentDelete(int position);
    }

    public void setVisibleDeleteTv(Map<Integer, Integer> visibleDeleteTv) {
        this.visibleDeleteTv = visibleDeleteTv;
    }

    public void setSelectCb(Map<Integer, Boolean> selectCb) {

        this.selectCb = selectCb;
    }
}
