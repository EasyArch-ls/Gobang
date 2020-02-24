package main.refl;

import com.alibaba.fastjson.JSON;
import main.bean.Interaction;
import main.bean.Person;
import main.controller.WebSocketServer;

import java.util.ArrayList;

public class Prepare {
    public String[] Return(Interaction interaction){
        String json="";
        String[] jsons=new String[2];
        int y=0;
        ArrayList arrayList= WebSocketServer.hashMap1.get(interaction.getHouseid());
        for (int i = 0; i <arrayList.size() ; i++) {
            if(arrayList.get(i).getClass()== Person.class){
                Person person= (Person) arrayList.get(i);
                if (person.getName().equals(interaction.getName())){
                    person.setPrepare("y");
                }else {
                    Interaction interaction1=new Interaction();
                    interaction1.setUrl(interaction.getUrl());
                    interaction1.setType("hiszb");
                    json=JSON.toJSONString(interaction1);
                }
            }
        }

        jsons[0]= json;

        return jsons;
    }
}
