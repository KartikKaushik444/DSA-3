package recursion;

public class WinCoinGame {
	
	static int winGame(int a[], int i, int j)
	{
		if(i == j-1)
			return Math.max(a[i], a[j]);
		
		return Math.max(a[i] + Math.min(winGame(a, i+2, j) , winGame(a, i+1, j-1)),
				        a[j] + Math.min(winGame(a, i+1, j-1), winGame(a, i, j-2))); 
	}
	// This can be optimized using dp
	
	static void winGameDP(int a[])
	{
		int n = a.length;
		int dp[][] = new int[n][n];
		
		for(int g=0;g<n;g++)
		{
			for(int i=0,j=g;j<n;i++, j++)
			{
				if(g == 0)
					dp[i][j] = a[i];
				else if(g == 1)
					dp[i][j] = Math.max(a[i], a[j]);
				else
				{
					int val1 = a[i] + Math.min(dp[i+2][j], dp[i+1][j-1]);
					int val2 = a[j] + Math.min(dp[i+1][j-1], dp[i][j-2]);
					
					dp[i][j] = Math.max(val1, val2);
				}
			}
		}
		System.out.println( dp[0][n-1]);
	}

	public static void main(String[] args) {
		int a[]= {1, 5, 700, 2};    //{1, 5, 7, 3, 2, 4};
		System.out.println(winGame(a, 0, a.length-1));
		winGameDP(a);

	}

}
