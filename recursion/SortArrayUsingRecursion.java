package recursion;

public class SortArrayUsingRecursion {
	// One more method is in progress
	static void sort(int []a, int n)
	{
		if(n == 0)
		{
			return;
		}
		
		sort(a, n-1);
		
		if(a[n-1] > a[n])
		{
			int temp = a[n];
			a[n] = a[n-1];
			a[n-1] = temp;
			sort(a, n-1);
		}
	}

	public static void main(String[] args) {
	
		int a[] = {5, 4, 3, 6, 2, 1};
		sort(a, a.length-1);
		for(int i:a)
			System.out.print(i+" ");

	}

}
