package com.accp.controller;

import com.accp.entity.Doctor;
import com.accp.entity.QRUser;
import com.accp.entity.User;
import com.accp.service.IDoctorService;
import com.accp.util.ImageLine;
import com.accp.util.ValiEmail;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.*;
import java.util.regex.Pattern;

@Controller
public class SocketController {

    @Resource(name = "iDoctorService")
    private IDoctorService doctorService;

    private final String HOST = "192.168.0.100:8080"; //服务器IP

    @RequestMapping("/validatecode{i}.jpg")
    @ResponseBody
    public String validatecode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        //生成随机字串
        String verifyCode = ImageLine.generateVerifyCode(4);
        //存入会话session
        HttpSession session = request.getSession(true);
        //删除以前的
        session.removeAttribute("verCode");
        session.setAttribute("verCode", verifyCode.toLowerCase());
        //生成图片
        int w = 350, h = 100;
        ImageLine.outputImage(w, h, response.getOutputStream(), verifyCode);
        return null;
    }

    @RequestMapping("/valiEmail")
    @ResponseBody
    public String valiEmail(HttpServletRequest req) throws Exception {
        String email = req.getParameter("email");
        Object se = req.getSession().getAttribute("user");
        Doctor user = null;
        try {
            user = (Doctor) se;
            if(null != user){
                email = user.getdEmail();
                Pattern compile = Pattern.compile("[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+");
                if (null != email && !"".equals(email)) {
                    if (compile.matcher(email).matches()) {
                        //数据库查询是否存在邮箱
                        Doctor doctor = doctorService.queryDoctorByEmail(email);
                        if(null != doctor){
                            if(!doctor.getdNumber().equals(user.getdNumber())){
                                return "{\"status\":\"0\"}";
                            }
                        }
                        String code = null;
                        try {
                            code = "" + (int) ((Math.random() * 9 + 1) * 100000);
                            boolean isTimeOut = ValiEmail.valiEmailIsTimeOut(new ValiEmail(InetAddress.getLocalHost().getHostName(),
                                    InetAddress.getLocalHost().getHostAddress(), email, null, null));
                            if (isTimeOut) {
                                ValiEmail.sendEmail(email, code, user.getName());
                                ValiEmail.addValiEmail(new ValiEmail(InetAddress.getLocalHost().getHostName(),
                                        InetAddress.getLocalHost().getHostAddress(), email, code, new Date()));
                                return "{\"status\":\"1\"}";
                            } else {
                                return "{\"status\":\"0\"}";
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            return "{\"status\":\"0\"}";
                        }
                    }
                }
            }
            return "{\"status\":\"0\"}";
        }catch(Exception ex){
            Pattern compile = Pattern.compile("[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+");
            if (null != email && !"".equals(email)) {
                if(compile.matcher(email).matches()){
                    String code = null;
                    try {
                        code = "" + (int) ((Math.random() * 9 + 1) * 100000);
                        boolean isTimeOut = ValiEmail.valiEmailIsTimeOut(new ValiEmail(InetAddress.getLocalHost().getHostName(),
                                InetAddress.getLocalHost().getHostAddress(), email, null, null));
                        if (isTimeOut) {
                            ValiEmail.sendEmail(email, code);
                            ValiEmail.addValiEmail(new ValiEmail(InetAddress.getLocalHost().getHostName(),
                                    InetAddress.getLocalHost().getHostAddress(), email, code, new Date()));
                            return "{\"status\":\"1\"}";
                        } else {
                            return "{\"status\":\"0\"}";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "{\"status\":\"0\"}";
                    }
                }
            }
            return "{\"status\":\"0\"}";
        }
    }

    @RequestMapping("/jump/{uuid}")
    @ResponseBody
    public String jump(@PathVariable String uuid, HttpServletResponse response,HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write("<html>");
        writer.write("<script src=\""+request.getContextPath()+"/js/jquery.js\"></script>");
        writer.write("<script>");
        try {
            User u = new User();
            u.setValidateUuid(uuid);
            u.setSuccessful("Loading");
            WebSocketServer.sendInfoNotRemove(u);
            Boolean sendT = WebSocketServer.checkIsExistsSession(uuid);
            if(sendT==false){
//                //验证失败 远程等待已销毁
                writer.write("alert('验证失败！请刷新二维码！');");
            }else{
                //验证成功 远程等待存在
                writer.write("var sUserAgent = navigator.userAgent.toLowerCase();");
                writer.write("var isWeiXin = sUserAgent.match(/MicroMessenger/i)==\"micromessenger\";");
                writer.write("if (!isWeiXin){");
                writer.write("    $.ajax({type:\"post\",url:\"/eNoJump/"+uuid+"\"});");
                writer.write("    alert('请使用微信扫一扫！');");
                writer.write("    window.opener=null;");
                writer.write("    window.open('','_self','');");
                writer.write("    window.close();");
                writer.write("} else {");
                writer.write("  var item = localStorage.getItem(\"uuid\");");
                writer.write("  if(null!=item && \"\"!=item){");
                writer.write("    window.location.href=\"http://"+HOST+"/loginJump/"+uuid+"/\"+item;");
                writer.write("  }else{");
                writer.write("    $.ajax({type:\"post\",url:\"/ejump/"+uuid+"\"});");
                writer.write("    alert('您的手机暂未绑定快捷账号！请先绑定');");
                writer.write("    window.location.href=\"http://www.baidu.com\";");
                writer.write("  }");
                writer.write("}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            writer.write("    $.ajax({type:\"post\",url:\"/ejump/"+uuid+"\"});");
            writer.write("alert(\"服务器验证错误！\");");
        }
        writer.write("</script>");
        writer.write("</html>");
        return null;
    }

    @RequestMapping("/loginJump/{valiuuid}/{uuid}")
    @ResponseBody
    public String doLoginWithJump(@PathVariable String valiuuid,@PathVariable String uuid,HttpServletResponse response,HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write("<html>");
        writer.write("<script src=\""+request.getContextPath()+"/js/jquery.js\"></script>");
        writer.write("<script>");
        try {
            Boolean sendT = WebSocketServer.checkIsExistsSession(valiuuid);
            if(sendT==false){
//                //验证失败 远程等待已销毁
                writer.write("alert('验证失败！请刷新二维码！');");
            }else{
                //验证成功 远程等待存在
                //获取数据库登录信息
                QRUser qrUsers = doctorService.queryQrUser(new QRUser(uuid, null));
                User u = new User();
                if(null==qrUsers){
                    u.setUuid(uuid);
                    u.setValidateUuid(valiuuid);
                    u.setSuccessful("Faillog");
                    WebSocketServer.sendInfo(u);
                    writer.write("alert('验证失败！请先检查帐号是否开启快捷登录');");
                }else {
                    u.setId(4);
                    u.setUuid(uuid);
                    u.setValidateUuid(valiuuid);
                    u.setdNumber(qrUsers.getdNumber());
                    u.setSuccessful("Successful");
                    WebSocketServer.sendInfoNotRemove(u);
                    writer.write("alert('验证成功！页面即将跳转');");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            writer.write("    $.ajax({type:\"post\",url:\"/ejump/"+valiuuid+"\"});");
            writer.write("alert(\"服务器验证错误！\");");
        }
        writer.write("    window.location.href=\"http://www.baidu.com\";");
        writer.write("</script>");
        writer.write("</html>");
        return null;
    }

    @RequestMapping("/SessionJump/{valiuuid}/{dNumber}")
    @ResponseBody
    public String doSessionWithJump(@PathVariable String valiuuid,@PathVariable String dNumber,HttpServletRequest request,HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Boolean sendT = WebSocketServer.checkIsExistsSession(valiuuid);
            if(sendT==false){
//                //验证失败 远程等待已销毁
                writer.write("False");
            }else{
                //验证成功 远程等待存在
                //获取数据库登录信息
                Doctor d = doctorService.getById(dNumber);
                if(null !=d){
                    request.getSession().setAttribute("user",d);
                    WebSocketServer.removeSessionByValidateUuid(valiuuid);
                    writer.write("True");
                }else{
                    writer.write("False");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            writer.write("False");
        }
        return null;
    }

    @RequestMapping("/qc/{uuid}.jpg")
    @ResponseBody
    public String qcJpg(@PathVariable String uuid,HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        String data = "http://"+HOST+request.getContextPath()+"/jump/"+uuid;
        Map<EncodeHintType, Object> hints=new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 255, 255, hints);
            OutputStream out = response.getOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "png", out);//输出二维码
            out.flush();
            out.close();
        } catch (WriterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/qr.json")
    @ResponseBody
    public String qrJson(HttpServletRequest request) throws ServletException, IOException {
        //生成参数
        JSONObject data = new JSONObject();
        data.put("Validateuuid", generateUUID());
        data.put("uuid", generateUUID());
        return data.toString();
    }

    @RequestMapping("/ejump/{uuid}")
    @ResponseBody
    public String ejump(@PathVariable String uuid, HttpServletResponse response){
        try {
            Boolean sendT = WebSocketServer.checkIsExistsSession(uuid);
            if(sendT){
                User u = new User();
                u.setValidateUuid(uuid);
                u.setSuccessful("Fail");
                WebSocketServer.sendInfo(u);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/sjump/{uuid}")
    @ResponseBody
    public String sjump(@PathVariable String uuid, HttpServletResponse response){
        try {
            Boolean sendT = WebSocketServer.checkIsExistsSession(uuid);
            if(sendT){
                User u = new User();
                u.setValidateUuid(uuid);
                u.setSuccessful("Stop");
                WebSocketServer.sendInfo(u);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/duQrUser/{qrid}")
    @ResponseBody
    public String duQrUser(@PathVariable String qrid, HttpServletRequest request){
        if(null != qrid && !"".equals(qrid)){
            doctorService.deleteQrUser(new QRUser(qrid,null));
        }
        return null;
    }

    @RequestMapping("/eNoJump/{uuid}")
    @ResponseBody
    public String eNoJump(@PathVariable String uuid, HttpServletResponse response){
        try {
            Boolean sendT = WebSocketServer.checkIsExistsSession(uuid);
            if(sendT){
                User u = new User();
                u.setValidateUuid(uuid);
                u.setSuccessful("Fail");
                WebSocketServer.sendInfoNotRemove(u);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/rjump/{uuid}/{dNumber}")
    @ResponseBody
    public String rjump(@PathVariable String uuid,@PathVariable String dNumber, HttpServletResponse response,HttpServletRequest request){
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write("<html>");
        writer.write("<script src=\""+request.getContextPath()+"/js/jquery.js\"></script>");
        writer.write("<script>");
        try {
            User u = new User();
            u.setValidateUuid(uuid);
            u.setSuccessful("Loading");
            WebSocketServer.sendInfoNotRemove(u);
            Boolean sendT = WebSocketServer.checkIsExistsSession(uuid);
            if(sendT==false){
//                //验证失败 远程等待已销毁
                writer.write("alert('匹配失败！请刷新网页！');");
            }else{
                //验证成功 远程等待存在
                if(null != dNumber){
                    writer.write("var sUserAgent = navigator.userAgent.toLowerCase();");
                    writer.write("var isWeiXin = sUserAgent.match(/MicroMessenger/i)==\"micromessenger\";");
                    writer.write("if (!isWeiXin){");
                    writer.write("    $.ajax({type:\"post\",url:\"/ejump/"+uuid+"\"});");
                    writer.write("    alert('请使用微信扫一扫！');");
                    writer.write("    window.opener=null;");
                    writer.write("    window.open('','_self','');");
                    writer.write("    window.close();");
                    writer.write("} else {");
                    writer.write("    var item = localStorage.getItem(\"uuid\");");
                    writer.write("    if(null!=item && \"\"!=item){");
                    writer.write("      if (!confirm(\"您的手机上绑定了其他账户 确定要继续绑定当前帐号吗？ 这将会删除与其他账户的绑定\")){");
                    writer.write("          $.ajax({type:\"post\",url:\"/sjump/"+uuid+"\"});");
                    writer.write("    window.location.href=\"http://www.baidu.com\";");
                    writer.write("      }");
                    writer.write("    }");
                    writer.write("    var code=prompt(\"请输入动态验证码\");");
                    writer.write("    if(null == code || '' == code){$.ajax({type:\"post\",url:\"/ejump/\"+uuid+\"\"});}");
                    writer.write("    window.location.href=\"http://"+HOST+"/registerJump/"+uuid+"/"+dNumber+"/\"+code;");
                    writer.write("}");
                }else{
                    writer.write("    $.ajax({type:\"post\",url:\"/ejump/"+uuid+"\"});");
                    writer.write("    alert('发生错误！');");
                    writer.write("    window.opener=null;");
                    writer.write("    window.open('','_self','');");
                    writer.write("    window.close();");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            writer.write("    $.ajax({type:\"post\",url:\"/ejump/"+uuid+"\"});");
            writer.write("alert(\"服务器验证错误！\");");
        }
        writer.write("</script>");
        writer.write("</html>");
        return null;
    }

    @RequestMapping("/registerJump/{valiuuid}/{dNumber}/{code}")
    @ResponseBody
    public String doRegisterWithJump(@PathVariable String valiuuid,@PathVariable String dNumber,@PathVariable String code,HttpServletResponse response,HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write("<html>");
        writer.write("<script src=\""+request.getContextPath()+"/js/jquery.js\"></script>");
        writer.write("<script>");
        try {
            Boolean sendT = WebSocketServer.checkIsExistsSession(valiuuid);
            if(sendT==false){
                //验证失败 远程等待已销毁
                writer.write("alert('验证失败！请刷新二维码！');");
            }else{
                //验证成功 远程等待存在
                Doctor d = doctorService.getById(dNumber);
                if(null != d){
                    User u = new User();
                    if (ValiEmail.checkValiEmail(d.getdEmail(), code)) {
                        int i = doctorService.insertQrUser(new QRUser(valiuuid, d.getdNumber()));
                        if (i == 1) {
                            writer.write("var item2 = localStorage.getItem(\"uuid\");");
                            writer.write("if(null!=item2 && \"\"!=item2){");
                            writer.write("      $.ajax({type:\"post\",url:\"/duQrUser/\"+item2});");
                            writer.write("}");
                            writer.write("localStorage.setItem(\"uuid\",\""+valiuuid+"\");");
                            u.setValidateUuid(valiuuid);
                            u.setSuccessful("Successful");
                            writer.write("  alert('账号匹配手机成功！');");
                        } else {
                            u.setValidateUuid(valiuuid);
                            u.setSuccessful("Fail");
                            writer.write("  alert('账号匹配手机失败！');");
                        }
                    } else {
                        u.setValidateUuid(valiuuid);
                        u.setSuccessful("Fail");
                        writer.write("  alert('动态验证码不正确！请稍后重新获取');");
                    }
                    WebSocketServer.sendInfo(u);
                }else{
                    writer.write("    $.ajax({type:\"post\",url:\"/ejump/"+valiuuid+"\"});");
                    writer.write("alert(\"账户出现问题！\");");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            writer.write("    $.ajax({type:\"post\",url:\"/ejump/"+valiuuid+"\"});");
            writer.write("alert(\"服务器验证错误！\");");
        }
        writer.write("    window.location.href=\"http://www.baidu.com\";");
        writer.write("</script>");
        writer.write("</html>");
        return null;
    }

    @RequestMapping("/sdQrUser.json")
    @ResponseBody
    public String sdQrUser(HttpServletRequest request) {
        Doctor user = (Doctor) request.getSession().getAttribute("user");
        if(null != user){
            int a = doctorService.deleteQrUser(new QRUser(null,user.getdNumber()));
            if(a != 0){
                return "{\"status\":\"True\"}";
            }else{
                return "{\"status\":\"False\"}";
            }
        }else{
            return "{\"status\":\"False\"}";
        }
    }
    @RequestMapping("/qr/{uuid}.jpg")
    @ResponseBody
    public String qrJpg(@PathVariable String uuid,HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        Doctor user = (Doctor) request.getSession().getAttribute("user");
        if(null != user){
            String data = "http://"+HOST+request.getContextPath()+"/rjump/"+uuid+"/"+user.getdNumber();
            Map<EncodeHintType, Object> hints=new HashMap<EncodeHintType, Object>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.MARGIN, 1);
            try {
                BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 255, 255, hints);
                OutputStream out = response.getOutputStream();
                MatrixToImageWriter.writeToStream(bitMatrix, "png", out);//输出二维码
                out.flush();
                out.close();
            } catch (WriterException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String generateUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        Long currentTime = System.currentTimeMillis();
        String currentDate = String.valueOf(currentTime);
        return uuid + currentDate;
    }
}
