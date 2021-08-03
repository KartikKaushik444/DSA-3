package recursion;

public class JosephusProblem {
	
	// this function will return 1 less value (actually the index)
	static int solve(int n, int k)
	{
		if(n == 1)
			return 0;   // This is the index who will survive ( n-1)
		
		int x = solve(n-1, k);    // this will survive
		int y = (x + k)%n;        // mapping it with original value
		return y;
	}
	
	// adding one will return position
	static int jos(int n, int k)
	{
		return solve(n,k) + 1;
	}

	public static void main(String[] args) {
		
		System.out.println(jos(5, 2));  
		System.out.println(jos(7, 3));
		System.out.println(jos(14, 2));  

	}

}
