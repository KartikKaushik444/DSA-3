package recursion;

public class Power {
	// This function will call itself recursively b+1 times
	static int stepCount=0;
	static int pow(int a,int b)
	{
		stepCount++;
		if(b == 1)
			return a;
		else
		{
			return pow(a,b-1)*a;
		}
	}
	
	static int fastPow(int a,int b)                // log n solution
	{
		stepCount++;
		System.out.println(b);
		if(b == 0)
			return 1;
		if(b%2 == 0)                       // If even
			return fastPow(a*a, b/2);      // reduces b by half
		// If odd
		return a*fastPow(a, b-1);
	}

	public static void main(String[] args) {
		System.out.println(pow(3,5));
		System.out.println(stepCount);
		
		stepCount = 0;
		
		System.out.println(fastPow(3,1000));
		System.out.println(stepCount);
	}

}
