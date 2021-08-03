package linkedList;

public class OddEvenLL {
	
	static LNode oddEvenList(LNode head) {
        if (head == null) return null;
        LNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
	

	public static void main(String[] args) {
		

	}

}
