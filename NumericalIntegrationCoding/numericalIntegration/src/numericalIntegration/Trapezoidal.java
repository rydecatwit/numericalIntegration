package numericalIntegration;

public class Trapezoidal {
	
	public static double f(double[] coeffs, double x) {
		//our formula that was given
		//f(x)=Ax^5 + Bx^4 + Cx^3 + Dx^2 + Ex + F
		return (coeffs[0]*Math.pow(x, 5))+(coeffs[1]*Math.pow(x, 4))+
				(coeffs[2]*Math.pow(x, 3))+(coeffs[3]*Math.pow(x, 2))+
				(coeffs[4]*x)+coeffs[5];
	}
	
	public static double getTrapezoid(double a, double b, int n, double[] coeffs) {
		//avg/rect width
		
		//rect width = b-a/n
		double rectWidth = (b-a)/n;
		//running total for all trapezoids to have added up
		double total = 0;
		//These are the heights we will use in our trapezoid formula
		double leftX, rightX;
		
		//Initialize the left and right heights we will use
		leftX = a;
		rightX = a + rectWidth;
		
		//loop to calculate the total of all the trapezoids added up
		for(int i =0; i < n; i++) {
			total = total + (((f(coeffs, leftX) + f(coeffs, rightX)) / 2) * rectWidth);
			leftX = leftX + rectWidth;
			rightX = rightX + rectWidth;
		}
		return total;
	}
}
