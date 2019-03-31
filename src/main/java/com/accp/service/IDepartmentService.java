package com.accp.service;

import com.accp.entity.Department;
import com.accp.entity.Page;

import java.util.List;



public interface IDepartmentService {
	public abstract Page<Department> getAll(String chose,String info,int pageNum, int pageSize);


	public abstract List<Department> getAll();
	public abstract int delById(String id);
	
	public abstract Page<Department> findName(String chose,String info, int pageNum, int pageSize);
	public abstract Page<Department> findId(String chose,String info, int pageNum, int pageSize);
	public abstract Department findId(String cNumber);
	public abstract void update(Department department);
	int addDepart(Department d);
}
