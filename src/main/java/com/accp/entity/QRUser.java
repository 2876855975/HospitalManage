package com.accp.entity;

/**
 * @author Administrator
 *
 */
public class QRUser {
	private String uuid;
	private String dNumber;

	public QRUser() {
	}

	public QRUser(String uuid,String dNumber) {
		this.uuid = uuid;
		this.dNumber = dNumber;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getdNumber() {
		return dNumber;
	}

	public void setdNumber(String dNumber) {
		this.dNumber = dNumber;
	}
}
