package com.scb.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@XmlRootElement
@Table(name="Seat")
@SuppressWarnings("serial")
public class Seat implements Serializable {

@Id
@Column(name="seatId")	
private int seatId;
@Column(name="location")	
private String location;
@Column(name="isUsed")	
private int isUsed;
@Column(name="pwId")	
private int pwId;
@Column(name="area")	
private String area;
public Seat() {
	super();
	// TODO Auto-generated constructor stub
}
public Seat(int seatId, String location, int isUsed, int pwId, String area) {
	super();
	this.seatId = seatId;
	this.location = location;
	this.isUsed = isUsed;
	this.pwId = pwId;
	this.area = area;
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
public int getPwId() {
	return pwId;
}
public void setPwId(int pwId) {
	this.pwId = pwId;
}
public String getArea() {
	return area;
}
public void setArea(String area) {
	this.area = area;
}
@Override
public String toString() {
	return "Seat [seatId=" + seatId + ", location=" + location + ", isUsed=" + isUsed + ", pwId=" + pwId + ", area="
			+ area + "]";
}


	
	
	
}
