package recursion;

public class KthSymbolInGrammer {
	
	// Row 1: 0                    2^0
	// Row 2: 01                   2^1
	// Row 3: 0110                 2^2
	// Row 4: 01101001             2^3
	
	static int solve(int n , int k)
	{
		if(n ==1 || k == 1)
		{
			return 0;
		}
		
		int length = (int) Math.pow(2, n-1);
		
		int mid = length / 2;
		
		if(k <= mid)
		{
			return solve(n-1, k);
		}
		else
		{			
			if(solve(n-1, k - mid) == 0)
				return 1;
			else
				return 0;			
		}
		
	}

	public static void main(String[] args) {
		int n = 4;
		int k = 5;
		
		System.out.println(solve(n, k));

	}

}

