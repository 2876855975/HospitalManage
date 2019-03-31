package com.accp.service.impl;

import com.accp.dao.IDoctorDao;
import com.accp.entity.*;
import com.accp.service.IDoctorService;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("iDoctorService")
public class IDoctorServiceImpl  implements IDoctorService{
    @Resource
	private IDoctorDao doctorDao;

    public Doctor queryDoctorByEmail(String email) {
        List<Doctor> doctors = doctorDao.queryDoctorE(email);
        if(doctors.size()>0){
            return doctors.get(0);
        }else{
            return null;
        }
    }

    public Doctor queryDoctor(String dNumber, String pwd) {
        Doctor d=new Doctor(dNumber,pwd);
        return doctorDao.queryDoctor(d);
    }

    public Page<Doctor> selectAll(String chose,String info,int pageNum, int pageSize) {
        info="%"+info+"%";
        Page<Doctor> pager=new Page<Doctor>();
        pager.setPageIndex(pageNum);
        pager.setPageSize(pageSize);
        if(pageNum-1<1){
            pager.setPrePage(1);
        }else{
            pager.setPrePage(pageNum-1);
        }

        pager.setTotalRows(doctorDao.queryAll(chose,info).size());
        int[] a=new int[(pager.getTotalRows()+pageSize-1)/pageSize];
        for(int i=0;i<a.length;i++){
            a[i]=i+1;
        }
        pager.setTotalPage(a);
        if(pageNum+1>pager.getTotalPage().length){
            pager.setLastPage(pager.getTotalPage().length);
        }else{
            pager.setLastPage(pageNum+1);
        }
        pager.setDatas(doctorDao.selectAll(chose,info,(pageNum-1)*pageSize,pageSize));
        return pager;
    }

    public int deleteById(String number) {
        return doctorDao.deleteById(number);
    }

    public int addDoctor(Doctor doctor) {
        return doctorDao.addDoctor(doctor);
    }

    public int update(Doctor doctor) {
        return doctorDao.updateDoctor(doctor);
    }

    public Doctor getById(String id) {
        return doctorDao.getById(id);
    }

    public List<DoctorInfo> findName(String info, int pageNum, int pageSize) {
        return null;
    }

    public Page<Doctor> findId(String chose,String info, int pageNum, int pageSize) {
        info="%"+info+"%";
        Page<Doctor> pager=new Page<Doctor>();
        pager.setPageIndex(pageNum);
        pager.setPageSize(pageSize);
        if(pageNum-1<1){
            pager.setPrePage(1);
        }else{
            pager.setPrePage(pageNum-1);
        }

        pager.setTotalRows(doctorDao.queryAll(chose,info).size());
        int[] a=new int[(pager.getTotalRows()+pageSize-1)/pageSize];
        for(int i=0;i<a.length;i++){
            a[i]=i+1;
        }
        pager.setTotalPage(a);
        if(pageNum+1>pager.getTotalPage().length){
            pager.setLastPage(pager.getTotalPage().length);
        }else{
            pager.setLastPage(pageNum+1);
        }
        pager.setDatas(doctorDao.findId(info,(pageNum-1)*pageSize,pageSize));
        return pager;
    }

    public List<DoctorInfo> findDepartName(String info, int pageNum, int pageSize) {
        return null;
    }

    public int insertSch(Sch sch) {
        return doctorDao.insertSch(sch);
    }

    public Doctor getD(String dNumber) {
        return doctorDao.getD(dNumber);
    }

    public Page<Sch> getSInfo(String dNumber, int pageNum, int pageSize) {
        Page<Sch> pager=new Page<Sch>();
        pager.setPageIndex(pageNum);
        pager.setPageSize(pageSize);
        if(pageNum-1<1){
            pager.setPrePage(1);
        }else{
            pager.setPrePage(pageNum-1);
        }
        pager.setTotalRows(doctorDao.getSInfo(dNumber).size());
        int[] a=new int[(pager.getTotalRows()+pageSize-1)/pageSize];
        for(int i=0;i<a.length;i++){
            a[i]=i+1;
        }
        pager.setTotalPage(a);
        if(pageNum+1>pager.getTotalPage().length){
            pager.setLastPage(pager.getTotalPage().length);
        }else{
            pager.setLastPage(pageNum+1);
        }
        pager.setDatas(doctorDao.getSInfoByPage(dNumber,(pageNum-1)*pageSize,pageSize));
        return pager;
    }

    public Page<Order> getOrderByDoctor(String sNumber, int pageNum, int pageSize) {
        Page<Order> pager=new Page<Order>();
        pager.setPageIndex(pageNum);
        pager.setPageSize(pageSize);
        if(pageNum-1<1){
            pager.setPrePage(1);
        }else{
            pager.setPrePage(pageNum-1);
        }

        pager.setTotalRows(doctorDao.queryAllOrder(sNumber).size());
        int[] a=new int[(pager.getTotalRows()+pageSize-1)/pageSize];
        for(int i=0;i<a.length;i++){
            a[i]=i+1;
        }
        pager.setTotalPage(a);
        if(pageNum+1>pager.getTotalPage().length){
            pager.setLastPage(pager.getTotalPage().length);
        }else{
            pager.setLastPage(pageNum+1);
        }
        pager.setDatas(doctorDao.queryAllOrderByPage(sNumber,(pageNum-1)*pageSize,pageSize));
        return pager;
    }

    public int queryMaxSche() {
        return doctorDao.queryMaxSche();
    }

    public List<DoctorInfoJson> JsonDoctor(String dNumber, String cNumber) {
        return null;
    }

    public List<Jsondoctor> getAllJsonDoctor(String cNumber) {
        return null;
    }

    public List<Jsondoctor> JsonDoctorByName(String name) {
        return null;
    }

    public List<QRUser> queryQrUsers(QRUser qrUser) {
        return doctorDao.queryQrUser(qrUser);
    }
    public QRUser queryQrUser(QRUser qrUser) {
        List<QRUser> qrUsers = doctorDao.queryQrUser(qrUser);
        if(qrUsers.size() > 0){
            return qrUsers.get(0);
        }
        return null;
    }

    public int deleteQrUser(QRUser qrUser) {
        return doctorDao.deleteQrUser(qrUser);
    }

    public int insertQrUser(QRUser qrUser) {
        QRUser qrU = queryQrUser(qrUser);
        if(null != qrU){
            deleteQrUser(qrU);
        }
        return doctorDao.insertQrUser(qrUser);
    }
}
