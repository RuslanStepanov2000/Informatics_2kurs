package com.company;

import jdk.internal.org.xml.sax.Attributes;
import jdk.internal.org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
    private static ArrayList<Person> personList = new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        MyXMLHandler handler = new MyXMLHandler();
        parser.parse("people.xml", handler);


        for (Person person : personList)
            System.out.println(String.format("Имя : %s, : %s, Пол : %s, Соревнования : %s", person.getName(), person.getBirthday(), person.getSex(), person.getEventList()));
    }

    static class MyXMLHandler extends DefaultHandler {
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("employee")) {
                String name = attributes.getValue("name");
                String birthday = attributes.getValue("birthday");
                String sex=attributes.getValue("s");
                personList.add(new Person(name, job));
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
