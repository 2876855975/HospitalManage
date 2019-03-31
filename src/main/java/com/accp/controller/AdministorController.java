package com.accp.controller;

import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.accp.entity.*;
import com.accp.service.IAdministorService;
import com.accp.service.IDoctorService;
import com.accp.util.LoginSession;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;

@Controller
public class AdministorController {
	@Resource(name = "iAdministorService")
	private IAdministorService administorService;
	@Resource(name = "iDoctorService")
	private IDoctorService doctorService;

	@RequestMapping(value = "/")
	public String toLogin(){
		return "login";
	}
	@RequestMapping(value = "/Erlogin")
	public String erlogin(){
		return "erlogin";
	}
	@RequestMapping(value = "/Exit")
	public String exit(HttpServletRequest request){
		new LoginSession().removeLoginUser(request.getSession(),request.getRemoteAddr());
		request.getSession().invalidate();
		return "redirect:/";
	}
	@RequestMapping(value = "/dindex")
	public String dindex(){
		return "dindex";
	}

	@RequestMapping(value = "/doLogin")
	public String login(String name, String pwd, String status,String code,HttpSession session,Model model,HttpServletRequest request) throws Exception {
		if(null == session.getAttribute("user")){
			if(null != name && !"".equals(name)){
				model.addAttribute("name",name);
			}
			if((null == name || "".equals(name)) && (null == pwd || "".equals(pwd)) && (null == code || "".equals(code))){
				model.addAttribute("error4","1");
				model.addAttribute("error2","1");
				model.addAttribute("error5","1");
			}else if((null == pwd || "".equals(pwd)) && (null == code || "".equals(code))){
				model.addAttribute("error2","1");
				model.addAttribute("error5","1");
			}else if((null == name || "".equals(name)) && (null == code || "".equals(code))){
				model.addAttribute("error4","1");
				model.addAttribute("error5","1");
			}else if((null == name || "".equals(name)) && (null == pwd || "".equals(pwd))){
				model.addAttribute("error4","1");
				model.addAttribute("error2","1");
			}else if(null == name || "".equals(name)){
				model.addAttribute("error4","1");
			}else if(null == pwd || "".equals(pwd)){
				model.addAttribute("error2","1");
			}else if(null == code || "".equals(code)){
				model.addAttribute("error5","1");
			}else {
				if (status != null && code != null && !"".equals(code)) {
					Object verCode = session.getAttribute("verCode");
					if(null == verCode){
						model.addAttribute("error5", "2");
					}else {
						if (code.trim().toLowerCase().equals(verCode.toString().trim().toLowerCase())) {
							if ("1".equals(status)) {
								Administor a = administorService.queryAdministor(name, pwd, null);
								if (a != null) {
									if (!LoginSession.isExistsLogin("", a.getaNumber(), session, request.getRemoteAddr())) {
										new LoginSession("", a.getaNumber(), session, request.getRemoteAddr()).addLoginUser();
										session.setAttribute("user", a);
										return "index";
									} else {
										model.addAttribute("error6", "1");
									}
								} else {
									model.addAttribute("error", "1");
								}
							}
							if ("2".equals(status)) {
								Doctor d = doctorService.queryDoctor(name, pwd);
								if (d != null) {
									if (!LoginSession.isExistsLogin(d.getdNumber(), "", session, request.getRemoteAddr())) {
										new LoginSession(d.getdNumber(), "", session, request.getRemoteAddr()).addLoginUser();
										session.setAttribute("user", d);
										return "dindex";
									} else {
										model.addAttribute("error6", "1");
									}
								} else {
									model.addAttribute("error", "1");
								}
							}
						} else {
							model.addAttribute("error5", "2");
						}
					}
				} else {
					model.addAttribute("error5", "1");
				}
			}
			return "login";
		}else{
			try{
				Doctor user = (Doctor) session.getAttribute("user");
				return "dindex";
			}catch (Exception ex){
				return "index";
			}
		}
	}

	@RequestMapping("/getAllNew")
	public String getAllNew(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,Model model){
		pageSize = 6;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Page<New> list = administorService.queryNew(pageNum,pageSize);
		List<New> news = administorService.getNews();
		List<New> topnews = new ArrayList<New>();
		try {
			while (true) {
				if(news.size()==0){
					break;
				}
				Date one = sdf.parse(sdf.format(sdf.parse(news.get(news.size()-1).getTime())));
				New temp1 = null;
				for (int j = 0;j<news.size();j++){
					if(news.size() != 1){
						Date two = sdf.parse(sdf.format(sdf.parse(news.get(j).getTime())));
						if(two.getTime() > one.getTime()) {
							temp1 = news.get(j);
						}else if((two.getTime() < one.getTime()) && news.size() == 2){
							temp1 = news.get(news.size()-1);
						}
					}else{
						temp1 = news.get(0);
					}
					if(temp1 != null){
						one = sdf.parse(sdf.format(sdf.parse(temp1.getTime()).getTime()));
					}
				}
				if(null == temp1){
					long week = System.currentTimeMillis() - 604800000;
					if(week < sdf.parse(sdf.format(sdf.parse(news.get(news.size()-1).getTime()).getTime())).getTime()){
						topnews.add(news.get(news.size()-1));
					}
					news.remove(news.get(news.size()-1));
				}else{
					long week = System.currentTimeMillis() - 604800000;
					if(week < sdf.parse(sdf.format(sdf.parse(temp1.getTime()).getTime())).getTime()){
						news.remove(temp1);
						topnews.add(temp1);
					}else{
						news.remove(temp1);
					}
				}
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
		//最近一周
		model.addAttribute("topNews",topnews);
		model.addAttribute("PageInfo",list);
		return "welcome";
	}
	
	@RequestMapping("/getAllOreder")
	public String getAllOrder(@RequestParam("chose") String chose,@RequestParam("info") String info,@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,Model model){
		pageSize = 12;
		Page<Order> orderinfo = administorService.getAllOrder(chose,info,pageNum,pageSize);
		model.addAttribute("PageInfo",orderinfo);
		model.addAttribute("chose",chose);
		model.addAttribute("info",info);
		return "allOreder";
	}

	@RequestMapping("getCount")
	public String getCount(@RequestParam("chose") String chose,@RequestParam("info") String info,@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,Model model){
		pageSize = 6;
		Page<Count> list = administorService.getCount(chose,info,pageNum,pageSize);
		model.addAttribute("chose",chose);
		model.addAttribute("info",info);
		model.addAttribute("PageInfo",list);
		return "count";
	}

	@RequestMapping("/getFeedBack")
	public String getFeedback(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,Model model){
		pageSize = 6;
		Page<Feedback> pager = administorService.getFeedBackByPage(pageNum,pageSize);
		model.addAttribute("PageInfo",pager);
		return "feedback";
	}
	@RequestMapping("/toNew")
	public String toNew(){
		return "new";
	}
	@RequestMapping("addNew")
	public String addNew(String title,String content,String time){

		title=StringEscapeUtils.escapeHtml4(title);
		content=StringEscapeUtils.escapeHtml4(content);

		if((null != title && !"".equals(time)) && (null != content && !"".equals(content)) && (null != time && !"".equals(time))){
			New news = new New( title, content, time);
			administorService.addNew(news);
		}
		return "redirect:/getAllNew?pageNum=1&pageSize=6";
	}
	@RequestMapping("/muliDelete")
	public String muliDelete(HttpServletRequest request){
		String[] dels = request.getParameter("dels").split(",");
		if(dels.length >= 1){
			for (String de:dels) {
				administorService.deleteFeedBack(Integer.parseInt(de));
			}
		}
		return "redirect:/getFeedBack?pageNum=1&pageSize=6";
	}
}
