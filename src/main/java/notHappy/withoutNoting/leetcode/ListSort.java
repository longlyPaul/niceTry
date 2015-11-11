package notHappy.withoutNoting.leetcode;


import notHappy.withoutNoting.leetcode.baseStructure.ListNode;

/**
 * @��Ȩ��Ϣ : ƽ̨����
 * @�� ��  �� : IreportServer
 * @��      �� : @author liusl
 * @�������� : 2014-8-13����09:29:20
 * @�� ˵  �� :
 */
public class ListSort {
	
	public ListNode sortList(ListNode head){
		 int len = 0;
	        for( ListNode now=head; now!=null; now=now.next )
	            ++len;
	        if(len<2)
	            return head;
	        ListNode firstHead, secondHead, tail;
	        firstHead = tail = secondHead = head;
	        for( int pos=0; pos+1<len/2; ++pos )
	            tail = tail.next;       //"tail" is the firstHead's tail
	        secondHead = tail.next;
	        tail.next = null;
	        firstHead = sortList(firstHead);
	        secondHead = sortList(secondHead);
	        tail=null;                  //"tail" is the tail of new sorted list now
	        while( firstHead!=null&&secondHead!=null )
	        {
	            if(tail==null)
	            {
	                if(firstHead.val<=secondHead.val)
	                {
	                    tail = head = firstHead;    //"head" becomes the head of new sorted list
	                    firstHead = firstHead.next;
	                }
	                else
	                {
	                    tail = head = secondHead;
	                    secondHead = secondHead.next;
	                }
	            }
	            else
	            {
	                if(firstHead.val<=secondHead.val)
	                {
	                    tail.next = firstHead;
	                    firstHead = firstHead.next;
	                }
	                else
	                {
	                    tail.next = secondHead;
	                    secondHead = secondHead.next;
	                }
	                tail = tail.next;
	            }

	        }
	        if( firstHead==null )
	            tail.next = secondHead;
	        else if( secondHead==null )
	            tail.next = firstHead;
	        return head;
	}
	
	public ListNode quickList(ListNode head){
		ListNode now = head;
		int len=0;
		for(;now!=null;now=now.next)
			len++;
		return head;		
	}
	
	public int getRandom(int n,int m){
		return (int) (n+Math.random()*Integer.SIZE*10%(m-n+1));
	}
	
	public int partition(int[] num,int n,int m){
		int start=n,last=m,base=num[n];
		while(start<last){
			while(start<last&&num[start]<base)
				start++;
			num[start]=num[last];
			while(start<last&&num[last]>base)
				last--;
			num[last]=num[start];
		}
		num[start]=base;return last;
	}
	

	
	public static void main(String[] args) {
		int r=100;
		int p=80;
		for(int i=10;i<100;i++)
		System.out.println((int)(p+Math.random()*Integer.SIZE*10%(r-p+1)));
		ListSort ls=new ListSort();
		int[] nums=new int[]{4,2,5,1,6,8,12,3,9};
//		ls.quickSort(nums, 0, 8);
//		ls.quickSort(nums,0,nums.length-1);
//		for(int num:nums){
//			System.out.print(num+"\t");
//		}
	}
}
