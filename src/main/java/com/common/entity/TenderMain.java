package com.common.entity;

import java.util.List;

/**
 * Created by acer on 2015/4/5.
 */
public class TenderMain {
    private Integer id;
    private String projectName;//项目名称
    private String projectType;//项目类型
    private String projectYear;//项目年度
    private String projectArea;//地区
    private String agency;//中介公司
    private String bidorgId;//招标公司
    private String orgid;//投标公司

    /***
     * 非库表字段
     */
    private List<TenderProduct> tenderProductList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectYear() {
        return projectYear;
    }

    public void setProjectYear(String projectYear) {
        this.projectYear = projectYear;
    }

    public String getProjectArea() {
        return projectArea;
    }

    public void setProjectArea(String projectArea) {
        this.projectArea = projectArea;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public List<TenderProduct> getTenderProductList() {
        return tenderProductList;
    }

    public void setTenderProductList(List<TenderProduct> tenderProductList) {
        this.tenderProductList = tenderProductList;
    }

    public String getBidorgId() {
        return bidorgId;
    }

    public void setBidorgId(String bidorgId) {
        this.bidorgId = bidorgId;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }
}
