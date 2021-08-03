package recursion;

public class TowerOfHanoi {
	static long count = 0;
	
	static void TOH(int n, char start, char dest, char helper)
	{
		if(n == 1)
		{
			count++;
			System.out.println("Disk " + n + " moved from "+start+" to "+ dest);
		}
		
		else
		{
			count++;
			TOH(n-1, start, helper, dest);
			System.out.println("Disk " + n + " moved from "+start+" to "+ dest);
			TOH(n-1, helper, dest, start);
		}
	}

	public static void main(String[] args) {
		int n = 64;
		char start = 'A';
		char helper = 'B';
		char dest = 'C';
		TOH(n, start, dest, helper);
		System.out.println(count);

	}

}
