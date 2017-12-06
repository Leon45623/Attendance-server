package com.scb.vo;

import java.io.Serializable;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType
public class EmployeeVo implements Serializable{
	
	
	
	private int pwId;		
	private String position;	
	private String name;
	private String superId;
	private String phone;
	private int seatId;
	private String location;
	private int isUsed;
	private String area;

	private int exceptionNum;

    private String result;

    private String checkInfo;
    private String remake;
	private String headportrait;
	public EmployeeVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeVo(int pwId, String position, String name, String superId, String phone, int seatId, String location,
			int isUsed, String area, int exceptionNum, String result, String checkInfo, String remake,
			String headportrait) {
		super();
		this.pwId = pwId;
		this.position = position;
		this.name = name;
		this.superId = superId;
		this.phone = phone;
		this.seatId = seatId;
		this.location = location;
		this.isUsed = isUsed;
		this.area = area;
		this.exceptionNum = exceptionNum;
		this.result = result;
		this.checkInfo = checkInfo;
		this.remake = remake;
		this.headportrait = headportrait;
	}
	public int getPwId() {
		return pwId;
	}
	public void setPwId(int pwId) {
		this.pwId = pwId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSuperId() {
		return superId;
	}
	public void setSuperId(String superId) {
		this.superId = superId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getExceptionNum() {
		return exceptionNum;
	}
	public void setExceptionNum(int exceptionNum) {
		this.exceptionNum = exceptionNum;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
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
	public String getHeadportrait() {
		return headportrait;
	}
	public void setHeadportrait(String headportrait) {
		this.headportrait = headportrait;
	}
	@Override
	public String toString() {
		return "EmployeeVo [pwId=" + pwId + ", position=" + position + ", name=" + name + ", superId=" + superId
				+ ", phone=" + phone + ", seatId=" + seatId + ", location=" + location + ", isUsed=" + isUsed
				+ ", area=" + area + ", exceptionNum=" + exceptionNum + ", result=" + result + ", checkInfo="
				+ checkInfo + ", remake=" + remake + ", headportrait=" + headportrait + "]";
	}
	
    
    
    
    
	
	
	
	
	

}
