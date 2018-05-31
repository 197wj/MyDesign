package com.design.model.sys;

import java.util.Date;

public class SysCrop {
    private Integer id;

    private String cropName;

    private Double cropLandMoisture;

    private Double cropAirMoisture;

    private Double cropAirTemp;

    private Byte status;

    private Date gmtCreate;

    private Date gmtUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName == null ? null : cropName.trim();
    }

    public Double getCropLandMoisture() {
        return cropLandMoisture;
    }

    public void setCropLandMoisture(Double cropLandMoisture) {
        this.cropLandMoisture = cropLandMoisture;
    }

    public Double getCropAirMoisture() {
        return cropAirMoisture;
    }

    public void setCropAirMoisture(Double cropAirMoisture) {
        this.cropAirMoisture = cropAirMoisture;
    }

    public Double getCropAirTemp() {
        return cropAirTemp;
    }

    public void setCropAirTemp(Double cropAirTemp) {
        this.cropAirTemp = cropAirTemp;
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