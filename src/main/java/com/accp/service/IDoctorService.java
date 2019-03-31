package com.accp.service;

import com.accp.entity.*;

import java.util.List;



public interface IDoctorService {
	
	public abstract Doctor queryDoctor(String dNumber, String pwd);
//	public abstract Doctor queryDoctor(String dNumber, String pwd);

	public abstract Page<Doctor> selectAll(String chose,String info,int pageNum, int pageSize);


	public abstract Doctor queryDoctorByEmail(String email);
	public abstract int deleteById(String number);

	public abstract int addDoctor(Doctor doctor);//

	public abstract int update(Doctor doctor);// 
	
	public abstract Doctor getById(String id);
	
	public abstract List<DoctorInfo> findName(String info, int pageNum, int pageSize);
	public abstract Page<Doctor> findId(String chose,String info, int pageNum, int pageSize);
	public abstract List<DoctorInfo> findDepartName(String info, int pageNum, int pageSize);
	int queryMaxSche();
	public abstract int insertSch(Sch sch);
	public abstract Doctor getD(String dNumber);
	public abstract Page<Sch> getSInfo(String sNumber, int pageNum, int pageSize);
	
	public abstract Page<Order> getOrderByDoctor(String sNumber, int pageNum, int pageSize);
	
	public abstract List<DoctorInfoJson> JsonDoctor(String dNumber, String cNumber);
	
	public List<Jsondoctor> getAllJsonDoctor(String cNumber);
	
	public abstract List<Jsondoctor> JsonDoctorByName(String name);
	List<QRUser> queryQrUsers(QRUser qrUser);
	QRUser queryQrUser(QRUser qrUser);
	int deleteQrUser(QRUser qrUser);
	int insertQrUser(QRUser qrUser);
}
