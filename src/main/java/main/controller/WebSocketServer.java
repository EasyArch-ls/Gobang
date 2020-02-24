package main.controller;


import com.alibaba.fastjson.JSON;
import main.bean.Interaction;
import main.bean.Person;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/wuziqi/{name}")
@Component
public class WebSocketServer {

    private static Logger log = Logger.getLogger(WebSocketServer.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    public static ConcurrentHashMap<String, Session> hashMap = new ConcurrentHashMap<String, Session>();
    public static ConcurrentHashMap<String, ArrayList<Object>> hashMap1 = new ConcurrentHashMap<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("name") String name) {
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        log.info("有新窗口开始监听:" + name + ",当前在线人数为" + getOnlineCount() + "\n");
        System.out.println(session.getBasicRemote());
        try {
            //hashMap.put(name,session);
            session.getBasicRemote().sendText(JSON.toJSONString(hashMap1));
        } catch (IOException e) {
            log.error("main.websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subOnlineCount();
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount() + "\n");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口的信息:" + message + "\n");
        //群发消息
        Interaction interaction = JSON.parseObject(message, Interaction.class);
        if (!interaction.getUrl().equals("Init")) {

            Class c = null;
            try {
                c = Class.forName("main.refl." + interaction.getUrl());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Object object = null;
            try {
                object = c.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            Method method = null;
            try {
                method = c.getMethod("Return", Interaction.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            String[] jsonx;
            try {
                jsonx = (String[]) method.invoke(object, interaction);
                if (jsonx[1] == null || jsonx[1] == "") {
                    log.info(jsonx[0] + "\n");
                    if (interaction.getUrl().equals("Prepare") || interaction.getUrl().equals("Cancel") || interaction.getUrl().equals("Fall")) {
                        Interaction interaction1 = JSON.parseObject(jsonx[0], Interaction.class);
                        ArrayList arrayList = WebSocketServer.hashMap1.get(interaction.getHouseid());
                        int xxx = 0;
                        for (int i = 0; i < arrayList.size(); i++) {
                            if (arrayList.get(i).getClass() == Person.class) {
                                Person person = (Person) arrayList.get(i);
                                if (!person.getName().equals(interaction.getName())) {
                                    hashMap.get(person.getName()).getBasicRemote().sendText(jsonx[0]);
                                    if (interaction.getUrl().equals("Fall")) {
                                        break;
                                    }
                                }
                                if (person.getPrepare().equals("y")) {
                                    xxx++;

                                }
                                if (xxx == 2) {
                                    Random random = new Random();
                                    int z = random.nextInt(2);
                                    Interaction interaction2 = new Interaction();
                                    interaction2.setUrl("Start");
                                    interaction2.setColor("黑");
                                    Interaction interaction3 = new Interaction();
                                    interaction3.setUrl("Start");
                                    interaction3.setColor("白");
                                    String[][] strings = new String[15][15];
                                    hashMap1.get(interaction.getHouseid()).add(strings);

                                    for (int j = 0; j < 1; j++) {
                                        if (j == z) {
                                            log.info(JSON.toJSONString(interaction2) + "\t" + j);
                                            log.info(JSON.toJSONString(interaction3) + "\t" + 1);
                                            Person person1 = (Person) arrayList.get(j);
                                            person1.setHeibai("黑");
                                            hashMap.get(person1.getName()).getBasicRemote().sendText(JSON.toJSONString(interaction2));
                                            Person person2 = (Person) arrayList.get(1);
                                            person2.setHeibai("白");
                                            hashMap.get(person2.getName()).getBasicRemote().sendText(JSON.toJSONString(interaction3));
                                        } else {
                                            log.info(JSON.toJSONString(interaction2) + "\t" + z);
                                            log.info(JSON.toJSONString(interaction3) + "\t" + j);
                                            Person person1 = (Person) arrayList.get(z);
                                            hashMap.get(person1.getName()).getBasicRemote().sendText(JSON.toJSONString(interaction2));
                                            person1.setHeibai("黑");
                                            Person person2 = (Person) arrayList.get(j);
                                            person2.setHeibai("白");
                                            hashMap.get(person2.getName()).getBasicRemote().sendText(JSON.toJSONString(interaction3));
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    } else {
                        hashMap.get(interaction.getName()).getBasicRemote().sendText(jsonx[0]);
                    }
                } else {
                    log.info(jsonx[0] + "\n");
                    log.info(jsonx[1] + "\n");
                    ArrayList arrayList = WebSocketServer.hashMap1.get(interaction.getHouseid());
                    if (interaction.getUrl().equals("Fall")) {
                        for (int i = 0; i < arrayList.size(); i++) {
                            if (arrayList.get(i).getClass() == Person.class) {
                                Person person = (Person) arrayList.get(i);
                                hashMap.get(person.getName()).getBasicRemote().sendText(jsonx[1]);
                            }
                        }
                        WebSocketServer.hashMap1.remove(interaction.getHouseid());
                    } else {
                        hashMap.get(interaction.getName()).getBasicRemote().sendText(jsonx[0]);

                        for (int i = 0; i < arrayList.size(); i++) {
                            if (arrayList.get(i).getClass() == Person.class) {
                                Person person = (Person) arrayList.get(i);
                                if (!person.getName().equals(interaction.getName())) {
                                    hashMap.get(person.getName()).getBasicRemote().sendText(jsonx[1]);
                                    break;
                                }
                            }
                        }
                    }

                }

               /* if(interaction.getUrl().equals("Prepare")){
                    Interaction interaction1=JSON.parseObject(jsonx[0],Interaction.class);
                    String[] strings=interaction1.getNames().split(",");
                    for (int i = 0; i <strings.length ; i++) {
                        hashMap.get(strings[i]).getBasicRemote().sendText(jsonx[0]);
                    }
                }*/
                // hashMap.get(interaction.getName()).getBasicRemote().sendText(jsonx);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            hashMap.put(interaction.getName(), session);
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("url", "Init");
                hashMap.put("houses", hashMap1);
                log.info(hashMap);
                session.getBasicRemote().sendText(JSON.toJSONString(hashMap));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) throws IOException {
       /* log.info("推送消息到窗口" + sid + "，推送内容:" + message + "\n");
        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if (json == null) {
                    item.sendMessage(message);
                } else if (item..equals(json)) {
                    item.sendMessage(message);

                }
            } catch (IOException e) {
                continue;
            }
        }*/
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    public static void main(String[] args) {
        System.out.println(41 / 15);
        System.out.println(41 % 15);
    }
}

