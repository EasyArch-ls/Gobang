package main.method;

public class Wuzi {

    public static String disc(String[][] ss, int x, int y, String color) {
        int Horizontal = 1;//横
        int vertical = 1;//竖
        int loblique = 1;
        int roblique = 1;
        int z = x;
        int q = y;
        /*
         * 直上
         * */
        for (int i = 0; i < 4; i++) {
            if (x==0 ||z == 0  ) {
                z=x;
                break;
            } else {
                z--;
                if (ss[z][y]!=null&&ss[z][y].equals(color)) {
                    vertical++;
                    if (vertical == 5) {
                        System.out.println("1赢得比赛");
                        return color+"子赢得比赛";
                    }
                }else {
                    z=x;
                    q=y;
                    break;
                }
            }

        }
        /*
         * 直下
         * */
        for (int i = 0; i < 4; i++) {
            if (x==14 ||z == 14) {
                z = x;
                break;
            } else {
                z++;
                if (ss[z][y]!=null&&ss[z][y].equals(color)) {
                    vertical++;
                    if (vertical == 5) {
                        System.out.println("2赢得比赛");
                        return color+"子赢得比赛";
                    }
                }else {
                    z=x;
                    q=y;
                    break;
                }
            }

        }
        /*
         * 左
         * */
        for (int i = 0; i < 4; i++) {
            if (y==0 ||q == 0 ) {
                q = y;
                break;
            } else {
                q--;
                if (ss[x][q]!=null&&ss[x][q].equals(color)) {
                    Horizontal++;
                    if (Horizontal == 5) {
                        System.out.println("3赢得比赛");
                        return color+"子赢得比赛";
                    }
                }else {
                    z=x;
                    q=y;
                    break;
                }
            }
        }
        /*
         * 右
         * */
        for (int i = 0; i < 4; i++) {
            if (y==14 ||q == 14) {
                q = y;
                break;
            } else {
                q++;
                if (ss[x][q]!=null&&ss[x][q].equals(color)) {
                    Horizontal++;
                    if (Horizontal == 5) {
                        System.out.println("4赢得比赛");
                        return color+"子赢得比赛";
                    }
                }else {
                    z=x;
                    q=y;
                    break;
                }
            }
        }
        /*
         * 左上
         * */
        for (int i = 0; i < 4; i++) {
            if ( x==0 || y==0 || q==0 ||z == 0 ) {
                z=x;
                q=y;
                break;
            }else {
                z--;
                q--;
                if (ss[z][q]!=null&&ss[z][q].equals(color)) {
                    roblique++;
                    if (roblique == 5) {
                        System.out.println("5赢得比赛");
                        return color+"子赢得比赛";
                    }
                }else {
                    z=x;
                    q=y;
                    break;
                }
            }

        }
        /*
         * 右下
         * */
        for (int i = 0; i < 4; i++) {
            if ( x==14 || y==14 || z==14 ||q == 14 ) {
                z=x;
                q=y;
                break;
            }else {
                z++;
                q++;
                if (ss[z][q]!=null&&ss[z][q].equals(color)) {
                    roblique++;
                    if (roblique == 5) {
                        System.out.println("6赢得比赛");
                        return color+"子赢得比赛";
                    }
                }else {
                    z=x;
                    q=y;
                    break;
                }
            }

        }
        /*
         * 左下
         * */
        for (int i = 0; i < 4; i++) {
            if ( x==14 || y==0 || z==14 ||q == 0 ) {
                z=x;
                q=y;
                break;
            }else {
                z++;
                q--;
                if (ss[z][q]!=null&&ss[z][q].equals(color)) {
                    loblique++;
                    if (loblique == 5) {
                        System.out.println("7赢得比赛");
                        return color+"子赢得比赛";
                    }
                }else {
                    z=x;
                    q=y;
                    break;
                }
            }
        }
        /*
         * 右上
         * */
        for (int i = 0; i < 4; i++) {
            if ( x==0 || y==14 || z==0 ||q == 14 ) {
                z=x;
                q=y;
                break;
            }else {
                z--;
                q++;
                if (ss[z][q]!=null&&ss[z][q].equals(color)) {
                    loblique++;
                    if (loblique == 5) {
                        System.out.println("8赢得比赛");
                        return color+"子赢得比赛";
                    }
                }else {
                    z=x;
                    q=y;
                    break;
                }
            }
        }


        return "";
    }

    public static void main(String[] args) {
        disc(Test.getQipan(),Test.getX(),Test.getY(),Test.getcolor());

    }
}
