/**
 * Dylan Olney
 * CS1450 M/W
 * Due July 6
 * Assignment 4
 * This program will read information from VendingMachineCars.txt to place into a vending machine object which is a 2D array that holds car objects. 
 * All car objects in the vending machine will then be displayed.
 * An inventory report will then be displayed as well.
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.lang.Comparable;
import java.util.ArrayList;
import java.util.Collections;

public class OlneyDylanAssignment4 {
    
    public static void main(String[] args) throws IOException {
        
        File fileName = new File("VendingMachineCars.txt");
        Scanner fileInput = new Scanner(fileName);
        
        VendingMachine carTower = new VendingMachine(fileInput.nextInt(), fileInput.nextInt());
        
        while(fileInput.hasNext()){
            int column = fileInput.nextInt();
            int row = fileInput.nextInt();
            double price = fileInput.nextDouble();
            int year = fileInput.nextInt();
            String manufacturer = fileInput.next();
            String model = fileInput.next();
            
            Car4 car = new Car4(price, year, manufacturer, model);
            
            carTower.addCarToTower(row, column, car);
        }
        
        carTower.displayTower();
        
        printInventory(carTower);
    }
    
    static void printInventory (VendingMachine vendingMachine){
        
        ArrayList<Car4> cars = new ArrayList<>();
        
        for(int row = 0; row < vendingMachine.getNumRows(); row++){
            for(int column = 0; column < vendingMachine.getNumColumns(); column++){
                if(vendingMachine.getCarInTower(row, column) != null){
                    cars.add(vendingMachine.getCarInTower(row, column));
                }
            }
        }
        Collections.sort(cars);
        
        System.out.println("*********************************************************");
        System.out.println("VENDING MACHINE INVENTORY");
        System.out.println("From Low to High Price");
        System.out.println("*********************************************************");
        for(int i = 0; i < cars.size(); i++){
            System.out.println(cars.get(i).toString());
        }
    }
}

class VendingMachine{
    
    private int numRows;
    private int numColumns;
    private Car4[][] tower;
    
    public VendingMachine (int numRows, int numColumns){
        
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.tower = new Car4 [numRows][numColumns];
    }
    
    public void addCarToTower (int row, int column, Car4 car){
        
        this.tower[row][column] = car;
    }
    
    public Car4 getCarInTower (int row, int column){
        
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
}

class Car4 implements Comparable<Car4>{
    
    private double price;
    private int year;
    private String manufacturer;
    private String model;
    
    public Car4 (double price, int year, String manufacturer, String model){
        
        this.price = price;
        this.year = year;
        this.manufacturer = manufacturer;
        this.model = model;
        
    }
    
    public String getManufacturer(){
        
        return this.manufacturer;
    }
    
    @Override
    public String toString(){
        
        return String.format("%-4d\t%-10s\t%-15s\t%-1.2f", year, manufacturer, model, price);
    
    }
    
    @Override
    public int compareTo(Car4 otherCar){
        
        if(this.price > otherCar.price){
            return 1;
        }
        else if(this.price == otherCar.price){
            return 0;
        }
        else{
            return -1;
        }
    }
}