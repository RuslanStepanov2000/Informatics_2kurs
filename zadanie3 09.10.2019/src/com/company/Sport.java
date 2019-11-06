package com.company;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public void inputAthlete() {
        String name;
        String birtday;
        String gender;
        List<Event> eventList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя спортсмена:");
        name = scanner.next();
        System.out.print("Введите дату рождения cпортсмена (гггг-мм-дд): ");
        birtday = scanner.next();
        while (true){
            System.out.print("Введите пол (м/ж): ");
            gender = scanner.next();
            if (gender.equals("м")||gender.equals("ж")){
                break;
            }
        }
        while (true) {
            System.out.println("Хотите добавить соревнования портсмену? (да/нет)");
            String fl = scanner.next();
            if (fl.equals("да")) {
                String place;
                int year;
                int resoult;
                String award;

                System.out.print("Введите место проведения соревнования: ");
                place=scanner.next().toLowerCase();
                while (true){
                    System.out.print("Введите год проведения соревнования: ");
                    String yearString=scanner.next();
                    if (isNumber(yearString)){
                        year=Integer.parseInt(yearString);
                        break;
                    }
                }
                while (true){
                    System.out.print("Введите результат спортсмена на соревнованиях: ");
                    String resoultString=scanner.next();
                    if (isNumber(resoultString)){
                        resoult=Integer.parseInt(resoultString);
                        break;
                    }
                }
                System.out.print("Введите полученную медаль на соревнованиях (gold/silver/bronze/no medals): ");
                award=scanner.next();

                Event event=new Event();
                event.setPlace(place);
                event.setYear(year);
                event.setAward(award);
                event.setResult(resoult);
                eventList.add(event);
            } else if (fl.equals("нет")) {
                break;
            } else {
                continue;
            }
        }
        Person Person=new Person();
        Person.setName(name);
        Person.setBirthday(birtday);
        Person.setSex(gender);
        Person.setEventList(eventList);
        System.out.println(Person.toString());
    }

    private boolean isNumber(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    public List<Person> getPersonList() {
        return personList;
    }

    public void newXmlFile(){
        try {
            Document document=DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element team=document.createElement("team");
            document.appendChild(team);
            for (Person a:personList){
                Element Person=document.createElement("sportsmen");
                Attr name=document.createAttribute("name");
                name.setTextContent(a.getName());
                Attr numberOfEvent=document.createAttribute("numberOfEvent");
                numberOfEvent.setTextContent(String.valueOf(a.getEventList().size()));
                List<Event> eventList=a.getEventList();
                int sumResoultInt=0;
                for (Event event:eventList){
                    sumResoultInt+=event.getResult();
                }
                Attr sumResoult=document.createAttribute("sumResoult");
                sumResoult.setTextContent(String.valueOf(sumResoultInt));
                Person.setAttributeNode(name);
                Person.setAttributeNode(numberOfEvent);
                Person.setAttributeNode(sumResoult);
                team.appendChild(Person);
            }

            Transformer transformer= TransformerFactory.newInstance().newTransformer();
            DOMSource source=new DOMSource(document);
            StreamResult result=new StreamResult(new File("newFile.xml"));
            transformer.transform(source, result);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}

