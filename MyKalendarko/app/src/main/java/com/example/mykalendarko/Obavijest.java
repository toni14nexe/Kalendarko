package com.example.mykalendarko;

public class Obavijest {
    int id;
    String name, dodatno, day, month, year, godisnje;

    public Obavijest(int id, String name, String dodatno, String day, String month, String year, String godisnje){
        this.id = id;
        this.name = name;
        this.dodatno = dodatno;
        this.day = day;
        this.month = month;
        this.year = year;
        this.godisnje = godisnje;
    }
    public Obavijest(){};

    public String getName(){
        return name;
    }

    public void setID(int id){
        this.id = id;
    }

    public int getID(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDodatno(){
        return dodatno;
    }

    public void setDodatno(String dodatno){
        this.dodatno = dodatno;
    }

    public String getDay(){
        return day;
    }

    public void setDay(String day){
        this.day = day;
    }

    public String getMonth(){
        return month;
    }

    public void setMonth(String month){
        this.month = month;
    }

    public String getYear(){
        return year;
    }

    public void setYear(String year){
        this.year = year;
    }

    public String getGodisnje(){
        return godisnje;
    }

    public void setGodisnje(String godisnje){
        this.godisnje = godisnje;
    }
}
