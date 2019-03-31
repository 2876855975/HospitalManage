package com.accp.controller;

import com.accp.entity.Department;
import com.accp.entity.Page;
import com.accp.service.IDepartmentService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class DepartmentController {
	@Resource(name = "iDepartService")
	private IDepartmentService departmentService;

    @RequestMapping("/page")
    public String page(){
        return "page";
    }

    @RequestMapping("/addDepart")
    public String addDepart(Department d,Model model){

		d.setcNumber(StringEscapeUtils.escapeHtml4(d.getcNumber()));
		d.setdName(StringEscapeUtils.escapeHtml4(d.getdName()));
		d.setdDec(StringEscapeUtils.escapeHtml4(d.getdDec()));

    	if(null != d){
    		if((null != d.getcNumber() && !"".equals(d.getcNumber())) && (null != d.getdName() && !"".equals(d.getdName())) && (null != d.getdDec() && !"".equals(d.getdDec()))) {
				if (null == departmentService.findId(d.getcNumber())) {
					departmentService.addDepart(d);
				}else{
					model.addAttribute("errorMsg","科室编号已经被使用");
					return "page";
				}
			}else{
				model.addAttribute("errorMsg","请填写完整信息");
				return "page";
			}
		}
        return "redirect:/getDepart?chose=1&info=&pageNum=1&pageSize=2";
    }

	@RequestMapping(value = "/getDepart")
	public String getAll(@RequestParam("chose") String chose,@RequestParam("info")String info,@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,Model model) {
		pageSize = 6;
		Page<Department> list = departmentService.getAll(chose,info,pageNum,pageSize);
		model.addAttribute("PageInfo",list);
		if(chose==null){
			model.addAttribute("chose","1");
		}else{
			model.addAttribute("chose",chose);
		}
		if(info==null){
			model.addAttribute("info","");
		}else{
			model.addAttribute("info",info);
		}
		model.addAttribute("pageNum",pageNum);
		model.addAttribute("pageSize",pageSize);
		return "adv";
	}

	@RequestMapping(value = "/delByid")
	public String delById(@RequestParam("cNumber") String cNumber) {
		departmentService.delById(cNumber);
		return "redirect:/getDepart?chose=1&info=&pageNum=1&pageSize=2";
	}

	@RequestMapping(value = "find")
	public String findName(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,Model model,@RequestParam("info") String info,@RequestParam("chose") String chose) {
		// chose=1,按照科室搜索
		// 2姓名搜索
		pageSize = 6;
//		System.out.println(info + chose);
		if (chose.equals("1")) {
			Page<Department> list = departmentService.findId(chose,info,pageNum,pageSize);
			model.addAttribute("PageInfo",list);

			model.addAttribute("info",info);
			model.addAttribute("chose",chose);

			model.addAttribute("pageNum",pageNum);
			model.addAttribute("pageSize",pageSize);
			return "adv";
		}
		if (chose.equals("2")) {
			Page<Department> list = departmentService.findName(chose,info,pageNum,pageSize);
			model.addAttribute("PageInfo",list);
			model.addAttribute("info",info);
			model.addAttribute("chose",chose);

			model.addAttribute("pageNum",pageNum);
			model.addAttribute("pageSize",pageSize);
			return "adv";
		}
		return "404";
	}

	@RequestMapping("/updateByid")
	public String updateByid(Model model,@RequestParam("cNumber") String cNumber) {
		Department department = departmentService.findId(cNumber);
		model.addAttribute("department", department);
		return "updateDepartment";
	}

	@RequestMapping("update")
	public String update(Department department) {
		department.setdName(StringEscapeUtils.escapeHtml4(department.getdName()));
		department.setdDec(StringEscapeUtils.escapeHtml4(department.getdDec()));

		if(null != department) {
			if ((null != department.getcNumber() && !"".equals(department.getcNumber())) && (null != department.getdName() && !"".equals(department.getdName())) && (null != department.getdDec() && !"".equals(department.getdDec()))) {
				if(null != departmentService.findId(department.getcNumber())){
					departmentService.update(department);
				}
			}
		}
		return "redirect:/getDepart?chose=1&info=&pageNum=1&pageSize=2";
	}
}
