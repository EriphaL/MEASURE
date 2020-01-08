package com.nbst.model;
/**
 * 
 * @author zm
 *
 */
public class Engineconfig {
    private Integer yqId;//ID

    private String yqModuleName;//模块名称

    private Integer yqThreadsCount;//模块线程数

    private Integer yqHandlerType;//模块句柄类型

    private Long yqCreateDate;//模块创建日期

    private Long yqChangedDate;//模块修改日期

    private Long yqDeleteDate;//模块删除日期

    private Integer yqCreatePeople;//模块创建人

    private Integer yqChangePeople;//模块修改人

    private Integer yqDeletePeople;//模块删除人

    private Integer yqStatus;//模块状态

    

	public Integer getYqId() {
		return yqId;
	}

	public void setYqId(Integer yqId) {
		this.yqId = yqId;
	}

	public String getYqModuleName() {
		return yqModuleName;
	}

	public void setYqModuleName(String yqModuleName) {
		this.yqModuleName = yqModuleName;
	}

	public Integer getYqThreadsCount() {
		return yqThreadsCount;
	}

	public void setYqThreadsCount(Integer yqThreadsCount) {
		this.yqThreadsCount = yqThreadsCount;
	}

	public Integer getYqHandlerType() {
		return yqHandlerType;
	}

	public void setYqHandlerType(Integer yqHandlerType) {
		this.yqHandlerType = yqHandlerType;
	}

	public Long getYqCreateDate() {
		return yqCreateDate;
	}

	public void setYqCreateDate(Long yqCreateDate) {
		this.yqCreateDate = yqCreateDate;
	}

	public Long getYqChangedDate() {
		return yqChangedDate;
	}

	public void setYqChangedDate(Long yqChangedDate) {
		this.yqChangedDate = yqChangedDate;
	}

	public Long getYqDeleteDate() {
		return yqDeleteDate;
	}

	public void setYqDeleteDate(Long yqDeleteDate) {
		this.yqDeleteDate = yqDeleteDate;
	}

	public Integer getYqCreatePeople() {
		return yqCreatePeople;
	}

	public void setYqCreatePeople(Integer yqCreatePeople) {
		this.yqCreatePeople = yqCreatePeople;
	}

	public Integer getYqChangePeople() {
		return yqChangePeople;
	}

	public void setYqChangePeople(Integer yqChangePeople) {
		this.yqChangePeople = yqChangePeople;
	}

	public Integer getYqDeletePeople() {
		return yqDeletePeople;
	}

	public void setYqDeletePeople(Integer yqDeletePeople) {
		this.yqDeletePeople = yqDeletePeople;
	}

	public Integer getYqStatus() {
		return yqStatus;
	}

	public void setYqStatus(Integer yqStatus) {
		this.yqStatus = yqStatus;
	}

	@Override
	public String toString() {
		return "Engineconfig [yqId=" + yqId + ", yqModuleName=" + yqModuleName + ", yqThreadsCount=" + yqThreadsCount
				+ ", yqHandlerType=" + yqHandlerType + ", yqCreateDate=" + yqCreateDate + ", yqChangedDate="
				+ yqChangedDate + ", yqDeleteDate=" + yqDeleteDate + ", yqCreatePeople=" + yqCreatePeople
				+ ", yqChangePeople=" + yqChangePeople + ", yqDeletePeople=" + yqDeletePeople + ", yqStatus=" + yqStatus
				+ "]";
	}

    
}