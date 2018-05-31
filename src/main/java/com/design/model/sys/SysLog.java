package com.design.model.sys;

import java.util.Date;

public class SysLog {
    private Integer logId;

    private Integer userId;

    private String userName;

    private String operType;

    private String operPath;

    private String operObject;

    private String ipAddress;

    private Date addTime;

    private Byte status;

    private Date gmtCreate;

    private Date gmtUpdate;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType == null ? null : operType.trim();
    }

    public String getOperPath() {
        return operPath;
    }

    public void setOperPath(String operPath) {
        this.operPath = operPath == null ? null : operPath.trim();
    }

    public String getOperObject() {
        return operObject;
    }

    public void setOperObject(String operObject) {
        this.operObject = operObject == null ? null : operObject.trim();
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }
}