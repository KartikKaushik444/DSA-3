package recursion;

import java.util.ArrayList;

public class AllCombinationsOfParentheses {
	
	static ArrayList<String> ans = new ArrayList<>();
	
	static int ways = 0;// For 0 combinations ways will give one as ans(edge case)
	
	static void combinations(int open, int close, String str)
	{
		if(open > close)  // no need to proceed further if open > close
			return;
		
		if(open < 0 || close < 0)
			return;
		if(open == 0 && close == 0)
		{
			ways+=1;
			//ans.add(str);
			System.out.println(str);
			//str = str.substring(0, str.length() - 2);
			return;
		}

		if(open == close)
		{
            // only open bracket can be added
			combinations(open-1, close, str+"{");

		}
		else if(close > open)
		{
				
			combinations(open - 1, close, str+"{");
		   
			combinations(open, close - 1, str + "}");
		}
	
		
	}

	public static void main(String[] args) {
		int n=3;
		combinations(n,n,"");
		System.out.println(ways);

	}

}
