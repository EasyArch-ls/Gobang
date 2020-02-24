package main.refl;

import main.bean.Interaction;
import main.bean.Person;
import main.controller.WebSocketServer;

import java.util.ArrayList;

public class Exit {
    public String Return(Interaction interaction){
        ArrayList arrayList=WebSocketServer.hashMap1.get(interaction.getHouseid());
        for (int i = 0; i <arrayList.size() ; i++) {
            if(arrayList.get(i).getClass()== Person.class){
                Person person= (Person) arrayList.get(i);
                if(interaction.getName().equals(person.getName())){
                    arrayList.remove(i);
                    break;
                }

            }
        }
        if(arrayList.size()==1){
            WebSocketServer.hashMap1.remove(interaction.getHouseid());
        }
        return "";
    }
}
