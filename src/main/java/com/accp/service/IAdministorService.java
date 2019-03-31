package com.accp.service;

import com.accp.entity.*;

import java.util.List;




public interface IAdministorService {
   public abstract Administor queryAdministor(String name,String pwd,String aNumber);
   
   public abstract void updatePwd(Administor administor);
   
   public abstract int addDepart(Department department);

   public abstract int addDoctor(Doctor doctor);
   
   public abstract List<OrderInfo> getAll(int pageNum, int pageSize);
   
   public abstract Page<Count> getCount(String chose,String info, int pageNum, int pageSize);
   
   public abstract List<Feedback> getFeedBack(int pageNum, int pageSize);

   boolean deleteFeedBack(int id);
   
   public abstract int addNew(New news);
   
   public abstract Page<New> queryNew(int pageNum,int pageSize);
   
      public abstract Page<Order> getAllOrder(String chose,String info,int pageNum, int pageSize);
   public abstract Page<Feedback> getFeedBackByPage(int pageNum, int pageSize);
      public abstract Page<New> getAllNew(int pageNum, int pageSize);
     List<New> getNews();


}
