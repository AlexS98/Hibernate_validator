package main;

import validate.Contractor;
import validate.House;
import validate.Person;

import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args){
        House myHouse = new House(2, new Date(1151400000000L), Contractor.BudAlliance, 1532.2,
                new ArrayList<>(), new Person("Adam","Smith", 40), 1);
        myHouse.dwellers.add(myHouse.architect);
        myHouse.dwellers.add(new Person("Ann", "Luis", 25));
        myHouse.dwellers.add(new Person("Kate", "Lux", 25));
        System.out.println(myHouse.toString());
        System.out.println(myHouse.showDwellers());
    }
}
