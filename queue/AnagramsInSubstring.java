package queue;
import java.util.*;
// find all anagrams in a string
public class AnagramsInSubstring {
	
	static void solve(String s, String patt)
	{
		HashMap<Character, Integer> smap = new HashMap<>();
		HashMap<Character, Integer> pmap = new HashMap<>();
		
		for(int i=0;i<patt.length();i++)
		{
			char ch = patt.charAt(i);
			pmap.put(ch, pmap.getOrDefault(ch, 0)+1);
		}
		// first window of string
		for(int i=0;i<patt.length();i++)
		{
			char ch = s.charAt(i);
			smap.put(ch, smap.getOrDefault(ch, 0)+1);
		}
	
		int i = patt.length();
		int count = 0;
		while(i < s.length())
		{
			
			if(compare(pmap, smap) == true)
			{
				count++;
			}
			
			// acquire new character
			char ch = s.charAt(i);
			smap.put(ch, smap.getOrDefault(ch, 0)+1);
			
			// remove out of window character
			ch = s.charAt(i - patt.length());
			if(smap.get(ch) == 1)
				smap.remove(ch);
			else
				smap.put(ch, smap.get(ch)-1);
			
			i++;
			
		}
		// for last window
		if(compare(pmap, smap) == true)
		{
			count++;
		}
		
		System.out.println(count);
	}
	
	public static boolean compare(HashMap<Character, Integer> pmap, HashMap<Character, Integer> smap )
	{
		for(char sch : smap.keySet())
		{
			if(pmap.getOrDefault(sch, 0) != smap.get(sch))
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		solve("abcabaccba", "abac");
	}

}
