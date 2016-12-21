package com.example.dllo.hodgepodge.video.newest;

import java.util.List;

/**
 * 庭
 * Created by Ting on 16/12/21.
 */

public class NewestRunBean {

    /**
     * status : 0
     * msg : OK
     * data : [{"bannerid":"1375","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2016/12/09/584a4e51aa6fd.jpg","description":"","userid":"1","addtime":"1481264721","uptime":"1481264721","orderid":"7","cateid":"0","count_click":"25726","status":"1","start_time":"1481251440","end_time":"0","extra":"{\"app_banner_type\":\"1\",\"app_banner_param\":\"http:\\/\\/www.xinpianchang.com\\/activity\\/independence\\/ts-love_letter\"}","extra_data":{"app_banner_type":"1","app_banner_param":"http://www.xinpianchang.com/activity/independence/ts-love_letter"}},{"bannerid":"1399","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2016/12/20/58593914d1082.jpg","description":"","userid":"927555","addtime":"1482242324","uptime":"1482242324","orderid":"6","cateid":"0","count_click":"69","status":"1","start_time":"1482249600","end_time":"1482335940","extra":"{\"app_banner_type\":\"1\",\"app_banner_param\":\"http:\\/\\/www.vmovier.com\\/fan127?inner_app=1\"}","extra_data":{"app_banner_type":"1","app_banner_param":"http://www.vmovier.com/fan127?inner_app=1"}},{"bannerid":"1391","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2016/12/16/5853d25718fc2.jpg","description":"","userid":"927555","addtime":"1481888343","uptime":"1481888343","orderid":"4","cateid":"0","count_click":"1953","status":"1","start_time":"1481888343","end_time":"0","extra":"{\"app_banner_type\":\"2\",\"app_banner_param\":\"50671\"}","extra_data":{"app_banner_type":"2","app_banner_param":"50671","is_album":"0"}},{"bannerid":"1392","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2016/12/16/5853d47137dfa.jpg","description":"","userid":"927555","addtime":"1481888881","uptime":"1481888881","orderid":"3","cateid":"0","count_click":"2124","status":"1","start_time":"1481888881","end_time":"0","extra":"{\"app_banner_type\":\"2\",\"app_banner_param\":\"50649\"}","extra_data":{"app_banner_type":"2","app_banner_param":"50649","is_album":"0"}},{"bannerid":"1388","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2016/12/15/58520b9e2f3c9.jpg","description":"","userid":"919390","addtime":"1481771934","uptime":"1481771934","orderid":"2","cateid":"0","count_click":"672","status":"1","start_time":"1481771934","end_time":"0","extra":"{\"app_banner_type\":\"2\",\"app_banner_param\":\"50684\"}","extra_data":{"app_banner_type":"2","app_banner_param":"50684","is_album":"0"}},{"bannerid":"1318","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2016/11/22/5833b622913ff.jpg","description":"","userid":"551913","addtime":"1479783971","uptime":"1479783971","orderid":"1","cateid":"0","count_click":"27633","status":"1","start_time":"1478841300","end_time":"0","extra":"{\"app_banner_type\":\"1\",\"app_banner_param\":\"http:\\/\\/school.xinpianchang.com\\/film-room\\/application\\/app_m.html?inner_app=1\"}","extra_data":{"app_banner_type":"1","app_banner_param":"http://school.xinpianchang.com/film-room/application/app_m.html?inner_app=1"}}]
     */

    private String status;
    private String msg;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bannerid : 1375
         * type : 7
         * object_id : 0
         * title :
         * url : /
         * image : http://cs.vmoiver.com/Uploads/Banner/2016/12/09/584a4e51aa6fd.jpg
         * description :
         * userid : 1
         * addtime : 1481264721
         * uptime : 1481264721
         * orderid : 7
         * cateid : 0
         * count_click : 25726
         * status : 1
         * start_time : 1481251440
         * end_time : 0
         * extra : {"app_banner_type":"1","app_banner_param":"http:\/\/www.xinpianchang.com\/activity\/independence\/ts-love_letter"}
         * extra_data : {"app_banner_type":"1","app_banner_param":"http://www.xinpianchang.com/activity/independence/ts-love_letter"}
         */

        private String bannerid;
        private String type;
        private String object_id;
        private String title;
        private String url;
        private String image;
        private String description;
        private String userid;
        private String addtime;
        private String uptime;
        private String orderid;
        private String cateid;
        private String count_click;
        private String status;
        private String start_time;
        private String end_time;
        private String extra;
        private ExtraDataBean extra_data;

        public String getBannerid() {
            return bannerid;
        }

        public void setBannerid(String bannerid) {
            this.bannerid = bannerid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getObject_id() {
            return object_id;
        }

        public void setObject_id(String object_id) {
            this.object_id = object_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getUptime() {
            return uptime;
        }

        public void setUptime(String uptime) {
            this.uptime = uptime;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getCateid() {
            return cateid;
        }

        public void setCateid(String cateid) {
            this.cateid = cateid;
        }

        public String getCount_click() {
            return count_click;
        }

        public void setCount_click(String count_click) {
            this.count_click = count_click;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getExtra() {
            return extra;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }

        public ExtraDataBean getExtra_data() {
            return extra_data;
        }

        public void setExtra_data(ExtraDataBean extra_data) {
            this.extra_data = extra_data;
        }

        public static class ExtraDataBean {
            /**
             * app_banner_type : 1
             * app_banner_param : http://www.xinpianchang.com/activity/independence/ts-love_letter
             */

            private String app_banner_type;
            private String app_banner_param;

            public String getApp_banner_type() {
                return app_banner_type;
            }

            public void setApp_banner_type(String app_banner_type) {
                this.app_banner_type = app_banner_type;
            }

            public String getApp_banner_param() {
                return app_banner_param;
            }

            public void setApp_banner_param(String app_banner_param) {
                this.app_banner_param = app_banner_param;
            }
        }
    }
}
