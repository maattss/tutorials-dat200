package problem2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public class IntersectionOfTwoLists {
	

	public static <T> Collection<T> intersection (Collection<? extends T> c1, Collection<? extends T> c2) {
		Collection<T> result = new ArrayList<>();
		
		for (T element: c1) {
			if(c2.contains(element)) result.add(element);
		}
		
		return result;
	}

	public static void main(String[] args) {
		List<Integer> list1 = new ArrayList<>();
		
		List<Integer> list2 = new ArrayList<>();
	
		list1.add(1);
		list1.add(2);
		list1.add(3);
		
		list2.add(1);
		list2.add(3);
		list2.add(5);
		
		System.out.println(intersection(list1, list2));
	
	}

}
