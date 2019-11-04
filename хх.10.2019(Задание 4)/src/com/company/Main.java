package com.company;
public class Main {
    public static void main(String[] args) {
        //Первое задание
        //а
        Methods regex = new Methods();
        System.out.println(regex.isString("abcd111111102019"));
        System.out.println();

        //б
        regex.searchInts("adq12kdf34k56");
        System.out.println();

        //в
        regex.searchDoubles("12m,3.73.hfp<834,7340d73loe38");
        System.out.println();

        //г
        String str="qwertyuiop qwertyuiopp wqwertyuioppp w wq wqqq qqqqq";
        regex.replaceString(str);
        System.out.println();

        //д
        regex.isGUID("e02fd0e4-00fd-090A-ca30-0d00a0038ba0");
        regex.isGUID("e02fd0e400fd090Aca300d00a0038ba0");
        System.out.println();

        //е
        System.out.println(regex.isColorHTML("#FFF12F"));
        System.out.println();

        //ж
            regex.folderAddress("C:\\Users\\stiwe\\Desktop\\Учеба\\инфа\\хх.10.2019(Задание 4)");
    }
}