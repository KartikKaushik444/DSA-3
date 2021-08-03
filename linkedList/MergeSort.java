package linkedList;
// merge sort 
// things different than array merge sort
// 1. Finding the middle element has to be done using fast and slow pointers
// 2. In every iteration have to break the linked list to divide into two parts

public class MergeSort {
	static LNode merge_sort(LNode head)
	{
		if(head == null || head.next == null)
			return head;
		
		// Very Important
		if(head.next.next == null) // only two nodes
		{
			if(head.data < head.next.data)
				return head;
			
			int temp = head.data;
			head.data = head.next.data;
			head.next.data = temp;
			return head;
		}
		
		LNode slow = head;
		LNode fast = head;
		
		// find middle
		while(fast != null && fast.next != null)
		{
			fast = fast.next.next;
			slow = slow.next;
		}
		
		LNode first = head;
		LNode second = slow.next;
		
		slow.next = null;
		
		first = merge_sort(first);
		second = merge_sort(second);
		
		head = merge(first, second);
		
		return head;
		
	
		
	}
	
	static LNode merge(LNode l1, LNode l2)
	{
		if(l1 == null)
			return l2;
		if(l2 == null)
			return l1;
		LNode ans = new LNode(-1);
		LNode head = ans;
		
		while(l1 != null && l2 != null)
		{
			
			if(l1.data < l2.data)
			{
				ans.next = l1;
				l1 = l1.next;
			}
			else
			{
				ans.next = l2;
				l2 = l2.next;
			}
			
			ans = ans.next;
		
		}
		
	
		if(l1!=null )
		{
			ans.next = l1;
		}
		if(l2!=null)
		{
			ans.next = l2;
		}
		
		return head.next;
	}
	
	public static void printList(LNode head)
	{
		if(head==null)
			System.out.println("List is empty");
		else
		{
			LNode temp=head;
			while(temp!=null)
			{
				System.out.print(temp.data+" ");
			    temp=temp.next;

			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		LinkedList list1=new LinkedList();
		
		list1.insert(30);
		list1.insert(50);
	    list1.insert(60);
		list1.insert(40);
		list1.insert(250);
	    list1.insert(10);
		list1.insert(150);
		
		LNode merged = merge_sort(list1.head);
		printList(merged);
		

	}

}
