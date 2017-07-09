package com.cxz.news.bean.videos;

/**
 * Created by chenxz on 2017/7/9.
 */
public class NeteastVideoSummary {

    /**
     * 自己加的记录测出来的宽高
     */
    private int picWidth = -1;
    private int picHeight = -1;

    /**
     * replyCount : 895
     * videosource : 新媒体
     * mp4Hd_url : null
     * cover : http://vimg2.ws.126.net/image/snapshot/2016/2/5/H/VBEMM3F5H.jpg
     * title : 渥太华600人欢乐春节快闪
     * playCount : 22751
     * replyBoard : video_bbs
     * sectiontitle :
     * description : 超过600人在-11度的天气下庆祝春节！
     * replyid : BEML6HHI008535RB
     * mp4_url : http://flv2.bn.netease.com/videolib3/1602/12/BFihm9923/SD/BFihm9923-mobile.mp4
     * length : 666
     * playersize : 1
     * m3u8Hd_url : null
     * vid : VBEML6HHI
     * m3u8_url : http://flv2.bn.netease.com/videolib3/1602/12/BFihm9923/SD/movie_index.m3u8
     * ptime : 2016-02-12 12:05:39
     */

    private int sizeSHD;
    private int replyCount;
    private String videosource;
    private String mp4Hd_url;
    private String title;
    private String cover;
    private String description;
    private String replyid;
    private int length;
    private String m3u8_url;
    private String vid;
    private String topicName;
    private int votecount;
    private String topicImg;
    private String topicDesc;
    private String topicSid;
    private String replyBoard;
    private int playCount;
    private String sectiontitle;
    private String mp4_url;
    private int playersize;
    private int sizeSD;
    private int sizeHD;
    private String m3u8Hd_url;
    private String ptime;
    private VideoTopic videoTopic;

    public int getPicWidth() {
        return picWidth;
    }

    public void setPicWidth(int picWidth) {
        this.picWidth = picWidth;
    }

    public int getPicHeight() {
        return picHeight;
    }

    public void setPicHeight(int picHeight) {
        this.picHeight = picHeight;
    }

    public void setSizeSHD(int sizeSHD) {
        this.sizeSHD = sizeSHD;
    }

    public int getSizeSHD() {
        return sizeSHD;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setVideosource(String videosource) {
        this.videosource = videosource;
    }

    public String getVideosource() {
        return videosource;
    }

    public void setMp4Hd_url(String mp4Hd_url) {
        this.mp4Hd_url = mp4Hd_url;
    }

    public String getMp4Hd_url() {
        return mp4Hd_url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCover() {
        return cover;
    }

    public void setVideoTopic(VideoTopic videoTopic) {
        this.videoTopic = videoTopic;
    }

    public VideoTopic getVideoTopic() {
        return videoTopic;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setReplyid(String replyid) {
        this.replyid = replyid;
    }

    public String getReplyid() {
        return replyid;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setM3u8_url(String m3u8_url) {
        this.m3u8_url = m3u8_url;
    }

    public String getM3u8_url() {
        return m3u8_url;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getVid() {
        return vid;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    public int getVotecount() {
        return votecount;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    public void setTopicSid(String topicSid) {
        this.topicSid = topicSid;
    }

    public String getTopicSid() {
        return topicSid;
    }

    public void setReplyBoard(String replyBoard) {
        this.replyBoard = replyBoard;
    }

    public String getReplyBoard() {
        return replyBoard;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setSectiontitle(String sectiontitle) {
        this.sectiontitle = sectiontitle;
    }

    public String getSectiontitle() {
        return sectiontitle;
    }

    public void setMp4_url(String mp4_url) {
        this.mp4_url = mp4_url;
    }

    public String getMp4_url() {
        return mp4_url;
    }

    public void setPlayersize(int playersize) {
        this.playersize = playersize;
    }

    public int getPlayersize() {
        return playersize;
    }

    public void setSizeSD(int sizeSD) {
        this.sizeSD = sizeSD;
    }

    public int getSizeSD() {
        return sizeSD;
    }

    public void setSizeHD(int sizeHD) {
        this.sizeHD = sizeHD;
    }

    public int getSizeHD() {
        return sizeHD;
    }

    public void setM3u8Hd_url(String m3u8Hd_url) {
        this.m3u8Hd_url = m3u8Hd_url;
    }

    public String getM3u8Hd_url() {
        return m3u8Hd_url;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getPtime() {
        return ptime;
    }

    public class VideoTopic {
        private String alias;
        private String tname;
        private String ename;
        private String tid;
        private String topic_icons;

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getTopic_icons() {
            return topic_icons;
        }

        public void setTopic_icons(String topic_icons) {
            this.topic_icons = topic_icons;
        }
    }
}

