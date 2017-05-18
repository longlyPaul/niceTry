package statepattern;

/**
 * Created by liusonglin
 * Date:2017/5/18
 * Description:
 */
public class FirstCheck implements GoNext {
    @Override
    public GoNext checkProject(Project project) {
        System.out.print("FirstCheck check =====>>>>>"+ WorkFlow.FIRST_STEP.getCode());
        return new SecondCheck();
    }
}
