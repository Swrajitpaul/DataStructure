public class Element implements Comparable<Element> {
	
	private int index;
	
	private double value;
	
	/**
	 * 
	 * @param index takes a string with the index
	 * @param value takes a string with the value
	 */
	public Element(String index, String value) {
		
		setIndex(Integer.parseInt(index));
		
		setValue(Double.parseDouble(value));

	}
	
	/**
	 * 
	 * @return returns the index of the element
	 */
	public int getIndex(){
		 
		return this.index;
	
	}
	
	/**
	 * 
	 * @param index sets the index of an element
	 */
	public void setIndex(int index){
		
		this.index = index;
	
	}
	
	/**
	 * 
	 * @return returns the value associated with the element
	 */
	public double getValue(){
		
		return this.value;
	
	}
	
	/**
	 * 
	 * @param value sets the value of an element
	 */
	public void setValue(double value){
		
		this.value = value;
	
	}

	/**
	 * compares the indexes of two elements to see if they're greater than or equal or less than with respect to the other 
	 */
	public int compareTo(Element o) {
		
		int inequality = -2;
		
		if(this.getIndex() == o.getIndex()) {
			
			inequality = 0;
		
		} // checks if the index's are equal
		
		else if(this.getIndex() > o.getIndex()) { 
			
			inequality = 1;
		
		} // checks to see if the first index is greater than the second index
		
		else if (this.getIndex() < o.getIndex()) { 
			
			inequality = -1;
		
		} // checks to see if the first index is less than second index
		
		return inequality;
	}
	
	/**
	 * returns a string with the elements in a specific format
	 */
	public String toString(){
		
		String pair = "[" + String.valueOf(this.index) + "," + String.valueOf(this.value) + "]";
		
		return pair;
	}
	
} // end of class Element
