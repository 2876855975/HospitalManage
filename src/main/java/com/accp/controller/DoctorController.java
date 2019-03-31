package com.accp.controller;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Pattern;

import com.accp.entity.*;
import com.accp.service.IAdministorService;
import com.accp.service.IDepartmentService;
import com.accp.service.IDoctorService;
import com.accp.util.ValiEmail;


import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DoctorController {
	@Resource(name = "iDoctorService")
	private IDoctorService doctorService;
	@Resource(name = "iAdministorService")
	private IAdministorService administorService;
	@Resource(name = "iDepartService")
	private IDepartmentService departmentService;

	@RequestMapping(value = "/getAllDoctor")
	public  String selectAll(@RequestParam("chose") String chose,@RequestParam("info")String info,@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,Model model) {
		pageSize = 6;
		Page<Doctor> list = doctorService.selectAll(chose,info,pageNum,pageSize);
		model.addAttribute("PageInfo",list);
		model.addAttribute("chose",chose);
		model.addAttribute("info",info);
		model.addAttribute("pageNum",pageNum);
		model.addAttribute("pageSize",pageSize);
		return "allDoctor";
	}

	@RequestMapping(value="getById")
	public String getById(@RequestParam("dNumber") String dNumber,Model model){
		Doctor doctor = doctorService.getById(dNumber);
		List<Department> list = departmentService.getAll();
		model.addAttribute("department",list);
		model.addAttribute("doctor", doctor);
		return "updateDoctor";
	}

	@RequestMapping(value = "deletedoc")
	public String delete(@RequestParam("dNumber") String dNumber) {
		doctorService.deleteById(dNumber);
		return "redirect:/getAllDoctor?chose=3&info=&pageNum=1&pageSize=2";
	}

	@RequestMapping(value = "addDoctor",method = RequestMethod.POST)
	public String register(Doctor d, Model model, HttpServletRequest req) throws Exception {

		d.setdNumber(StringEscapeUtils.escapeHtml4(d.getdNumber()));
		d.setName(StringEscapeUtils.escapeHtml4(d.getName()));
		d.setdInfo(StringEscapeUtils.escapeHtml4(d.getdInfo()));
		d.setdResume(StringEscapeUtils.escapeHtml4(d.getdResume()));


		int a = 0;
		if(null != d){
			if((null != d.getdNumber() && !"".equals(d.getdNumber())) &&
					(null != d.getDepartment()) && (null != d.getdPwd() && !"".equals(d.getdPwd())) &&
					(null != d.getdEmail() && !"".equals(d.getdEmail())) &&
					(null != d.getdInfo() && !"".equals(d.getdInfo())) &&
					(null != d.getdResume() && !"".equals(d.getdResume())) &&
					(null != d.getdTel() && !"".equals(d.getdTel())) &&
					(null != d.getName() && !"".equals(d.getName()))){
				if(null == doctorService.getById(d.getdNumber()) && null == administorService.queryAdministor(null, null, d.getdNumber())){
					Pattern compile = Pattern.compile("[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+");
					if(compile.matcher(d.getdEmail()).matches()){
						if(ValiEmail.checkValiEmail(new ValiEmail(null, InetAddress.getLocalHost().getHostAddress(),d.getdEmail(),req.getParameter("code"),null))){
							a = doctorService.addDoctor(d);
						}else{
							model.addAttribute("errorEmail","邮箱验证码不正确！");
						}
					}else{
						model.addAttribute("errorEmail","邮箱格式不正确！");
					}
				}else{
					model.addAttribute("errordNumber","工号已被使用！");
				}
				model.addAttribute("edNumber",d.getdNumber());
				model.addAttribute("ecNumber",d.getDepartment().getcNumber());
				model.addAttribute("edInfo",d.getdInfo());
				model.addAttribute("edResume",d.getdResume());
				model.addAttribute("edTel",d.getdTel());
				model.addAttribute("eName",d.getName());
			}
		}
		if (a == 1) {
			return "redirect:/getAllDoctor?chose=3&info=&pageNum=1&pageSize=2";
		}
		return getName(model);
	}

	@RequestMapping(value = "updateDoctor",method = RequestMethod.POST)
	public String update(Doctor d,Model model,HttpServletRequest req) throws Exception {
		d.setName(StringEscapeUtils.escapeHtml4(d.getName()));
		d.setdInfo(StringEscapeUtils.escapeHtml4(d.getdInfo()));
		d.setdResume(StringEscapeUtils.escapeHtml4(d.getdResume()));
		int a = 0;
		if(null != d){
			if((null != d.getdNumber() && !"".equals(d.getdNumber())) &&
					(null != d.getDepartment()) &&
					(null != d.getdEmail() && !"".equals(d.getdEmail())) &&
					(null != d.getdInfo() && !"".equals(d.getdInfo())) &&
					(null != d.getdResume() && !"".equals(d.getdResume())) &&
					(null != d.getdTel() && !"".equals(d.getdTel())) &&
					(null != d.getName() && !"".equals(d.getName()))){
				Doctor di = doctorService.getById(d.getdNumber());
				if(null != di){
					Pattern compile = Pattern.compile("[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+");
					if(compile.matcher(d.getdEmail()).matches()){
						if(ValiEmail.checkValiEmail(new ValiEmail(null, InetAddress.getLocalHost().getHostAddress(),d.getdEmail(),req.getParameter("code"),null))){
							d.setdNumber(di.getdNumber());
							d.setdPwd(di.getdPwd());
							a = doctorService.update(d);
						}else{
							model.addAttribute("errorEmail","邮箱验证码不正确！");
						}
					}else{
						return "redirect:/getAllDoctor?chose=3&info=&pageNum=1&pageSize=2";
					}
				}else{
					return "redirect:/getAllDoctor?chose=3&info=&pageNum=1&pageSize=2";
				}
			}else{
				return "redirect:/getAllDoctor?chose=3&info=&pageNum=1&pageSize=2";
			}
		}
		if (a == 1) {
			return "redirect:/getAllDoctor?chose=3&info=&pageNum=1&pageSize=2";
		}
		return getById(d.getdNumber(),model);
	}

	@RequestMapping("modifyDoctor")
	public String modifyDoctor(Doctor d,HttpSession session,Model model,HttpServletRequest req) throws  Exception{
		if(null != d) {
			if ((null != d.getdNumber() && !"".equals(d.getdNumber())) &&
				    (null != d.getdPwd() && !"".equals(d.getdPwd())) &&
					(null != d.getDepartment()) &&
					(null != d.getdInfo() && !"".equals(d.getdInfo())) &&
					(null != d.getdResume() && !"".equals(d.getdResume())) &&
					(null != d.getdTel() && !"".equals(d.getdTel())) &&
					(null != d.getName() && !"".equals(d.getName()))) {
				Doctor di = doctorService.getById(d.getdNumber());
				if(null != di && null != session.getAttribute("user") && null == administorService.queryAdministor(null, null, d.getdNumber())){
					if (ValiEmail.checkValiEmail(new ValiEmail(null, InetAddress.getLocalHost().getHostAddress(), di.getdEmail(), req.getParameter("code"), null))) {
						d.setdNumber(di.getdNumber());
						d.setDepartment(di.getDepartment());
						d.setdEmail(di.getdEmail());
						doctorService.update(d);
						session.invalidate();
						model.addAttribute("succ", "1");
					} else {
						model.addAttribute("errorEmail", "邮箱验证码不正确！");
					}
				}else{
					model.addAttribute("errorMsg","医生工号已被使用！");
				}
			}else{
				model.addAttribute("errorMsg","请填写完整！");
			}
		}
		return getByDoctor(d.getdNumber(),model,req);
	}

	@RequestMapping("modifyDoctorEmail")
	@ResponseBody
	public String modifyDoctorEmail(Model model,HttpServletRequest request) throws Exception{
		String email = request.getParameter("email");
		String code = request.getParameter("code");
		if((null != email && !"".equals(email)) && (null != code && !"".equals(code))){
			Doctor user = (Doctor) request.getSession().getAttribute("user");
			if(null != user){
				Pattern compile = Pattern.compile("[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+");
				if(compile.matcher(email).matches() && compile.matcher(user.getdEmail()).matches()) {
					if (ValiEmail.checkValiEmail(new ValiEmail(null, InetAddress.getLocalHost().getHostAddress(), user.getdEmail(), code, null))) {
						Doctor d = doctorService.getById(user.getdNumber());
						d.setdEmail(email);
						doctorService.update(d);
						request.getSession().invalidate();
						request.getSession().setAttribute("user",d);
						return "{\"status\":\"1\"}";
					}
				}
			}
		}
		return "{\"status\":\"0\"}";
	}

	@RequestMapping(value="getByDoctor")
	public String getByDoctor(String dNumber,Model model,HttpServletRequest request){
		Doctor user = (Doctor) request.getSession().getAttribute("user");
		if(null != user){
			model.addAttribute("doctor", doctorService.getById(user.getdNumber()));
			if(null != doctorService.queryQrUser(new QRUser(null, user.getdNumber()))){
				model.addAttribute("isQRUser","已启用");
			}else{
				model.addAttribute("isQRUser","未启用");
			}
		}else if(null != dNumber && !"".equals(dNumber)){
			Doctor d = doctorService.getById(dNumber);
			if(null != d){
				model.addAttribute("doctor", d);
				if(null != doctorService.queryQrUser(new QRUser(null, d.getdNumber()))){
					model.addAttribute("isQRUser","已启用");
				}else{
					model.addAttribute("isQRUser","未启用");
				}
			}else{
				return "404";
			}
		}else{
			return "404";
		}
		return "getByDoctorInfo";
	}

	@RequestMapping(value = "findDoctor")
	public String findDoctor(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,Model model,@RequestParam("info") String info,@RequestParam("chose") String chose) {
		// chose=1,按照科室搜索
		// 2姓名搜索
		System.out.println(info + chose);
		if (chose.equals("1")) {
			Page<Doctor> list = doctorService.findId(chose,info,pageNum,pageSize);
			model.addAttribute("PageInfo",list);
			return "allDoctor";
		}
		if (chose.equals("2")) {
			List<DoctorInfo> dInfos = doctorService.findName(info,pageNum,pageSize);
			PageInfo<DoctorInfo> list = new PageInfo<DoctorInfo>(dInfos);
			model.addAttribute("PageInfo",list);
			return "allDoctor";
		}
		if(chose.equals("3")){
			List<DoctorInfo> dInfos = doctorService.findDepartName(info,pageNum,pageSize);
			PageInfo<DoctorInfo> list = new PageInfo<DoctorInfo>(dInfos);
			model.addAttribute("PageInfo",list);
			return "allDoctor";
		}
		return "404";
	}
	@RequestMapping("getName")
	public String getName(Model m){
		List<Department> list = departmentService.getAll();
		m.addAttribute("department",list);
		return "info";
	}

	@RequestMapping(value = "getD")
	public String getDe(Model model,HttpServletRequest request){
		Doctor user = (Doctor) request.getSession().getAttribute("user");
		if(user != null){
			model.addAttribute("department", doctorService.getD(user.getdNumber()));
			return "total";
		}else{
			return "404";
		}
	}

	@RequestMapping(value="addSch")
	public String addSch(Sch sch,HttpSession session) throws  Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if (null != sch) {
			if((null != sch.getsTime() && !"".equals(sch.getsTime())) &&
					(null != sch.geteTime() && !"".equals(sch.geteTime())) &&
					(null != sch.getPrice() && !"".equals(sch.getPrice())) && (0 != sch.getTotal())) {
				if(sdf.parse(sch.getsTime()).getTime() < sdf.parse(sch.geteTime()).getTime()){
					Doctor d=(Doctor)session.getAttribute("user");
					if(null != d){
						Integer i=doctorService.queryMaxSche()+1;
						sch.setsNumber(i.toString());
						sch.setDoctor(d);
						sch.setcNumber(d.getDepartment().getcNumber());
						doctorService.insertSch(sch);
					}else{
						return "redirect:/getAllNew?pageNum=1&pageSize=3";
					}
				}else{
					return "redirect:/getAllNew?pageNum=1&pageSize=3";
				}
			}else{
				return "redirect:/getAllNew?pageNum=1&pageSize=3";
			}
		}
		return "redirect:/getS?pageNum=1";
	}

	@RequestMapping(value="getS")
	public String getSInfo(@RequestParam("pageNum") int pageNum,Model model,HttpServletRequest request){
		Doctor user = (Doctor) request.getSession().getAttribute("user");
		if(null != user){
			Page<Sch> list = doctorService.getSInfo(user.getdNumber(),pageNum,6);
			model.addAttribute("PageInfo",list);
			return "success";
		}else{
			return "404";
		}
	}

	@RequestMapping("/getOrederByDoctor")
	public String getOrederByDoctor(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,Model model,HttpServletRequest request){
		pageSize = 6;
		Page<Order> list = doctorService.getOrderByDoctor(((Doctor)request.getSession().getAttribute("user")).getdNumber(), pageNum, pageSize);
		model.addAttribute("PageInfo",list);
		return "orderByDoctor";
	}
}
