
public class OlneyDylanHW06 {

	public static void main(String[] args) {
		
		int[] numbers = {10, 3, 8, 6, 1, 7, 2};
		
		System.out.println("Individual values");
		System.out.println("-----------------------------");
		display(numbers);
		
		System.out.println("Sum of even values");
		System.out.println("-----------------------------");
		int evenSum = computeSumOfEvenValues(numbers);
		System.out.println("Even sum: " + evenSum + "\n");
		
		System.out.println("Sum of each consecutive pair:");
		System.out.println("-----------------------------");
		displaySumOfEachConsecutivePair(numbers);
		System.out.println("\n");
		
		System.out.println("Sum of all consecutive pairs:");
		System.out.println("-----------------------------");
		int consecutivePairsSum = computeSumOfAllConsecutivePairs(numbers);
		System.out.println(consecutivePairsSum + "\n");
		
		System.out.println("Sum of all possible pairs:");
		System.out.println("-----------------------------");
		displaySumOfAllPairs(numbers);
		System.out.println("\n");
		
		System.out.println("Two largest values:");
		System.out.println("-----------------------------");
		int[] highestValues = findTwoLargestValues(numbers);
		System.out.println("The two largest values in the array are " + highestValues[0] + " and " + 
		highestValues[1]);
		
	}

		//Displays all values in the array
		public static void display(int[] numbers)
		{
			for(int i = 0; i < numbers.length; i++) //loops through the array and displays the value at each position
			{
				System.out.println("numbers[" + i +"] = " + numbers[i]);
			}
			System.out.println("\n");
		}

		// Computes and returns the sum of all even values in the array
		public static int computeSumOfEvenValues(int[] numbers)
		{
			int evenSum = 0;
			for(int i = 0; i < numbers.length; i++)
			{
				if((numbers[i] % 2) == 0)
				{
					evenSum += numbers[i];
				}
			}
			return evenSum;
		}

		// Displays the sum of EACH consecutive pair 
		// Displays the values used in sum and result (a + b = c)
		public static void displaySumOfEachConsecutivePair(int[] numbers)  
		{
			for(int i = 0; i < (numbers.length - 1); i++)
			{
				int num1 = numbers[i];
				int num2 = numbers[i + 1];
				System.out.println(num1 + "+" + num2 + "=" + (num1+num2));
			}
		}

		// Computes and returns the sum of ALL consecutive pairs in the array
		public static int computeSumOfAllConsecutivePairs(int[] numbers) 
		{
			int consecutivePairsSum = 0;
			for(int i = 0; i < (numbers.length - 1); i++)
			{
				int num1 = numbers[i];
				int num2 = numbers[i + 1];
				consecutivePairsSum += num1 + num2;
			}
			return consecutivePairsSum;
		}

		// Displays the sum of all possible pairs
		// Display the values used in sum and result (a + b = c)
		public static void displaySumOfAllPairs(int[] numbers)
		{
			for(int i = 0; i < (numbers.length - 1); i++)
			{
				for(int iteration = (i + 1); iteration < numbers.length; iteration++)
				{
				System.out.println(numbers[i] + " + " + numbers[iteration] + " = " + (numbers[i] + numbers[iteration]));
				}
			}
		}

		// Find and return an array with the two largest values 
		public static int[] findTwoLargestValues(int[] numbers)
		{
			int highestValue = 0;
			int secondHighestValue = 0;
			
			for(int i = 0; i < numbers.length; i++)
			{
				if(numbers[i] > highestValue)
				{
					highestValue = numbers[i];
				}
				if(numbers[i] > secondHighestValue && numbers[i] != highestValue)
				{
					secondHighestValue = numbers[i];
				}
			}
			
			int[] highestValues = {highestValue, secondHighestValue};
			
			return highestValues;
		}
		
}
