import java.util.StringTokenizer;

public class SparseVector {
	
	DoublyLinkedList<Element> list = new DoublyLinkedList<>(); // doublyLinkedlist stores the elements of the sparsed vector
	public Element[] element; // arrays of element
	
	/**
	 * 
	 * @param vector takes in one string that contains a sparsedVector
	 */
	public SparseVector(String vector){
		
		if(!vector.equals("")) {
			
			StringTokenizer tokenOne = new StringTokenizer(vector);
		
			element = new Element[ (vector.split(" ").length) / 2 ]; // amount of elements that are needed
		
			int counter=0;
		
			while (tokenOne.hasMoreTokens()) {
			
				element[counter]  = new Element(tokenOne.nextToken(), tokenOne.nextToken());
			
				list.add(element[counter]); // adds the returned string to the doublyLinkedlist
				
				counter++;
			
			} // while loop calls on class element and passes on the appropriate parameters 
		
		} // if block execute only if the string is valid
		
	} // end of constructor
	
	/**
	 * 
	 * @param sv the sparseVector that the operation is performed on
	 * @return returns the resultant sparsedVector
	 */
	public SparseVector add(SparseVector sv){
		
		SparseVector total = new SparseVector(""); // Initializing the resulting sparseVector
	
		total.element = new Element[this.list.size() + sv.list.size()];
		
		total.list.clear();
		
		int count =0;
		
		int iValue=0;
		
		int jValue=0;
		
		for(int i = iValue; i < this.list.size(); i++) {
			
			boolean indleft = false;
			
			for(int j =jValue; j <= sv.list.size(); j++) {

				if( j == sv.list.size()) {
					
					indleft = true;
					
					break;
					
				} // end of if block
				
				if(this.list.get(i).compareTo(sv.list.get(j)) == -1) {
					
					total.element[count] = new Element(String.valueOf(this.list.get(i).getIndex()), String.valueOf(this.list.get(i).getValue()));
				
					total.list.add(total.element[count++]);
					
					iValue++;
					
					break;
				
				} // end of if block
				
				else if( this.list.get(i).compareTo(sv.list.get(j)) == 1) {
					
					total.element[count] = new Element(String.valueOf(sv.list.get(j).getIndex()), String.valueOf(sv.list.get(j).getValue()));
				
					total.list.add(total.element[count++]);
					
					jValue++;
					
					continue;
				
				} // end of else if block
				
				else if( this.list.get(i).compareTo(sv.list.get(j)) == 0) {
					
					if((int)(this.list.get(i).getValue() + sv.list.get(j).getValue()) != 0) {
						
						total.element[count] = new Element(String.valueOf(this.list.get(i).getIndex()), String.valueOf(this.list.get(i).getValue() + sv.list.get(j).getValue()));
						
						total.list.add(total.element[count]);
		
						count++;
						
					} // end of if block
					
					iValue++;
					
					jValue++;
					
					break;
					
				} // end of else if block
				
					
			} // end of inner for loop
			
			if(indleft == true) {
				
				total.element[count] = new Element(String.valueOf(this.list.get(i).getIndex()), String.valueOf(this.list.get(i).getValue()));
				
				total.list.add(total.element[count++]);
			
			} // end of is block
			
		} // end of outer for loop
		
		
		print(this, sv, total, "+", "");
		
		return total;
	}
	
	/**
	 * 
	 * @param sv a parameter of 
	 * @return returns the sparseVector called total, which is the sum of two sparseVector
	 */
	public SparseVector subtract(SparseVector sv){
		
		SparseVector difference = new SparseVector("");
		
		difference.element = new Element[this.list.size() + sv.list.size()];
		
		difference.list.clear();
		
		int count =0;
		
		int iValue=0;
		
		int jValue=0;
		
		for(int i = iValue; i < this.list.size(); i++) {
			
			boolean indexleft = false;
			
			for(int j =jValue; j <= sv.list.size(); j++) {

				if( j == sv.list.size()) {
					
					indexleft = true;
					
					break;
				}
				
				if( this.list.get(i).compareTo(sv.list.get(j)) == -1) {
					
					difference.element[count] = new Element(String.valueOf(this.list.get(i).getIndex()), String.valueOf(-1.0 * this.list.get(i).getValue()));
					
					difference.list.add(difference.element[count++]);
					
					iValue++;
					
					break;
				
				}
				
				else if( this.list.get(i).compareTo(sv.list.get(j)) == 1) {
					
					difference.element[count] = new Element(String.valueOf(sv.list.get(j).getIndex()), String.valueOf(-1.0 * sv.list.get(j).getValue()));
					
					difference.list.add(difference.element[count++]);
					
					jValue++;
					
					continue;
				
				}
				
				else if( this.list.get(i).compareTo(sv.list.get(j)) == 0) {
					
					if((int)(this.list.get(i).getValue() - sv.list.get(j).getValue()) != 0) {
						
						difference.element[count] = new Element(String.valueOf(this.list.get(i).getIndex()), String.valueOf(this.list.get(i).getValue() - sv.list.get(j).getValue()));
						
						difference.list.add(difference.element[count]);
						
						count++;
						
					} // if the difference 
					
					iValue++;
					
					jValue++;
					
					break;
					
				} // end of else if block
				
					
			} // end of inner for loop
			
			if(indexleft == true) {
				
				difference.element[count] = new Element(String.valueOf(this.list.get(i).getIndex()), String.valueOf(-1.0 * this.list.get(i).getValue()));
				
				difference.list.add(difference.element[count++]);
			
			}
			
		} // end of outer for loop
	
		print(this, sv, difference, "-", "");
		
		return difference;
		
	}
	
	/**
	 * 
	 * @param sv the vector being multiplied
	 * @return sum of the product
	 */
	public double dot(SparseVector sv){
		
		double product = 0.0;
		
		for(int i = 0; i < this.list.size(); i++) {
			
			for(int j =0; j < sv.list.size(); j++) {
				
				if( this.list.get(i).compareTo(sv.list.get(j)) == 0) {
					
					product += (this.list.get(i).getValue() * sv.list.get(j).getValue());
					
				} // end of if block
				
			} // end of inner for loop
			
		} // end of outer for loop
	
		print(this, sv, null, "·", String.valueOf(product)); 
		
		return product;
		
	}
	
	/**
	 * prints out the elements in a vector by looping through the doublyLinkedlist
	 */
	public String toString(){
		
		String string = "";
		
		if (this.list.isEmpty()) 
			
			string = "empty vector";
		
		for( int i=0; i< list.size(); i++) {
			
			System.out.print(list.get(i) + ",");
		
		}
		
		return string;
	
	}
	
	/**
	 * 
	 * @param one: sparseVector one
	 * @param two: sparseVector two
	 * @param three: resultant sparseVector
	 * @param operation: the operation that is being performed
	 * @param product: only used for dot product operation
	 */
	public void print(SparseVector one, SparseVector two, SparseVector three, String operation, String product) {
		
		System.out.print("(");
		
		System.out.print(one.toString()); //([3, 1.0], [2500, 6.3], [5000, 10.0], [60000, 5.7]) FIRST LINE
		
		System.out.println(")");  

		
		System.out.println(operation); // + - ·  SECOND LINE
		
		
		System.out.print("(");
		
		System.out.print(two.toString()); // ([1, 7.5], [3, 5.7], [2500, -6.3]) THIRD LINE
		
		System.out.println(")");
			
		
		System.out.println("="); // =   FOURTH LINE
		
		
		if(operation.equals("+") || operation.equals("-")) {
			
			System.out.print("(");
			
			System.out.print(three.toString()); // ([1, 7.5], [3, 6.7], [5000, 10.0], [60000, 5.7])
			
			System.out.println(")");
			
		} // this if block is performed only if the operation is either add or subtract
		
		else {
			
			System.out.println(product);
		
		} // prints only the dot product
	}
		
}
