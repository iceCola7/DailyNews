package com.cxz.news.bean.Photos;

import java.util.Date;

/**
 * 干货图片信息
 * Created by chenxz on 2017/4/17.
 */
public class PhotoInfo {

    private String Id;
    private Date createdat;
    private String desc;
    private Date publishedat;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    public void setId(String Id) {
        this.Id = Id;
    }
    public String getId() {
        return Id;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }
    public Date getCreatedat() {
        return createdat;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }

    public void setPublishedat(Date publishedat) {
        this.publishedat = publishedat;
    }
    public Date getPublishedat() {
        return publishedat;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public String getSource() {
        return source;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
    public boolean getUsed() {
        return used;
    }

    public void setWho(String who) {
        this.who = who;
    }
    public String getWho() {
        return who;
    }

}
