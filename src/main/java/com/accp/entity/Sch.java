package com.accp.entity;

public class Sch {
	private String  sNumber;
	private Doctor doctor;
	private String  cNumber;
	private Integer total;
	private  String price;
	private String sTime;
	private String eTime;

	public String getsNumber() {
		return sNumber;
	}
	public void setsNumber(String sNumber) {
		this.sNumber = sNumber;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getcNumber() {
		return cNumber;
	}
	public void setcNumber(String cNumber) {
		this.cNumber = cNumber;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getsTime() {
		return sTime;
	}
	public void setsTime(String sTime) {
		this.sTime = sTime;
	}
	public String geteTime() {
		return eTime;
	}
	public void seteTime(String eTime) {
		this.eTime = eTime;
	}

	
	
}
