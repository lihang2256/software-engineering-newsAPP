package com.example.newsAPP.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.ArrayList;
import java.util.List;



public class FollowBean {

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

//    public String getPickerViewText() {
//
//        //   List<String> mapList1 = name.stream().map(FollowBean.DataBean::getPassword).collect(Collectors.toList());   java8语法，不适用
//
//        List<FollowBean.DataBean> followbeans = new ArrayList<>();
//        List<String> name = new ArrayList<>();
//        for( int i = 0; i < followbeans.size(); i++ ) {
//            name.add(followbeans.get(i).getPassword());
//        }
//        return name.toString();
//    }

    public static class DataBean  implements IPickerViewData {
        /**
         * nick_name : cxiaoyu
         * ID : 26
         */

        private String nick_name;
        private String ID;

        /**
         *      实现 IPickerViewData 接口，
         *      这个用来显示在PickerView上面的字符串，
         *      PickerView会通过IPickerViewData获取getPickerViewText方法显示出来。
         */
        @Override
        public String getPickerViewText() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;

        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

    }


}

