import java.io.FileInputStream;
import java.util.Scanner;

public class Project2 {

	public static void main(String[] args) throws Exception {
		
		FileInputStream fstream = new FileInputStream("project2.txt");
		
		Scanner scan = new Scanner(fstream);
		
		
		while (scan.hasNextLine()) {
			
			PostFix	inPost = new PostFix(scan.nextLine()); // reads each line and sends it as a parameter to postFix constructor	
		
		}
		
		scan.close(); // closes scan
		
		fstream.close(); // closes file
	}

}
