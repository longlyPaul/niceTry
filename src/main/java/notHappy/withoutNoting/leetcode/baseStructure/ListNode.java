package notHappy.withoutNoting.leetcode.baseStructure;
/**
 * @��Ȩ��Ϣ : ƽ̨����
 * @�� ��  �� : IreportServer
 * @��      �� : @author liusl
 * @�������� : 2014-8-13����09:27:44
 * @�� ˵  �� :
 */
public class ListNode {
		public int val;
		public ListNode next;
		public ListNode(int x) {
		val = x;
		next = null;
	   }

	public void print() {
		ListNode item = this;
		while (item!=null){
			System.out.println(item.val);
			item=item.next;
		}
	}
}
