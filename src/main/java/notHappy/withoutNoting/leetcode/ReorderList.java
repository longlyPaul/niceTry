package notHappy.withoutNoting.leetcode;

import notHappy.withoutNoting.leetcode.baseStructure.ListNode;

import java.util.Stack;

/**
 * @��Ȩ��Ϣ : ƽ̨����
 * @�� ��  �� : IreportServer
 * @��      �� : @author liusl
 * @�������� : 2014-8-13����01:14:03
 * @�� ˵  �� :
 */
public class ReorderList {
	public void reorderList(ListNode head){
		
		int count=0;
		ListNode tmpe=head;
		for(ListNode now=head;now!=null;now=now.next)
			count++;
		if(count<3)
			return;
		ListNode[] nodes=new ListNode[count];
		for(int i=0;i<count;i++){
			nodes[i]=tmpe;
			tmpe=tmpe.next;
		}
		
		int start=0;
		while(start<count-1){
			ListNode secode=nodes[start].next;
			nodes[start].next=nodes[count-1];
			nodes[count-1].next=secode;
			start++;count--;
			if(start>=count-1)
				nodes[start].next=null;
		}
		
	}
	
	public void reorderList2(ListNode head) {
        if(head==null||head.next==null||head.next.next==null)return;
        ListNode temp=head;
        Stack<ListNode> sln=new Stack<ListNode>();
        sln.push(head);
        ListNode end;
        int count=1;
        while(temp.next!=null){
        	temp=temp.next;
			sln.push(temp);
			count++;
		}
        ListNode current=head;
        end=sln.pop();
        while(current!=end&&current.next!=end){
        	temp=current.next;
        	current.next=end;
        	end.next=temp;
        	current=temp;
        	end=sln.pop();
        	count--;
        }
        end.next=null;
    }
	
	public static void main(String[] args) {
		ListNode ln = new ListNode(1);
		ln.next=new ListNode(2);
		ln.next.next=new ListNode(3);
		ln.next.next.next=new ListNode(4);
		ln.next.next.next.next=new ListNode(5);
		ReorderList rl=new ReorderList();
		rl.reorderList(ln);
		for(int i=0;i<8;i++){
			System.out.println(ln.val);
			ln=ln.next;
		}
	}
}
