package com.accp.util;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class LoginSession {
    private static Set<LoginSession> users = new HashSet<LoginSession>();

    private String dNumber;
    private String aNumber;
    private HttpSession session;
    private String logIp;
    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getdNumber() {
        return dNumber;
    }

    public void setdNumber(String dNumber) {
        this.dNumber = dNumber;
    }

    public String getaNumber() {
        return aNumber;
    }

    public void setaNumber(String aNumber) {
        this.aNumber = aNumber;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public String getLogIp() {
        return logIp;
    }

    public LoginSession(String dNumber, String aNumber, HttpSession session, String logIp) {
        this.dNumber = dNumber;
        this.aNumber = aNumber;
        this.session = session;
        this.logIp = logIp;
    }

    public LoginSession() {
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }

    public static boolean isExistsLogin(String dNumber,String aNumber,HttpSession session,String logIp){
        for (LoginSession u:users) {
            long longtime = u.getTime().getTime()+300000;
            if((session.getId().equals(u.getSession().getId())) || ((dNumber.equals(u.getdNumber())) || (logIp.equals(u.getLogIp())) ||
                    (aNumber.equals(u.getaNumber()))) && (longtime>=System.currentTimeMillis()) ){
                return true;
            }
        }
        return false;
    }

    public void removeLoginUser(HttpSession session,String logIp){
        for (LoginSession u:users) {
            if((session.getId().equals(u.getSession().getId())) && (logIp.equals(u.getLogIp()))){
                users.remove(u);
            }
        }
    }
    public static int changeTime(HttpSession session,String logIp){
        for (LoginSession u:users) {
            long longtime = u.getTime().getTime()+300000;
            if((session.getId().equals(u.getSession().getId())) && (logIp.equals(u.getLogIp())) && (longtime>=System.currentTimeMillis())){
                u.time = new Date();
                return 1;
            }
            if(longtime<System.currentTimeMillis() && logIp.equals(u.getLogIp()) && session.getId().equals(u.getSession().getId()) ){
                return 2;
            }
        }
        return 0;
    }

    public void addLoginUser(){
        this.time = new Date();
        users.add(this);
    }
}
