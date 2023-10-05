/*
 *  Name: Dylan Olney
 *  Class: CS1150 M/W
 *  Due:  Feb 13th, 2023
 *  Description: Guided Exploration 01 
 *  This program will display a menu to order tacos from with 5 different options and a max of 12 tacos.       
 *  A discount is applied for orders with at least 5 tacos, 
 *  and a tax rate of 6.5% will be applied to the order before the discount.
 *  If an incorrect order is made, the program will end, otherwise the program will display a receipt.
 */
import java.util.Scanner;
public class OlneyDylanHW02 {

	public static void main(String[] args) {
		// Creating constants for the price of each type of taco
		final double VEGGIE_TACO_PRICE = 3.49;
		final double CHICKEN_TACO_PRICE = 3.99;
		final double CARNITAS_TACO_PRICE = 4.19;
		final double BEEF_TACO_PRICE = 4.59;
		final double SHRIMP_TACO_PRICE = 5.19;
		final double TAX_RATE = 0.065;
		final double DISCOUNT = 0.08;
		double tacoCost = 1;	
		double taxes = 1;
		double priceDiscount = 1;
		double discountCost = 1;
		String tacoFilling = "Filling";
				
		Scanner input = new Scanner(System.in); //Creating new object for scanner input
		System.out.println("Hungry for some Tacos?!");//Printed lines to create a "menu" to order from
		System.out.println("Option Filling Price");
		System.out.println("--------------------------------------");
		System.out.println("1 Veggie $3.49");
		System.out.println("2 Chicken $3.99");
		System.out.println("3 Carnitas $4.19");
		System.out.println("4 Beef $4.59");
		System.out.println("5 Shrimp $5.19");
		System.out.print("Select a filling(1, 2, 3, 4, 5):"); //Requesting user to select a filling
		int tacoType = input.nextInt(); //Creating variable to use the input from user
		
		
		
		if(tacoType >= 1 && tacoType <=5)
		{
			System.out.print("Select number of tacos (from 1 to 12):"); //Requesting user to order a number of tacos
			int tacoAmount = input.nextInt(); //Creating variable to use input from user for amount of tacos
			switch(tacoType) //Creating switch statement for different possibilities for filling
			{
				case 1: //Case for Veggie taco order
					tacoCost = tacoAmount * VEGGIE_TACO_PRICE;
					tacoFilling = "Veggie";
				break;	
				case 2: //Case for Chicken taco order
					tacoCost = tacoAmount * CHICKEN_TACO_PRICE;
					tacoFilling = "Chicken";
				break;
				case 4: //Case for Beef taco order
					tacoCost = tacoAmount * BEEF_TACO_PRICE;
					tacoFilling = "Beef";
				break;	
				case 3: //Case for Carnitas taco order
					tacoCost = tacoAmount * CARNITAS_TACO_PRICE;
					tacoFilling = "Carnitas";
				break;	
				case 5: //Case for Shrimp taco order
					tacoCost = tacoAmount * SHRIMP_TACO_PRICE;
					tacoFilling = "Shrimp";
				break;					
			}
			if(tacoAmount >= 1 && tacoAmount <= 4) //If statement check for amount of tacos without a discount
			{
				taxes = tacoCost * TAX_RATE; //Calculates taxes normally
				double total_cost = (double)tacoCost + taxes; //Sums up the cost 
				System.out.println("--------------------------");
				System.out.printf("%.0f %s Tacos $%.2f\n", (double)tacoAmount, tacoFilling, (double)tacoCost);
				System.out.printf("Taxes $%.2f\n", taxes);
				System.out.println("--------------------------");
				System.out.printf("Total cost $%.2f", total_cost);
			}
			else if(tacoAmount >= 5 && tacoAmount <= 12) //If statement check for amount of tacos with a discount
			{
				priceDiscount = tacoCost * DISCOUNT; //Calculates the discount for the order
				discountCost = tacoCost - priceDiscount; //Calculates to account for taxes being applied after the discount
				taxes = discountCost * TAX_RATE; //Calculates taxes based on the discounted amount instead of the normal amount
				double total_cost = tacoCost + taxes - priceDiscount;
				System.out.println("--------------------------");
				System.out.printf("%.0f %s Tacos $%.2f\n", (double)tacoAmount, tacoFilling, (double)tacoCost);
				System.out.printf("Discount -$%.2f\n", priceDiscount);
				System.out.printf("Taxes $%.2f\n", taxes);
				System.out.println("--------------------------");
				System.out.printf("Total cost $%.2f", total_cost);
			}
			else //Output for invalid order for tacos
			{
				System.out.print(tacoAmount + " is not a valid amount of tacos, unable to process your online order."
						+ "Please run program again."); 
				//Output for incorrect input on amount of tacos
			}
		}
		else
		{
			System.out.println(tacoType + " is not a valid filling option, unable to process your online order. Please run "
					+ "program again.");
		}
		
		input.close();
	}

}
