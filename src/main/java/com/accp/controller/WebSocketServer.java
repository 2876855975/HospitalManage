package com.accp.controller;

import com.accp.entity.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/socket/{uuid}")
@Component
public class WebSocketServer {
    private static Set<WebSocketServer> webSocketSet = new HashSet<WebSocketServer>();

    private Session session;

    private String uuid;
    private String validateuuid;
    private boolean isOpen = false;

    @OnOpen
    public void onOpen(@PathParam("uuid") String uuid,Session session) {
        if(null == uuid || "".equals(uuid)){
            return;
        }
        this.session = session;
        this.uuid = uuid;
        webSocketSet.add(this);
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        if(this.session != session){
            this.onClose();
        }
        try {
            JSONObject jsonObject = JSONObject.parseObject(message);
            String validateuuid = jsonObject.getString("Validateuuid");
            if(null != validateuuid && !"".equals(validateuuid)){
                this.validateuuid = validateuuid;
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("转换错误");
        }
    }

    public static Boolean sendInfo(User user) throws IOException {
        for (WebSocketServer item : webSocketSet) {
            try {
                if(user.getValidateUuid().equals(item.validateuuid)){
                    item.sendMessage(JSON.toJSON(user).toString());
                    webSocketSet.remove(item);
                    return true;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return false;
    }

    public static Boolean sendInfoNotRemove(User user) throws IOException {
        for (WebSocketServer item : webSocketSet) {
            try {
                if(user.getValidateUuid().equals(item.validateuuid)){
                    item.sendMessage(JSON.toJSON(user).toString());
                    item.isOpen = true;
                    return true;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return false;
    }

    public static void removeSessionByValidateUuid(String uuid){
        for (WebSocketServer item : webSocketSet) {
            if(uuid.equals(item.validateuuid)){
                webSocketSet.remove(item);
            }
        }
    }

    public static Boolean checkIsExistsSession(String uuid) throws IOException {
        for (WebSocketServer item : webSocketSet) {
            try {
                if(uuid.equals(item.validateuuid)){
                    return true;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return false;
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}
