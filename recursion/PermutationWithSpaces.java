package recursion;

public class PermutationWithSpaces {
	
	static void print(String input, String output)
	{
		if(input.length() == 0)
			System.out.println(output);
		else if(output.length() == 0)
		{
			// not adding space with the element
			print(input.substring(1), output + input.charAt(0));
		}
		else
		{
			// adding space with the element
			String add = " " + input.charAt(0);
			print(input.substring(1), output + add);
			// not adding space with the element
			print(input.substring(1), output + input.charAt(0));
		}
	}

	public static void main(String[] args) {
		String s = "ABCD";
		print(s, "");

	}

}
