package com.example.newsAPP.bean;

import java.util.List;

public class NewsBean {

    /**
     * data : [{"author":"中国新闻网","time":"2019-11-26 20:38:00","title":"达州市达川区一老人多年无户籍 派出所民警实地走访解难题","type":"头条","url":"http://mini.eastday.com/mobile/191126203843884.html","picture":"http://08imgmini.eastday.com/mobile/20191126/20191126203843_6bf761175986aa2d2728710014cd83cf_1_mwpm_03200403.jpg"},{"author":"强国兵器","time":"2019-11-26 20:30:00","title":"153年来首次，英国皇家航空学会向中国颁发一个奖项，网友：示好","type":"头条","url":"http://mini.eastday.com/mobile/191126203012700.html","picture":"http://08imgmini.eastday.com/mobile/20191126/20191126203012_757bec226b2d38c24cd367ecfeb7afef_4_mwpm_03200403.jpg"},{"author":"明佳体育","time":"2019-11-26 20:24:00","title":"这瓜真甜！出手数跟得分都创新高，安东尼一定会越来越好的","type":"头条","url":"http://mini.eastday.com/mobile/191126202415062.html","picture":"http://02imgmini.eastday.com/mobile/20191126/20191126202415_f8e89fc000defc4aa00af68e5ae95fdf_1_mwpm_03200403.jpg"},{"author":"ZAKER网","time":"2019-11-26 20:21:00","title":"汽车圈内最丑的5个车标，尤其是最后一个，简直无法直视","type":"头条","url":"http://mini.eastday.com/mobile/191126202106882.html","picture":"http://01imgmini.eastday.com/mobile/20191126/20191126202106_18746466d547d65d62c96abbc7763e05_1_mwpm_03200403.jpg"},{"author":"自娱自乐12号站","time":"2019-11-26 20:16:00","title":"吉娜不听劝做饭出现失误，林依轮接过吉娜手中的锅铲却被故意放大","type":"头条","url":"http://mini.eastday.com/mobile/191126201624741.html","picture":"http://07imgmini.eastday.com/mobile/20191126/2019112620_23a782451dfd49af8f31f4dad218c249_8284_mwpm_03200403.jpg"},{"author":"每期发现","time":"2019-11-26 20:15:00","title":"神奇有趣的国境线","type":"头条","url":"http://mini.eastday.com/mobile/191126201510377.html","picture":"http://06imgmini.eastday.com/mobile/20191126/20191126201510_3e4e7b5c1bc7a7d6952debeeec3097e8_17_mwpm_03200403.jpg"},{"author":"太平洋游戏网","time":"2019-11-26 20:07:00","title":"藤新担任比心陪练王者荣耀声优配音赛评委，为配音行业的未来寻找新星","type":"头条","url":"http://mini.eastday.com/mobile/191126200720945.html","picture":"http://05imgmini.eastday.com/mobile/20191126/20191126200720_d562dcbd61fcc8f06fd4eb7b573c5537_3_mwpm_03200403.jpg"},{"author":"瑑玉","time":"2019-11-26 20:06:00","title":"有关和田玉子料的产出与皮色","type":"头条","url":"http://mini.eastday.com/mobile/191126200622842.html","picture":"http://02imgmini.eastday.com/mobile/20191126/20191126200622_cefa17cf8bb27972685df9f3d0a6f4b8_1_mwpm_03200403.jpg"},{"author":"娱记家的猫","time":"2019-11-26 20:05:00","title":"六款比国产护肤品推荐，没有奢华的包装，只有实用的高性价比","type":"头条","url":"http://mini.eastday.com/mobile/191126200554770.html","picture":"http://03imgmini.eastday.com/mobile/20191126/20191126200554_3b37111b0f219f8bbfd7c7b927a2acc1_1_mwpm_03200403.jpg"}]
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
         * author : 中国新闻网
         * time : 2019-11-26 20:38:00
         * title : 达州市达川区一老人多年无户籍 派出所民警实地走访解难题
         * type : 头条
         * url : http://mini.eastday.com/mobile/191126203843884.html
         * picture : http://08imgmini.eastday.com/mobile/20191126/20191126203843_6bf761175986aa2d2728710014cd83cf_1_mwpm_03200403.jpg
         */

        private String author;
        private String time;
        private String title;
        private String type;
        private String url;
        private String picture;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
