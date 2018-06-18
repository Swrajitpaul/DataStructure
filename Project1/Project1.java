import java.io.*;
import java.util.Scanner;

public class Project1 {
	
	/**
	 * 
	 * @param args 
	 * @throws Exception if a file is not found
	 */
	public static void main(String[] args) throws Exception {
		
		FileInputStream fstream = new FileInputStream("project1.txt");
		
		Scanner scan = new Scanner(fstream);
		
		String operation;
		
		while (scan.hasNextLine()) {
			
			SparseVector A = new SparseVector( scan.nextLine() ); // first line is the first sparse vector
			
			SparseVector B = new SparseVector( scan.nextLine() ); // second line is the second sparse vector
			
			operation = scan.nextLine(); // operation (add, subtract, dot)
			
			// uses try and catch blocks for a valid operation
			try {
				
				if(operation.equals("add")) {
					
					A.add(B);
				
				}// if the operation is add then it calls the method add with the two vectors
				
				
				else if(operation.equals("subtract")) {
					
					A.subtract(B);
				
				}// if the operation is subtract then it calls the method subtract with the two vectors
				
				
				else if(operation.equals("dot")) {
					
					A.dot(B);
				
				}// if the operation is dot then it calls the method dot with the two vectors
				
				
				else throw new IllegalArgumentException(); // if the operation is invalid
			
			}
			
			catch(Exception e) {
				
				System.out.println("Invalid Operation : please input a valid operation(add, subtract or dot)");
			
			}
			
			
			System.out.println(); // space between two consecutive vector operations
			System.out.println();
			
		} // end of while loop
		
		scan.close(); // closes scan
		fstream.close(); // closes the file

	} // end of main method

} // end of class project1