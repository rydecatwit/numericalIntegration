//Cameron Ryde
//Numerical Integration

package numericalIntegration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		double a =0 , b=0;
		int n = 0;
		double[] coefficients = new double[6];
		
		System.out.print("Enter a name for your file (You do not need to add '.txt'): ");
		String fileName = keyboard.nextLine();
		
		try {
			System.out.print("a = ");
			a = keyboard.nextDouble();
			System.out.print("b = ");
			b = keyboard.nextDouble();
		}catch(InputMismatchException e) {
			System.out.println("Error: Make sure a and b are numbers.");
			System.exit(0);
		}
		
		System.out.print("n = ");
		try {
			n = keyboard.nextInt();
			if(n <= 0) {
				System.out.println("Error: n must be greater than 0.");
				System.exit(0);
			}
		}catch(InputMismatchException e) {
			System.out.println("Error: n must be an integer.");
			System.exit(0);
		}
		
		try {
			System.out.print("A = ");
			coefficients[0] = keyboard.nextDouble();
			System.out.print("B = ");
			coefficients[1] = keyboard.nextDouble();
			System.out.print("C = ");
			coefficients[2] = keyboard.nextDouble();
			System.out.print("D = ");
			coefficients[3] = keyboard.nextDouble();
			System.out.print("E = ");
			coefficients[4] = keyboard.nextDouble();
			System.out.print("F = ");
			coefficients[5] = keyboard.nextDouble();
		}catch(InputMismatchException e) {
			System.out.println("Error: Make Sure all coefficients are numbers.");
			System.exit(0);
		}
			
		keyboard.close();
		File file = new File(fileName + ".txt");
		
		double exact = Exact.getExact(coefficients, a, b);
		double midpoint = Math.abs(Midpoint.getMidpoint(a, b, n, coefficients));
		double trapezoidal = Math.abs(Trapezoidal.getTrapezoid(a, b, n, coefficients));
		double simpsons = Math.abs(Simpsons.getSimps(a, b, n, coefficients));
		int expCount = 5;		
		
		try(PrintWriter fout = new PrintWriter(file)){
			fout.print("Function: ");
			for(int i = 0; i <= 5; i++) {
				if(i == 4) {
					fout.print(coefficients[i] + "x + ");
					expCount--;
				}else if(i == 5) {
					fout.print(coefficients[i]);
				}else {
					fout.print(coefficients[i] + "x^" + expCount + " + ");
					expCount--;
				}
			}
			
			fout.printf("%na: %f%nb: %f%nn: %d%n%n", a,b,n);
			
			fout.printf("Exact: %.8f%n", exact);
			fout.printf("Midpoint: %.8f%n", midpoint);
			fout.printf("Midoint error: %.8f%n", (midpoint - exact)/exact);
			fout.printf("Trapezoidal: %.8f%n", trapezoidal );
			fout.printf("Trapezoidal error: %f%n", (trapezoidal - exact)/exact);
			fout.printf("Simpson's: %f%n", simpsons );
			fout.printf("Simpson's error: %f", (simpsons - exact)/exact);
			fout.close();
		}catch (FileNotFoundException ex) {
			System.out.print("File could not be created.");
		}
		System.out.print("File created.");
	}

}
