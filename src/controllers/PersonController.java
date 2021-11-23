/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import models.Person;

/**
 *
 * @author Edwin Niño
 */
public class PersonController {
    
    private final String path;

    public PersonController() {
        path="person.ser";
    }
    
    public void save(ArrayList<Person> people) throws IOException{
        FileOutputStream file = new FileOutputStream(path);
        ObjectOutputStream output = new ObjectOutputStream(file);
        output.writeObject(people);
        output.close();
        file.close();
    }
    
    public ArrayList<Person> read() throws IOException, ClassNotFoundException{
        FileInputStream file = new FileInputStream(path);
        ObjectInputStream input = new ObjectInputStream(file);
        ArrayList<Person> people = (ArrayList<Person>) input.readObject();
        input.close();
        file.close();
        return people;
    }
    
}
