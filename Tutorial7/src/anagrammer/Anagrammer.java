package anagrammer;

import java.util.HashMap;
import java.util.Map;

public class Anagrammer {
	
	public static boolean sjekkAnagrammer(String ord1, String ord2) {
		char [] ord1CharArray = ord1.toCharArray();
		char [] ord2CharArray = ord2.toCharArray();
		
		Map<Character, Integer> bokstaverIOrd = new HashMap<>();
		for(char c: ord1CharArray) {
			int teller = 1;
			if(bokstaverIOrd.containsKey(c)) {
				teller = bokstaverIOrd.get(c) + 1;
			}
			bokstaverIOrd.put(c, teller);
		}
		
		for(char c: ord2CharArray) {
			int teller = -1;
			if(bokstaverIOrd.containsKey(c)) {
				teller = bokstaverIOrd.get(c) - 1;
				if(teller == 0) {
					bokstaverIOrd.remove(c);
				} else {
					bokstaverIOrd.put(c, teller);
				}
			} else {
				return false;
			}	
		}
		
		if(bokstaverIOrd.isEmpty()) return true;
		return false;
	}

	public static void main (String [] args) {
		String ord1 = "schoolmaster";
		String ord2 = "theclassroom";
		System.out.println(sjekkAnagrammer(ord1, ord2));
	}
}
