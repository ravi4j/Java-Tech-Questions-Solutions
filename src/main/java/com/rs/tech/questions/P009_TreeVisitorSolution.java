import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

enum Color {
	RED, GREEN
}

abstract class Tree {

	private int value;
	private Color color;
	private int depth;

	public Tree(int value, Color color, int depth) {
		this.value = value;
		this.color = color;
		this.depth = depth;
	}

	public int getValue() {
		return value;
	}

	public Color getColor() {
		return color;
	}

	public int getDepth() {
		return depth;
	}

	public abstract void accept(TreeVis visitor);

	@Override
	public String toString() {
		return "Tree{" + "value=" + value + ", color=" + color + ", depth=" + depth + '}';
	}
}

class TreeNode extends Tree {

	private ArrayList<Tree> children = new ArrayList<>();

	public TreeNode(int value, Color color, int depth) {
		super(value, color, depth);
	}

	public void accept(TreeVis visitor) {
		visitor.visitNode(this);

		for (Tree child : children) {
			child.accept(visitor);
		}
	}

	public void addChild(Tree child) {
		children.add(child);
	}
}

class TreeLeaf extends Tree {

	public TreeLeaf(int value, Color color, int depth) {
		super(value, color, depth);
	}

	public void accept(TreeVis visitor) {
		visitor.visitLeaf(this);
	}

}

abstract class TreeVis {
	public abstract int getResult();

	public abstract void visitNode(TreeNode node);

	public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
	int sum = 0;

	public int getResult() {
		//implement this
		return sum;
	}

	public void visitNode(TreeNode node) {
		//implement this
	}

	public void visitLeaf(TreeLeaf leaf) {
		//implement this
		sum += leaf.getValue();
	}
}

class ProductOfRedNodesVisitor extends TreeVis {
	private long count = 1;
	private int mod = (int) Math.pow(10, 9) + 7;

	public int getResult() {
		return (int) count;
	}

	public void visitNode(TreeNode node) {
		if (node.getColor() == Color.RED) {
			count *= node.getValue();
			count %= mod;
		}
	}

	public void visitLeaf(TreeLeaf leaf) {
		if (leaf.getColor() == Color.RED) {
			count *= leaf.getValue();
			count %= mod;
		}
	}
}

class FancyVisitor extends TreeVis {
	int greenLeafSum = 0;
	int evenNodeSum = 0;

	public int getResult() {
		//implement this
		return Math.abs(greenLeafSum - evenNodeSum);
	}

	public void visitNode(TreeNode node) {
		//implement this
		if (node.getDepth() % 2 == 0) {
			evenNodeSum += node.getValue();
		}
	}

	public void visitLeaf(TreeLeaf leaf) {
		//implement this
		if (Color.GREEN.equals(leaf.getColor())) {
			greenLeafSum += leaf.getValue();
		}
	}
}

public class TreeVisitorSolution {
	private static int n;
	private static int[] x;
	private static Color[] c;
	private static List<Set<Integer>> links;

	private static Tree solve(int i, int d) {
		Set<Integer> childs = links.get(i);
		if (childs.isEmpty())
			return new TreeLeaf(x[i], c[i], d);

		TreeNode node = new TreeNode(x[i], c[i], d);
		for (int j : childs) {
			links.get(j).remove(i);
			node.addChild(solve(j, d + 1));
		}
		return node;
	}

	public static Tree solve() {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		x = new int[n];
		c = new Color[n];
		links = new ArrayList<Set<Integer>>();

		for (int i = 0; i < n; i++) {
			links.add(new HashSet<Integer>());
		}

		for (int i = 0; i < n; i++) {
			x[i] = sc.nextInt();
		}

		for (int i = 0; i < n; i++) {
			c[i] = sc.nextInt() == 0 ? Color.RED : Color.GREEN;
		}

		for (int i = 0; i < n - 1; i++) {
			int j1 = sc.nextInt() - 1;
			int j2 = sc.nextInt() - 1;
			links.get(j1).add(j2);
			links.get(j2).add(j1);
		}

		sc.close();
		return solve(0, 0);
	}

	public static void main(String[] args) {
		Tree root = solve();
		System.out.println(root);

		SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
		ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
		FancyVisitor vis3 = new FancyVisitor();

		root.accept(vis1);
		root.accept(vis2);
		root.accept(vis3);

		int res1 = vis1.getResult();
		int res2 = vis2.getResult();
		int res3 = vis3.getResult();

		System.out.println(res1);
		System.out.println(res2);
		System.out.println(res3);
	}
}