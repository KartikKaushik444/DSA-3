package recursion;

public class IsPalindrome {
	
	static boolean isPalindrome(String s)
	{		
		int n=s.length();
		if(n == 0)
				return true;
		if(n%2==0)
			return isPalindromeUtil(s, true, 0, n-1);
		else
			return isPalindromeUtil(s, false, 0, n-1);
		
		
		// Iterative
//		int i = 0;
//		int j = s.length() - 1;
//		
//		while(i <= j)
//		{
//			if(s.charAt(i) != s.charAt(j))
//				return false;
//			i++;
//			j--;
//		}
//		return true;
	}

	
	static boolean isPalindromeUtil(String s, boolean isEven, int i, int j)
	{
		if(isEven)
		{
			// base condition if even 
			if(j == i+1 )
			{
				 if( s.charAt(i) == s.charAt(j))
					 return true;
				 else
					 return false;
			}
				
		}
		
		if(! isEven)
		{
			// base condition if odd
			if(i == j )
			{
				 if( s.charAt(i) == s.charAt(j))
					 return true;
				 else
					 return false;
			}
		}
		 
		return isPalindromeUtil(s, isEven, i+1, j-1) && s.charAt(i) == s.charAt(j);
			
			
	}
	
	
	public static void main(String[] args) {
		System.out.println(isPalindrome("abcba"));

	}

}
