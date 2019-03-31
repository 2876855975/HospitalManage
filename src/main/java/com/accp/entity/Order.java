package com.accp.entity;

import java.util.Date;


/**
 * @author Administrator
 *
 */
public class Order {
	private Integer oNumber;
	private Sch sch;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private String oTime;
	private String price;
	private Integer status;
	public Integer getoNumber() {
		return oNumber;
	}
	public void setoNumber(Integer oNumber) {
		this.oNumber = oNumber;
	}

	public Sch getSch() {
		return sch;
	}

	public void setSch(Sch sch) {
		this.sch = sch;
	}

	public String getoTime() {
		return oTime;
	}
	public void setoTime(String oTime) {
		this.oTime = oTime;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStatus() {
		return status;
	}
	
}
