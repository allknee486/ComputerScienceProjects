import java.util.Scanner;


public class OlneyDylanQuiz01 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter ONE character for section- ");
		System.out.println("O: Orchestra L: Loge M: Mezzanine B: Balcony");
		
		char sectionSelection = input.next().charAt(0);
		String section = "default";
		int rowSelection = 1;
		final int closeSeat = 450;
		final int middleSeat = 300;
		final int farSeat = 200;
		int seatPrice = 0;
		
		if((sectionSelection == 'O') || (sectionSelection == 'L') || 
				(sectionSelection == 'M') || (sectionSelection == 'B'))
				{
					if(sectionSelection == 'O') 
					{
						section = "Orchestra";
					}
					if(sectionSelection == 'L') 
					{
						section = "Loge";
					}
					if(sectionSelection == 'M') 
					{
						section = "Mezzanine";
					}
					if(sectionSelection == 'B') 
					{
						section = "Balcony";
					}
					
					System.out.println("Selected " + section + " section");
					System.out.println("Enter row");
					rowSelection = input.nextInt();
					
					if((rowSelection >= 1) && (rowSelection <= 60))
					{
						if((rowSelection >= 1) && (rowSelection <= 15))
						{
							seatPrice = closeSeat;
						}
						if((rowSelection >= 16) && (rowSelection <= 30))
						{
							seatPrice = middleSeat;
						}
						if((rowSelection >= 31) && (rowSelection <= 60))
						{
							seatPrice = farSeat;
						}
						System.out.println("Section " + sectionSelection + 
								" row " + rowSelection + " price: $" + seatPrice);
					}
					else
					{
						System.out.println("Invalid row - goodbye");
					}
				}
		else
		{
			System.out.println("Invalid section - goodbye");
		}

	}

}
