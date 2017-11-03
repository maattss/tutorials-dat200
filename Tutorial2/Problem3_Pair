package problem3;

public class Pair<T> {

	private T object1;
	private T object2;
	
	public Pair(T object1, T object2) {
		this.object1 = object1;
		this.object2 = object2;
	}
	
	public T getObject1() {
		return object1;
	}

	public void setObject1(T object1) {
		this.object1 = object1;
	}

	public T getObject2() {
		return object2;
	}

	public void setObject2(T object2) {
		this.object2 = object2;
	}
	
	@Override
	public String toString() {
		return "Object 1: " + object1.toString() + ", Object 2: " + object2.toString();
	}
	

	public static void main (String [] args) {
		Pair<Integer> integerPair = new Pair<Integer>(1, 2);
		Pair<String> strengPair = new Pair<String>("Test1", "Test2");
		
		System.out.println("Integer " + integerPair);
		System.out.println("Streng " + strengPair);
	}
}
