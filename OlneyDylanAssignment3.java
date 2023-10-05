/**
 * Dylan Olney
 * CS1450 M/W
 * Due Jun 29
 * Assignment 3
 * This program will read information from animals.txt to create a polymorphic Animals array that holds all animals from the file. 
 * All the animals in the file will then be displayed using the displayAnimal method. The animals that can climb will then be added to
 * an ArrayList and the ArrayList will then be displayed. The final part will find the most skilled animal's index position and 
 * that animal will then be displayed as the most skilled.
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class OlneyDylanAssignment3 {
    
    public static void main(String[] args) throws IOException {
        
        File fileName = new File("Animals.txt");
        Scanner fileInput = new Scanner(fileName);
        
        Animal animalArray[] = new Animal[fileInput.nextInt()];
        
        for(int i = 0; i < animalArray.length; i++){
            
            String animalType = fileInput.next();
            
            String name = fileInput.next();
            int swimSpeed = fileInput.nextInt();
            int runSpeed = fileInput.nextInt();
            int climbSpeed = fileInput.nextInt();
            
            switch(animalType){
                case ("alligator"):
                    animalArray[i] = new Alligator(name, swimSpeed, runSpeed);
                    break;
                case ("bear"):
                    animalArray[i] = new Bear(name, swimSpeed, runSpeed, climbSpeed);
                    break;
                case ("giraffe"):
                    animalArray[i] = new Giraffe(name, runSpeed);
                    break;
                case ("monkey"):
                    animalArray[i] = new Monkey(name, runSpeed, climbSpeed);
                    break;
                case ("sloth"):
                   animalArray[i] = new Sloth(name, swimSpeed, climbSpeed);
                   break;
            }
        }
        
        System.out.println("------------------------------------------------------------");
        System.out.println("All ANIMALS IN ARRAY");
        System.out.println("------------------------------------------------------------");
        System.out.println("Animal SpeciesClimbing Speed");
        System.out.println("------------------------------------------------------------");
        
        for(int i = 0; i < animalArray.length; i++){
           
            System.out.println("\n");
            displayAnimal(animalArray[i]);
        }
        
        findClimbers(animalArray);
        
        int mostSkilledIndex = findMostSkilled(animalArray);
        
        System.out.println("------------------------------------------------------------");
        System.out.println("MOST SKILLED ARRAY");
        System.out.println("------------------------------------------------------------");
        
        System.out.println("The winner is " + animalArray[mostSkilledIndex].getName() + " the " 
                + animalArray[mostSkilledIndex].getSpecies());
        
        displayAnimal(animalArray[mostSkilledIndex]);
    }
    
    
    static void displayAnimal(Animal animal){
        
            System.out.println(animal.getName() + " the " + animal.getSpecies() 
                    + " says " + animal.makeNoise());
            
            if(animal instanceof Swimmer){
                System.out.println("Swimming speed: " + ((Swimmer)animal).swim());
            }
            
            if(animal instanceof Runner){
                System.out.println("Running speed: " + ((Runner)animal).run());
            }
            
            if(animal instanceof Climber){
                System.out.println("Climbing speed: " + ((Climber)animal).climb());
            }
    }
    
    static void findClimbers(Animal[] animalArray){
        
        ArrayList<Animal> climbersArrayList = new ArrayList<>();
        
        for(Animal animal: animalArray){
            if(animal instanceof Climber){
                climbersArrayList.add(animal);
            }
        }
        
        System.out.println("------------------------------------------------------------");
        System.out.println("ANIMALS THAT CAN CLIMB");
        System.out.println("------------------------------------------------------------");
        System.out.println("------------------------------------------------------------");
        System.out.println("Animal Species   Climb Speed");
        System.out.println("------------------------------------------------------------");
        
        for(Animal animal: climbersArrayList){
            System.out.println(animal.getName() + "\t" + animal.getSpecies() + "\t" + ((Climber)animal).climb());
        }
    }
    
    static int findMostSkilled(Animal[] animalArray){
        int mostSkilledIndex = 0;
        int highestSkill = 0;
        for(int i = 1; i < animalArray.length; i++){
            int totalSkill = 0;
            
            if(animalArray[i] instanceof Swimmer){
                totalSkill += ((Swimmer)animalArray[i]).swim();
            }
            
            if(animalArray[i] instanceof Runner){
                totalSkill += ((Runner)animalArray[i]).run();
            }
            
            if(animalArray[i] instanceof Climber){
                totalSkill += ((Climber)animalArray[i]).climb();
            }
            
            if(totalSkill > highestSkill){
                mostSkilledIndex = i;
                highestSkill = totalSkill;
            }
        }
        return mostSkilledIndex;
    }
}


interface Swimmer{
    int swim();
}

interface Runner{
    int run();
}

interface Climber{
    int climb();
}

abstract class Animal {
    
    String name;
    String species;
    
    public String getName(){
        return this.name;
    }
    
    public String getSpecies(){
        return this.species;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setSpecies(String species){
        this.species = species;
    }
    
    public abstract String makeNoise();
}

class Alligator extends Animal implements Swimmer, Runner{
    
    private int swimSpeed;
    private int runSpeed;
    private int climbSpeed = 0;
    
    public Alligator(String name, int swimSpeed,int runSpeed){
       this.name = name;
       this.species = "Alligator";
       this.swimSpeed = swimSpeed;
       this.runSpeed = runSpeed;
    }
    
    @Override
    public String makeNoise(){
        return "Crunch";
    }
    
    @Override
    public int swim(){
        return this.swimSpeed;
    }
    
    @Override
    public int run(){
        return this.runSpeed;
    }
}

class Bear extends Animal implements Swimmer, Runner, Climber{
    
    private int swimSpeed;
    private int runSpeed;
    private int climbSpeed;
    
    public Bear(String name, int swimSpeed, int runSpeed, int climbSpeed){
        this.name = name;
        this.species = "Bear";
        this.swimSpeed = swimSpeed;
        this.runSpeed = runSpeed;
        this.climbSpeed = climbSpeed;
    }
    
    @Override
    public String makeNoise(){
        return "Growl";
    }
    
    @Override
    public int swim(){
        return this.swimSpeed;
    }
    
    @Override
    public int run(){
        return this.runSpeed;
    }
    
    @Override
    public int climb(){
        return this.climbSpeed;
    }
}

class Giraffe extends Animal implements Runner{
    
    private int swimSpeed = 0;
    private int runSpeed;
    private int climbSpeed = 0;
    
    public Giraffe(String name, int runSpeed){
        this.name = name;
        this.species = "Giraffe";
        this.runSpeed = runSpeed;
    }
    
    @Override
    public String makeNoise(){
        return "Bleat";
    }
    
    @Override
    public int run(){
        return this.runSpeed;
    }
}

class Monkey extends Animal implements Runner, Climber{
    
    private int swimSpeed = 0;
    private int runSpeed;
    private int climbSpeed;
    
    public Monkey(String name, int runSpeed, int climbSpeed){
        this.name = name;
        this.species = "Monkey";
        this.runSpeed = runSpeed;
        this.climbSpeed = climbSpeed;
    }
    
    @Override
    public String makeNoise(){
        return "Screech";
    }
    
    @Override
    public int run(){
        return this.runSpeed;
    }
    
    @Override
    public int climb(){
        return this.climbSpeed;
    }
}

class Sloth extends Animal implements Swimmer, Climber{
    
    private int swimSpeed;
    private int runSpeed = 0;
    private int climbSpeed;
    
    public Sloth(String name, int swimSpeed, int climbSpeed){
        this.name = name;
        this.species = "Sloth";
        this.swimSpeed = swimSpeed;
        this.climbSpeed = climbSpeed;
    }
    
    @Override
    public String makeNoise(){
        return "Squeak";
    }
    
   @Override
    public int swim(){
        return this.swimSpeed;
    }
    
    @Override
    public int climb(){
        return this.climbSpeed;
    }
}