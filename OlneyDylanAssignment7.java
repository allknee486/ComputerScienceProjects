/**
 * Dylan Olney
 * CS1450 M/W
 * Due July 20
 * Assignment 7
 * This program will add a car wash object with two conveyors to wash all cars from assignment 4. 
 * All car objects will be added to a waiting line and given priority washing with the oldest being first.
 * The cars will then need to be washed in the priority provided but also split accordingly based on whether the car needs the basic or unlimited wash option.
 */

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.lang.Comparable;

public class OlneyDylanAssignment7 {
 
    public static void main(String[] args) throws IOException {
        
        File fileName = new File("VendingMachineCars7.txt");
        Scanner fileInput = new Scanner(fileName);
        
        VendingMachine7 carTower = new VendingMachine7(fileInput.nextInt(), fileInput.nextInt());
        
        while(fileInput.hasNext()){
            int column = fileInput.nextInt();
            int row = fileInput.nextInt();
            String washPlan = fileInput.next();
            double price = fileInput.nextDouble();
            int year = fileInput.nextInt();
            String manufacturer = fileInput.next();
            String model = fileInput.next();
            
            Car7 car = new Car7(price, year, washPlan, manufacturer, model);
            
            carTower.addCarToTower(row, column, car);
        }
        
        carTower.displayTower();
        
        CarWash carWash = new CarWash();
        
        CarWashController.moveCarsToWaitingLine(carTower, carWash);
        
        carTower.displayTower();
        
        CarWashController.moveCarsIntoCarWash(carWash);
        
        CarWashController.displayBasicConveyor(carWash);
        
        CarWashController.displayUnlimitedConveyor(carWash);
        
        CarWashController.moveCarsBackToVendingMachine(carTower, carWash);
        
        carTower.displayTower();
    }
}

class VendingMachine7{
    
    private int numRows;
    private int numColumns;
    private Car7[][] tower;
    
    public VendingMachine7 (int numRows, int numColumns){
        
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.tower = new Car7 [numRows][numColumns];
    }
    
    public void addCarToTower (int row, int column, Car7 car){
        
        this.tower[row][column] = car;
    }
    
    public Car7 getCarInTower (int row, int column){
        
        return this.tower[row][column];
    }
    
    public int getNumRows(){
        
        return this.numRows;
    }
    
    public int getNumColumns(){
        
        return this.numColumns;
    }
    
    //Method to display each position in the 2D array. Returns blank if null and the car manufacturer if not
    public void displayTower(){
        
        for(int column = 0; column < tower[0].length; column++){
            System.out.printf("   Column %d", column);
        }
        System.out.println("\n");
        for(int row = 0; row < tower.length; row++){
            System.out.printf("Row %d ", row);
            for(int column = 0; column < tower[row].length; column++){
                if(tower[row][column] != null){
                    System.out.printf("%s\t", tower[row][column].getManufacturer());
                }
                else{
                    System.out.printf("-----\t");
                }
            }
            System.out.println("\n");
        }
    }
    
    public Car7 removeCarFromTower(int row, int column){

        Car7 tempCar;
        
        //Checks if the position is not already empty in the tower
        if(tower[row][column] != null){
            //Sets temp car to the car in that position and changes that position in tower to null
            tempCar = tower[row][column];
            tower[row][column] = null;
            return tempCar;
        }
        else{
            return null;
        }
    }
    
    public int[] findOpenLocation(){
        //temp variables to iterate through and a tempArray to return those values in
        int row = 0;
        int column = 0;
        int[] tempArray;
        
        //Nested for loops to iterate through each row in a column then move to the next column
        for(column = 0; column < numColumns; column++){
            for(row = 0; row < numRows; row++){
                //Checks if that position is empty and returns that value via temp array then ends method
                if(tower[row][column] == null){
                    tempArray = new int[]{ row, column};
                    return tempArray;
                }
            }
        }
        //Return case if the tower has no empty spaces
        return null;
    }
}

class Car7 implements Comparable<Car7>{
    
    private double price;
    private int year;
    private String manufacturer;
    private String model;
    private String washPlan;
    
    public Car7 (double price, int year, String washPlan, String manufacturer, String model){
        
        this.price = price;
        this.year = year;
        this.manufacturer = manufacturer;
        this.model = model;
        this.washPlan = washPlan;
        
    }
    
    public String getManufacturer(){
        
        return this.manufacturer;
    }
    
    public int getYear(){
        return this.year;
    }
    
    public String getModel(){
        return this.model;
    }
    
    public String getWashPlan(){
        return this.washPlan;
    }
    
    @Override
    public String toString(){
        
        return String.format("%-4d\t%-10s\t%-15s\t%-1.2f", year, manufacturer, model, price);
    
    }
    
    @Override
    public int compareTo(Car7 otherCar){
        
        if(this.year > otherCar.year){
            return 1;
        }
        else if(this.year == otherCar.year){
            return 0;
        }
        else{
            return -1;
        }
    }
}

class CarWash{
    
    private PriorityQueue<Car7> waitingLine;
    private Queue<Car7> unlimitedConveyor;
    private Queue<Car7> basicConveyor;
    
    //Constructor for CarWash
    public CarWash(){
        waitingLine = new PriorityQueue<>();
        unlimitedConveyor = new LinkedList<>();
        basicConveyor = new LinkedList<>();
    }
    
    public boolean isWaitingLineEmpty(){
        return waitingLine.isEmpty();
    }
    
    public void addCarToWaitingLine(Car7 car){
        waitingLine.offer(car);
    }
    
    public Car7 removeCarFromWaitingLine(){
        return waitingLine.poll();
    }
    
    public int waitingLineSize(){
        return waitingLine.size();
    }
    
    public boolean isUnlimitedConveyorEmpty(){
        return unlimitedConveyor.isEmpty();
    }
    
    public void addCarToUnlimitedLine(Car7 car){
        unlimitedConveyor.offer(car);
    }
    
    public Car7 removeCarFromUnlimitedLine(){
        return unlimitedConveyor.poll();
    }
    
    public int unlimitedLineSize(){
        return unlimitedConveyor.size();
    }
    
    public boolean isBasicConveyorEmpty(){
        return basicConveyor.isEmpty();
    }
    
    public void addCarToBasicLine(Car7 car){
        basicConveyor.offer(car);
    }
    
    public Car7 removeCarFromBasicLine(){
        return basicConveyor.poll();
    }
    
    public int basicLineSize(){
        return basicConveyor.size();
    }
}

class CarWashController{
    
    public static void moveCarsToWaitingLine(VendingMachine7 vendingMachine, CarWash carWash){
        
        //Nested for loop to check each column in a row before moving to the next row
        for(int row = 0; row < vendingMachine.getNumRows(); row++){
            for(int column = 0; column < vendingMachine.getNumColumns(); column++){
                //Checks if the there is a car in that position and moves the car to the waiting line
                if(vendingMachine.getCarInTower(row, column) != null){
                    System.out.printf("Moved to waiting line %d %s %s\n", vendingMachine.getCarInTower(row, column).getYear(), vendingMachine.getCarInTower(row, column).getManufacturer(), 
                     vendingMachine.getCarInTower(row, column).getModel());
                    carWash.addCarToWaitingLine(vendingMachine.removeCarFromTower(row, column));
                }
            }
        }
    }
    
    public static void moveCarsIntoCarWash(CarWash carWash){
        
        Car7 tempCar;
        //While loop to continue iterating until the waiting line is empty
        while(carWash.isWaitingLineEmpty() != true){
           tempCar = carWash.removeCarFromWaitingLine();
           
           //If/else statement checks if car has unlimited wash plan and sends it there, otherwise send it to the basic line
           if(tempCar.getWashPlan().equalsIgnoreCase("Unlimited")){
               carWash.addCarToUnlimitedLine(tempCar);
           }
           else{
               carWash.addCarToBasicLine(tempCar);
           }
        }
    }
    
    public static void displayBasicConveyor(CarWash carWash){
        
        Car7 tempCar;
        Queue<Car7> tempQueue = new LinkedList<>();
        //While loop to continue iterating until BasicConveyor is empty 
        while(carWash.isBasicConveyorEmpty() != true){
            tempCar = carWash.removeCarFromBasicLine();
            
            System.out.printf("On basic wash conveyor %d %s %s\n", tempCar.getYear(), tempCar.getManufacturer(), 
                     tempCar.getModel());
            tempQueue.add(tempCar);
        }
        
        //Moves all cars back to the original Queue as tempQueue will be removed after end of method
        while(tempQueue.isEmpty() != true){
            carWash.addCarToBasicLine(tempQueue.poll());
        }
    }
    
    public static void displayUnlimitedConveyor(CarWash carWash){
        
        Car7 tempCar;
        Queue<Car7> tempQueue = new LinkedList<>();
        //While loop to continue iterating until UnlimitedConveyor is empty
        while(carWash.isUnlimitedConveyorEmpty() != true){
            tempCar = carWash.removeCarFromUnlimitedLine();
            
            System.out.printf("On unlimited wash conveyor %d %s %s\n", tempCar.getYear(), tempCar.getManufacturer(), 
                     tempCar.getModel());
            tempQueue.add(tempCar);
        }
        
        //Moves all cars back to the original Queue as tempQueue will be removed after end of method
        while(tempQueue.isEmpty() != true){
            carWash.addCarToUnlimitedLine(tempQueue.poll());
        }
    }
    
    public static void moveCarsBackToVendingMachine(VendingMachine7 vendingMachine, CarWash carWash){
        
        Car7 tempCarUnlimited;
        Car7 tempCarBasic;
        int[] tempArray;
        //While loop that continues until both basic and unlimited conveyor are empty
        while(carWash.isBasicConveyorEmpty() != true || carWash.isUnlimitedConveyorEmpty() != true){
            tempCarUnlimited = carWash.removeCarFromUnlimitedLine();
            tempCarBasic = carWash.removeCarFromBasicLine();
            //If statement moves car from unlimited line back into vending machine as long as there is an actual car object
            if(tempCarUnlimited != null){
                tempArray  = vendingMachine.findOpenLocation();
                System.out.printf("Reloading: %d %s %s\n", tempCarUnlimited.getYear(), tempCarUnlimited.getManufacturer(), 
                     tempCarUnlimited.getModel());
                vendingMachine.addCarToTower(tempArray[0], tempArray[1], tempCarUnlimited);
            }
            //If statement moves car from basic line back into vending machine as long as there is an actual car object
            if(tempCarBasic != null){
                tempArray  = vendingMachine.findOpenLocation();
                System.out.printf("Reloading: %d %s %s\n", tempCarBasic.getYear(), tempCarBasic.getManufacturer(), 
                     tempCarBasic.getModel());
                vendingMachine.addCarToTower(tempArray[0], tempArray[1], tempCarBasic);
            }
        }
    }
}