package main.refl;

import com.alibaba.fastjson.JSON;
import main.bean.Interaction;
import main.bean.Person;
import main.controller.WebSocketServer;
import main.method.Wuzi;

import java.util.ArrayList;

public class Fall {
    public synchronized String[] Return(Interaction interaction){
        String json="";
        String json1="";
        String []jsons = new String[2];
        ArrayList arrayList= WebSocketServer.hashMap1.get(interaction.getHouseid());
        for (int i = 0; i <arrayList.size() ; i++) {
            if(arrayList.get(i).getClass()== String[][].class){
                String[][]ss= (String[][]) arrayList.get(2);
                String[] s=interaction.getS().split(",");
                int x=Integer.valueOf(s[0])/15;
                int y=Integer.valueOf(s[0])%15;
                String color=s[1];
                ss[x][y]=color;
                Interaction interaction1=new Interaction();
                interaction1.setUrl(interaction.getUrl());
                interaction1.setIndex(s[0]);
                json= JSON.toJSONString(interaction1);
                jsons[0]=json;
                String res=Wuzi.disc(ss,x,y,color);
                if(res!=""){
                   Interaction interaction2=new Interaction();
                    interaction2.setUrl(interaction.getUrl());
                    interaction2.setRes(res);
                    json1=JSON.toJSONString(interaction2);
                    jsons[1]=json1;

                }
                break;
            }
        }
        for (int i = 0; i <arrayList.size() ; i++){
            if(arrayList.get(i).getClass()== Person.class){
                Person person= (Person) arrayList.get(i);
                String[] s=interaction.getS().split(",");
                if(person.getHeibai().equals(s[1])){
                    person.setPrev(s[0]);
                    break;
                }
            }
        }
        return jsons;
    }
}
