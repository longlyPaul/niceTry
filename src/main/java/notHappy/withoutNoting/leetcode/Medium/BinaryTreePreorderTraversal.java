package notHappy.withoutNoting.leetcode.Medium;

import notHappy.withoutNoting.leetcode.baseStructure.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by acer on 2015/6/4.
 */
public class BinaryTreePreorderTraversal {
    /*Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},

   1
    \
     2
    /
   3

return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?*/

    /*递归*/
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> preorder=new ArrayList<Integer>();
        if(root==null)return preorder;
        preorder.add(root.val);
        if(root.left!=null)
            preorder.addAll(preorderTraversal(root.left));
        if(root.right!=null)
            preorder.addAll(preorderTraversal(root.right));
        return preorder;
    }

    /*非递归*/
    public ArrayList<Integer> preorderTraversal2(TreeNode root) {
        ArrayList<Integer> preorder=new ArrayList<Integer>();
        Stack<TreeNode> st=new Stack<TreeNode>();
        if(root==null)return preorder;
        st.push(root);
        TreeNode currentNode;
        while(!st.empty()){
            currentNode=st.pop();
            preorder.add(currentNode.val);
            if(currentNode.right!=null){
                st.push(currentNode.right);
            }
            if(currentNode.left!=null){
                st.push(currentNode.left);
            }
        }
        return preorder;
    }
}
