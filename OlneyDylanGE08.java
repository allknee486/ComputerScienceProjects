
public class OlneyDylanGE08 {

	public static void main(String[] args) {
		
		//Creates rectangle1 with default length and width values
		SimpleRectangle rectangle1 = new SimpleRectangle();
		System.out.println("The perimeter of rectangle1 is " + rectangle1.getPerimeter());
		System.out.println("The area of rectangle1 is " + rectangle1.getArea());
		
		//Creates rectangle2 with length 2.34 and width 5
		SimpleRectangle rectangle2 = new SimpleRectangle(2.34, 5);
		System.out.println("The perimeter of rectangle2 is " + rectangle2.getPerimeter());
		System.out.println("The area of rectangle2 is " + rectangle2.getArea());
		
		//Creates rectangle3 with length 25 and width 17.4
		SimpleRectangle rectangle3 = new SimpleRectangle(25, 17.4);
		//Changes the length of rectangle3 to 2
		rectangle3.setLength(2);
		System.out.println("The perimeter of rectangle3 is " + rectangle3.getPerimeter());
		System.out.println("The area of rectangle3 is " + rectangle3.getArea());
	}

}

//Define SimpleRectangle class with constructors
class SimpleRectangle
{
	private double length;
	private double width;
	
	//Creates a basic rectangle with length and width of 1
	public SimpleRectangle()
	{
		length = 1;
		width = 1;
	}
	
	//Creates a basic rectangle with specified length and width 
	public SimpleRectangle(double newLength, double newWidth)
	{
		length = newLength;
		width = newWidth;
	}
	
	//Calculates and returns the area of the rectangle
	public double getArea()
	{
		return length * width;
	}
	
	//Calculates and returns the perimeter of the rectangle
	public double getPerimeter()
	{
		return (length * 2) + (width * 2);
	}
	
	//Changes the length of the rectangle
	public double setLength(double newLength)
	{
		return length = newLength;
	}
	
	//Changes the width of the rectangle
	public double setWidth(double newWidth)
	{
		return width = newWidth;
	}
}
