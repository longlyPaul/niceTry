package notHappy.withoutNoting.leetcode.Medium;

import notHappy.withoutNoting.leetcode.baseStructure.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by acer on 2015/6/4.
 */
public class BinaryTreeInorderTraversal {
/*    Given a binary tree, return the inorder traversal of its nodes' values.

    For example:
    Given binary tree {1,#,2,3},

            1
            \
            2
            /
            3

            return [1,3,2].

    Note: Recursive solution is trivial, could you do it iteratively?

    confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.*/

    /*非递归*/
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> inorder=new ArrayList<Integer>();
        if(root==null)return inorder;
        Stack<TreeNode> st=new Stack<TreeNode>();
        st.push(root);
        while(root.left!=null){
            st.push(root.left);
            root=root.left;
        }
        while(root!=null&&!st.empty()){
            root=st.pop();
            inorder.add(root.val);
            if(root.right!=null){
                st.push(root.right);
                while(root.right.left!=null){
                    st.push(root.right.left);
                    root.right=root.right.left;
                }
            }
        }
        return inorder;
    }

    /*递归*/
    public ArrayList<Integer> inorderTraversal2(TreeNode root) {
        ArrayList<Integer> inorder=new ArrayList<Integer>();
        if(root==null)return inorder;
        if(root.left!=null){
            inorder.addAll(inorderTraversal2(root.left));
        }
        inorder.add(root.val);
        if(root.right!=null){
            inorder.addAll(inorderTraversal2(root.right));
        }
        return inorder;
    }
}
