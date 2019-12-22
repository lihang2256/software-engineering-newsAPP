package com.example.newsAPP.bean;

import java.util.ArrayList;
import java.util.List;


import com.contrarywind.interfaces.IPickerViewData;

public class FollowBean implements IPickerViewData {

    /**
     * data : [{"nick_name":"cxiaoyu","ID":26},{"nick_name":"dxiaoyu","ID":27},{"nick_name":"exiaoyu","ID":28},{"nick_name":"fxiaoyu","ID":29}]
     * status : success
     */
    private String status;
    private List<DataBean> data;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


    // 实现 IPickerViewData 接口，
    // 这个用来显示在PickerView上面的字符串，
    // PickerView会通过IPickerViewData获取getPickerViewText方法显示出来。
    @Override
    public String getPickerViewText() {

        //   List<String> mapList1 = name.stream().map(FollowBean.DataBean::getNick_name).collect(Collectors.toList());  java8

        List<FollowBean.DataBean> list = new ArrayList<>();
        List<String> name = new ArrayList<>();
        for( int i = 0; i < list.size(); i++ ) {
            name.add(list.get(i).getNick_name());
        }
        return name.toString();
    }

    public static class DataBean {
        /**
         * nick_name : cxiaoyu
         * ID : 26
         */

        private String nick_name;
        private int ID;

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;

        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

    }



}

