/*
 *  Name: Dylan Olney
 *  Class: CS1150 M/W 
 *  Due: Mar 12th 2023 
 *  Description: Homework 05 
 *  The program:
        •	will first need to display the options for the user. 
		•	Based on the user inputs, the program will run the segment to check BMI, blood pressure, or cholesterol.
		•	The fourth option will allow the user to exit the loop and end the program.
		•	For the BMI, the user will be asked their weight and height to provide the user with a result based on their BMI.
		•	The cholesterol will option will request the user’s cholesterol and provide a response based off that regarding whether that number is healthy.
		•	Blood pressure will request a number from a user and provide information on if that number is healthy like the cholesterol option. 

 */

import java.util.Scanner;

public class OlneyDylanHW05 {

	public static void main(String[] args) {
		
 
		//All variables used in the main method
		Scanner input = new Scanner(System.in);
		boolean continueProgram = true;
		double healthValue = 0;
		double healthValue2 = 0;
		int validInput = 0; 
		String healthOutput = "Default";
		
		do
		{
			validInput = getHealthCheck(input);
			
			if (validInput >= 1 && validInput <= 3)
			{
				switch (validInput)
				{
				case 1: //Case for user selecting BMI option
					healthValue = computeBMI(input);
					
					healthOutput = checkBMI(healthValue); 
					break;
				case 2: //Case for user selecting Blood Pressure option
					healthValue = requestTopBloodPressure(input);
					
					healthValue2 = requestBottomBloodPressure(input);
					
					healthOutput = checkBloodPressure((int)healthValue, (int)healthValue2);
					break;
				case 3: //Case for user selecting Cholesterol option
					healthValue = requestCholesterol(input);
					
					healthOutput = checkCholesterol((int)healthValue);
					break;
				}
				displayResults(validInput, healthOutput);
			}
			else
				{
					continueProgram = false;
					System.out.println("Have a healthy day! Goodbye");
				}
			
		}
		while (continueProgram);
		
		
		input.close();
	} //End of main
	
	public static int getHealthCheck (Scanner userInput) //Method to request health check option from user and ensure it is a correct input
	{
		System.out.println("Option   Health Check"); //Displays options for user
		System.out.println("------------------------");
		System.out.println("1        BMI");
		System.out.println("2        Blood Pressure");
		System.out.println("3        Cholesterol");
		System.out.println("4        Exit\n");
		
		System.out.printf("Which health check? Select option 1, 2, 3, or 4: ");
		int healthCheckInput = userInput.nextInt(); //Request for initial input from user
		
		while (healthCheckInput < 1 || healthCheckInput >4) //Loop verifying that input is an integer from 1 to 4
		{
			System.out.println(healthCheckInput + " is not a valid entry - try again");
			healthCheckInput = userInput.nextInt();
		}
		
		return healthCheckInput;
	}
	
	// Prompt user for values needed to compute body mass index (BMI) & computes BMI.
	// Returns the computed BMI value.
	public static double computeBMI(Scanner userInput)
	{
		final int FEET_TO_INCHES = 12; //Constant for converting from feet to inches
		final int BMI_CONSTANT = 703; //Constant in the equation for BMI
		
		int weightInput = 0;
		int heightFeetInput = 0;
		int heightInchInput = 0;
		int totalHeightInches = 0;
		double bmi = 0;
		
		System.out.printf("Enter weight in pounds: \n"); //Input for weight
		weightInput = userInput.nextInt();
		System.out.printf("Enter height in feet: \n"); //Input for height in feet
		heightFeetInput = userInput.nextInt();
		System.out.printf("Enter height in inches: \n"); //Input for height in inches
		heightInchInput = userInput.nextInt();
		
		totalHeightInches = (heightFeetInput * FEET_TO_INCHES) + heightInchInput; //Sum of height after converting feet to inches
		
		bmi = BMI_CONSTANT * (weightInput / Math.pow(totalHeightInches, 2));
		
		return bmi;
	}

	// Checks the range for the body mass index and returns the result as a string:
	// underweight, normal, or overweight. 
	public static String checkBMI(double bodyMassIndex) 
	{
		final double MIN = 18.5; //Minimum and Maximum values for the BMI thresholds 
		final double MAX = 25;
		
		String result = "Default";
		
		if (bodyMassIndex < MIN) //Changes value to underweight if criteria is met
		{
			result = "Underweight";
		}
		else if (bodyMassIndex >= MIN && bodyMassIndex <= MAX) //Changes value to normal if criteria is met
		{
			result = "Normal";
		}
		else if (bodyMassIndex > MAX) //Changes value to overweight if criteria is met
		{
			result = "Overweight";
		}
		
		return result;
	}
	
	
	// Requests input of cholesterol number of the user
	public static int requestCholesterol (Scanner userInput) 
	{
		System.out.printf("Enter your Cholesterol: ");
		int cholesterolInput = userInput.nextInt();
		
		return cholesterolInput;
	}

	// Checks the range for the cholesterol and returns the result as a string:
	// good, borderline, high 
	public static String checkCholesterol(int cholesterol) 
	{
		final double MIN = 200;
		final double MAX = 239;
		
		String result = "Default";
		
		if (cholesterol < MIN) //Changes value to good if criteria is met
		{
			result = "Good";
		}
		else if (cholesterol >= MIN && cholesterol <= MAX) //Changes value to borderline if criteria is met
		{
			result = "Borderline";
		}
		else if (cholesterol > MAX) //Changes value to high if criteria is met
		{
			result = "High";
		}
		
		return result;
	}

	
	// Requests input of the top number for cholesterol
		public static int requestTopBloodPressure (Scanner userInput) 
		{
			System.out.println("Enter the top number (systolic): ");
			int bloodPressureInputTop = userInput.nextInt();
			
			return bloodPressureInputTop;
		}
		
		// Requests input of the bottom number for cholesterol
		public static int requestBottomBloodPressure (Scanner userInput) 
		{
		
			System.out.println("Enter the bottom number (diastolic): ");
			int bloodPressureInputBottom = userInput.nextInt();
					
			return bloodPressureInputBottom;
		}
	
		// Checks the range for the blood pressure and returns the result as a string:
		// normal blood pressure, elevated blood pressure, stage 1 high blood pressure, 
		// stage 2 high blood pressure
		public static String checkBloodPressure(int topNumber, int bottomNumber ) 
		{
			final double MIN_BOTTOM = 80;
			final double MAX_BOTTOM = 89;
			final double MIN_TOP = 120;
			final double SECOND_MAX_TOP = 130;
			final double MAX_TOP = 139;
			
			String result = "Default";
			
			if (topNumber <= MIN_TOP && bottomNumber <= MIN_BOTTOM) //Changes value to good if criteria is met
			{
				result = "Normal Blood Pressure";
			}
			else if (topNumber > MIN_TOP && topNumber < SECOND_MAX_TOP && bottomNumber <= MIN_BOTTOM) //Changes value to Elevated Blood Pressure if criteria is met
			{
				result = "Elevated Blood Pressure";
			}
			else if (topNumber >= SECOND_MAX_TOP && topNumber <= MAX_TOP && bottomNumber > MIN_BOTTOM && bottomNumber <= MAX_BOTTOM) 
				//Changes value to Stage 1 High Blood Pressure if criteria is met
			{
				result = "Stage 1 High Blood Pressure";
			}
			else //Changes value to Stage 2 High Blood Pressure if all other checks return false
			{
				result = "Stage 2 High Blood Pressure";
			}
			
			return result;
		}
		
		// Displays the results for a specific health check.  
		// Requires using a switch statement. 
		public static void displayResults(int healthCheck, String healthCheckResult) 
		{
			switch(healthCheck)
			{
			case 1:
				System.out.println("***************************************");
				System.out.println("BMI Result = " + healthCheckResult);
				System.out.println("***************************************");
				break;
			case 2:
				System.out.println("***************************************");
				System.out.println("Cholesterol Result = " + healthCheckResult);
				System.out.println("***************************************");
				break;
			case 3:
				System.out.println("***************************************");
				System.out.println("Blood Pressure Result = " + healthCheckResult);
				System.out.println("***************************************");
				break;
			}
		}

} //End of main class
