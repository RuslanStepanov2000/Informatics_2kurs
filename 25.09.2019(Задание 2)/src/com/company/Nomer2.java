package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Stack;

public class Nomer2 {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("input.txt"), "UTF-8");
            Stack stack = new Stack();


            int age;
            Date born = new Date();
            String sex;
            String name;
            String surname;


            int i = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] spitLine = line.split(" ");
                try {
                    People people = new People(spitLine[0], spitLine[1], spitLine[2], born = new SimpleDateFormat("dd/MM/yyyy").parse(spitLine[3]), 10);

                    stack.add(people);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static class People {


        private int age;
        private Date born = new Date();
        private String sex;
        private String name;
        private String surname;


        public People(String name, String surname, String sex, Date born, int age) {
            this.name = name;
            this.surname = surname;
            this.sex = sex;
            this.born = born;
            this.age = age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public void setBorn(Date born) {
            this.born = born;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public Date getBorn() {
            return born;
        }

        public String getSex() {
            return sex;
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }
    }
}