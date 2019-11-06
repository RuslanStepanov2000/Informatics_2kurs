package com.company;

public class Main {

    public static void main(String[] args) {
        //Номер 1
        newclass.readAndPrintXML();
        //Номер 2
        Sport sp = new Sport();
        sp.readDoc("nomer2.xml");
        System.out.println("Successful read");
        sp.print3a();
        System.out.println("Successful 3a");
        sp.print3b();
        System.out.println("Successful 3b");
        sp.print3c();
        System.out.println("Successful 3c");
        sp.inputAthlete();
        System.out.println("Successful 4");
        sp.newXmlFile();
        System.out.println("Successful 5");
    }
}
