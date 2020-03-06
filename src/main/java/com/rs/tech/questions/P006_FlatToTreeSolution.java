/*
 * Copyright (c) Transplace, Inc, 2012. All rights reserved.
 * Unauthorized copying or usage of this file, via any medium is strictly prohibited.
 * Proprietary and confidential. Illegal distribution of files prohibited via any manner.
 */

	/* InputObject - > Output(Text) , Answer(Text) , Conclusion(Text) , and Goto(1:Label)
	   Indentation -> Integer

	// 0 {intend  0 , output , outout }
	// 1 {indentation  0 , output }
	//2 {indentation  0 , output }
		// 3 {indentation  1 , Answer {No} }
			// 4 {indentation  2 , output  }
			// 5 {indentation  2 , output  }
			// 6 {indentation  2 , output  }
				// 7 {indentation  3 , Answer }
					// 8 {indentation  4 , Conclusion }
					// 9 {indentation  4 , Conclusion }
				// 10 {indentation  3 , Answer }
		//11 {indentation  1 , Answer {Yes} }
  		//12 {indentation  2 , GoTo {1} }

   List<IndentationAndInputObject> , List<Answer>

		root - { 0:0 , 1:0, 2:0 }
		 |_  [  A{3:1 , No } , A{11:1 , Yes}  ]
		 			|_
	 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P006_FlatToTreeSolution {
	public static void main(String[] args) {
		List<IndentationAndInputObject> flat = new ArrayList<>();
		flat.add(new IndentationAndInputObject(1, 0, new OutPut()));
		flat.add(new IndentationAndInputObject(2, 0, new OutPut()));
		flat.add(new IndentationAndInputObject(3, 0, new OutPut()));
		flat.add(new IndentationAndInputObject(4, 1, new Answer("No")));
		flat.add(new IndentationAndInputObject(5, 2, new OutPut()));
		flat.add(new IndentationAndInputObject(6, 2, new OutPut()));
		flat.add(new IndentationAndInputObject(7, 2, new OutPut()));
		flat.add(new IndentationAndInputObject(8, 3, new Answer("Not")));
		flat.add(new IndentationAndInputObject(9, 4, new Conclusion()));
		flat.add(new IndentationAndInputObject(10, 4, new Conclusion()));
		flat.add(new IndentationAndInputObject(11, 3, new Answer("Yes")));
		flat.add(new IndentationAndInputObject(12, 1, new Answer("Yes")));
		flat.add(new IndentationAndInputObject(13, 2, new GoTo()));
		Node root = buildTreeIteratively(flat);
		printTree(root, 0);
		Node root2 = new Node(0, null);
		//buildTreeWithStack(flat, root2);
		buildTreeRecusively(flat, root2 , 0 ,0);
		printTree(root2, 0);
	}

	static Node buildTreeIteratively(List<IndentationAndInputObject> flat) {
		Node root = new Node(-1, null);
		for (int i = 0; i < flat.size(); i++) {
			IndentationAndInputObject io = flat.get(i);
			Node last = root;
			for (int j = 0; j < io.indentation; j++) {
				last = last.children.get(last.children.size() - 1);
			}
			last.children.add(new Node(io.indentation, io.inputObject));
		}
		return root;
	}

	static void buildTreeRecusively(List<IndentationAndInputObject> flat, Node root , int startAt , int level){

		if(flat == null || flat.size() == 0){
			return;
		}
		IndentationAndInputObject io = flat.get(startAt);
		Node current = new Node(io.indentation , io.inputObject);
		root.children.add(current);
		flat.remove(io);
		int idx = startAt;
		while(!flat.isEmpty()){
			IndentationAndInputObject next = flat.get(startAt);
			if(next.indentation == level){
				buildTreeRecusively(flat , root , idx , level);
			} else if(next.indentation > level){
				buildTreeRecusively(flat , current , idx , level + 1);
			}else {
				break;
			}
			if(next.indentation < level){
				idx++;
			}
		}


	}

	static void buildTreeWithStack(List<IndentationAndInputObject> flat, Node root) {
		Deque<Node> stack = new ArrayDeque<>();
		Deque<Integer> levelS = new ArrayDeque<>();
		stack.add(root);
		levelS.add(0);
		for (int i = 0; i < flat.size(); i++) {
			IndentationAndInputObject io = flat.get(i);
			Node top = stack.peek();
			Integer level = levelS.peek();
			if (io.indentation == level) {
				Node node = new Node(io.indentation, io.inputObject);
				top.children.add(node);
			} else if (io.indentation > level) {
				Node node = new Node(io.indentation, io.inputObject);
				if (top.children.size() > 0) {
					top.children.get(top.children.size() - 1).children.add(node);
					levelS.push(node.level);
					stack.push(node);
				} else {
					levelS.push(node.level);
					top.children.add(node);
				}
			} else if (io.indentation < level) {
				System.out.println(level);
				while (level != io.indentation) {
					level = levelS.pop();
					if (level == stack.peek().level) {
						stack.pop();
					}
				}
				Node node = new Node(io.indentation, io.inputObject);
				top = stack.peek();
				top.children.get(top.children.size() - 1).children.add(node);
				levelS.push(node.level);
			}
		}
	}

	static void printTree(Node root, int depth) {
	/*	if (root.io != null && root.io instanceof Answer && ((Answer) root.io).data.equals("Yes")) {
			return;
		} */
		System.out.println(root.io);
		for (Node child : root.children) {
			for (int i = 0; i <= depth; i++) {
				System.out.print("   ");
			}
			//System.out.println(child.io);
			printTree(child, depth + 1);

		}
	}

	static class Node {
		int level;
		InputObject io;
		List<Node> children = new ArrayList<>();

		public Node(int level, InputObject io) {
			this.level = level;
			this.io = io;
		}

		@Override
		public String toString() {
			return "Node{" + "io=" + io + ", children=" + children + '}';
		}
	}

	static class IndentationAndInputObject {
		private Integer id;
		private Integer indentation;
		private InputObject inputObject;

		public IndentationAndInputObject(int id, int indentation, InputObject io) {
			this.id = id;
			this.indentation = indentation;
			this.inputObject = io;
		}

		@Override
		public String toString() {
			return "IndentationAndInputObject{" + "id=" + id + ", indentation=" + indentation + ", inputObject=" + inputObject
					+ '}';
		}
	}

	static abstract class InputObject {
		public String id;

		public InputObject(String id) {
			this.id = id;
		}

		public String getId() {
			return id;
		}

		@Override
		public String toString() {
			return "InputObject{" + "id='" + id + '\'' + '}';
		}
	}

	static class OutPut extends InputObject {
		public OutPut() {
			super("O");
		}

	}

	static class Answer extends InputObject {
		String data;

		public Answer(String data) {
			super("A");
			this.data = data;
		}

		@Override
		public String toString() {
			return "Answer{id=" + id + " , data='" + data + '\'' + '}';
		}
	}

	static class Conclusion extends InputObject {
		public Conclusion() {
			super("C");
		}
	}

	static class GoTo extends InputObject {
		public GoTo() {
			super("G");
		}
	}

}
