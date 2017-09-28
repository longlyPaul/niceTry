package thinking;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by liusonglin
 * Date:2017/9/28
 * Description:
 */
public class DFAalg {

    static final String textPath = "/Users/liusonglin/workSpace/niceTry/src/main/resources/word.txt";

    static Map<Character,Map> sensitiveWordMap;

    public static void main(String[] args){

        List<String> keylist = readeFile();

        String word = "醉乙醚,左转是政,uoouo阿杜里斯就发了圣诞节发生的发，阿萨德积着护士的好胸分，转是政府";

        buildSensitiveWordMap(keylist);

        long start = System.currentTimeMillis();

        System.out.println(checkAndReplaceWord(word));

        System.out.println("-------->"+(System.currentTimeMillis()-start));

        long two = System.currentTimeMillis();


        for(String item : keylist){
            if(word.indexOf(item)!=-1){
                word = word.replace(item,"****");
            }
        }

        System.out.println(word);

        System.out.println("--------======>"+(System.currentTimeMillis()-two));


    }

    //验证并替换敏感字符
    public static String checkAndReplaceWord(String word){
        //当前敏感字符路径
        Map<Character,Map> tempSensitiveWordMap = sensitiveWordMap;

        //匹配起始位置标记
        int startFlag = -1;

        for(int i = 0; i<word.length();i++ ){
            char x = word.charAt(i);
            //取的匹配向下路径
            Map wordMap = tempSensitiveWordMap.get(x);

            if(wordMap == null){
                //如果向下路径为空，重置匹配起始位与路径图
                startFlag = -1;
                tempSensitiveWordMap = sensitiveWordMap;
                continue;
            }
            //是否是起始位置
            if(startFlag == -1){
                startFlag = i;
            }

            //是否是路径结束位置，如果结束，说明当前词匹配结束，重置起始位置匹配其他词
            if("1".equals(String.valueOf(wordMap.get("isEnd")))){
                char[] temp = word.toCharArray();
                //从其实位置开始替换支付
                while(startFlag<=i){
                    temp[startFlag] = '*';
                    startFlag++;
                }
                word = String.valueOf(temp);
                startFlag = -1;
                tempSensitiveWordMap = sensitiveWordMap;
            }else {
                //如果未结束进入下一位置
                tempSensitiveWordMap = wordMap;
            }
        }
        return word;
    }


    public static void buildSensitiveWordMap(List<String> keywords){
        //敏感词路径图初始化
        sensitiveWordMap = new HashMap<>(keywords.size());

        //代表当前路径节点,作指针用
        Map nowMap = null;

        //新生成的路径
        Map<String,String> newWordMap;

        String key = null;

        Iterator<String> keyword = keywords.iterator();

        //循环生成路径，每个词代表一个路径。
        while (keyword.hasNext()){
            key = keyword.next();

            nowMap = sensitiveWordMap;

            for(int i = 0;i<key.length();i++){
                char x = key.charAt(i);

                Map wordMap = (Map) nowMap.get(x);

                //当前路径已存在，不用新生成
                if(wordMap != null){
                    nowMap = wordMap;
                }else {
                    //新生成一个路径，已isEnd为标识，0代表有下一个路径，1代表路径结束。
                    newWordMap = new HashMap<>();
                    newWordMap.put("isEnd","0");
                    nowMap.put(x,newWordMap);
                    nowMap = newWordMap;
                }

                //如果词已结束，路径也结束了
                if(i == key.length() - 1){
                    nowMap.put("isEnd","1");
                }
            }
        }
    }


    public static List<String> readeFile(){
        List<String> result = new ArrayList<>(1000);
        try(InputStreamReader reader =  new InputStreamReader(new FileInputStream(new File(textPath)),"gbk")) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String data = null;
            while ((data = bufferedReader.readLine())!=null){
                result.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
