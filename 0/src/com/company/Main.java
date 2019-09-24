package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------\n"+
                           "№   |слово                                                    |           |              Количество информации                    \n" +
                           "--------------------------------------------------------------------------------------------------------------------------------\n" +
                           "    |                                                         |           | кол-во    | байт, размер |     бит,     |   бит,      \n" +
                           "    |                                                         | палиндром |  символов | в программе  |  по Хартли   | по Шеннону  \n" +
                           "--------------------------------------------------------------------------------------------------------------------------------");
        try(BufferedReader reader = new BufferedReader(new FileReader("input.txt")))
        {
           String line;
           int i=1;
           String space=" ";
           while ((line = reader.readLine())!=null){
               System.out.println();
               System.out.printf("%-4d",i);
               System.out.print("|");
               System.out.printf("%-57s",line);
               System.out.print("|");
               System.out.printf("%-11s",palindrom2(line));
               System.out.print("|");
               System.out.printf("%-11d",line.length());
               System.out.print("|");
               System.out.printf("%14d",ByteSize(line));
               System.out.print("|");
               System.out.printf("%14.2f", Hartley(line));
               System.out.print("|");
               System.out.printf("%14.9f",Shannon(line));
               System.out.println();
               System.out.print("----------------------------------------------------------------------------"
               +"--------------------------------------------------------------");
               i++;
           }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

    }


    /*static String Palindrom(String line) {
        char[] chararray = line.toCharArray();
        int check = 0;
        for (int i = 0; i < line.length(); i++) {
            if (chararray[0] == line.charAt(line.length()-1)) {
                check=1;
            }
            else check=0;
        }
        if (check==1) return ("+"); else return ("-");

    }
    */
    static String palindrom2(String k) {


        int flag=0;
        for(int i=0; i<k.length();i++){
            if (k.charAt(i) != k.charAt(k.length()- i-1)) {
            flag=0;
            }
            else {
                flag=1;
            }

        }
        if (flag==1) return ("+"); else return ("-");
    }



    static int ByteSize(String line){
        int size;
        size=line.getBytes().length;
        return size;
    }

    static double Hartley(String line) {
        int charsCount = (int) line.chars().distinct().count();//количество различных букв в слове, т.е мощность алфавита
        double I = (Math.log(charsCount) / Math.log(2));
        //log a (b) = log c (b) / log c (a)
        return I;
    }

     static double Shannon(String line) {
         int size=line.length();
         double shannon_val = 0;
         for (int i = 0; i < size; i++) {
             double p = line.charAt(i) / size;
             shannon_val += p * Math.log10(p) / Math.log10(2);
         }
         shannon_val *= (-1);
         return shannon_val;
     }
}


