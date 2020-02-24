package main.method;

import java.util.List;

//0 hei 1 bai
public class Test {
    private static String [][] qipan=new String[15][15];

    public static String[][] getQipan() {
        qipan[0][0]="0";
        qipan[0][1]="0";
        qipan[0][3]="0";
        qipan[0][4]="0";
        qipan[1][0]="0";
        qipan[1][1]="1";
        qipan[1][3]="0";
        qipan[1][4]="0";
        qipan[2][0]="0";
        qipan[2][1]="1";
        qipan[2][2]="0";
        qipan[2][3]="1";
        qipan[2][4]="1";
        qipan[2][5]="1";
        qipan[3][0]="0";
        qipan[3][1]="1";
        qipan[3][2]="1";
        qipan[3][3]="0";
        qipan[3][4]="0";
        qipan[4][0]="0";
        qipan[4][1]="0";
        qipan[4][2]="1";
        qipan[4][3]="0";
        qipan[4][4]="0";
        qipan[4][5]="0";
        qipan[5][1]="1";
        qipan[5][2]="0";
        qipan[5][3]="1";
        qipan[5][5]="0";
        qipan[5][6]="1";
        qipan[6][1]="0";
        qipan[6][2]="1";
        qipan[6][3]="1";
        qipan[6][4]="1";
        qipan[6][6]="1";
        qipan[7][0]="0";
        qipan[7][1]="1";
        qipan[7][5]="1";
        qipan[8][0]="1";
        qipan[8][6]="1";
        return qipan;
    }
    //heide [0][0]yes [0][3] no [3][0]yes  [3][3] no [4][3] yes
    //baide [6][4] yes [6][6] no [7][1] no [7][5] yes
    public static int getX(){

        return 7;
    }

    public static int getY(){
        return 5;
    }

    public static String getcolor(){
        return "1";
    }
}
