package com.me.entity;

import java.sql.Timestamp;

public class LoopholeCnvd {
	private long id;
	private String loopholeName;
	private String cnnvdNo;
	private Timestamp publishTime;
	private Timestamp updateTime;
	private String harmLevel;
	private String harmType;
	private String source;
	private String description;
	private String introduction;
	private String referWebsite;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLoopholeName() {
		return loopholeName;
	}
	public void setLoopholeName(String loopholeName) {
		this.loopholeName = loopholeName;
	}
	public String getCnnvdNo() {
		return cnnvdNo;
	}
	public void setCnnvdNo(String cnnvdNo) {
		this.cnnvdNo = cnnvdNo;
	}
	public Timestamp getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public String getHarmLevel() {
		return harmLevel;
	}
	public void setHarmLevel(String harmLevel) {
		this.harmLevel = harmLevel;
	}
	public String getHarmType() {
		return harmType;
	}
	public void setHarmType(String harmType) {
		this.harmType = harmType;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getReferWebSite() {
		return referWebsite;
	}
	public void setReferWebSite(String webSite) {
		this.referWebsite = webSite;
	}
	@Override
	public String toString() {
		return "LoopholeCnvd [id=" + id + ", loopholeName=" + loopholeName
				+ ", cnnvdNo=" + cnnvdNo + ", publishTime=" + publishTime
				+ ", updateTime=" + updateTime + ", harmLevel=" + harmLevel
				+ ", harmType=" + harmType + ", source=" + source
				+ ", description=" + description + ", introduction="
				+ introduction + ", webSite=" + referWebsite + "]";
	}
	
}
