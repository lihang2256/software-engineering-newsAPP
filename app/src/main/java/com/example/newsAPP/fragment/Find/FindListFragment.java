package com.example.newsAPP.fragment.Find;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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
import com.example.newsAPP.Utils.DensityUtils;
import com.example.newsAPP.adapter.FindListAdapter;
import com.example.newsAPP.bean.FindBean;
import com.example.newsAPP.bean.TypeBean;
import com.example.newsAPP.fragment.BaseFragment;
import com.example.newsAPP.widget.ClassicRefreshHeaderView;
import com.example.newsAPP.widget.DividerGridItemDecoration;
import com.example.newsAPP.widget.LoadMoreFooterView;
import com.example.newsAPP.widget.LoadingPage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Xv on 2019/12/14
 */
public class FindListFragment extends BaseFragment {

    private String tid; // 动态频道id
    private View mView;
    private final String TAG = FindListFragment.class.getSimpleName();
    private static final String KEY_TID = "TID";  //频道id
    private IRecyclerView mIRecyclerView;
    private LoadMoreFooterView mLoadMoreFooterView;
    private FindListAdapter mFindListAdapter;
    private LoadingPage mLoadingPage;

    private List<FindBean> mFindBean;   // 启动时获得的数据
    private List<FindBean> findlist;   // 上拉刷新后获得的数据


    private int mStartIndex = 0;    // 请求数据的起始参数
    private boolean isPullRefresh;  // 判断当前是下拉刷新还是上拉刷新
    private boolean isShowCache = false; // 是否有缓存数据被展示
    private boolean isConnectState = false;  // 判断当前是否在联网刷新, false表示当前没有联网刷新

    Button btn_Time;
    Button btn_Kind;
    Button btn_Find;
    EditText input_key_word;
    TimePickerView pvTime;
    OptionsPickerView pvKind;
    private ArrayList<TypeBean> options1Items = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_find_list, container, false);

        //等数据加载完毕再初始化并显示Picker,以免还未加载完数据就显示,造成APP崩溃。
        getOptionData();

        initView();
        initValidata();
        initListener();
        initTimePicker();
        initKindPicker();


       btn_Time = (Button)mView.findViewById(R.id.btn_Time);
       btn_Kind = (Button)mView.findViewById(R.id.btn_Kind);
       btn_Find = (Button)mView.findViewById(R.id.btn_Find);
       input_key_word = (EditText) mView.findViewById(R.id.input_key_word);



        btn_Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // if (v.getId() == R.id.btn_Time && pvTime != null) {
                    // pvTime.setDate(Calendar.getInstance());
                    // pvTime.show(); //show timePicker
                   pvTime.show(v);//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
             //   }
            }
        });
        btn_Kind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (v.getId() == R.id.btn_Kind && pvKind != null) {
                // pvKind.show(); //show Picker
                pvKind.show(v);//弹出选择器，传递参数过去，回调的时候则可以绑定此view
                //   }
            }
        });

        return mView;
    }
    @Override
    public void initView() {
        mLoadingPage = (LoadingPage) mView.findViewById(R.id.loading_page);
        mIRecyclerView = (IRecyclerView) mView.findViewById(R.id.iRecyclerView);

        mIRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mIRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        mLoadMoreFooterView = (LoadMoreFooterView) mIRecyclerView.getLoadMoreFooterView();
        ClassicRefreshHeaderView classicRefreshHeaderView = new ClassicRefreshHeaderView(getActivity());
        classicRefreshHeaderView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtils.dip2px(getActivity(), 80)));
        // we can set view
        mIRecyclerView.setRefreshHeaderView(classicRefreshHeaderView);
        //showLoadingPage();
    }

    @Override
    public void initValidata() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void bindData() {

    }

    public static FindListFragment newInstance(String tid, String column){
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_TID, tid);
        FindListFragment fragment = new FindListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void requestData(){

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

    private void initKindPicker(){  //选择器  选择搜索的类型
        pvKind = new OptionsPickerBuilder(getActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()
                       // + options2Items.get(options1).get(options2)
                        /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/;
                btn_Kind.setText(tx);
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
        pvKind.setPicker(options1Items);//一级选择器*/
        //  pvOptions.setPicker(options1Items, options2Items);//二级选择器
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/
    }
    private void getOptionData() {
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



}
