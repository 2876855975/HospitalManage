package com.accp.service.impl;

import com.accp.dao.IAdministorDao;
import com.accp.entity.*;
import com.accp.service.IAdministorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("iAdministorService")
public class IAdministorServiceImpl implements IAdministorService{

    @Resource
	private IAdministorDao administorDao;

    public Administor queryAdministor(String name,String pwd,String aNumber) {
        Administor a =new Administor();
        a.setName(name);
        a.setPwd(pwd);
        a.setaNumber(aNumber);
        return administorDao.queryAdministor(a);
    }

    public boolean deleteFeedBack(int id) {
        return administorDao.deleteFeedBack(id)==1?true:false;
    }

    public void updatePwd(Administor administor) {
    }

    public int addDepart(Department department) {
        return 0;
    }

    public int addDoctor(Doctor doctor) {
        return 0;
    }

    public List<OrderInfo> getAll(int pageNum, int pageSize) {
        return null;
    }

    public Page<Count> getCount(String chose,String info,int pageNum, int pageSize) {
        info="%"+info+"%";
        Page<Count> pager=new Page<Count>();
        pager.setPageIndex(pageNum);
        pager.setPageSize(pageSize);
        if(pageNum-1<1){
            pager.setPrePage(1);
        }else{
            pager.setPrePage(pageNum-1);
        }
        pager.setTotalRows(administorDao.getAllC(chose,info).size());
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
        pager.setDatas(administorDao.getCount(chose,info,(pageNum-1)*pageSize,pageSize));
        return pager;
    }

    public List<Feedback> getFeedBack(int pageNum, int pageSize) {
        return null;
    }

    public int addNew(New news) {
        return administorDao.addNew(news);
    }

    public Page<New> queryNew(int pageNum,int pageSize) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Page<New> pager=new Page<New>();
        pager.setPageIndex(pageNum);
        pager.setPageSize(pageSize);
        if(pageNum-1<1){
            pager.setPrePage(1);
        }else{
            pager.setPrePage(pageNum-1);
        }
        List<New> newse = administorDao.getAllNew();
        pager.setTotalRows(newse.size());
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
        List<New> news = new ArrayList<New>();
        try {
            while (true) {
                if(newse.size()==0){
                    break;
                }
                Date one = sdf.parse(sdf.format(sdf.parse(newse.get(newse.size()-1).getTime())));
                New temp1 = null;
                for (int j = 0;j<newse.size();j++){
                    if(newse.size() != 1){
                        Date two = sdf.parse(sdf.format(sdf.parse(newse.get(j).getTime())));
                        if(two.getTime() > one.getTime()) {
                            temp1 = newse.get(j);
                        }else if((two.getTime() < one.getTime()) && newse.size() == 2){
                            temp1 = newse.get(newse.size()-1);
                        }
                    }else{
                        temp1 = newse.get(0);
                    }
                    if(temp1 != null){
                        one = sdf.parse(sdf.format(sdf.parse(temp1.getTime()).getTime()));
                    }
                }
                if(null == temp1){
                    news.add(newse.get(newse.size()-1));
                    newse.remove(newse.get(newse.size()-1));
                }else{
                    newse.remove(temp1);
                    news.add(temp1);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        List<New> newsPage = new ArrayList<New>();
        for (int i = (pageNum - 1) * pageSize;i<pageSize*pageNum;i++){
            if(i>=news.size()){
                break;
            }
            newsPage.add(news.get(i));
        }
        pager.setDatas(newsPage);
        return pager;
    }

    public Page<Order> getAllOrder(String chose,String info,int pageNum, int pageSize) {
        info="%"+info+"%";
        Page<Order> pager=new Page<Order>();
        pager.setPageIndex(pageNum);
        pager.setPageSize(pageSize);
        if(pageNum-1<1){
            pager.setPrePage(1);
        }else{
            pager.setPrePage(pageNum-1);
        }
        pager.setTotalRows(administorDao.getAllO(chose,info).size());
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
        pager.setDatas(administorDao.getAllOrder(chose,info,(pageNum-1)*pageSize,pageSize));
        return pager;
    }

    public Page<Feedback> getFeedBackByPage(int pageNum, int pageSize) {
        Page<Feedback> pager=new Page<Feedback>();
        pager.setPageIndex(pageNum);
        pager.setPageSize(pageSize);
        if(pageNum-1<1){
            pager.setPrePage(1);
        }else{
            pager.setPrePage(pageNum-1);
        }
        pager.setTotalRows(administorDao.getFeedBack().size());
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
        pager.setDatas(administorDao.getFeedBackByPage((pageNum-1)*pageSize,pageSize));
        return pager;
    }

    public Page<New> getAllNew(int pageNum, int pageSize) {
        Page<New> pager=new Page<New>();
        pager.setPageIndex(pageNum);
        pager.setPageSize(pageSize);
        if(pageNum-1<1){
            pager.setPrePage(1);
        }else{
            pager.setPrePage(pageNum-1);
        }
        pager.setTotalRows(administorDao.getAllNew().size());
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
        pager.setDatas(administorDao.getAllNewByPage((pageNum-1)*pageSize,pageSize));
        return pager;
    }

    public List<New> getNews() {
        return administorDao.getAllNew();
    }
}
