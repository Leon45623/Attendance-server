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
@Table(name="Result")
@SuppressWarnings("serial")
public class Result implements Serializable {
	
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")  
    @GeneratedValue(generator = "generator")   // 自增长
	@Column(name="id")
	private int id;
	@Column(name="pwId")
	private int pwId;
	@Column(name="date")
	private Date date;
	@Column(name="result")
	private String result;
	@Column(name="isNomal")
	private int isNomal;
	@Column(name="isCheck")
	private int isCheck;
	@Column(name="month")
	private String month;
	@Column(name="checkInfo")
	private String checkInfo;
	@Column(name="remake")
    private String remake;
	@Column(name="superId") 
	private int superId;
	@Column(name="name") 
	private String name;
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Result(int id, int pwId, Date date, String result, int isNomal, int isCheck, String month, String checkInfo,
			String remake, int superId, String name) {
		super();
		this.id = id;
		this.pwId = pwId;
		this.date = date;
		this.result = result;
		this.isNomal = isNomal;
		this.isCheck = isCheck;
		this.month = month;
		this.checkInfo = checkInfo;
		this.remake = remake;
		this.superId = superId;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPwId() {
		return pwId;
	}
	public void setPwId(int pwId) {
		this.pwId = pwId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getIsNomal() {
		return isNomal;
	}
	public void setIsNomal(int isNomal) {
		this.isNomal = isNomal;
	}
	public int getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getCheckInfo() {
		return checkInfo;
	}
	public void setCheckInfo(String checkInfo) {
		this.checkInfo = checkInfo;
	}
	public String getRemake() {
		return remake;
	}
	public void setRemake(String remake) {
		this.remake = remake;
	}
	public int getSuperId() {
		return superId;
	}
	public void setSuperId(int superId) {
		this.superId = superId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Result [id=" + id + ", pwId=" + pwId + ", date=" + date + ", result=" + result + ", isNomal=" + isNomal
				+ ", isCheck=" + isCheck + ", month=" + month + ", checkInfo=" + checkInfo + ", remake=" + remake
				+ ", superId=" + superId + ", name=" + name + "]";
	}
	

}
