package com.scb.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
@Entity
@XmlRootElement
@Table(name="Record")
@SuppressWarnings("serial")
public class Record implements Serializable {
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")  
    @GeneratedValue(generator = "generator")   // 自增长
	@Column(name="id")
	private int id;
	@Column(name="date")
	private Date date;
	@Column(name="pwId")
	private int pwId;
	@Column(name="beginTime")
	private Date beginTime;
	@Column(name="endTime")
	private Date endTime;
	@Column(name="location")
	private String location;
	@Column(name="month")
	private String month;
	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Record(int id, Date date, int pwId, Date beginTime, Date endTime, String location, String month) {
		super();
		this.id = id;
		this.date = date;
		this.pwId = pwId;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.location = location;
		this.month = month;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getPwId() {
		return pwId;
	}
	public void setPwId(int pwId) {
		this.pwId = pwId;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	@Override
	public String toString() {
		return "Record [id=" + id + ", date=" + date + ", pwId=" + pwId + ", beginTime=" + beginTime + ", endTime="
				+ endTime + ", location=" + location + ", month=" + month + "]";
	}

	

	
	

}
