/*
 *  Name: Dylan Olney
 *  Class: CS1150 M/W 
 *  Due: Mar 1st 2023 
 *  Description: Homework 09 
 *  The program will create a polymorphic array that adds subclass objects to the array
 *  The program will then display the information of each animal in each position of the array
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class OlneyDylanHW09 {

	public static void main(String[] args) throws IOException {
		
		//Create variables for the file the information is coming from
		File fileName = new File("Animals.txt");
		Scanner inputFile = new Scanner(fileName);
		
		//Creating the polymorphic array
		Animals[] animalArray = new Animals[inputFile.nextInt()];
		
		//Loop to go through the entire file and add objects based on the read information
		for(int i = 0; i < animalArray.length; i++)
		{
			String animalType = inputFile.next();
			String animalName = inputFile.next();
			String animalFood = inputFile.next();
			int animalWeight = inputFile.nextInt();
			int animalSleep = inputFile.nextInt();
			String animalLocation = inputFile.next() + " " + inputFile.next();
			
			//Switch statement to properly create each object based on information read from the file
			switch (animalType)
			{
			case "Bear":
				animalArray[i] = new Bear(animalName.trim(), animalFood.trim(), animalWeight, animalSleep,
						animalLocation);
			break;
			case "Elephant":
				animalArray[i] = new Elephant(animalName.trim(), animalFood.trim(), animalWeight, animalSleep,
						animalLocation);
			break;
			case "Monkey":
				animalArray[i] = new Monkey(animalName.trim(), animalFood.trim(), animalWeight, animalSleep,
						animalLocation);
			break;
			case "Sloth":
				animalArray[i] = new Sloth(animalName.trim(), animalFood.trim(), animalWeight, animalSleep,
						animalLocation);
			break;
			}
		}
		
		//Second for loop to print the information of each object in the array
		for(int i = 0; i < animalArray.length; i++)
		{
			System.out.printf("\nAnimal[%d] is a %s", i, animalArray[i].getType());
			System.out.printf("%s", animalArray[i].toString());
			animalArray[i].eat();
			animalArray[i].sleep();
			animalArray[i].swim();
			System.out.printf("\n");
		}
		inputFile.close();
	}

}

class Animals
{
	//Create variables for animals class
	private String name;
	private String food;
	private int weight;
	private int sleep;
	private String location;
	
	//Initialization criteria for animals object
	public Animals(String name, String food, int weight, int sleep, String location)
	{
		this.name = name;
		this.food = food;
		this.weight = weight;
		this.sleep = sleep;
		this.location = location;
	}
	
	//Display name of object
	public String getName()
	{
		return this.name;
	}
	
	//Display food of object
	public String getFood()
	{
		return this.food;
	}
	
	//Display weight of object
	public int getWeight()
	{
		return this.weight;
	}
	
	//Display amount of sleep object gets
	public int getSleep()
	{
		return this.sleep;
	}
	
	//Display location of animal object
	public String getLocation()
	{
		return this.location;
	}
	
	//Print what object eats
	public void eat()
	{
		System.out.printf("\nAnimal is eating");
	}
	
	public void sleep()
	{
		System.out.printf("\nAnimal is sleeping - Do not disturb");
	}
	
	public void swim()
	{
		System.out.printf("\nAnimal is swimming");
	}
	
	public String getType()
	{
		return "Animal";
	}
}

class Bear extends Animals
{
	public Bear(String name, String food, int weight, int sleep, String location)
	{
		super(name, food, weight, sleep, location);
	}
	
	@Override
	public void eat()
	{
		System.out.printf("\nBear is eating %s", getFood());
	}
	
	@Override
	public void sleep()
	{
		System.out.printf("\nBear is sleeping %d hours", getSleep());
	}
	
	@Override
	public void swim()
	{
		System.out.printf("\nBear is swimming");
	}
	
	public String toString()
	{
		return "\nBear: Name: " + getName() + " - Eats: " + getFood() + " - Weighs: " + getWeight() + " lbs - Sleeps: " + 
				getSleep() + " hours - Location: " + getLocation();
	}
	
	public String getType()
	{
		return "Bear";
	}
}

class Elephant extends Animals
{
	public Elephant(String name, String food, int weight, int sleep, String location)
	{
		super(name, food, weight, sleep, location);
	}
	
	@Override
	public void sleep()
	{
		System.out.printf("\nElephant is sleeping %d hours", getSleep());
	}
	
	@Override
	public void swim()
	{
		System.out.printf("\nElephant is swimming");
	}
	
	public String toString()
	{
		return "\nElephant: Name: " + getName() + " - Eats: " + getFood() + " - Weighs: " + getWeight() + " lbs - Sleeps: " + 
				getSleep() + " hours - Location: " + getLocation();
	}
	
	public String getType()
	{
		return "Elephant";
	}
	
}

class Monkey extends Animals
{
	public Monkey(String name, String food, int weight, int sleep, String location)
	{
		super(name, food, weight, sleep, location);
	}
	
	@Override
	public void eat()
	{
		System.out.printf("\nMonkey is eating %s", getFood());
	}
	
	@Override
	public void swim()
	{
		System.out.printf("\nMonkey is swimming");
	}
	
	public String toString()
	{
		return "\nMonkey: Name: " + getName() + " - Eats: " + getFood() + " - Weighs: " + getWeight() + " lbs - Sleeps: " + 
				getSleep() + " hours - Location: " + getLocation();
	}
	
	public String getType()
	{
		return "Monkey";
	}
	
}

class Sloth extends Animals
{
	public Sloth(String name, String food, int weight, int sleep, String location)
	{
		super(name, food, weight, sleep, location);
	}
	
	public String toString()
	{
		return "\nSloth: Name: " + getName() + " - Eats: " + getFood() + " - Weighs: " + getWeight() + " lbs - Sleeps: " + 
				getSleep() + " hours - Location: " + getLocation();
	}
	
	public String getType()
	{
		return "Sloth";
	}
}