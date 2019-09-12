package com.rs.hackerrank.alogrithm.graphtheory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by rsharma on 8/23/2017.
 */
public class P002_JourneyToTheMoon {

	public static void main(String[] args) throws Exception {
		//BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader bfr = new BufferedReader(new InputStreamReader(
				Thread.currentThread().getContextClassLoader().getResourceAsStream("input/P002_JourneyToTheMoon-Input.txt")));
		String[] temp = bfr.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int I = Integer.parseInt(temp[1]);
		Graph graph = new Graph(N);
		for (int i = 0; i < I; i++) {
			temp = bfr.readLine().split(" ");
			int a = Integer.parseInt(temp[0]);
			int b = Integer.parseInt(temp[1]);
			// Store a and b in an appropriate data structure of your choice
			graph.addEdge(a, b);
		}

		long combinations = getCombinations(graph);
		System.out.println(combinations);
	}

	public static long getCombinations(Graph g) {
		boolean[] visited = new boolean[g.v];
		LinkedList<Long> countrySize = new LinkedList<>();
		for (int i = 0; i < g.v; i++) {
			if (!visited[i]) {
				long size = dfs(g, visited, i);
				countrySize.add(size);
			}
		}
		long sum = 0;
		long result = 0;
		for (long size : countrySize) {
			result += sum * size;
			sum += size;
		}
		return result;
	}

	public static long dfs(Graph g, boolean[] visited, int current) {
		long count = 1;
		visited[current] = true;
		for (int v : g.adj[current]) {
			if(!visited[v]) {
				count += dfs(g, visited, v);
			}
		}
		return count;
	}
}

class Graph {
	int v;
	LinkedList<Integer> adj[];

	public Graph(int v) {
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
