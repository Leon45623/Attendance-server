package com.scb.vo;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType
public class RecordVo implements Serializable {
	private int id;
	private String date;
	private int pwId;
	private String beginTime;
	private String endTime;
    private String location;
    private String result;
    private String month;
	public RecordVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RecordVo(int id, String date, int pwId, String beginTime, String endTime, String location, String result,
			String month) {
		super();
		this.id = id;
		this.date = date;
		this.pwId = pwId;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.location = location;
		this.result = result;
		this.month = month;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getPwId() {
		return pwId;
	}
	public void setPwId(int pwId) {
		this.pwId = pwId;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	@Override
	public String toString() {
		return "RecordVo [id=" + id + ", date=" + date + ", pwId=" + pwId + ", beginTime=" + beginTime + ", endTime="
				+ endTime + ", location=" + location + ", result=" + result + ", month=" + month + "]";
	}

    
	
	
}
