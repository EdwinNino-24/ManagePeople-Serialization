/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenters;

import controllers.PersonController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import models.Person;

/**
 *
 * @author Edwin Niño
 */
public class Main {
    
    static ArrayList<Person> people = new ArrayList<Person>();
    Scanner scanner = new Scanner(System.in);
    
    public Main(){
        run();
    }
    
    public void run() {
        
        readInit();
        //createTestData();
        String banner =        "__________________________________________________________________________________________________\n" +
                               " ________________________________________________________________________________________________\n" +
                               "|                                         _______________                         [ManagePeople®]|\n" +
                               "|--------------------------------------- | MANAGE PEOPLE | --------------------------------------|\n" +
                               "|________________________________________________________________________________________________|\n" +
                               "__________________________________________________________________________________________________";
        int optionMenu = 0;
        String mainMenu =  " ________________________________________________________________________________________________\n" +
                               "|                                        ________________                                        |\n" +
                               "|-------------------------------------- | MENÚ PRINCIPAL | --------------------------------------|\n" +
                               "|________________________________________________________________________________________________|\n" +
                               " ________________________________________________________________________________________________\n" +
                               "|                         [--->] 1. Registrar persona nueva------------>(1)                      |\n" +
                               "|                         [--->] 2. Mostrar personas registradas------->(2)                      |\n" +
                               "|                         [--->] 3. Remover persona de los registros--->(3)                      |\n" +
                               "|                         [--->] 4. Cerrar el programa----------------->(4)                      |\n" +
                               "|________________________________________________________________________________________________|";
        String formatOption = 
                               "__________________________________________________________________________________________________";
        String format1 =   " ________________________________________________________________________________________________ ";
        String format2 =   "|________________________________________________________________________________________________|";
        String closeSucccessfully = " EL PROGRAMA SE HA CERRADO EXITOSAMENTE...";
        String optionMenuError = " SELECCIONE UNA OPCION QUE SE ENCUENTRE EN EL MENU...";
            
        System.out.println(banner);
        do {
            System.out.println(mainMenu);
            try{
                System.out.println(formatOption);
                optionMenu = Integer.parseInt(scanner.nextLine());
                System.out.println(formatOption);
            }
            catch(NumberFormatException x){ 
            }    
            
            switch(optionMenu) {
		case 1:
                    addPerson();
                    break;
                case 2:
                    showPeople();
                    break;
                case 3:
                    removePerson();
                    break;
                case 4:
                    System.out.println(formatOption);
                    System.out.println(closeSucccessfully);
                    System.out.println(formatOption);
                    break;
		default:
                    System.out.println(formatOption);
                    System.out.println(optionMenuError);
                    System.out.println(formatOption);
                    break;
            }
            
        }while(optionMenu != 4);
    
    }
    
    
    public void addPerson(){
        
        String formatOption = "__________________________________________________________________________________________________";
        String enterNames = " Ingrese sus nombres: ";
        String enterSurnames = " Ingrese sus apellidos: ";
        String enterNumberPhone = " Ingrese su numero celular: ";
        String successfulRegistration = " LA PERSONA SE HA REGISTRADO EXITOSAMENTE EN EL SISTEMA...";
        String navigation = "                     PRESIONE CUALQUIER TECLA PARA REGRESAR AL MENÚ PRINCIPAL.                     ";
        
        System.out.print(enterNames);
        String names = scanner.nextLine();
        System.out.println(formatOption);
        System.out.print(enterSurnames);
        String surnames = scanner.nextLine();
        System.out.println(formatOption);
        System.out.print(enterNumberPhone);
        String numberPhone = scanner.nextLine();
        
        Person person = new Person(names,surnames,numberPhone);
        people.add(person);
        saveData();
        
        System.out.println(formatOption);
        System.out.println(successfulRegistration);
        
        System.out.println(formatOption);
            System.out.println(navigation);
            System.out.println(formatOption);
            scanner.nextLine();
        
    }
    
    
    public void showPeople(){
        
        String formatOption = "__________________________________________________________________________________________________";
        String emptySystem = " AUN NO SE ENCUENTRAN REGISTROS EN EL SISTEMA...";
        int id;
        String messageId = "ID--> #";
        String navigation = "                     PRESIONE CUALQUIER TECLA PARA REGRESAR AL MENÚ PRINCIPAL                     ";
        
        if(people == null || people.isEmpty()){
            System.out.println(emptySystem);
        }
        else{
            id = 0;
            for (Person person : people) {
                System.out.println(messageId + id);
                System.out.println(person.toString());
                System.out.println();
                id++;
            }
        }
        
        System.out.println(formatOption);
        System.out.println(navigation);
        System.out.println(formatOption);
        scanner.nextLine();
        
    }
    
   
    public void removePerson(){
        
        String formatOption = "__________________________________________________________________________________________________";
        String emptySystem = " AUN NO SE ENCUENTRAN REGISTROS EN EL SISTEMA...";
        int id;
        String messageId = "ID--> #";
        String removeIdPerson = " Ingrese el ID de la persona a remover: ";
        String removeSuccessfully = " LA PERSONA SE HA REMOVIDO EXITOSAMENTE DEL SISTEMA...";
        String NonExistentId = " EL ID INGRESADO NO SE ENCUENTRA REGISTRADO EN EL SISTEMA...";
        String navigation = "                     PRESIONE CUALQUIER TECLA PARA REGRESAR AL MENÚ PRINCIPAL                     ";
        
        if(people == null || people.isEmpty()){
            System.out.println(emptySystem); 
        }
        else{
            try{
                id = 0;
                for (Person person : people) {
                    System.out.println(messageId + id);
                    System.out.println(person.toString());
                    System.out.println();
                    id++;
                }
                
                System.out.println(formatOption);
                System.out.print(removeIdPerson);
                int removeId = scanner.nextInt();
                System.out.println(formatOption);
                people.remove(removeId);
                System.out.println(removeSuccessfully);
                saveData();
            }
            catch(IndexOutOfBoundsException x){
                System.out.println(NonExistentId);
            }
        }
        
        System.out.println(formatOption);
        System.out.println(navigation);
        System.out.println(formatOption);
        scanner.nextLine();
        
    }
        
        
    public void createTestData() {
        
        Person firstPerson = new Person("EDUARDO","PEREZ","3294734532");
        people.add(firstPerson);
        
        Person secondPerson = new Person("CLAUDIO","GUTIERREZ","3294734532");
        people.add(secondPerson);
        
        Person thirdPerson = new Person("STAR","BUTTERFLY","3294734532");
        people.add(thirdPerson);
        
    }  
    
    public void saveData(){
        
        PersonController personController = new PersonController();
                try {
                    personController.save(people);
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }   
    }
    
    public void readInit(){
        PersonController personController = new PersonController();
        try {
            people = personController.read();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public static void main(String[] args) {
        
        Main main = new Main();
        
    }
    
}
