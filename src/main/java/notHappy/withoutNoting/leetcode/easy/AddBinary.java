package notHappy.withoutNoting.leetcode.easy;

/**
 * Created by acer on 2015/6/4.
 */
/*descrip*/
/*Given two binary strings, return their sum (also a binary string).

        For example,
        a = "11"
        b = "1"
        Return "100".*/
public class AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder sb=new StringBuilder();
        int len=Math.max(a.length(),b.length());
        int[] A_temp=new int[a.length()];
        int[] B_temp=new int[b.length()];
        int[] sum=new int[len+1];
        for(int i=0;i<len;i++){
            if(a.length()>i){
                if(a.charAt(i)=='0')
                    A_temp[i]=0;
                else A_temp[i]=1;
            }
            if(b.length()>i){
                if(b.charAt(i)=='0')
                    B_temp[i]=0;
                else B_temp[i]=1;
            }
        }

        for (int i = 1; i <=len; i++) {
            if(a.length()-i>=0&&b.length()-i>=0)
                sum[sum.length-i] = A_temp[a.length()-i]+ B_temp[b.length()-i];
            else if(a.length()-i>=0)
                sum[sum.length-i] = A_temp[a.length()-i];
            else sum[sum.length-i]=B_temp[b.length()-i];
        }
        for (int i = len; i >0; i--){
            if (sum[i] == 2) {
                sum[i - 1] += 1;
                sum[i] = 0;
            }
            if(sum[i]==3){
                sum[i - 1] += 1;
                sum[i] = 1;
            }
        }

        for(int i=0;i<=len;i++){
            sb.append(sum[i]);
        }

        if (sum[0] == 0)
            return sb.substring(1);
        return sb.toString();
    }
}
