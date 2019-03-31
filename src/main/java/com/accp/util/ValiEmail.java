package com.accp.util;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

import static com.accp.util.OEmail.SMTP_QQ;

public class ValiEmail {
    private static final String HOSTEMAIL = ""; //服务器邮件
    private static final String HOSTPASS = "";  //邮件密匙
    private static final String PROJECTNAME = "Graduate";
    private static Set<ValiEmail> valiEmails = new HashSet<ValiEmail>();
    private String hostName;
    private String hostIp;
    private String email;
    private String code;
    private Date date;
    private Boolean isUse = false;

    public Boolean getUse() {
        return isUse;
    }

    public void setUse(Boolean use) {
        isUse = use;
    }

    public ValiEmail(String hostName, String hostIp, String email, String code, Date date) {
        this.hostName = hostName;
        this.hostIp = hostIp;
        this.email = email;
        this.code = code;
        this.date = date;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static void addValiEmail(ValiEmail valiEmail) throws Exception{
        valiEmails.add(valiEmail);
    }

    public static boolean checkValiEmail(ValiEmail valiEmail){
        boolean b = valiEmailCodeIsTimeOut(valiEmail);
        if(b){
            for (ValiEmail ve:valiEmails) {
                if(valiEmail.getEmail().equals(ve.getEmail())){
                    if(valiEmail.getCode().equals(ve.getCode())){
                        if(!ve.getUse()){
                            ve.setUse(true);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean checkValiEmail(String email,String code){
        boolean b = valiEmailCodeIsTimeOut(email);
        if(b){
            for (ValiEmail ve:valiEmails) {
                if(email.equals(ve.getEmail())){
                    if(code.equals(ve.getCode())){
                        if(!ve.getUse()) {
                            ve.setUse(true);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean valiEmailIsTimeOut(ValiEmail valiEmail){
        //t到时 f未到时
        for (ValiEmail ve:valiEmails) {
            if((valiEmail.getEmail().equals(ve.getEmail()) && valiEmail.getHostIp().equals(ve.getHostIp())) ||
                    (!valiEmail.getEmail().equals(ve.getEmail()) && valiEmail.getHostIp().equals(ve.getHostIp())) ||
                    (!valiEmail.getHostIp().equals(ve.getHostIp()) && valiEmail.getEmail().equals(ve.getEmail()))){
                long m3 = ve.getDate().getTime()+120000;
                if(m3 <= System.currentTimeMillis()){
                    valiEmails.remove(ve);
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    public static boolean valiEmailCodeIsTimeOut(ValiEmail valiEmail){
        //t未超时 f已超时
        for (ValiEmail ve:valiEmails) {
            if((valiEmail.getEmail().equals(ve.getEmail()) && valiEmail.getHostIp().equals(ve.getHostIp())) ||
                    (!valiEmail.getEmail().equals(ve.getEmail()) && valiEmail.getHostIp().equals(ve.getHostIp())) ||
                    (!valiEmail.getHostIp().equals(ve.getHostIp()) && valiEmail.getEmail().equals(ve.getEmail()))){
                long m3 = ve.getDate().getTime()+600000;
                if(m3 >= System.currentTimeMillis()){
                    return true;
                }
                return false;
            }
        }
        return true;
    }
    public static boolean valiEmailCodeIsTimeOut(String email){
        //t未超时 f已超时
        for (ValiEmail ve:valiEmails) {
            if(email.equals(ve.getEmail())){
                long m3 = ve.getDate().getTime()+600000;
                if(m3 >= System.currentTimeMillis()){
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    public static void sendEmail(String email,String code) throws IOException, PebbleException, MessagingException {
        OEmail.config(SMTP_QQ(false), HOSTEMAIL, HOSTPASS);
        PebbleEngine engine = new PebbleEngine.Builder().build();
        PebbleTemplate compiledTemplate = engine.getTemplate("valid.html");
        Map<String, Object> context = new HashMap<String, Object>();
        context.put("email", "yelinf11@foxmail.com");
        context.put("code", code);
        context.put("proname", PROJECTNAME);
        Writer writer = new StringWriter();
        compiledTemplate.evaluate(writer, context);
        OEmail.subject("【"+PROJECTNAME+"】验证信息")
                .from(PROJECTNAME)
                .to(email)
                .html(writer.toString())
                .send();
    }

    public static void sendEmail(String email,String code,String username) throws IOException, PebbleException, MessagingException {
        OEmail.config(SMTP_QQ(false), HOSTEMAIL, HOSTPASS);
        PebbleEngine engine = new PebbleEngine.Builder().build();
        PebbleTemplate compiledTemplate = engine.getTemplate("validU.html");
        Map<String, Object> context = new HashMap<String, Object>();
        context.put("email", "yelinf11@foxmail.com");
        context.put("username", username);
        context.put("code", code);
        context.put("proname", PROJECTNAME);
        Writer writer = new StringWriter();
        compiledTemplate.evaluate(writer, context);
        OEmail.subject("【"+PROJECTNAME+"】验证信息")
                .from(PROJECTNAME)
                .to(email)
                .html(writer.toString())
                .send();
    }
}
