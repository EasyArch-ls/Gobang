package main.refl;

import com.alibaba.fastjson.JSON;
import main.bean.Interaction;
import main.bean.Person;
import main.controller.WebSocketServer;

import java.util.ArrayList;
import java.util.Random;

public class Create {
    public String[] Return(Interaction interaction) {
        String json = "";
        String[] jsons=new String[2];
        System.out.println("aaaaaaaaaaaaaaa");
        System.out.println(JSON.toJSONString(interaction));
        while (true) {
            String houseid = random();
            if (WebSocketServer.hashMap1.get(houseid) == null) {
                ArrayList list=new ArrayList();
                Person person=new Person();
                person.setName(interaction.getName());
                list.add(person);
                interaction.setHouseid(houseid);
                WebSocketServer.hashMap1.put(houseid,list);
                json = JSON.toJSONString(interaction);
                System.out.println(json);
                break;
            }
        }
        jsons[0]=json;
        return jsons;
    }

    private String random() {
        Random random = new Random();
        int id = random.nextInt(900000) + 100000;
        String houseid = String.valueOf(id);
        return houseid;
    }
}
