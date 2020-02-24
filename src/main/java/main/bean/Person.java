package main.bean;

public class Person {
    private String name;
    private String prepare="n";
    private String heibai;
    private String prev;
    private int huiqicount=3;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrepare() {
        return prepare;
    }

    public void setPrepare(String prepare) {
        this.prepare = prepare;
    }

    public String getHeibai() {
        return heibai;
    }

    public void setHeibai(String heibai) {
        this.heibai = heibai;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public int getHuiqicount() {
        return huiqicount;
    }

    public void setHuiqicount(int huiqicount) {
        this.huiqicount = huiqicount;
    }
    public String subcount(){
        if (this.getHuiqicount()>0){
            this.huiqicount--;
            return "";
        }
        return "毁棋次数已达上限";
    }

}
