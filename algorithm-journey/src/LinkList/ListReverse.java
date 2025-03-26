package LinkList;

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
		if(head == null || head.next == null) {
			return head;
		}
		
		ListNode pre = null, next = head;
		
		while(head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		
		return pre;
	}
	 // ✅ 测试用例
    public static void main(String[] args) {
        // 创建链表：1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("原始链表：");
        printList(head);

        // 反转链表
        ListNode reversedHead = ListReverse(head);

        System.out.println("反转后的链表：");
        printList(reversedHead);
    }

    // 辅助方法：打印链表
    public static void printList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val);
            if (temp.next != null) {
                System.out.print(" -> ");
            }
            temp = temp.next;
        }
        System.out.println();
    }
}
