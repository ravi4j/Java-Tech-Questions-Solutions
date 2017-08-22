package com.rs.hackerrank.alogrithm.graphtheory;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Ravi Sharma on 8/22/2017.
 */
public class P001_RoadsAndLibraries {

	public static void main(String[] args) {
		Scanner in = new Scanner(
				Thread.currentThread().getContextClassLoader().getResourceAsStream("input/P001_RoadsAndLibraries-Input.txt"));
		int q = in.nextInt();
		for (int a0 = 0; a0 < q; a0++) {
			int n = in.nextInt();
			int m = in.nextInt();
			UnDirectedGraph g = new UnDirectedGraph(n);
			long x = in.nextLong();
			long y = in.nextLong();
			for (int a1 = 0; a1 < m; a1++) {
				int city_1 = in.nextInt();
				int city_2 = in.nextInt();
				g.addEdge(city_1 - 1 , city_2 - 1);
			}
			System.out.println(cost(g, x , y));
		}
	}

	public static long cost(UnDirectedGraph g , long costOfLibrary , long costOfRoad){
		if(costOfLibrary < costOfRoad){
			return  costOfLibrary * g.v ;
		}
		boolean[] visited = new boolean[g.v];
		long cost = 0;
		for( int i = 0 ; i < g.v ; i++){
			if(!visited[i]){
				long x = dfs(g , visited , i);
				cost += (x - 1) * costOfRoad;
				cost += costOfLibrary;
			}
		}
		return cost;
	}

	public static long dfs(UnDirectedGraph g , boolean[] visited , int current){
		long count = 1;
		visited[current] = true;
		for( int i : g.adj[current]){
			if(!visited[i]){
				count+=dfs(g , visited , i);
			}
		}
		return count;
	}
}

class UnDirectedGraph {
	int v;
	LinkedList<Integer> adj[];

	public UnDirectedGraph(int v) {
		this.v = v;
		this.adj = new LinkedList[v];
		for (int i = 0; i < v; i++) {
			this.adj[i] = new LinkedList<>();
		}
	}

	public void addEdge(int v, int u) {
		this.adj[v].add(u);
		this.adj[u].add(v);
	}
}
