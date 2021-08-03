package recursion;

public class PermutationWithCaseChange {

	static void print(String input, String output)
	{
		if(input.length() == 0)
			System.out.println(output);
		else
		{
			print(input.substring(1), output + input.substring(0, 1).toLowerCase());
			print(input.substring(1), output + input.substring(0, 1).toUpperCase());
			
		}
	}
	
	// Letter case permutations
	// Input may contain digits as well
	
	static void printWithDigits(String input, String output)
	{
		
		if(input.length() == 0)
		{
			System.out.println("Output "+output);
		}
		// checking if it is alphabet
		else if((int)input.charAt(0) >= 65 && (int)input.charAt(0) <= 122)
		{
			
			printWithDigits(input.substring(1), output + input.substring(0, 1).toLowerCase());
			printWithDigits(input.substring(1), output + input.substring(0, 1).toUpperCase());
			
		}
		//if not an alphabet
		else
		{
			
			printWithDigits(input.substring(1), output + input.charAt(0));
		}
	}

	public static void main(String[] args) {
		String s = "2a1b3";
		
		String s1 = "aBc";
		//print(s, "");
		printWithDigits(s,"");
		
		print(s1,"");

	}

}
