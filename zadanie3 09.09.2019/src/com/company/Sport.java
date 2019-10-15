package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sport {
    private List<Person> personList = null;

    public void readDoc(String file) {
        personList = new ArrayList<>();

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(file);

            Node root = document.getDocumentElement();
            NodeList personList = root.getChildNodes();

            for (int i = 0; i < personList.getLength(); i++) {
                Node Person = personList.item(i);
                if (Person.getNodeName().equals("sportsman")) {
                    //Заполнение информации о человеке
                    Person thisPerson = new Person();
                    thisPerson.setName(Person.getAttributes().getNamedItem("name").getNodeValue());
                    thisPerson.setBirthday(Person.getAttributes().getNamedItem("birthday").getNodeValue());
                    thisPerson.setSex(Person.getAttributes().getNamedItem("s").getNodeValue());  //"s" is sex field in nomer2.xml

                    //Заполнение информации о соревнованиях
                    List<Event> events = new ArrayList<>();
                    NodeList eventList = Person.getChildNodes();

                    for (int j = 0; j < eventList.getLength(); j++) {
                        Node event = eventList.item(j);
                        if (event.getNodeName().equals("event")) {
                            Event thisEvent = new Event();
                            thisEvent.setPlace(event.getAttributes().getNamedItem("place").getNodeValue());
                            thisEvent.setYear(Integer.parseInt(event.getAttributes().getNamedItem("year").getNodeValue()));
                            thisEvent.setResult(Integer.parseInt(event.getChildNodes().item(1).getChildNodes().item(0).getNodeValue()));
                            thisEvent.setAward(event.getChildNodes().item(3).getChildNodes().item(0).getNodeValue());
                            events.add(thisEvent);
                        }
                    }
                    thisPerson.setEventList(events);
                    this.personList.add(thisPerson);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print3a() {
        for (Person pers : this.personList) {
            if (pers.getSex().equals("м")) {
                System.out.println("Name: " + pers.getName() + "  Birthday:" + pers.getBirthday());
            }
        }
    }

    public void print3b() {
    for(Person pers : this.personList)
        if( (pers.getSex().equals("ж")) && (yearBirthday(pers.getBirthday())<1985) ){
            System.out.println("Name: "+pers.getName()+" Bithday"+pers.getBirthday()+" Medals count:"+medalCount(pers.getEventList()) );
        }
    }


    public void print3c() {
        for (Person pers : personList) {
        boolean flag = false;
        Event event = null;
        for (Event e : pers.getEventList()) {
            if (  (e.getPlace().equals("москва"))  &&  (e.getYear() == 2002) ) {
                event = e;
                flag = true;
            }
        }
        if (flag) {
            System.out.println("Name: " + pers.getName() + " Result: " + event.getResult());
        }
    }
    }


    private String medalCount(List<Event> events) {
        int gold = 0;
        int silver = 0;
        int bronze = 0;
        for (Event e : events) {
            switch (e.getAward()) {
                case "gold":
                    gold++;
                    break;
                case "silver":
                    silver++;
                    break;
                case "bronze":
                    bronze++;
                    break;
            }
        }
        return "    Gold: " + gold + "    Silver: " + silver + "   Bronze: " + bronze;
    }
    private int yearBirthday(String str) {
        //Разбиваем строку на массив строк гггг-мм-дд
        String[] s = str.split("-");
        return Integer.parseInt(s[0]);
    }
}
