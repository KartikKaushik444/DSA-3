package queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//Given two strings s and t of lengths m and n respectively, return the minimum window substring of s 
//such that every character in t (including duplicates) is included in the window. If there is
//no such substring, return the empty string "".
//
//The testcases will be generated such that the answer is unique.
//
//A substring is a contiguous sequence of characters within the string.
//
//
//
//Example 1:
//
//Input: s = "ADOBECODEBANC", t = "ABC"
//Output: "BANC"
//Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

public class MinimumWindowSubstring {
	
	  static String minWindow(String s, String t) {

	      if (s.length() == 0 || t.length() == 0) {
	          return "";
	      }

	      // Dictionary which keeps a count of all the unique characters in t.
	      Map<Character, Integer> dictT = new HashMap<Character, Integer>();
	      for (int i = 0; i < t.length(); i++) {
	          int count = dictT.getOrDefault(t.charAt(i), 0);
	          dictT.put(t.charAt(i), count + 1);
	      }

	      // Number of unique characters in t, which need to be present in the desired window.
	      int required = dictT.size();

	      // Left and Right pointer
	      int l = 0, r = 0;

	      // formed is used to keep track of how many unique characters in t
	      // are present in the current window in its desired frequency.
	      // e.g. if t is "AABC" then the window must have two A's, one B and one C.
	      // Thus formed would be = 3 when all these conditions are met.
	      int formed = 0;

	      // Dictionary which keeps a count of all the unique characters in the current window.
	      Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();

	      // ans list of the form (window length, left, right)
	      int[] ans = {-1, 0, 0};

	      while (r < s.length()) {
	          // Add one character from the right to the window
	          char c = s.charAt(r);
	          int count = windowCounts.getOrDefault(c, 0);
	          windowCounts.put(c, count + 1);

	          // If the frequency of the current character added equals to the
	          // desired count in t then increment the formed count by 1.
	          if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
	              formed++;
	          }

	          // Try and contract the window till the point where it ceases to be 'desirable'.
	          while (l <= r && formed == required) {
	              c = s.charAt(l);
	              // Save the smallest window until now.
	              if (ans[0] == -1 || r - l + 1 < ans[0]) {
	                  ans[0] = r - l + 1;
	                  ans[1] = l;
	                  ans[2] = r;
	              }

	              // The character at the position pointed by the
	              // `Left` pointer is no longer a part of the window.
	              windowCounts.put(c, windowCounts.get(c) - 1);
	              if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
	                  formed--;
	              }

	              // Move the left pointer ahead, this would help to look for a new window.
	              l++;
	          }

	          // Keep expanding the window once we are done contracting.
	          r++;   
	      }

	      return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
	  }
	

/////////////////////////////////////////////////////////////////////////////////////
//Approach 2: Optimized Sliding Window
//Intuition
//
//A small improvement to the above approach can reduce the time complexity of the algorithm 
//to better by removing all the elements not present in T.
//
//This complexity reduction is evident when |filtered\_S| <<< |S|filtered_S<<<S.
//
//This kind of scenario might happen when length of string T is way too small than the length of 
//string S and string S consists of numerous characters which are not present in T.
//
//Algorithm
//
//We create a list called filtered\_Sfiltered_S which has all the characters from string SS along
//with their indices in SS, but these characters should be present in TT.
//
//  S = "ABCDDDDDDEEAFFBC" T = "ABC"
//  filtered_S = [(0, 'A'), (1, 'B'), (2, 'C'), (11, 'A'), (14, 'B'), (15, 'C')]
//  Here (0, 'A') means in string S character A is at index 0.
//We can now follow our sliding window approach on the smaller string filtered\_Sfiltered_S.


static class Pair<A, B>
{
	A key;
	B value;
	
	Pair(A key, B value)
	{
		this.key = key;
		this.value = value;
	}
	
	B getValue()
	{
		return this.value;
	}
	
	A getKey()
	{
		return this.key;
	}
}
    static String minWindow1(String s, String t) {

        if (s.length() == 0 || t.length() == 0) {
            return "";
        }

        Map<Character, Integer> dictT = new HashMap<Character, Integer>();

        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(t.charAt(i), count + 1);
        }

        int required = dictT.size();

        // Filter all the characters from s into a new list along with their index.
        // The filtering criteria is that the character should be present in t.
        List<Pair<Integer, Character>> filteredS = new ArrayList<Pair<Integer, Character>>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (dictT.containsKey(c)) {
                filteredS.add(new Pair<Integer, Character>(i, c));
            }
        }

        int l = 0, r = 0, formed = 0;
        Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();  
        int[] ans = {-1, 0, 0};

        // Look for the characters only in the filtered list instead of entire s.
        // This helps to reduce our search.
        // Hence, we follow the sliding window approach on as small list.
        while (r < filteredS.size()) {
            char c = filteredS.get(r).getValue();
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);

            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }

            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                c = filteredS.get(l).getValue();

                // Save the smallest window until now.
                int end = filteredS.get(r).getKey();
                int start = filteredS.get(l).getKey();
                if (ans[0] == -1 || end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1;
                    ans[1] = start;
                    ans[2] = end;
                }

                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                    formed--;
                }
                l++;
            }
            r++;   
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }


	public static void main(String[] args) {
		System.out.println(minWindow("ADOBECODEBANC", "ABC"));
	}

}
