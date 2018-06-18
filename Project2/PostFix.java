public class PostFix {
	
	public PostFix(String line) {
		
		DynamicArrayStack<String>  opPost = new DynamicArrayStack<>();
		
		System.out.println(line); // prints out the infix expression

		String nLine = line.replaceAll("\\s+",""); // replaces the spaces in a string

		for (int i = 0; i < nLine.length(); i++) { //goes through the string
			
			String a = nLine.substring(i, i+1);
			
			if( !a.equals("+") && !a.equals("–") && !a.equals("*") && !a.equals("/") ) {
				
				if(a.equals("(") ) { // left parenthesis 
					
					opPost.push(a);
				}
				
				else if (a.equals(")") ) { // right parenthesis 
					
					if(!opPost.top().equals("(")) { //prints out the operators in the stack and throws out the left parenthesis
						
						System.out.print(" " +opPost.pop());
						
						opPost.pop();
					}
					
				}
				
				else {
					
					System.out.print(a); // prints the operand 
					
				}
				
			}
		
				
			else if(!opPost.isEmpty()) {
					
				while(!opPost.isEmpty() && ( prec(opPost.top()) ==  prec(a) ||  prec(opPost.top()) >  prec(a)) ) {
					
					System.out.print(" " +opPost.pop() );	
					
				} // prints the operator in the stack until the conditions are not met
				
				System.out.print(" ");
				opPost.push(a);
				
			}
			
			else if(opPost.isEmpty()) {
				
				opPost.push(a);
				
				System.out.print(" ");
				
			} // if the stack is empty then the operator is placed into the stack
			
		
		}
		
		System.out.print(" ");
		
		while (!opPost.isEmpty()) {
			
			System.out.print(opPost.pop() + " ");
			
		} // prints out the operators remaining in the stack
		
		
		System.out.println();
		System.out.println(); 

	}
	
	/**
	 * 
	 * @param op takes in a string that represents a operator
	 * @return returns a value representing the order of the operator
	 */
	private int prec(String op) {
		
		int prec = -1;
		
		if(op.equals("(") || op == ")"){
			prec = 0;
		}
		
		if(op.equals("^")) {
			prec = 3;
		}
		
		if(op.equals("*") || op.equals("/")) {
			prec = 2;
		}
		
		if(op.equals("+") || op.equals("–")) {
			prec = 1;
		}
		
		
		return prec;
	} // end of precedence method

} // end of class
