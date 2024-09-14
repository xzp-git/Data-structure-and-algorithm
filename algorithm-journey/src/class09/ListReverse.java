package class09;

public class ListReverse {
	public static class ListNode {
		int val;
		ListNode next;
		
		public ListNode(int val) {
			this.val = val;
		}
		
		public ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}
	
	
	public static ListNode ListReverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode pre = null,  next = head;
		
		while(head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}
}
