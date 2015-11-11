package thinking;

import java.util.EnumSet;

/**
 * Created by liusonglin on 15/11/8.
 */
public class TestEnum {
    enum demo{
        name (1){
            @Override
            boolean myfunction(String s) {
                return Integer.parseInt(s)==(this.value);
            }
        },
        age(2){
            @Override
            boolean myfunction(String s) {
                return s.equals(this.age);
            }
        };
        Integer value;
        demo(Integer value){
            this.value=value;
        }
        abstract boolean myfunction(String s);
    }

    public static void main(String[] args){
        System.out.println(demo.age.value);
        System.out.println(demo.name.myfunction("1"));
        for(demo item:demo.values()){
            CommonUtils.printObjec(item.value);
        }
        CommonUtils.printObjec(demo.values());
    }
}
