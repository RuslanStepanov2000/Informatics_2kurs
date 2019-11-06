package com.company;

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
