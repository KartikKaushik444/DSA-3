package recursion;
import java.util.Stack;

// delete middle element of stack
public class DeleteMiddleElement {
	
	static int count = 0;
	static int mid  = -1;
	static Stack<Integer> s = new Stack<>();
	
	static void deleteMiddle(Stack<Integer> s)
	{
		if(s.isEmpty())
		{
			if(count %2 != 0)
	        {
				mid = count/2 + 1;
	        }
			else
			{
				mid = count/2;
			}
		    count = 0;
			return;
		}
		int a = s.pop();
		count++;
		deleteMiddle(s);
		// this count is increased again to know when we encounter middle element
		count++;
		
		if(count == mid)
		{
			return;
		}
		else
			s.push(a);

	}
	
	public static void main(String[] args) {
		
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		s.push(6);
		System.out.println(s);
		deleteMiddle(s);
		System.out.println(s);
		
		
	}

}
