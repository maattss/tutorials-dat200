package modifiedArrayList;

public class MyArrayList <T> {

	private Object[] elements;
	private int nOfElements;
	private int startIndex;
	
	public MyArrayList() { //Emtpy constructor with standard start capacity
		elements = new Object[10];
		nOfElements = 0;
		startIndex = 5;
	}
	
	
	public MyArrayList(int startCapacity) {
		elements = new Object[startCapacity];
		nOfElements = 0;
		startIndex = startCapacity/2;
	}
	
	public void add(int index, T element) {
		if(index > nOfElements || index < 0) {
			System.out.println("Illegal index!");
		} else {
			elements[startIndex + index] = element;
			nOfElements++;
		}
	}
	
	public void addFirst(T element) {
		if(startIndex <= 0) increaseStart();
		startIndex--;
		nOfElements++;
		elements[startIndex] = element;
		
	}
	
	public void addLast(T element) {
		if(nOfElements >= (elements.length - startIndex)) increaseEnd();
		elements[startIndex + nOfElements] = element;
		nOfElements++;
	}
	
	public T get(int index) {
		return (T)elements[startIndex + index];
	}
	
	public void remove(int index) {
		for(int i=startIndex + index; i <= startIndex + nOfElements; i++) {
			elements[i] = elements[i+1];
		}
		nOfElements--;
	}
	
	private void increaseStart() {
		if (nOfElements <= elements.length/2) {
			for(int i = startIndex; i < startIndex + nOfElements; i++) {
				elements[i + elements.length/4] = elements[i];
				elements[i] = null;
			}
			startIndex += elements.length/4;
		} else {
			Object[] newArray = new Object [elements.length*2];
			for(int i = startIndex; i < startIndex + nOfElements; i++) {
				newArray[i + elements.length] = elements[i];
		}
			elements = newArray;
		}
		
	}
	
	public void increaseEnd() {
		if(startIndex >= elements.length/2) {
			for(int i = startIndex; i < startIndex + nOfElements; i++) {
				elements[i - elements.length/4] = elements[i];
				elements[i] = null;
			}
		} else {
			Object[] newArray = new Object[elements.length*2];
			for(int i = startIndex; i < startIndex + nOfElements; i++) {
				newArray[i] = elements[i];
			}
			elements = newArray;
		}
	}
	
	public String toString() {
		String s = "";
		for(int i = 0; i < elements.length; i++) {
			if (elements[i] != null) {
				s += (T)elements[i];
				s += " ";
			} 
		}
		return s;
	}
	
	public String debugtoString() {
		String s = "";
		for(int i = 0; i < elements.length; i++) {
			if (elements[i] == null) {
				s += "x";
			} else {
				s += (T)elements[i];
			}
			if (i == elements.length -1) break;
			s += "\t";
		}
		return s;
	}
	
	
	//Testing
	public static void main (String [] args) {
		MyArrayList<Integer> test = new MyArrayList<>();
		test.addFirst(0);
		test.addFirst(1);
		test.addFirst(2);
		System.out.println(test.debugtoString());
		test.addLast(3);
		test.addLast(4);
		test.addLast(5);
		System.out.println(test.debugtoString());
		test.addLast(6);
		test.addLast(7);
		test.addLast(8);
		System.out.println(test.debugtoString());
		test.remove(5);
		test.remove(3);
		System.out.println(test.debugtoString());
		test.add(48, 99);
		test.add(2, 99);
		System.out.println(test.debugtoString());
		test.addLast(9);
		test.addLast(9);
		test.addLast(9);
		test.addLast(9);
		test.addLast(9);
		test.addLast(9);
		test.addLast(9);
		test.addLast(9);
		test.addLast(9);
		test.addLast(9);
		test.addLast(9);
		test.addLast(9);
		test.addLast(9);
		test.addLast(9);
		test.remove(0);
		test.remove(0);
		test.remove(0);
		test.remove(0);
		test.remove(0);
		test.remove(0);
		test.remove(0);
		test.remove(0);
		test.remove(0);
		test.remove(0);
		test.remove(0);
		test.remove(0);
		test.remove(0);
		test.remove(0);
		test.remove(0);
		test.remove(0);
		test.remove(0);
		test.remove(0);
		test.addFirst(8);
		test.addFirst(8);
		test.addFirst(8);
		System.out.println(test.debugtoString());
		test.addFirst(11);
		test.addLast(11);
		System.out.println(test.debugtoString());
		
		
	}
	
}
