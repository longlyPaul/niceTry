package statepattern;

/**
 * Created by liusonglin
 * Date:2017/5/18
 * Description:
 */
public class SecondCheck implements GoNext {
    @Override
    public GoNext checkProject(Project project) {
        System.out.print("SecondCheck check =======>>>>>>>>"+WorkFlow.SECOND_STEP.getCode());
        return new ThirdCheck();
    }
}
