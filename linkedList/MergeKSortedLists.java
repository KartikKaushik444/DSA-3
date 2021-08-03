package linkedList;

import java.util.PriorityQueue;

public class MergeKSortedLists {
	
	static class Pair implements Comparable<Pair>
	{
		int data;
		LNode node;
		
		public Pair(int data, LNode node)
		{
			this.data = data;
			this.node = node;
		}

		@Override
		public int compareTo(Pair o) {
			
			return this.data - o.data;
		}
	}
	
	static LNode solve(LNode a[])
	{
		int n = a.length;
		
		PriorityQueue<Pair> heap = new PriorityQueue<>();
		
		for(int i=0;i<n;i++)
		{
			if(a[i] != null)
			{
				heap.add(new Pair(a[i].data, a[i]));
			}
		}
		LNode head = new LNode(-1);
		LNode ans = head;
		while(!heap.isEmpty())
		{
			Pair curr = heap.poll();
			
			if(curr != null)
			{
				if(curr.node.next != null)
				{
					heap.add(new Pair(curr.node.next.data, curr.node.next));
				}
				ans.next = curr.node;
				ans = ans.next;
			}
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
		
		list1.insert(3);
		list1.insert(5);
	    list1.insert(6);
		
LinkedList list2=new LinkedList();
		
		list2.insert(2);
		list2.insert(4);
	    list2.insert(8);
	    
	   // LNode a[] = new LNode[2];
	    
	    LNode a[]  = {list1.head, list2.head};
//	    a[0] = list1.head;
//	    a[1] = list2.head;
	    LNode merge = solve(a);
	    
	    printList(merge);

	}

}
