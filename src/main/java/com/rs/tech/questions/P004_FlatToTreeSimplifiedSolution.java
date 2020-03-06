/*
 * Copyright (c) Transplace, Inc, 2012. All rights reserved.
 * Unauthorized copying or usage of this file, via any medium is strictly prohibited.
 * Proprietary and confidential. Illegal distribution of files prohibited via any manner.
 */

import java.util.ArrayList;
import java.util.List;

public class P004_FlatToTreeSimplifiedSolution {
	public static void main(String[] args) {
		List<FlatPoint> flat = getFlatPoint();
		TreeNode root = new TreeNode(null);
		buildTree(flat, root, 0, 0);
		printTree(root, 0);
	}

	static void buildTree(List<FlatPoint> list, TreeNode parent, int statAt, int level) {
		if (list == null || list.isEmpty()) {
			return;
		}
		FlatPoint fp = list.get(statAt);
		TreeNode current = new TreeNode(fp);
		parent.children.add(current);
		list.remove(fp);
		int idx = statAt;
		while (!list.isEmpty()) {
			FlatPoint next = list.get(idx);
			if (next.level == level) {
				buildTree(list, parent, idx, level);
			} else if (next.level > level) {
				buildTree(list, current, idx, level + 1);
			} else if (next.level < level) {
				break;
			}
			if (next.level < level) {
				idx++;
			}
		}
	}

	static void printTree(TreeNode root, int level) {
		if (root.fp != null) {
			System.out.println("<" + root.fp.ch + "/>");
		}
		for (TreeNode child : root.children) {
			for (int i = 0; i <= level; i++) {
				System.out.print("  ");
			}
			printTree(child, level + 1);
		}
	}

	static List<FlatPoint> getFlatPoint() {
		List<FlatPoint> list = new ArrayList<>();
		list.add(new FlatPoint(0, 'A'));
		list.add(new FlatPoint(1, 'B'));
		list.add(new FlatPoint(2, 'C'));
		list.add(new FlatPoint(3, 'D'));
		list.add(new FlatPoint(3, 'E'));
		list.add(new FlatPoint(2, 'C'));
		list.add(new FlatPoint(1, 'B'));
		list.add(new FlatPoint(0, 'A'));
		return list;
	}

	static class TreeNode {
		FlatPoint fp;
		List<TreeNode> children = new ArrayList<>();

		public TreeNode(FlatPoint fp) {
			this.fp = fp;
		}
	}

	static class FlatPoint {
		char ch;
		int level;

		public FlatPoint(int level, char ch) {
			this.level = level;
			this.ch = ch;
		}

		@Override
		public String toString() {
			return "FlatPoint{" + "ch=" + ch + ", level=" + level + '}';
		}
	}
}

