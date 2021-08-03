package recursion;
// Four keys are allowed to be pressed
// A
// Ctrl + A
// Ctrl + C
// Ctrl + V
// find max A which can be printed if you are allowed to press n keys
public class SpecialKeyboard {
// Idea is to find the breakpoint after which we can use A*, C*, V* followed by V*
	
	static int solve(int n)
	{
		if(n<=6)
			return n;
		// An array to store result of subproblems
		int screen[] = new int[n+1];
		
		int b;// to pick a breakpoint
		
		for(int i=1;i<=6;i++)
		{
			screen[i] = i;
		}
		
		for(int i=7;i<=n;i++)
		{
			// Initializing length of optimal string for n keyStrokes
			screen[i] = 0;
			for(b = n-3 ; b>=1 ; b--)
			{
				// if breakpoint is at b'th keystroke then the optimal string would have
				// length (n-b-1)*screen[b-1]
				int curr = (i-b-1) * screen[b];
				
				if(curr > screen[i])
					screen[i] = curr;
			}
		}
		return screen[n];
		
	}
	public static void main(String[] args) {
		for(int n=1;n<=20;n++)
			System.out.println("For "+n+" keystrokes answer is "+solve(n));

	}

}
