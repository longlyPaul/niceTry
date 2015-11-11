package notHappy.withoutNoting.leetcode.Medium;

/**
 * Created by acer on 2015/6/4.
 */
public class MaxProfit {

  /*  Say you have an array for which the ith element is the price of a given stock on day i.
    Design an algorithm to find the maximum profit. You may complete as many transactions as you like
   (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time
     (ie, you must sell the stock before you buy again).*/
  public int maxProfit(int[] prices) {
      int profit=0;
      int in=Integer.MIN_VALUE;
      if(prices.length==1)return 0;
      if(prices.length==2){
          if(prices[1]>prices[0])return prices[1]-prices[0];
          else return 0;

      }
      for(int i=0;i<prices.length;i++){
          if(in!=Integer.MIN_VALUE&&in<prices[i]){
              profit+=prices[i]-in;
              in=prices[i];
              continue;
          }
          if(i+1<prices.length&&prices[i]<prices[i+1])in=prices[i];
      }
      return profit;
  }
}

