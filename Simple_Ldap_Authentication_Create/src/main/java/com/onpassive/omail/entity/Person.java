package com.onpassive.omail.entity;

public class Person {
	
	private String sn;
	private String cn;
	private String userName;
	private String userPassword;
	private String uid;
	private int uidNumber;
	private int gidNumber;
	private String homeDirectory;

	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public int getUidNumber() {
		return uidNumber;
	}
	public void setUidNumber(int uidNumber) {
		this.uidNumber = uidNumber;
	}
	public int getGidNumber() {
		return gidNumber;
	}
	public void setGidNumber(int gidNumber) {
		this.gidNumber = gidNumber;
	}
	public String getHomeDirectory() {
		return homeDirectory;
	}
	public void setHomeDirectory(String homeDirectory) {
		this.homeDirectory = homeDirectory;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "Person [sn=" + sn + ", cn=" + cn + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", uid=" + uid + ", uidNumber=" + uidNumber + ", gidNumber=" + gidNumber + ", homeDirectory="
				+ homeDirectory + "]";
	}
	
	
}
