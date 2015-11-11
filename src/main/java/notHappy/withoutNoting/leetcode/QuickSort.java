package notHappy.withoutNoting.leetcode;

/**
 * Created by acer on 2015/4/17.
 */
public class QuickSort {
    public static void quickSort(int[] num, int n, int m){
        int base = num[n];//取第一个数为基准数
        int star=n,last=m;//设定起始位和结束位
        while(star<last){
            while(star<last&&num[last]>base)
                last--;//从后向前取，取第一个比基准数小的位置，和起始位交换
            num[star]=num[last];
            while(star<last&&num[star]<base)
                star++;//从前向后去，取第一个比基准数大，和末尾交换
            num[last]=num[star];
        }//基准位前面的数都比他小，后面的都比他大
        num[star]=base;//现在把基准数放到合适的位置
        if(star-n>0)//取从这部分开始到基准前一位
            quickSort(num, n, star-1);
        if(m-last>0)//取从基准后一位这到这部分结束
            quickSort(num, star+1, m) ;

    }
    public static void main(String[] args) {
//		int r=100;
//		int p=80;
//		for(int i=10;i<100;i++)
//		System.out.println((int)(p+Math.random()*Integer.SIZE*10%(r-p+1)));
        int[] nums = new int[]{4, 2, 5, 1, 6, 8, 12, 3, 9};
        quickSort(nums, 0, 8);
//		ls.quickSort(nums,0,nums.length-1);
        for (int num : nums) {
            System.out.print(num + "\t");
        }
    }
}
