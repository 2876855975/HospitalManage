package com.accp.dao;

import com.accp.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;



public interface IDoctorDao {
	
	public abstract Doctor queryDoctor(Doctor doctor);
	public abstract List<Doctor> queryDoctorE(String email);

	public abstract List<DoctorInfo> queryAll(@Param("chose") String chose,@Param("info") String info);//

	public abstract List<Doctor> selectAll(@Param("chose") String chose,@Param("info") String info,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);//

	public abstract int deleteById(@Param("dNumber") String number);//

	public abstract int addDoctor(Doctor doctor);//

	public abstract int updateDoctor(Doctor doctor);//
	
	public abstract Doctor getById(@Param("id") String id);
	
	public abstract List<DoctorInfo> findName(String info, int pageNum, int pageSize);
	public abstract List<Doctor> findId(@Param("info") String info,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);
	public abstract List<DoctorInfo> findDepartName(String info, int pageNum, int pageSize);
	
	public abstract int insertSch(Sch sch);
	
	public abstract Doctor getD(@Param("dNumber") String dNumber);
	
	public abstract List<Sch> getSInfo(@Param("dNumber") String dNumber);
	public abstract List<Sch> getSInfoByPage(@Param("dNumber") String dNumber,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);

	int queryMaxSche();

	List<Order> queryAllOrder(@Param("sNumber") String sNumber);

	List<Order> queryAllOrderByPage(@Param("sNumber") String sNumber,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);

	public abstract List<OrderInfo> getOrderByDoctor(String sNumber, int pageNum, int pageSize);
	
	
	//json
	public abstract List<DoctorInfoJson> getJsonDoctor(DoctorInfoJson doctorInfoJson);
	
	public abstract List<Jsondoctor> getAllJsonDoctor(String cNumber);
	public abstract List<Jsondoctor> getJsonDoctorByName(String name);

	List<QRUser> queryQrUser(QRUser qrUser);
	int deleteQrUser(QRUser qrUser);
	int insertQrUser(QRUser qrUser);
}
