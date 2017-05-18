package statepattern;

import lombok.Data;

import java.io.Console;

/**
 * Created by liusonglin
 * Date:2017/5/18
 * Description:
 */
@Data
public class Project {

    private Integer state;

    private GoNext goNext;



    public Project(){
        goNext = new FirstCheck();
    }

    public void goNext(){
        if(goNext!=null){
            goNext=goNext.checkProject(this);
        }else {
            System.out.print("done will");
        }
    }

    public static void main(String[] args){
        Project project = new Project();
        project.setState(0);
        project.goNext();
        System.out.println(project.getState());
        project.goNext();
        System.out.println(project.getState());
        project.goNext();
        System.out.println(project.getState());

    }
}
