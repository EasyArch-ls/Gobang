package main.refl;

import com.alibaba.fastjson.JSON;
import main.bean.Interaction;
import main.bean.Person;
import main.controller.WebSocketServer;

import java.util.ArrayList;

public class Join {
    public String[] Return(Interaction interaction){
        String json="";
        String json1="";
        String []jsons = new String[2];
        if (WebSocketServer.hashMap1.get(interaction.getHouseid())!=null){
            ArrayList arrayList=WebSocketServer.hashMap1.get(interaction.getHouseid());
            switch (arrayList.size()){
                case 0:
                    interaction.setR("0");
                    WebSocketServer.hashMap1.remove(interaction.getHouseid());
                    interaction.setType("myjoin");
                    json=JSON.toJSONString(interaction);
                    System.out.println(json);
                    jsons[0]=json;
                    break;
                case 1:
                    interaction.setR("1");
                    Person person=new Person();
                    person.setName(interaction.getName());
                    WebSocketServer.hashMap1.get(interaction.getHouseid()).add(person);
                    Interaction interaction1=new Interaction();
                    interaction1.setUrl(interaction.getUrl());
                    interaction1.setR("1");
                    interaction1.setType("myjoin");
                    Person person1= (Person) arrayList.get(0);
                    interaction1.setHisname(person1.getName());
                    interaction1.setHisstatus(person1.getPrepare());
                    json=JSON.toJSONString(interaction1);
                    Interaction interaction2=new Interaction();
                    interaction2.setUrl(interaction.getUrl());
                    interaction2.setType("hisjoin");
                    interaction2.setHisname(interaction.getName());
                    json1=JSON.toJSONString(interaction2);
                    jsons[0]=json;
                    jsons[1]=json1;
                    break;
                default:
                    interaction.setR("2");
                    interaction.setType("myjoin");
                    json=JSON.toJSONString(interaction);
                    jsons[0]=json;
                    break;
            }

        }else{
            interaction.setR("0");
            interaction.setType("myjoin");
            json=JSON.toJSONString(interaction);
            jsons[0]=json;
        }

        return jsons;
    }
}
