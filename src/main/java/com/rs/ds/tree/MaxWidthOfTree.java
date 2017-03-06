package com.rs.ds.tree;

import java.util.ArrayDeque;
import java.util.List;

/**
 * Created by Ravi Sharma on 2/24/2017.
 */
public class MaxWidthOfTree<T> {

   public int findMaxWidth(TreeNode<T> root){
       int maxWidth = 0 , h = height(root);
       for(int i = 1 ; i <= h ; i++){
           maxWidth = Math.max(maxWidth,getWidthAtLevel(root , i));
       }
       return maxWidth;
   }

   public int getWidthAtLevel(TreeNode<T> node , int level){
       if(node == null)
           return 0;
       if(level == 1)
           return 1;
       else if (level > 1){
           int sum = 0;
           for(TreeNode<T> child : node.children){
               sum += getWidthAtLevel(child , level - 1);
           }
           return sum;
       }
       return 0;
   }

   public int height(TreeNode<T> root){
       if(root == null)
           return 0;
       else{
           int height = 0;
           for(TreeNode<T> child : root.children){
              height = Math.max(height , height(child));
           }
           return height + 1;
       }
   }

   public void printTree(TreeNode<T> root){
       if(root == null) return;
       ArrayDeque<TreeNode<T>> deque = new ArrayDeque<>();
       deque.offer(root);
       while(!deque.isEmpty()){
           TreeNode<T> node = deque.poll();
           System.out.println(node);
           for(TreeNode child : node.children){
               deque.offer(child);
           }
       }
   }
}


