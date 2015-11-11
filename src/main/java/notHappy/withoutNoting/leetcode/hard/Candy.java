package notHappy.withoutNoting.leetcode.hard;

/**
 * Created by acer on 2015/6/5.
 */

/* There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

    Each child must have at least one candy.
    Children with a higher rating get more candies than their neighbors.

What is the minimum candies you must give? */
public class Candy {
    public int candy(int[] ratings) {
        int sum=0;
        int[] candis=new int[ratings.length];
        for(int i=1;i<ratings.length;i++){
            if(ratings[i]>ratings[i-1]&&candis[i]<=candis[i-1])
                candis[i]=1+candis[i-1];
        }
        for(int i=ratings.length-1;i>0;i--){
            if(ratings[i-1]>ratings[i]&& candis[i-1]<=candis[i]){
                candis[i-1]=1+candis[i];
            }
        }
        for(int i=0;i<candis.length;i++){
            sum+=candis[i];
        }
        return sum+candis.length;
    }

    /*not correct yet*/
    public int candy2(int[] ratings) {
        int preCandy=1;
        int sum=0;
        int min=1;
        int pre=ratings[0];
        for(int i=0;i<ratings.length;i++){
            int count=1;
            if(pre<ratings[i]){
                preCandy++;
                sum+=preCandy;
            }
            if(pre==ratings[i]){
                sum+=count;
                preCandy=1;
            }
            if(pre>ratings[i]){
                if(preCandy-1==0){
                    sum+=i+1;
                    pre=ratings[i];
                    continue;
                }
                sum+=count;
                preCandy=1;
            }
            pre=ratings[i];
        }
        return sum;
    }
}
