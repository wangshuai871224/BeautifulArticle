package com.example.dllo.hodgepodge.video.series.seriesdetail;

/**
 * 庭
 * Created by Ting on 16/12/23.
 */

public class SeriesVideoBean {

    /**
     * status : 0
     * msg : ok
     * data : {"title":"番外篇-绿幕搭建和拍摄技巧 上海温哥华电影学院","seriesid":"45","series_postid":"1792","video_link":"http://v.youku.com/v_show/id_XMTYyNDExOTkyNA==.html?f=26857029","episode":"85","count_comment":"193","thumbnail":"http://cs.vmoiver.com/Uploads/Series/2016-06-28/5772695e63d1a.jpeg","share_link":{"sweibo":"http://www.vmovier.com/series/45/85?debug=1&_vfrom=VmovierApp_sweibo","weixin":"http://www.vmovier.com/series/45/85?debug=1&_vfrom=VmovierApp_weixin","qzone":"http://www.vmovier.com/series/45/85?debug=1&_vfrom=VmovierApp_qzone","qq":"http://www.vmovier.com/series/45/85?debug=1&_vfrom=VmovierApp_qq"},"qiniu_url":"http://qiniu.vmovier.vmoviercdn.com/5772693f14597.mp4","share_sub_title":"分享自#V电影#Android客户端","weibo_share_image":"http://cs.vmoiver.com/Uploads/Series/2016-06-28/5772695e63d1a.jpeg","count_share":"1672"}
     */

    private String status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 番外篇-绿幕搭建和拍摄技巧 上海温哥华电影学院
         * seriesid : 45
         * series_postid : 1792
         * video_link : http://v.youku.com/v_show/id_XMTYyNDExOTkyNA==.html?f=26857029
         * episode : 85
         * count_comment : 193
         * thumbnail : http://cs.vmoiver.com/Uploads/Series/2016-06-28/5772695e63d1a.jpeg
         * share_link : {"sweibo":"http://www.vmovier.com/series/45/85?debug=1&_vfrom=VmovierApp_sweibo","weixin":"http://www.vmovier.com/series/45/85?debug=1&_vfrom=VmovierApp_weixin","qzone":"http://www.vmovier.com/series/45/85?debug=1&_vfrom=VmovierApp_qzone","qq":"http://www.vmovier.com/series/45/85?debug=1&_vfrom=VmovierApp_qq"}
         * qiniu_url : http://qiniu.vmovier.vmoviercdn.com/5772693f14597.mp4
         * share_sub_title : 分享自#V电影#Android客户端
         * weibo_share_image : http://cs.vmoiver.com/Uploads/Series/2016-06-28/5772695e63d1a.jpeg
         * count_share : 1672
         */

        private String title;
        private String seriesid;
        private String series_postid;
        private String video_link;
        private String episode;
        private String count_comment;
        private String thumbnail;
        private ShareLinkBean share_link;
        private String qiniu_url;
        private String share_sub_title;
        private String weibo_share_image;
        private String count_share;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSeriesid() {
            return seriesid;
        }

        public void setSeriesid(String seriesid) {
            this.seriesid = seriesid;
        }

        public String getSeries_postid() {
            return series_postid;
        }

        public void setSeries_postid(String series_postid) {
            this.series_postid = series_postid;
        }

        public String getVideo_link() {
            return video_link;
        }

        public void setVideo_link(String video_link) {
            this.video_link = video_link;
        }

        public String getEpisode() {
            return episode;
        }

        public void setEpisode(String episode) {
            this.episode = episode;
        }

        public String getCount_comment() {
            return count_comment;
        }

        public void setCount_comment(String count_comment) {
            this.count_comment = count_comment;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public ShareLinkBean getShare_link() {
            return share_link;
        }

        public void setShare_link(ShareLinkBean share_link) {
            this.share_link = share_link;
        }

        public String getQiniu_url() {
            return qiniu_url;
        }

        public void setQiniu_url(String qiniu_url) {
            this.qiniu_url = qiniu_url;
        }

        public String getShare_sub_title() {
            return share_sub_title;
        }

        public void setShare_sub_title(String share_sub_title) {
            this.share_sub_title = share_sub_title;
        }

        public String getWeibo_share_image() {
            return weibo_share_image;
        }

        public void setWeibo_share_image(String weibo_share_image) {
            this.weibo_share_image = weibo_share_image;
        }

        public String getCount_share() {
            return count_share;
        }

        public void setCount_share(String count_share) {
            this.count_share = count_share;
        }

        public static class ShareLinkBean {
            /**
             * sweibo : http://www.vmovier.com/series/45/85?debug=1&_vfrom=VmovierApp_sweibo
             * weixin : http://www.vmovier.com/series/45/85?debug=1&_vfrom=VmovierApp_weixin
             * qzone : http://www.vmovier.com/series/45/85?debug=1&_vfrom=VmovierApp_qzone
             * qq : http://www.vmovier.com/series/45/85?debug=1&_vfrom=VmovierApp_qq
             */

            private String sweibo;
            private String weixin;
            private String qzone;
            private String qq;

            public String getSweibo() {
                return sweibo;
            }

            public void setSweibo(String sweibo) {
                this.sweibo = sweibo;
            }

            public String getWeixin() {
                return weixin;
            }

            public void setWeixin(String weixin) {
                this.weixin = weixin;
            }

            public String getQzone() {
                return qzone;
            }

            public void setQzone(String qzone) {
                this.qzone = qzone;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }
        }
    }
}
