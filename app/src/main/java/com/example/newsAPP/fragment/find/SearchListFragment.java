package com.example.newsAPP.fragment.find;

import android.app.Dialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.newsAPP.R;
import com.example.newsAPP.Utils.HttpUtils;
import com.example.newsAPP.Utils.SharedPreferenceUtils;
import com.example.newsAPP.bean.FollowBean;
import com.example.newsAPP.bean.TypeBean;
import com.example.newsAPP.common.DefineView;
import com.example.newsAPP.fragment.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SearchListFragment extends BaseFragment implements DefineView  {

    private String tname;
    private String userID;
    private View mView;
    private final String TAG = SearchListFragment.class.getSimpleName();
    private static final String FINDTNAME = "FINDTNAME";
    private static final String KEY = "TNAME";
    private Button first;
    private Button second;
    private Button search;
    private EditText editText;
    private TimePickerView pvTime;
    private OptionsPickerView pv;
    private ArrayList<TypeBean> options1Items = new ArrayList<>();
    private List<FollowBean.DataBean> followbeans = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_find_list, container, false);
        first = (Button)mView.findViewById(R.id.btn_first);
        second = (Button)mView.findViewById(R.id.btn_second);
        search = (Button)mView.findViewById(R.id.btn_search);
        editText = (EditText) mView.findViewById(R.id.input_key_word);
        userID = SharedPreferenceUtils.getInstance().getString(getActivity(),"USERID",null);
        SharedPreferenceUtils.getInstance().setString(getActivity(),"SEARCHTYPE",null);
        SharedPreferenceUtils.getInstance().setString(getActivity(),"NEWSINPUT","");
        SharedPreferenceUtils.getInstance().setString(getActivity(),"SEARCHTIME",null);
        SharedPreferenceUtils.getInstance().setString(getActivity(),"SEARCHFRIEND",null);
        SharedPreferenceUtils.getInstance().setString(getActivity(),"TRENDINPUT","");

        if (getArguments() != null) {
            tname = getArguments().getString("FINDTNAME");
        }
        //做出判断：
        // 如果是新闻搜索的tab，需要初始化时间选择器和类型选择器，
        // 如果是动态搜索的tab,需要初始化时间选择器和好友选择器
        if (tname == "新闻搜索") {
            getTypeOptionData();
            initView();
            initValidata();
            initListener();
            initTimePicker();
            initTypePicker();
        }
        else {
            getFocusOptionData();
            initView();
            initValidata();
            initListener();
            initTimePicker();
            initFocusPicker();
        }
        return mView;
    }

    @Override
    public void initView() {
        if (tname == "新闻搜索") {
            first.setText("时间");
            second.setText("类型");

        }
        else {
            first.setText("时间");
            second.setText("关注");
        }
    }

    @Override
    public void initValidata() {

    }

    @Override
    public void initListener() {
        if (tname == "新闻搜索") {
            first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pvTime.show(v);
                }
            });
            second.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pv.show(v);
                }
            });
            search.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    //把文本框的输入值 传值给 SearchNewsFragment
                    SharedPreferenceUtils.getInstance().setString(getActivity(),"NEWSINPUT",editText.getText().toString());
                    //如果tname是新闻搜索，那么就调SearchNewsFragment这个fragment
                    Fragment news = new SearchNewsFragment();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.search_fragment, news,"").commit();

                }
            });
        }
        else {
            first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pvTime.show(v);
                }
            });

            second.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pv.show(v);
                }
            });
            search.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    //把文本框的输入值 传值给 SearchTrendFragment
                    SharedPreferenceUtils.getInstance().setString(getActivity(),"TRENDINPUT",editText.getText().toString());

                    //如果tname是动态搜索，那么就调SearchTrendFragment这个fragment
                    Fragment trend = new SearchTrendFragment();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.search_fragment, trend,"").commit();


                }
            });
        }
    }

    @Override
    public void bindData() {

    }

    public static SearchListFragment newInstance(String tname){
        Bundle bundle = new Bundle();
        bundle.putSerializable(FINDTNAME, tname);
        SearchListFragment fragment = new SearchListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * 初始化 时间选择器
     */
    private void initTimePicker() {
        //Dialog 模式下，在底部弹出
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();


        //设置 起止时间
        startDate.set(2018,0,1);
        endDate.set(2020,11,31);

        pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
               //   Toast.makeText(getActivity() ,getTime(date), Toast.LENGTH_SHORT).show();
                Log.i("pvTime", "onTimeSelect");
                first.setText(getTime(date));
                //把时间选择器的选择的值 传值给 Fragment
                SharedPreferenceUtils.getInstance().setString(getActivity(),"SEARCHTIME",getTime(date));
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setType(new boolean[]{true, true, true, true, true, true})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .addOnCancelClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("pvTime", "onCancelClickListener");
                    }
                })
                .setTitleText("时间选择")
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示,年月日时分秒
                .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate,endDate)//起始终止年月日设定
                .setItemVisibleCount(5) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
                .setLineSpacingMultiplier(2.0f)
                .isAlphaGradient(true)
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.3f);
            }
        }
    }


    /**
     *  时间选择器选择的时间  显示
     */
    private String getTime(Date date) {
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private void getTypeOptionData() {

         // 注意：如果是添加JavaBean实体数据，则实体类需要实现 IPickerViewData 接口，
         // PickerView会通过getPickerViewText方法获取字符串显示出来。


        //选项
        options1Items.add(new TypeBean(0, "头条", "描述部分", "其他数据"));
        options1Items.add(new TypeBean(1, "社会", "描述部分", "其他数据"));
        options1Items.add(new TypeBean(2, "国内", "描述部分", "其他数据"));
        options1Items.add(new TypeBean(3, "国际", "描述部分", "其他数据"));
        options1Items.add(new TypeBean(4, "娱乐", "描述部分", "其他数据"));
        options1Items.add(new TypeBean(5, "体育", "描述部分", "其他数据"));
        options1Items.add(new TypeBean(6, "军事", "描述部分", "其他数据"));
        options1Items.add(new TypeBean(7, "科技", "描述部分", "其他数据"));
        options1Items.add(new TypeBean(8, "财经", "描述部分", "其他数据"));
        options1Items.add(new TypeBean(9, "时尚", "描述部分", "其他数据"));

        /*--------数据源添加完毕---------*/
    }

    /**
     * 初始化 选择器 ，此选择器是类型选择器
     */
    private void initTypePicker(){
        pv = new OptionsPickerBuilder(getActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()
                        // + options2Items.get(options1).get(options2)
                        /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/;
                second.setText(tx);
                //把类型选择器选择的值 传值给 SearchNewsFragment
                 SharedPreferenceUtils.getInstance().setString(getActivity(),"SEARCHTYPE",tx);
            }
        })
                .setTitleText("类型选择")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.LTGRAY)
                .setTitleColor(Color.BLACK)
                .setCancelColor(Color.BLUE)
                .setSubmitColor(Color.BLUE)
                .setTextColorCenter(Color.BLACK)
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("", "", "")
                .setOutSideColor(0x00000000) //设置外部遮罩颜色
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                        String str = "类型： " + options1 ;
                        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

               //  pvOptions.setSelectOptions(1,1);
                  pv.setPicker(options1Items);//一级选择器
        //  pvOptions.setPicker(options1Items, options2Items);//二级选择器
        //pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器
    }

    /**
     * 初始化选择器，此选择器是好友选择器
     */
    private void initFocusPicker(){  //选择器  选择关注的人
        pv = new OptionsPickerBuilder(getActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = followbeans.get(options1).getPickerViewText()
                        // + options2Items.get(options1).get(options2)
                        /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/ ;
                second.setText(tx);

                //把好友选择器选择的值 传值给 SearchTrendFragment
                String friend = SharedPreferenceUtils.getInstance().getString(getActivity(),"SEARCHFRIEND",tx);
            }
        })
                .setTitleText("好友选择")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.LTGRAY)
                .setTitleColor(Color.BLACK)
                .setCancelColor(Color.BLUE)
                .setSubmitColor(Color.BLUE)
                .setTextColorCenter(Color.BLACK)
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("", "", "")
                .setOutSideColor(0x00000000) //设置外部遮罩颜色
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                        String str = "好友： " + options1 ;
                        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

        //        pvOptions.setSelectOptions(1,1);
        pv.setPicker(followbeans);//一级选择器*/
        //  pvOptions.setPicker(options1Items, options2Items);//二级选择器
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/
    }

    private void getFocusOptionData() {
        /*
         * 注意：如果是添加JavaBean实体数据，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */

         new SearchFollowAsyncTask().execute();
    }
    /**
     * 好友 接口
     * 异步方法，获取并渲染
     */
    class SearchFollowAsyncTask extends AsyncTask<String,Integer,ArrayList<FollowBean.DataBean>> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected ArrayList<FollowBean.DataBean> doInBackground(String... strings) {
            ArrayList<FollowBean.DataBean> list = new HttpUtils().getFollow(userID);

            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<FollowBean.DataBean> list) {
            super.onPostExecute(list);
            followbeans = list;
            bindData();
        }
    }
}
