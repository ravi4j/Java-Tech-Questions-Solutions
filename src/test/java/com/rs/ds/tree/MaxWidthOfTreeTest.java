package com.rs.ds.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Ravi Sharma on 2/24/2017.
 */
public class MaxWidthOfTreeTest {

    private TreeNode<Integer> root;
    @Before
    public void setupTree(){
        Scanner sc = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("ds/tree/MaxWidthOfTree-Input.txt"));
        int noOfNodes = sc.nextInt();
        List<TreeNode<Integer>> nodes = new ArrayList<>();
        for(int i = 0 ; i < noOfNodes ;i++){
            nodes.add(new TreeNode<Integer>(sc.nextInt()));
        }
        for(int i = 0 ; i < noOfNodes - 1 ;i++){
            nodes.get(sc.nextInt() - 1).children.add(nodes.get(sc.nextInt() - 1));
        }
        root = nodes.get(0);
    }

    @Test
    public void testTree(){
        Assert.assertNotNull(root);
        Assert.assertEquals(3, root.children.size());
        MaxWidthOfTree<Integer> maxWidthOfTree = new MaxWidthOfTree<>();
       // maxWidthOfTree.printTree(root);
    }

    @Test
    public void testTreeHeight(){
        Assert.assertNotNull(root);
        Assert.assertEquals(3 ,root.children.size());
        MaxWidthOfTree<Integer> maxWidthOfTree = new MaxWidthOfTree<>();
        int height = maxWidthOfTree.height(root);
        Assert.assertEquals(height , 3);
    }

    @Test
    public void testGetWidthAtLevel(){
        Assert.assertNotNull(root);
        Assert.assertEquals(root.children.size() , 3);
        MaxWidthOfTree<Integer> maxWidthOfTree = new MaxWidthOfTree<>();
        Assert.assertEquals(1 , maxWidthOfTree.getWidthAtLevel(root , 1) );
        Assert.assertEquals(3 , maxWidthOfTree.getWidthAtLevel(root , 2));
        Assert.assertEquals( 1 ,maxWidthOfTree.getWidthAtLevel(root , 3));
    }

    @Test
    public void testFindMaxWidth(){
        Assert.assertNotNull(root);
        Assert.assertEquals(root.children.size() , 3);
        MaxWidthOfTree<Integer> maxWidthOfTree = new MaxWidthOfTree<>();
        Assert.assertEquals( 3 , maxWidthOfTree.findMaxWidth(root));
    }
}
