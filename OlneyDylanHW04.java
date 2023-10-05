/*
 *  Name: Dylan Olney
 *  Class: CS1150 M/W 
 *  Due: Mar 5th 2023 
 *  Description: Homework 04 
 *  The program will need to:
        1.	Display a menu with the initial rental options and the number of bikes available. There should be 9 bikes at the start.
	    2.	Prompt the customer to select one of three rental options, and the prompt will display again if an invalid option is selected. Prompt the customer to select up to 4 bikes to rent. 
	    3.	If customer requested to return bike, display a success statement and loop back to beginning.
	    4.	Insert special option to shut down the station using code 999 that is not displayed to customer. This option should print a report for the total number of rented bikes for all customers and total sales. 
        5.	Display an error message if there are 9 bikes, and user attempts to return a bike.
 */
import java.util.Scanner;


public class OlneyDylanHW04 {

	public static void main(String[] args) {
		
		// Establish variables and constants
		final double ONE_HOUR_PASS = 1.50;
		final double TWO_HOUR_PASS = 2.50;
		final double SIX_HOUR_PASS = 4.00;
		final int MIN = 10000;
		final int MAX = 99999;
		final int MAX_BIKE_COUNT = 9;
		
		String rentalTime = "default";
		boolean stationInService = true;
		boolean correctRentalOption = true;
		Scanner input = new Scanner(System.in);
		double bikeFee = 0;
		double bikeCode = 0;
		double rentalCost = 0;
		double totalSales = 0;
		int bikeCount = MAX_BIKE_COUNT;
		int rentalOption = 0;
		int totalBikeRental = 0;
		int requestedBikes = 0;
		int i = 0;
		
		
		while (stationInService) // while statement to run as long as code 999 is not entered
		{
			System.out.println("************************************************");
			System.out.println("\tWelcome to UCCS Bike Share");
			System.out.println("\t" + bikeCount + " bikes are available");
			System.out.println("************************************************\n");
			System.out.println("Rental Options:");
			System.out.println("Option 1: 1-hour Pass - $1.50");
			System.out.println("Option 2: 2-hour Pass - $2.50");
			System.out.println("Option 3: 6-hour Pass - $4.00");
			System.out.println("Option 4: Return Bike");
			System.out.println("Select rental option 1, 2, 3, or 4:"); 
			//Prompt user for rental option and provide options
			rentalOption = input.nextInt();
			

			do
			{
				correctRentalOption = true;
				switch (rentalOption)
				{
				case 1: //Case for first option and changing accordingly
					bikeFee = ONE_HOUR_PASS; 
					rentalTime = "1-hour"; //Changing what is displayed on receipt for rental type
					break;
				case 2: //Case for second option
					bikeFee = TWO_HOUR_PASS;
					rentalTime = "2-hour"; //Changing what is displayed on receipt for rental type
					break;
				case 3: //Case for third option
					bikeFee = SIX_HOUR_PASS;
					rentalTime = "6-hour"; //Changing what is displayed on receipt for rental type
					break;
				case 4: //Case for returning bike and checking to make sure room is available for a bike
					if (bikeCount < MAX_BIKE_COUNT)
					{
						bikeCount++;
						System.out.println("Bike returned. Have a great day!");
					}
					else
					{
						System.out.println("Request cannot be fulfilled, this Bike Share can only hold 9 bikes. Please "
								+ "use another station."); //Error displayed when there is not room remaining for bikes
					}
					break;
				case 999: //Case for employee code entry that closes out program
					stationInService = false;
					System.out.println("UCCS Bike Station at Main Hall was successfully shut down");
					System.out.println("Total Bikes Rented = " + totalBikeRental);
					System.out.printf("Total Sales = $%.2f\n",totalSales);
					break;
				default: //Case for error in entry
					correctRentalOption = false;
					System.out.println("Invalid entry. Enter 1, 2, 3, or 4:");
					rentalOption = input.nextInt();
				}
			}
			while (!correctRentalOption);  //While statement that loops until a valid entry is entered
			
			if (rentalOption >= 1 && rentalOption <= 3) //If statement running only when the first 3 rental options are selected
			{
				System.out.println("How many bikes do you want to rent? The limit is 4:");
				requestedBikes = input.nextInt();
				while (requestedBikes < 1 || requestedBikes > 4) //While loop as long as user requests a valid number of bikes
				{
					System.out.println("Invalid entry. Request at least 1 bike or up to 4 bikes:");
					requestedBikes = input.nextInt();
				}
				if (requestedBikes > bikeCount) //Error displayed when user requests more bikes than available
				{
					System.out.println("There are only " + bikeCount + " bikes available.");
					System.out.println("Request cannot be fulfilled. Please use a different station.");
				}
				else //Case for valid entry in number of bikes requested and available
				{
					System.out.println("-------------------------------------");
					System.out.println("-------------- Receipt --------------");
					System.out.println("-------------------------------------");
					System.out.println("\t" + rentalTime + " rental for " + requestedBikes + " bikes\n");
					
					for (i = 1; i <= requestedBikes; i++) //Loop to generate bike codes for every bike
					{
						bikeCode = Math.random() * (MAX - MIN + 1) + MIN;
						System.out.println("Unlock code for bike# " + i + ": " + (int)bikeCode);
					}
					
					rentalCost = bikeFee * requestedBikes;
					bikeCount -= requestedBikes;
					System.out.printf("\tRental Cost: $%.2f\n", rentalCost);
					System.out.println("\tThank you for your business!");
					System.out.println("-------------------------------------");
					
					totalBikeRental += requestedBikes; //Adds to total number of rentals and sales after successfully getting through program
					totalSales += rentalCost;
				}
			}			
		}
	}
}