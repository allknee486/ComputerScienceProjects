/**
 * Dylan Olney
 * CS1450 M/W
 * Due Jun 26
 * Assignment 2
 * This program will read vehicles from a file, add them to an array using the superclass, and display each object's information. The program will then add all cars and motorcycles to a repair shop array and display the object information of those objects added.
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class OlneyDylanAssignment2 {
    
    public static void main(String[] args) throws IOException {
        
        File fileName = new File("Vehicles.txt");
        Scanner inputFromFile = new Scanner(fileName);
        
        Vehicle vehicleArray[] = new Vehicle[inputFromFile.nextInt()];
        
        //For loop to read each object from file and add to the vehicleArray
        for(int i = 0; i < vehicleArray.length; i++){
            
            String carType = inputFromFile.next();
            
            if(carType.equalsIgnoreCase("Bus")){
                vehicleArray[i] = new Bus(inputFromFile.nextLine());
            }
            else if(carType.equalsIgnoreCase("Car")){
                vehicleArray[i] = new Car(inputFromFile.nextLine());
            }
            else if(carType.equalsIgnoreCase("Motorcycle")){
                vehicleArray[i] = new Motorcycle(inputFromFile.nextLine());
            }
            else if(carType.equalsIgnoreCase("Train")){
                vehicleArray[i] = new Train(inputFromFile.nextLine());
            }
            else{
                System.out.println("Error not valid object");
                carType = inputFromFile.nextLine();
            }
            
        }
        
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Type\t\tName\tSound");
        System.out.println("----------------------------------------------------------------------------");
        
        //For loop to display type, name, and sound information of each object in the vehicleArray
        for(int i = 0; i < vehicleArray.length; i++){
            
            System.out.println(vehicleArray[i].getType() + "\t\t" + 
                    vehicleArray[i].getName() + "\t" + vehicleArray[i].sound());
        }
        
        //Create new repair shop object for cars and motorcycles
        RepairShop repairShop = new RepairShop();
        
        //Copying all cars and motorcycles from the vehicleArray to the vehiclesToRepair array and printing details after
        repairShop.fillRepairShop(vehicleArray);
        repairShop.printRepairShopDetails();
        
        inputFromFile.close();
    }
}

 class Vehicle{
     
     String type;
     String name;
      
     public Vehicle(String type, String name){
         this.type = type;
         this.name = name;
     }
     
     public Vehicle(String name){
         this.name = name;
         this.type = "Default";
     }
     
     public String getType(){
         return this.type;
     }
     
     public String getName(){
         return this.name;
     }
     
     public String sound(){
         return "Noise";
     }
 }

class Bus extends Vehicle{
       
    public Bus(String name){
        super(name);
        this.type = "Bus";
    }
    
    @Override
    public String sound(){
        return "Rum-rum-rum-rummm";
    }
}

class Car extends Vehicle{
    
    public Car(String name){
        super(name);
        this.type = "Car";
    }
    
    @Override
    public String sound(){
        return "Vroom-vroom-vroommm";
    }
}

class Motorcycle extends Vehicle{
    
    public Motorcycle(String name){
        super(name);
        this.type = "Motorcycle";
    }
    
    @Override
    public String sound(){
        return "Bom-bom-bom";
    }
}

class Train extends Vehicle{
    
    public Train(String name){
        super(name);
        this.type = "Train";
    }
    
    @Override
    public String sound(){
        return "Chooga-chooga-chooga";
    }
}

class RepairShop{
    
    private int numCars = 0;
    private int numMotorcycles = 0;
    private Vehicle[] vehiclesToRepair;
    
    public RepairShop(){
        
    }
    
    public void fillRepairShop(Vehicle[] vehicles){
        
        int secondArrayI = 0;
        
        for(int i = 0; i < vehicles.length; i++){
            if(vehicles[i] instanceof Car){
                numCars ++;
            }
            else if(vehicles[i] instanceof Motorcycle){
                numMotorcycles ++;
            }
        }
        
        vehiclesToRepair = new Vehicle[numCars + numMotorcycles];
        
        for(int i = 0; i <vehicles.length; i++){
            if((vehicles[i] instanceof Car) || (vehicles[i] instanceof Motorcycle)){
                vehiclesToRepair[secondArrayI] = vehicles[i];
                secondArrayI ++;
            }
        }
    }
    
    public void printRepairShopDetails(){
        
        System.out.println("--------------------------------------------");
        System.out.println("Car and Motorcycle Repair Shop");
        System.out.println("--------------------------------------------");
        System.out.println("There are " + numCars + " cars");
        System.out.println("There are " + numMotorcycles + " motorcycles");
        
        for(int i = 0; i < vehiclesToRepair.length; i++){
            System.out.println("Vehicle [" + i + "] is " + 
                    vehiclesToRepair[i].getType() + " " + vehiclesToRepair[i].getName());
        }
    }
}