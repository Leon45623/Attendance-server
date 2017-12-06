package com.scb.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType
public class AcceptOneVo implements Serializable {
	private String msg;

	public AcceptOneVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AcceptOneVo(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "AcceptOneVo [msg=" + msg + "]";
	}
	
	

}
