package com.scb.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@Entity
@XmlRootElement()
@Table(name="Employee")
@SuppressWarnings("serial")
public class Employee implements Serializable{
	
@Id
@Column(name="pwId")	
private int pwId;	
@Column(name="password")	
private String password;
@Column(name="position")	
private String position;
@Column(name="name")	
private String name;
@Column(name="superId")	
private String superId;
@Column(name="phone")
private String phone;
@Column(name="headportrait")
private String headportrait;
public Employee() {
	super();
	// TODO Auto-generated constructor stub
}
public Employee(int pwId, String password, String position, String name, String superId, String phone,
		String headportrait) {
	super();
	this.pwId = pwId;
	this.password = password;
	this.position = position;
	this.name = name;
	this.superId = superId;
	this.phone = phone;
	this.headportrait = headportrait;
}
public int getPwId() {
	return pwId;
}
public void setPwId(int pwId) {
	this.pwId = pwId;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
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
public String getHeadportrait() {
	return headportrait;
}
public void setHeadportrait(String headportrait) {
	this.headportrait = headportrait;
}
@Override
public String toString() {
	return "Employee [pwId=" + pwId + ", password=" + password + ", position=" + position + ", name=" + name
			+ ", superId=" + superId + ", phone=" + phone + ", headportrait=" + headportrait + "]";
}

	
	
}
