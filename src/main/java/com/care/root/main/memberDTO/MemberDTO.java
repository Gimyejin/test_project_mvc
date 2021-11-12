package com.care.root.main.memberDTO;

import java.sql.Date;

public class MemberDTO {
	private String id;
	private String pwd;
	private String name;
	private String addr;
	private Date limDate;
	private String sessionId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Date getLimDate() {
		return limDate;
	}

	public void setLimDate(Date limDate) {
		this.limDate = limDate;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
