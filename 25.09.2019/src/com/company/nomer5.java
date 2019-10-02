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

        FileReader fr=new FileReader("input5.txt");
        BufferedReader br=new BufferedReader(fr);
        List<String> array=new ArrayList();
        String line=br.readLine();

        int i=0;

        System.out.println("Begin");


        while(line!=null){
            array.add(line);
            System.out.println(array.get(i));
            i++;
            line = br.readLine();
        }

        System.out.println("Сортировка по алфавиту");

        Collections.sort(array);
        System.out.println(array);

        Comparator<ArrayList> comparator = new Comparator<ArrayList>() {
            @Override
            public int compare(ArrayList list, ArrayList o2) {
                return array.getId().compareTo(o2.getId());
            }
        };

    }
}
