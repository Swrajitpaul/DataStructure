import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Scanner;

public class PostFix {
	
	private DynamicArrayStack<String>  postFixDynamicArrayStack = new DynamicArrayStack<>(); // stack from project 2
	
	private LinkedQueue<String> postFixQueue = new LinkedQueue<>(); // used in order to store post fix expression 
	
	private LinkedStack<BinaryTree<String>> postFixLinkedBinaryTreeStack; // stack that stores binary trees
	
	private BinaryTree<String> tree; // tree used to store the final arithmetic expression tree
	
	
	
	public PostFix(String line) {
		
		System.out.println("Original expression in infix notation: " + line); // prints out the infix expression (PART 1 of outline)

		String nLine = line.replaceAll("\\s+",""); // replaces the spaces in a string

		
		
		for (int i = 0; i < nLine.length(); i++) { //goes through the string
			
			String a = nLine.substring(i, i+1);
			
			if( !a.equals("+") && !a.equals("–") && !a.equals("*") && !a.equals("/") ) {
				
				if(a.equals("(") ) { // if the operation is the left parenthesis 
					
					postFixDynamicArrayStack.push(a);
				}
				
				else if (a.equals(")") ) { // if the operation is right parenthesis 
					
					if(!postFixDynamicArrayStack.top().equals("(")) { //places the operators in the queue from the stack and throws out the left parenthesis
						
						String op = postFixDynamicArrayStack.pop();
						
						postFixQueue.enqueue(" " + op);
						
						postFixDynamicArrayStack.pop();
					}
					
				}
				
				else { // else if it is an operand
					
					postFixQueue.enqueue(a);
					
				}
				
			}
		
				
			else if(!postFixDynamicArrayStack.isEmpty()) {
					
				while(!postFixDynamicArrayStack.isEmpty() && ( prec(postFixDynamicArrayStack.top()) ==  prec(a) ||  prec(postFixDynamicArrayStack.top()) >  prec(a)) ) {
					
					String op = postFixDynamicArrayStack.pop();
					
					postFixQueue.enqueue(" "+ op);
				
				} // places the operator to the queue from the stack until the condition is met
				
				postFixQueue.enqueue(" ");
				
				postFixDynamicArrayStack.push(a);
				
			}
			
			else if(postFixDynamicArrayStack.isEmpty()) {
				
				postFixDynamicArrayStack.push(a);
				
				postFixQueue.enqueue(" ");
				
			} // if the stack is empty then the operator is placed into the stack
			
		
		}
		
		postFixQueue.enqueue(" ");
		
		while (!postFixDynamicArrayStack.isEmpty()) {
			
			String op = postFixDynamicArrayStack.pop();
			
			postFixQueue.enqueue(op+ " ");
			
		} // enqueue out the operators remaining in the stack
		

		postFixQueue = stringTogether(postFixQueue);
		
		arithmeticExpressionTreeCreation();
		
	}
	
	/**
	 * This method takes the queue and creates a binary expression tree by using LinkedStacks.
	 * The code implements the algorithm that was described on the project handout
	 */
	private  void arithmeticExpressionTreeCreation () {
		
		postFixLinkedBinaryTreeStack= new LinkedStack<>();
		
		while (!postFixQueue.isEmpty()) {
			
			if(!postFixQueue.first().equals("+") && !postFixQueue.first().equals("–") && !postFixQueue.first().equals("*") && !postFixQueue.first().equals("/")) {
				
				BinaryTree<String> T = new BinaryTree<>(postFixQueue.dequeue());
				
				postFixLinkedBinaryTreeStack.push(T);
				
				
			}
			
			else {
				
				BinaryTree<String> T = new BinaryTree<>(postFixQueue.dequeue());
				
				BinaryTree<String> rightChild = new BinaryTree<>();
				
				BinaryTree<String> leftChild = new BinaryTree<>();
				
				rightChild = postFixLinkedBinaryTreeStack.pop();
					
				leftChild = postFixLinkedBinaryTreeStack.pop();
					
				T.attach(leftChild, rightChild);
					
				postFixLinkedBinaryTreeStack.push(T);
	
			}
		
		}
		
		tree = postFixLinkedBinaryTreeStack.pop();
		
		
		arithmeticExpressionTreeEvaluation();
		
	}
	
	/**
	 * The method iterates through binary tree and creates 
	 */
	private  void arithmeticExpressionTreeEvaluation() {
		
		Iterator printPostFix = tree.iterator();
		
		System.out.print("postfix from the arithmetic expression tree: ");

		
		while(printPostFix.hasNext()) {
			
			System.out.print(printPostFix.next() + " ");
		
		}
		
		System.out.println();
		
		Iterator it = tree.iterator();
		
		LinkedStack<Double> expressionStack= new LinkedStack<>();
		
		while(it.hasNext()) {
			
			String temp = it.next().toString();
			
			if(!temp.equals("*") && !temp.equals("/") && !temp.equals("+") && !temp.equals("–")) {
				
				// check to see if it the the popped item from the stack is a double or a variable, if it is a variable then ask for user input
				try {
					
					expressionStack.push(Double.parseDouble(temp));
				
				} 
				
				catch (Exception e) {
					
					Scanner userInput = new Scanner(System.in);
					
					double dobTemp;
					
					System.out.print("Please enter an integer or floating point number for the variable " + temp + ": ");
					
					while(true) {
						
						try {
		
							dobTemp = Double.parseDouble(userInput.next());
							
							expressionStack.push(dobTemp);
							
							break;
						
						} 
						
						catch (Exception e2) {
							
							System.out.print("Try again! Enter an integer or floating point number for the variable " + temp + ": "  );
						
						}
							
					} // end of while loop
					
				}
				
			} // end of if block
			
			else {
				
				double rightOperand;
				
				double leftOperand;
				
				double total;
				
				rightOperand = expressionStack.pop();
				
				leftOperand = expressionStack.pop();
				
				if(temp.equals("*")) {
					
					total = rightOperand * leftOperand;
					
					expressionStack.push(total);
					
				}
				
				if(temp.equals("/")) {
					
					total = leftOperand / rightOperand;
					
					expressionStack.push(total);
				
				}
				
				if(temp.equals("+")) {
					
					total = rightOperand + leftOperand;
					
					expressionStack.push(total);
				
				}
				
				if(temp.equals("–")) {
					
					total = leftOperand - rightOperand;
					
					expressionStack.push(total);
					
				}
	
			}
			
		}
		
		double result = expressionStack.pop();
		
		System.out.println("Evaluated expression: " + result);
	
	} // end  of constructor
	
	
	
	/**
	 * this method determines the operation precedence by comparing the input to specified precedence value
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
	
	
	
	/**
	 * The purpose of this method is to take a LinkedQueue and truncate it by taking all the space out of it
	 * 
	 * @param queueOne takes in a linkedQueue that contains spaces
	 * @return a linkedQueue that doesn't contain any spaces by a converting the linkedQueue to string and back to linkedQueue again 
	 */
	private LinkedQueue<String> stringTogether(LinkedQueue<String> queueOne){
		
		LinkedQueue<String> que = queueOne;
		
		String str = "";
		
		while(!que.isEmpty()) {
			
			str += que.dequeue();
		
		}
		
		StringTokenizer token = new StringTokenizer(str);
		
		while (token.hasMoreTokens()) {
			
			que.enqueue(token.nextToken());
			
		}
		
		return que;
		
	} //end of stringTogether method
		

} // end of class
