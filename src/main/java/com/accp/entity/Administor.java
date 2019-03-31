package com.accp.entity;


/**
 * @author Administrator
 *
 */
public class Administor {


	private String aNumber;
	private String name;
	private String pwd;
	private int status;

	@Override
	public String toString() {
		return super.toString();
	}

	public String getaNumber() {
		return aNumber;
	}

	public void setaNumber(String aNumber) {
		this.aNumber = aNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Administor(String aNumber, String name, String pwd) {
		super();
		this.aNumber = aNumber;
		this.name = name;
		this.pwd = pwd;
	}

public Administor() {
}
}
