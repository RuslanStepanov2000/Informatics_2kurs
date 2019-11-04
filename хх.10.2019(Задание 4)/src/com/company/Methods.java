package com.company;

import com.sun.istack.internal.NotNull;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Methods {

    public boolean isString(String str) {
        return Pattern.matches("abcd1{7}02019", str);
    }

    public void searchInts(String str) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);

        ArrayList<Integer> arr = new ArrayList<>();
        while (matcher.find()) {
            arr.add(Integer.parseInt(matcher.group())); //разбиение на группы по числам, условие чисел \\d в паттерне
        }
        if (arr.isEmpty()) {
            System.out.println("No numbers");
            return;
        }
        int sum = 0;
        int max = arr.get(0);
        for (int a : arr) {
            sum += a;
            if (max < a) {
                max = a;
            }
            System.out.println(a);
        }
        System.out.println("Max: " + max + "|Sum: " + sum );
    }

    public void searchDoubles(String str) {
        Pattern pattern = Pattern.compile("\\d+[,.]\\d+");
        Matcher matcher = pattern.matcher(str);

        ArrayList<Double> doubleList = new ArrayList<>();
        while (matcher.find()) {
            String tmp=matcher.group().replace(",",".");
            doubleList.add(Double.parseDouble(tmp));
        }
        for (double a : doubleList) {
            System.out.println(a);
        }
    }

    public void replaceString(String str) {
                //Замена на звездочку
        Pattern pattern = Pattern.compile("[a-z]{10,}");
        Matcher matcher = pattern.matcher(str);
        String s = "";
        while (matcher.find()) {
            s += matcher.replaceAll("*");
        }
        //System.out.println(s);


                //Замена на первую букву
        s = "";
        matcher = pattern.matcher(str); //Ищем слова больше 10символов
        while (matcher.find()) {
            s += matcher.group() + " ";
        }
        //Создали строку со словами 10и больше символов
        //Не понимаю как сделать нормальное выражение, которое решает сразу все проблемы, поэтому костыль
        Pattern pattern2 = Pattern.compile("\\b[a-z]");
        Matcher matcher2=pattern2.matcher(s);
        String s2="";
        while (matcher2.find()) {
            s2 += matcher2.group() + " ";
        }
        System.out.println(s2);




    }


    public void isGUID(String str) {
        Pattern pattern = Pattern.compile("(?i)[a-f\\d]{8}-([a-f\\d]{4}-){3}[a-f\\d]{12}");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) System.out.println("строка " + str + "\n Является GUID");
        else System.out.println("строка " + str + "\n НЕ является GUID");
    }

    public boolean isColorHTML(String str) {
        if (str.charAt(0) == '#' && numberColorHTML(str.substring(1, str.length() - 1)) && str.length() == 7) {
            System.out.println("Код цвета написан праивльно");
            return true;
        }
        return false;
    }
    private boolean numberColorHTML(String str) {
        return str.matches("^[0-9A-F]+$");

    }

    public void folderAddress(String str) {
        str=str.replace("\\", "/");
        System.out.println(str);


        Pattern pattern = Pattern.compile("/");
        //Pattern pattern = Pattern.compile("");
        Matcher matcher = pattern.matcher(str);

        ArrayList<String> arrayList=new ArrayList<>();
        System.out.println("Working");

        while (matcher.find()) {
            arrayList.add(matcher.group());
        }
        for (String s:arrayList){
            System.out.println(s+" ");
        }
    }

}
