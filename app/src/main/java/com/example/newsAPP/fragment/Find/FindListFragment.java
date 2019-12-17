package com.example.newsAPP.fragment.Find;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.aspsine.irecyclerview.IRecyclerView;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.newsAPP.R;
import com.example.newsAPP.adapter.FindListAdapter;
import com.example.newsAPP.bean.FindBean;
import com.example.newsAPP.bean.FriendBean;
import com.example.newsAPP.bean.TypeBean;
import com.example.newsAPP.common.DefineView;
import com.example.newsAPP.fragment.BaseFragment;
import com.example.newsAPP.widget.LoadMoreFooterView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FindListFragment extends BaseFragment implements DefineView {

    private String tname;
    private View mView;
    private final String TAG = FindListFragment.class.getSimpleName();
    private static final String FINDTNAME = "FINDTNAME";

    private Button first;
    private Button second;
    private Button search;
    private EditText editText;
    private TimePickerView pvTime;
    private OptionsPickerView pv;
    private ArrayList<TypeBean> options1Items = new ArrayList<>();
    private ArrayList<FriendBean> beans = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_find_list, container, false);
        first = (Button)mView.findViewById(R.id.btn_first);
        second = (Button)mView.findViewById(R.id.btn_second);
        search = (Button)mView.findViewById(R.id.btn_search);
        editText = (EditText) mView.findViewById(R.id.input_key_word);

        if (getArguments() != null) {
            tname = getArguments().getString("FINDTNAME");
        }

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
        }
    }

    @Override
    public void bindData() {

    }

    public static FindListFragment newInstance(String tname){
        Bundle bundle = new Bundle();
        bundle.putSerializable(FINDTNAME, tname);
        FindListFragment fragment = new FindListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private void initTimePicker() {    //Dialog 模式下，在底部弹出
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();


        //正确设置方式 原因：注意事项有说明
        startDate.set(2018,0,1);
        endDate.set(2020,11,31);

        pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Toast.makeText(getActivity() ,getTime(date), Toast.LENGTH_SHORT).show();
                Log.i("pvTime", "onTimeSelect");

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

    private String getTime(Date date) {  //时间选择器选择的时间  显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
        return format.format(date);
    }

    private void getTypeOptionData() {
        /*
         * 注意：如果是添加JavaBean实体数据，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */

        //选项1
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

    private void initTypePicker(){  //选择器  选择搜索的类型
        pv = new OptionsPickerBuilder(getActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()
                        // + options2Items.get(options1).get(options2)
                        /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/;
                second.setText(tx);
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

        //        pvOptions.setSelectOptions(1,1);
        pv.setPicker(options1Items);//一级选择器*/
        //  pvOptions.setPicker(options1Items, options2Items);//二级选择器
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/
    }

    private void initFocusPicker(){  //选择器  选择搜索的类型
        pv = new OptionsPickerBuilder(getActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = beans.get(options1).getPickerViewText()
                        // + options2Items.get(options1).get(options2)
                        /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/;
                second.setText(tx);
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

        //        pvOptions.setSelectOptions(1,1);
        pv.setPicker(beans);//一级选择器*/
        //  pvOptions.setPicker(options1Items, options2Items);//二级选择器
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/
    }

    private void getFocusOptionData() {
        /*
         * 注意：如果是添加JavaBean实体数据，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */

        //选项1
        beans.add(new FriendBean(0, "韩愈", "描述部分", "其他数据"));
        beans.add(new FriendBean(1, "柳宗元", "描述部分", "其他数据"));
        beans.add(new FriendBean(2, "欧阳修", "描述部分", "其他数据"));
        beans.add(new FriendBean(3, "苏洵", "描述部分", "其他数据"));
        beans.add(new FriendBean(4, "苏轼", "描述部分", "其他数据"));
        beans.add(new FriendBean(5, "苏辙", "描述部分", "其他数据"));
        beans.add(new FriendBean(6, "王安石", "描述部分", "其他数据"));
        beans.add(new FriendBean(7, "曾巩", "描述部分", "其他数据"));

        /*--------数据源添加完毕---------*/
    }

}
