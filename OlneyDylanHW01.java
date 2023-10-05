// Import Scanner to read user input for calculation
import java.util.Scanner; 

public class OlneyDylanHW01 { // Beginning of class
	// heightInput, neckInput, waistInput, weightInput
	public static void main(String[] args) { //Beginning of method
		// TODO Auto-generated method stub
		// Create Scanner object for user entries
		Scanner input = new Scanner (System.in);
		System.out.println("The following is a calculation for male BMI.");
		
		// Prompt for entry for Height
		System.out.println("Enter a number for height: ");
		double heightInput = input.nextDouble();
		
		// Prompt for entry for Neck circumference
		System.out.println("Enter a number for neck circumference: ");
		double neckInput = input.nextDouble();
		
		// Prompt for entry for Waist
		System.out.println("Enter a number for waist: ");
		double waistInput = input.nextDouble();
		
		// Prompt for entry for Weight
		System.out.println("Enter a number for weight: ");
		double weightInput = input.nextDouble();
		
		// Declare variables for and calculate US Army BMI and Adult BMI
		double armyBMI = (86.01 * Math.log10(waistInput - neckInput) - 70.041 * Math.log10(heightInput) + 36.76);
		// double adultBMI = (weightInput / (heightInput * heightInput) * 703);
		double adultBMI = (weightInput / Math.pow(heightInput, 2) * 703);
		
		// Output for US Army BMI and Adult BMI
		System.out.println("According to the US Army BMI, your BMI is " + armyBMI + "%");
		System.out.println("According to the Adult BMI, your BMI is " + adultBMI);
		
		input.close();
	} // End of method

} // End of class
