package com.company;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private String birthday;
    private String sex;

    //Сразу создаем всем спортсменам пустой класс соревнований
    private List<Event> eventList=null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }







    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public void addEvent(Event event) {
        if (eventList == null) {
            eventList = new ArrayList<>();
        }
        this.eventList.add(event);
    }
}
