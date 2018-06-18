public class DynamicArrayStack<AnyType> implements Stack<AnyType>
{
  public static final int DEFAULT_CAPACITY = 256;
  AnyType[] data;
  int topOfStack;

  public DynamicArrayStack() { this(DEFAULT_CAPACITY); }

  public DynamicArrayStack(int capacity)
  {
    topOfStack = -1;
    
    data = (AnyType[]) new Object[capacity];
  }

  public int size()
  {
    int size = (topOfStack + 1);
	  
    return size;
  }

  public boolean isEmpty()
  {
    
	boolean empty = false;
    
	if (topOfStack == -1)
    	empty = true;
    
	return empty;
  }

  public void push(AnyType newValue)
  {
    topOfStack++;
	
    if (topOfStack == data.length) {
		resize(2 * data.length);
	}
    
    data[topOfStack] = newValue;
	  
  }

  public AnyType top()
  {
    if(isEmpty() == true)
    	return null;
	  
	return data[topOfStack];
  }

  public AnyType pop()
  {
	  
	 int n = size();
     
	 if (isEmpty() == true) {
		return null;
	}
     
     else if ( n <= (data.length / 4)) 
    	 resize(data.length / 2);
	  
     return data[topOfStack--];
     
  }

  protected void resize(int newCapacity)
  {
    int n = size();

    AnyType[] temp = (AnyType[]) new Object[newCapacity];
    for (int i=0; i < n; i++)
      temp[i] = data[i];
    data = temp;
  }
}