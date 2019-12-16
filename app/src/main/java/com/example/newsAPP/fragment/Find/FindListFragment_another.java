package com.example.newsAPP.fragment.Find;

import android.app.Dialog;
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
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.newsAPP.R;
import com.example.newsAPP.Utils.DensityUtils;
import com.example.newsAPP.adapter.FindListAdapter;
import com.example.newsAPP.bean.CardBean;
import com.example.newsAPP.bean.FindBean;
import com.example.newsAPP.fragment.BaseFragment;
import com.example.newsAPP.widget.ClassicRefreshHeaderView;
import com.example.newsAPP.widget.DividerGridItemDecoration;
import com.example.newsAPP.widget.LoadMoreFooterView;
import com.example.newsAPP.widget.LoadingPage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FindListFragment_another extends BaseFragment {

    private String tid; // 动态频道id
    private View mView;
    private final String TAG = FindListFragment_another.class.getSimpleName();
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

    Button btn_Time_another;
    Button btn_Friend;
    Button btn_Find_another;
    EditText input_key_word_another;
    TimePickerView pvTime_another;
    private ArrayList<CardBean> cardItem = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_find_list_another, container, false);
        initView();
        initValidata();
        initListener();
        initTimePicker();

        btn_Time_another = (Button)mView.findViewById(R.id.btn_Time_another);



        btn_Time_another.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // if (v.getId() == R.id.btn_Time && pvTime != null) {
                // pvTime.setDate(Calendar.getInstance());
                // pvTime.show(); //show timePicker
                pvTime_another.show(v);//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
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

    public static FindListFragment_another newInstance(String tid, String column){
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_TID, tid);
        FindListFragment_another fragment = new FindListFragment_another();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void requestData(){

    }



    private void initTimePicker() {//Dialog 模式下，在底部弹出
        pvTime_another = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
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
                .setItemVisibleCount(5) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
                .setLineSpacingMultiplier(2.0f)
                .isAlphaGradient(true)
                .build();

        Dialog mDialog = pvTime_another.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime_another.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.3f);
            }
        }
    }
    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

}
