package com.example.newsAPP.bean;

import java.util.List;

public class TrendBean {

    /**
     * data : [{"nick_name":"bxiaoyu","ID":2,"author_id":25,"title":"no","content":"中美贸易摩擦你们怎么看","news_id":2,"url":"no","release_time":"2019-12-05 23:35:46.0"},{"nick_name":"bxiaoyu","ID":1,"author_id":25,"title":"达州市达川区一老人多年无户籍 派出所民警实地走访解难题","content":"真的难受这条新闻","news_id":1,"url":"http://mini.eastday.com/mobile/191126203843884.html","release_time":"2019-12-10 23:34:13.0"},{"nick_name":"cxiaoyu","ID":3,"author_id":26,"title":"达州市达川区一老人多年无户籍 派出所民警实地走访解难题","content":"老人无家可归难啊","news_id":1,"url":"http://mini.eastday.com/mobile/191126203843884.html","release_time":"2019-12-14 21:46:21.0"},{"nick_name":"dxiaoyu","ID":4,"author_id":27,"title":"no","content":"这条新闻你们怎么看","news_id":2,"url":"no","release_time":"2019-12-14 21:50:12.0"},{"nick_name":"exiaoyu","ID":5,"author_id":28,"title":"153年来首次，英国皇家航空学会向中国颁发一个奖项，网友：示好","content":"这真是让人震惊","news_id":3,"url":"http://mini.eastday.com/mobile/191126203012700.html","release_time":"2019-12-14 21:51:12.0"},{"nick_name":"fxiaoyu","ID":6,"author_id":29,"title":"153年来首次，英国皇家航空学会向中国颁发一个奖项，网友：示好","content":"好新闻","news_id":3,"url":"http://mini.eastday.com/mobile/191126203012700.html","release_time":"2019-12-14 21:51:15.0"},{"nick_name":"cxiaoyu","ID":7,"author_id":26,"title":"153年来首次，英国皇家航空学会向中国颁发一个奖项，网友：示好","content":"难得一见","news_id":3,"url":"http://mini.eastday.com/mobile/191126203012700.html","release_time":"2019-12-14 21:51:48.0"},{"nick_name":"bxiaoyu","ID":8,"author_id":25,"title":"no","content":"令人深思","news_id":2,"url":"no","release_time":"2019-12-14 21:51:52.0"}]
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
         * nick_name : bxiaoyu
         * ID : 2
         * author_id : 25
         * title : no
         * content : 中美贸易摩擦你们怎么看
         * news_id : 2
         * url : no
         * release_time : 2019-12-05 23:35:46.0
         */

        private String nick_name;
        private String ID;
        private String author_id;
        private String title;
        private String content;
        private String news_id;
        private String url;
        private String release_time;

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

        public String getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(String author_id) {
            this.author_id = author_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getNews_id() {
            return news_id;
        }

        public void setNews_id(String news_id) {
            this.news_id = news_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getRelease_time() {
            return release_time;
        }

        public void setRelease_time(String release_time) {
            this.release_time = release_time;
        }
    }
}
