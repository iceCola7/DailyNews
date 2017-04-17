package com.cxz.news.bean.Photos;

import java.util.List;

/**
 * Created by chenxz on 2017/4/17.
 */
public class GankPhotos {

    private boolean error;
    private List<PhotoInfo> results;
    public void setError(boolean error) {
        this.error = error;
    }
    public boolean getError() {
        return error;
    }

    public void setResults(List<PhotoInfo> results) {
        this.results = results;
    }
    public List<PhotoInfo> getResults() {
        return results;
    }
}
