package com.rs.ds.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravi Sharma on 3/5/2017.
 */
public class TreeNode<T> {

        List<TreeNode<T>> children;

        T item;

        public TreeNode(T item){
            this.item = item;
            children = new ArrayList<>();
        }


        @Override
        public String toString(){
            return item + "-> " + children;
        }
}
