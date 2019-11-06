package com.company;


import jdk.nashorn.internal.parser.JSONParser;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    private static ArrayList<Person> personList = new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        System.out.println("SAXParserFactory created");

        XMLHandler handler = new XMLHandler();
        File input = new File("people.xml");
        parser.parse(input, handler);
        System.out.println("Reading complete");


        for (Person person : personList) {
            System.out.println(String.format("Имя: %s, Дата рождения: %s, Пол: %s, Соревнования: %s", person.getName(), person.getBirthday(), person.getSex(), person.getEventList()));
        }
        System.out.println("Cycle end");
    }

    static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("sportsman")) {
                Person thisPerson = new Person();
                thisPerson.setName(attributes.getValue("name"));
                thisPerson.setBirthday(attributes.getValue("birthday"));
                thisPerson.setSex(attributes.getValue("s"));

                List<Event> events = new ArrayList<>();
                if (qName.equals("event")) {
                        Event thisEvent = new Event();
                        thisEvent.setPlace(attributes.getValue("place"));
                    thisEvent.setPlace("ibgufondspmfjhbjpofjbpwfomek");
                    System.out.println(attributes.getValue("place"));
                        thisEvent.setYear(Integer.parseInt(attributes.getValue("yaer")));
                    System.out.println(attributes.getValue("yaer"));
                        events.add(thisEvent);
                }
                thisPerson.setEventList(events);
                personList.add(thisPerson);
            }
        }
    }
}


    /*

        for (int i = 0; i < personList.getLength(); i++) {
            if (Person.getNodeName().equals("sportsman")) {
                //Заполнение информации о человеке
                Person thisPerson = new Person();
                thisPerson.setName();
                thisPerson.setBirthday();
                thisPerson.setSex();

                //Заполнение информации о соревнованиях
                List<Event> events = new ArrayList<>();


                for (int j = 0; j < events.size(); j++) {
                    Node event = events.indexOf(j);
                    if (event.getNodeName().equals("event")) {
                        Event thisEvent = new Event();
                        thisEvent.setPlace();
                        thisEvent.setYear();
                        thisEvent.setResult();
                        thisEvent.setAward();
                        events.add(thisEvent);
                    }
                }
                thisPerson.setEventList(events);
                this.personList.add(thisPerson);


    }
    private static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("employee")) {
                String name = attributes.getValue("name");
                String job = attributes.getValue("job");
                employees.add(new Employee(name, job));
            }
        }

        */
