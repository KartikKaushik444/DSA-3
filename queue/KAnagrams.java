package queue;

import java.util.*;

// check if two string are k anagrams or not
// two string are k anagrams if - 
// 1. -both have same number of characters
// 2. -two strings can become anagram by changing at most k characters in a string

public class KAnagrams {
	
	// idea is to find out how many individual characters are more or less in s1, which is how many
	// characters we need to change, If more than k than return false else true
	static boolean solve(String s1, String s2, int k)
	{
		if(s1.length() != s2.length())
			return false;
		
		HashMap<Character, Integer> map = new HashMap<>();
		
		for(int i=0;i<s1.length();i++)
		{
			char ch = s1.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0)+1);
		}
		
		for(int i=0;i<s2.length();i++)
		{
			char ch = s2.charAt(i);
			// reduce the frequency of every character if it is not already 0 
			if( map.getOrDefault(ch, 0) > 0)
				map.put(ch, map.getOrDefault(ch, 0)-1);
		}
		
		int count = 0;
		for(char ch : map.keySet())
		{
			count += map.get(ch);
		}
		
		return count > k?false:true;
	}

	public static void main(String[] args) {
		System.out.println(solve("anagram", "grammar", 3));
		System.out.println(solve("geeks", "eggkf", 1));
		
		
	}

}
