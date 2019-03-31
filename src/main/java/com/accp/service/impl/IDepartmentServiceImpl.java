package com.accp.service.impl;

import com.accp.dao.IDepartmentDao;
import com.accp.entity.Department;
import com.accp.entity.New;
import com.accp.entity.Page;
import com.accp.service.IDepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("iDepartService")
public class IDepartmentServiceImpl  implements IDepartmentService{
	@Resource
	private IDepartmentDao departmentDao;

    public Page<Department> getAll(String chose,String info,int pageNum, int pageSize) {
        info="%"+info+"%";
        Page<Department> pager=new Page<Department>();
        pager.setPageIndex(pageNum);
        pager.setPageSize(pageSize);
        if(pageNum-1<1){
            pager.setPrePage(1);
        }else{
            pager.setPrePage(pageNum-1);
        }
        pager.setTotalRows(departmentDao.getAllDepartment(chose,info).size());
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
        pager.setDatas(departmentDao.getAll(chose,info,(pageNum-1)*pageSize,pageSize));
        return pager;
    }

    public List<Department> getAll() {
        return departmentDao.Bing();
    }

    public int delById(String id) {
        return departmentDao.deleteById(id);
    }

    public Page<Department> findName(String chose,String info, int pageNum, int pageSize) {
        info="%"+info+"%";
        Page<Department> pager=new Page<Department>();
        pager.setPageIndex(pageNum);
        pager.setPageSize(pageSize);
        if(pageNum-1<1){
            pager.setPrePage(1);
        }else{
            pager.setPrePage(pageNum-1);
        }
        pager.setTotalRows(departmentDao.getAllDepartment(chose,info).size());
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
        pager.setDatas(departmentDao.findName(info,(pageNum-1)*pageSize,pageSize));
        return pager;
    }

    public Page<Department> findId(String chose,String info, int pageNum, int pageSize) {
        info="%"+info+"%";
        Page<Department> pager=new Page<Department>();
        pager.setPageIndex(pageNum);
        pager.setPageSize(pageSize);
        if(pageNum-1<1){
            pager.setPrePage(1);
        }else{
            pager.setPrePage(pageNum-1);
        }
        pager.setTotalRows(departmentDao.getAllDepartment(chose,info).size());
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
        pager.setDatas(departmentDao.findId(info,(pageNum-1)*pageSize,pageSize));
        return pager;
    }

    public Department findId(String d) {
        return departmentDao.findIds(d);
    }

    public void update(Department department) {
        departmentDao.updateDepart(department);
    }

    public int addDepart(Department d) {
        return departmentDao.addDepart(d);
    }
}
