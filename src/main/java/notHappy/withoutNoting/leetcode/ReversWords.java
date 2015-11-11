package notHappy.withoutNoting.leetcode;
/**
 * @��Ȩ��Ϣ : ƽ̨����
 * @�� ��  �� : IreportServer
 * @��      �� : @author liusl
 * @�������� : 2014-8-13����09:03:59
 * @�� ˵  �� :
 */
public class ReversWords {
	public String ReversWord(String s){
		if(s==null)return null;
		String[] words=s.split(" ");
		StringBuffer sb=new StringBuffer();
		for(int i=words.length-1;i>=0;i--){
			if(words[i].equals(""))continue;
			sb.append(words[i].replaceAll(" ", ""));
			sb.append(" ");
		}
		return sb.toString();		
	}
	
	public static void main(String[] args) {
		ReversWords rw=new ReversWords();
		System.out.println(rw.ReversWord("abv zx cvs dv"));
	}
}
