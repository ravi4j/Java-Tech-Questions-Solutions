package com.rs.tech.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by rsharma on 6/13/2017.
 */
public class P002_GenerateAllBinaryTrees {

	public static void main(String[] args){
	 List<Node> treeList = generateAllBinaryTrees(2 , new CopyOnWriteArrayList<>());
	 for( Node root : treeList){
	 	System.out.println(root);
	 }
	}

	public static List<Node> generateAllBinaryTrees(int numNodes , List<Node> results){
		if(numNodes == 0){
			results.add(null);
			return results;
		}
		for(int i = 0 ; i < numNodes ; i++){
			int noOfLeftNode = i;
			int noOfRightNode = numNodes - 1 - i;
			List<Node> leftTrees = generateAllBinaryTrees(noOfLeftNode , new ArrayList<>());
			List<Node> rightTrees = generateAllBinaryTrees(noOfRightNode, new ArrayList<>());
			for(Node left : leftTrees){
				for(Node right : rightTrees){
					results.add(new Node(left , right));
				}
			}
		}
		return results;
	}
}


class Node{
	static int idCounter = 1;
	Node left;
	Node right;
	int id;
	public Node(Node left , Node right){
		this.left = left;
		this.right = right;
		this.id = idCounter++;
	}

	@Override
	public String toString(){
		return  "( " + id +" : " + left + " , " + right + " )";
	}
}