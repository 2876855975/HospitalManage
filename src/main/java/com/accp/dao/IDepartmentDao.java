package com.accp.dao;

import java.util.List;

import com.accp.entity.Department;
import net.sf.jsqlparser.statement.delete.Delete;
import org.apache.ibatis.annotations.Param;


public interface IDepartmentDao {
	public abstract List<Department> getAll(@Param("chose") String chose,@Param("info")String info,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);
	public abstract List<Department> getAllDepartment(@Param("chose") String chose,@Param("info")String info);
	public abstract int deleteById(@Param("id") String id);
	
	public abstract List<Department> findName(@Param("info") String info,@Param("pageNum")  int pageNum,@Param("pageSize")  int pageSize);
	public abstract List<Department> findId(@Param("info") String info,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);
	public abstract Department findIds(@Param("cNumber") String d);
	
	public abstract void updateDepart(Department department);
	int addDepart(Department d);
	public abstract List<Department> Bing();
}
