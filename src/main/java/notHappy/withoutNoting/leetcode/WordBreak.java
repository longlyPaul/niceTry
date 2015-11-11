package notHappy.withoutNoting.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {


	
	public List<String> wordBreak(String s, Set<String> dict) {
		List<String> result;
		int second=1;
		result=splitword(s, dict);
		return result;        
    }
	private List<String> splitword(String s, Set<String> dict) {
		String temp=null;
		List<String> list=new ArrayList<String>();
		List<String> atmep=null;
		int i=0;
		int point=1;
		while(i<=s.length()){
			String temp2=s.substring(i,point);
			if(dict.contains(temp2)){
				atmep=splitword(s.substring(point, s.length()),dict);
				if(atmep.size()!=0){
					for(int j=0;j<atmep.size();j++){
						list.add(temp2+""+atmep.get(j));
					}
				}
			}
			point++;
		}
		return list;
	}


	
	HashSet<String> map = null;

	public ArrayList<String> wordBreak2(String s, Set<String> dict) {
	    ArrayList<String> res = new ArrayList<String>();
	    if(s == null || s.length() == 0)
	        return res;

	    map = new HashSet<String>();
	    res = recBreak(s, dict);

	    return (res != null)?res:new ArrayList<String>();
	}

	private ArrayList<String> recBreak(String s, Set<String> dict){
	    if(map.contains(s))
	        return null;

	    ArrayList<String> list = new ArrayList<String>();

	    ArrayList<String> temp = null;

	    for(int i =1; i<= s.length(); i++){
	        temp = new ArrayList<String>();
	        String str = s.substring(0, i);
	        if(dict.contains(str)){
	            if(i < s.length())
	                temp = recBreak(s.substring(i, s.length()), dict);
	            if(temp != null){
	                if(temp.size() == 0)
	                    list.add(str);
	                else{
	                    for(int j = 0; j< temp.size(); j++){
	                        list.add(str+" "+temp.get(j));
	                    }
	                }
	            }else{
	                map.add(s.substring(i, s.length()));
	            }
	        }
	    }

	    return (list.size() > 0)?list:null;

	}
	
	
	public void bags(int max,int[][] value2weight){
		int[][] dp=new int[value2weight[0].length+1][max+1];
		for(int i=1;i<value2weight[0].length+1;i++){
			for(int j=1;j<max+1;j++){
				if(value2weight[0][i-1]<=j){
					if(dp[i-1][j]<(dp[i-1][j-value2weight[0][i-1]]+value2weight[1][i-1]))
						dp[i][j]=(dp[i-1][j-value2weight[0][i-1]]+value2weight[1][i-1]);
					else 
						dp[i][j]=dp[i-1][j];
				}
				else
					dp[i][j]=dp[i-1][j];
			}
		}
		
	}
	
	public static void main(String[] args) {
		WordBreak wb=new WordBreak();
//		int[] nums=new int[]{3, 5, 3, 6, 4, 7, 5, 7, 4};
//		int[] nums2=wb.megerSort(nums);
//		for(int j=0;j<9;j++){
//			System.out.println(nums2[j]);
//		}
		String s="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
		Set<String> dict=new HashSet<String>();
		String[] x=new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
		for(int i=0;i<x.length;i++){
			dict.add(x[i]);
		}
		System.out.println(wb.wordBreak (s, dict));
	}
}
