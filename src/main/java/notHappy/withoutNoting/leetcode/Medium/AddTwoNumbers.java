package notHappy.withoutNoting.leetcode.Medium;

import notHappy.withoutNoting.leetcode.baseStructure.ListNode;

/**
 * Created with AddTwoNumbers
 * User: pual
 * Date: 2016/3/2
 * Desc:
 */
public class AddTwoNumbers {
    private final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(AddTwoNumbers.class);

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int count1=0,count2=0;
        ListNode temp1=l1,temp2=l2;
        while (temp1!=null || temp2!=null){
            if(temp1!=null){
                count1++;
                temp1=temp1.next;
            }
            if (temp2!=null){
                count2++;
                temp2=temp2.next;
            }
        }
        ListNode result = new ListNode(0);
        result.next = new ListNode(0);
        ListNode resultTemp = result;
        result=result.next;
        ListNode resultPre=null;
        while(count1!=count2){
            if(count2>count1){
                result.val=l2.val;
                l2=l2.next;
                count2--;
            }
            if(count2<count1){
                result.val=l1.val;
                l1=l1.next;
                count1--;
            }
            if(count1!=0||count2!=0) {
                result.next = new ListNode(0);
                resultPre = result;
                result = result.next;
            }
        }
        while(count1!=0&&count2!=0){
            int tempSum=l1.val+l2.val;
            if(tempSum>=10 && resultPre!=null){
                resultPre.val++;
                result.val=tempSum-10;
            }else {
                result.val=tempSum;
            }
            l1=l1.next;
            l2=l2.next;

            count1--;count2--;
            if(count1!=0||count2!=0) {
                result.next = new ListNode(0);
                resultPre = result;
                result = result.next;
            }
        }
        result=resultTemp;
        while (resultTemp!=null){
            if(resultTemp.val>=10){
                resultPre.val++;
                resultTemp.val=resultTemp.val-10;
            }
            resultPre=resultTemp;
            resultTemp=resultTemp.next;
        }
        if(result.val==0){
            return result.next;
        }
        return result;
    }

    public static ListNode functionTwo(ListNode l1,ListNode l2){
        ListNode result=new ListNode(0);
        ListNode temp=result;
        boolean isAdvance=false;
        while (l1!=null || l2!=null){
            if(l1!=null&&l2!=null){
                result.val=l1.val+l2.val;
                l1=l1.next;
                l2=l2.next;
            }else if (l1!=null&&l2==null){
                result.val=l1.val;
                l1=l1.next;
            }else if (l2!=null&&l1==null){
                result.val=l2.val;
                l2=l2.next;
            }
            if(isAdvance){
                result.val++;
            }
            if(result.val>=10){
                isAdvance=true;
                result.val-=10;
            }else {
                isAdvance=false;
            }
            if(l1!=null||l2!=null){
                result.next = new ListNode(0);
                result = result.next;
            }
        }
        if(isAdvance){
            result.next=new ListNode(1);
        }
        result=temp;
        isAdvance=false;
        while (temp!=null){
            if(isAdvance){
                temp.val++;
            }
            if(temp.val>=10){
                isAdvance=true;
                temp.val=temp.val-10;
            }else {
                isAdvance=false;
            }
            if(temp.next==null&&isAdvance){
                temp.next=new ListNode(1);
                temp=null;
                continue;
            }
            temp=temp.next;
        }
        return result;
    }

    public static void main(String[] args){
        ListNode listNode = new ListNode(9);
        listNode.next=new ListNode(8);
//        listNode.next.next=new ListNode(3);
        ListNode listNode1=new ListNode(1);
//        listNode1.next=new ListNode(6);
//        listNode1.next.next=new ListNode(7);
        functionTwo(listNode,listNode1).print();
    }
}
