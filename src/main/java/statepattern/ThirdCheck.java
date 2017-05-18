package statepattern;

/**
 * Created by liusonglin
 * Date:2017/5/18
 * Description:
 */
public class ThirdCheck implements GoNext {
    @Override
    public GoNext checkProject(Project project) {
        System.out.print("ThirdCheck check ==========>>>>>>>>"+WorkFlow.THIRD_STEP.getCode());
        return null;
    }
}
