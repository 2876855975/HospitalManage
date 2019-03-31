package com.accp.dao;

import com.accp.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IAdministorDao {
  Administor queryAdministor(Administor administor);
  
  public abstract void updatePwd(Administor administor);
  
  public abstract int addDepart(Department department);

  public abstract int addDoctor(Doctor doctor);
  
  public abstract List<OrderInfo> getAll(int pageNum, int pageSize);
  
  public abstract List<Count> getCount(@Param("chose")String chose,@Param("info")String info,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);
  
  public abstract List<Feedback> getFeedBack();
  
  public abstract int addNew(New news);
  
  public abstract List<New> getAllNew();
  List<Count> getAllC(@Param("chose")String chose,@Param("info")String info);

  List<New> queryNew(@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);

  List<Order> getAllO(@Param("chose")String chose,@Param("info")String info);
  
  
  public abstract List<Order> getAllOrder(@Param("chose")String chose,@Param("info")String info,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);
  
  public abstract List<Order> getAllOrderByPage(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize);
    public abstract List<Count> getCountByPage(int pageNum, int pageSize);
      public abstract List<Feedback> getFeedBackByPage(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize);
      int deleteFeedBack(int id);
        public abstract List<New> getAllNewByPage(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize);




}
