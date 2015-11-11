package com.common.entity;

/**
 * Created by acer on 2015/4/13.
 */
public class LogEntity {
//    logUrl:httpUrlContent,
//    logHisRefer:hisPageUrl,
//    logParams:logParams,
//    logQuery:logQuery
    private String urlContent;
    private String pageUrl;
    private String logParams;
    private String logQuery;

    public String getUrlContent() {
        return urlContent;
    }

    public void setUrlContent(String urlContent) {
        this.urlContent = urlContent;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getLogParams() {
        return logParams;
    }

    public void setLogParams(String logParams) {
        this.logParams = logParams;
    }

    public String getLogQuery() {
        return logQuery;
    }

    public void setLogQuery(String logQuery) {
        this.logQuery = logQuery;
    }
}
