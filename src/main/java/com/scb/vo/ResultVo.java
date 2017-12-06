package com.scb.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType
public class ResultVo {
	private int id;

	private int pwId;

	private String date;

	private String result;

	private int isNomal;

	private int isCheck;

	private String month;

	private String checkInfo;

    private String remake;

	private int superId;

	private String name;

	public ResultVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultVo(int id, int pwId, String date, String result, int isNomal, int isCheck, String month,
			String checkInfo, String remake, int superId, String name) {
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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
		return "ResultVo [id=" + id + ", pwId=" + pwId + ", date=" + date + ", result=" + result + ", isNomal="
				+ isNomal + ", isCheck=" + isCheck + ", month=" + month + ", checkInfo=" + checkInfo + ", remake="
				+ remake + ", superId=" + superId + ", name=" + name + "]";
	}
	
}
