package com.accp.entity;

import java.util.Date;




/**
 * @author Administrator
 *
 */
public class Doctor {

	private String dNumber;
	private String name;
	private String dPwd;
	private Department department;
	private String dInfo;
	private String dResume;
	private String dTel;
	private String dEmail;
	private String image;
	public String getdNumber() {
		return dNumber;
	}
	public void setdNumber(String dNumber) {
		this.dNumber = dNumber;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public String getdPwd() {
		return dPwd;
	}
	public void setdPwd(String dPwd) {
		this.dPwd = dPwd;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getdInfo() {
		return dInfo;
	}
	public void setdInfo(String dInfo) {
		this.dInfo = dInfo;
	}
	public String getdResume() {
		return dResume;
	}
	public void setdResume(String dResume) {
		this.dResume = dResume;
	}
	public String getdTel() {
		return dTel;
	}
	public void setdTel(String dTel) {
		this.dTel = dTel;
	}
	public String getdEmail() {
		return dEmail;
	}
	public void setdEmail(String dEmail) {
		this.dEmail = dEmail;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public Doctor() {
	}
	public Doctor(String dNumber, String dPwd) {
		super();
		this.dNumber = dNumber;
		this.dPwd = dPwd;
	}

}
