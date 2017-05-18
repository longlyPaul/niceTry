package statepattern;

import lombok.Getter;

/**
 * Created by liusonglin
 * Date:2017/5/18
 * Description:
 */
public enum WorkFlow {
    FIRST_STEP(1),
    SECOND_STEP(2),
    THIRD_STEP(3),
    FORTH_STEP(4);

    @Getter
    private Integer code;

    WorkFlow(int code){
        this.code=code;
    }
}
