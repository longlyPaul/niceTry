package notHappy.withoutNoting.leetcode;

/**
 * Created by acer on 2015/4/17.
 */
public class MergerSort {

    /**
     * 合并排序
     * @param arr
     * @return
     */
    public int[] megerSort(int[] arr){
        if(arr.length==1){
            return arr;//直接返回
        }
        int half = arr.length/2;//取中间位
        //申明两个数组，空间开销
        int[] arr1 = new int[half];
        int[] arr2 = new int[arr.length-half];
        //把原数组前半部分放到arr1
        System.arraycopy(arr, 0, arr1, 0, arr1.length);
        //把原数组后半部分放到arr1
        System.arraycopy(arr, half, arr2, 0, arr2.length);
        arr1 = megerSort(arr1);//直到只有一个值
        arr2 = megerSort(arr2);
        return meger(arr1,arr2);

    }

    /**
     *
     * @param arr1 数组前半部分-》小
     * @param arr2 数组后半部分-》大
     * @return
     */
    public int[] meger(int[] arr1, int[] arr2) {
        int m=0;
        int n=0;
        int i=0;//记录temp数组的步长
        int[] temp=new int[arr1.length+arr2.length];//合并后的数组2+4+8+。。。。+n
        //循环arr1和arr2，并把结果保存到temp中
        while(m<arr1.length&&n<arr2.length){
            if(arr1[m]>arr2[n]){
//				swap
                temp[i]=arr2[n];//把小的数放到temp，并arr2的指针向后移动一位
                i++;n++;
                continue;
            }
            if(arr1[m]<=arr2[n]){
//				swap
                temp[i]=arr1[m];//如果出现相等的情况，有限放入arr1，稳定排序，m向后移位
                i++;m++;
                continue;
            }
        }
        //可能出现的其中一组排玩另一组没有拍的情况
        while(m<arr1.length){
            temp[i]=arr1[m];
            m++;i++;
        }
        while(n<arr2.length){
            temp[i]=arr2[n];
            n++;i++;
        }
        return temp;
    }
}
