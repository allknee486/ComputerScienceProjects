/*
 *  Name: Dylan Olney
 *  Class: CS1150 M/W 
 *  Due: Feb 20th 2023 
 *  Description: Homework 03 Design and Implement
 *  The program will take input from the user for a theme park ticket for 1 to 10 days.
 *  Acceptable inputs will use the letters A, E, H, or M for each theme park.
 *  A random ticket number will be generated based on the theme park requested and the number of days.
 */

import java.util.Scanner;

public class OlneyDylanHW03 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in); //Creating new object for scanner input
		
		//Creating variables for random generation of numbers for the tickets
		double randomDigit = Math.random() * (9 - 1 + 1) + 1;
 		double randomTwoDigit = Math.random() * (99 - 10 + 1) + 10;
 		double randomThreeDigit = Math.random() * (999 - 100 + 1) + 100;
		double randomFourDigit = Math.random() * (9999 - 1000 + 1) + 1000;
		double randomFiveDigit = Math.random() * (99999 - 10000 + 1) + 10000;
		double randomLetter1 = Math.random() * (90 - 65 + 1) + 65;
		double randomLetter2 = Math.random() * (90 - 65 + 1) + 65;
		double randomLetter3 = Math.random() * (90 - 65 + 1) + 65;
		
		
		//Output request for user input for the theme park and number of days
		System.out.println("Enter theme park character (A,E,H,M) then a space then the number of days (1-10):");
		System.out.println("(A: Animal Kingdom  E: Epcot  H: Hollywood Studios  M: Magic Kingdom)");
		
		String themePark = input.next().toUpperCase(); //User input for the theme park
		double numDays = input.nextInt(); //User input for the number of days
		
		if (numDays >= 1 && numDays <= 10) //Check for correct user input for the number of days
		{
			switch (themePark)
			{
			case "A": //Case for entry of Animal Kingdom
				System.out.printf("Animal Kingdom: %.0f-%.0f-AK (%.0f Day Ticket)\n", randomTwoDigit, randomFourDigit, numDays);
				break;
			case "E": //Case for entry of Epcot
				System.out.printf("Epcot: %.0f--%c%c--E (%.0f Day Ticket)\n", randomFiveDigit, (char)randomLetter1, 
						(char)randomLetter2, numDays);
				break;
			case "H": //Case for entry of Hollywood Studios
				System.out.printf("Hollywood Studios: %c%c%c %.0f HS (%.0f Day Ticket)\n", (char)randomLetter1, 
						(char)randomLetter2, (char)randomLetter3, randomThreeDigit, numDays);
				break;
			case "M": //Case for entry of Magic Kindgom
				System.out.printf("Magic Kingdom: %.0f %c%c%c MK (%.0f Day Ticket)\n", randomDigit, (char)randomLetter1, 
						(char)randomLetter2, (char)randomLetter3, numDays);
				break;	
			default: //Case for invalid theme park entry by user
				System.out.println("Invalid input for theme park - run program again");
			}
		}
		else //Case for invalid days entry by user
		{
			System.out.println("Invalid input for number of days - run program again");
		}
		
		input.close();
	}

}
