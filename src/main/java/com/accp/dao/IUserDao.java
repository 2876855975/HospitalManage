package com.accp.dao;

import com.accp.entity.Feedback;
import com.accp.entity.Order;
import com.accp.entity.OrderInfo;
import com.accp.entity.User;

import java.util.List;



public interface IUserDao {
	public abstract List<User> selectAll();//

	public abstract int deleteById(int id);//

	public abstract int register(User users);
	public abstract int update(User user);//
	
	public abstract List<User> userLogin(User user); 
	public abstract int updateUser(User user);
	
	public abstract int insertOrder(Order order);
	public abstract List<OrderInfo> getOrderById(Integer id);
	
	public abstract int insertFeedback(Feedback feedback);

	public abstract int deleteOrderById(Integer oNumber);
}
