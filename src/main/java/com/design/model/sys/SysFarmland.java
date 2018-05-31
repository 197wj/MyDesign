package com.design.model.sys;

import java.io.Serializable;
import java.util.Date;

public class SysFarmland implements Serializable{
    private Integer id;

    private Integer farmlandId;

    private Double farmlandMoisture;

    private Double airTemp;

    private Double airMoisture;

    private Date testTime;

    private Byte status;

    private Date gmtCreate;

    private Date gmtUpdate;

	@Override
	public String toString() {
		return "SysFarmland [id=" + id + ", farmlandId=" + farmlandId + ", farmlandMoisture=" + farmlandMoisture
				+ ", airTemp=" + airTemp + ", airMoisture=" + airMoisture + ", testTime=" + testTime + ", status="
				+ status + ", gmtCreate=" + gmtCreate + ", gmtUpdate=" + gmtUpdate + "]";
	}

	public Integer getId() {
        return id;
    }

	public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFarmlandId() {
		return farmlandId;
	}

	public void setFarmlandId(Integer farmlandId) {
		this.farmlandId = farmlandId;
	}

	public Double getFarmlandMoisture() {
		return farmlandMoisture;
	}

	public void setFarmlandMoisture(Double farmlandMoisture) {
		this.farmlandMoisture = farmlandMoisture;
	}

	public Double getAirTemp() {
        return airTemp;
    }

    public void setAirTemp(Double airTemp) {
        this.airTemp = airTemp;
    }

    public Double getAirMoisture() {
        return airMoisture;
    }

    public void setAirMoisture(Double airMoisture) {
        this.airMoisture = airMoisture;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
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