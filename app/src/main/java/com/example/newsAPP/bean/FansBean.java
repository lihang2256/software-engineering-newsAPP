package com.example.newsAPP.bean;

import java.util.List;

public class FansBean {
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



    public static class DataBean {
        /**
         * nick_name : cxiaoyu
         * ID : 26
         */

        private String nick_name;
        private String ID;

        public String getNick_name() {
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

