package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class nomer5 {
    public static void main(String[] args) throws IOException {

        FileReader fr = new FileReader("input5.txt");
        BufferedReader br = new BufferedReader(fr);
        List<String> array = new ArrayList();
        String line = br.readLine();

        int i = 0;

        System.out.println("Begin");


        while (line != null) {
            array.add(line);
            System.out.println(array.get(i));
            i++;
            line = br.readLine();
        }

        System.out.println("Сортировка по алфавиту");
        Collections.sort(array);
        System.out.println(array);


        System.out.println("Сортировка по длине строки");
        array.sort(Comparator.comparingInt(String::length));
        System.out.println(array);


        System.out.println("Сортировка в лексикографичсеком порядке");
        array.sort(Comparator.naturalOrder());
        System.out.println(array);


        System.out.println("Сортировка по количеству заглавыных латинских букв в строке(по убыванию)");
        ComparatorUpper myPriceComparator = new ComparatorUpper();
        array.sort(myPriceComparator);
        System.out.println(array);

    }

    static class ComparatorUpper implements Comparator<String> {
        @Override
        public int compare(String s, String t1) {
            int a = 0, b = 0;
            for (int i = 0; i < s.length(); i++) {
                if (Character.isUpperCase(s.charAt(i))) a++;
            }
            for (int i = 0; i < t1.length(); i++) {
                if (Character.isUpperCase(t1.charAt(i))) b++;
            }
            return Integer.compare(b, a);
        }
    }
}

