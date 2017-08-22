package com.rs.hackerrank.crackingcoding.datastructures;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by rsharma on 6/1/2017.
 */
public class P002_RunningMedian {

	public static void main(String[] args) {
		//Scanner in = new Scanner(System.in);
		Scanner in = new Scanner(
				Thread.currentThread().getContextClassLoader().getResourceAsStream("input/P002_RunningMedian-Input.txt"));
		int n = in.nextInt();
		int[] a = new int[n];
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
		}
		getMedian(a, a.length);
	}

	public static int signNum(int a, int b) {
		if (a == b)
			return 0;
		return a > b ? 1 : -1;
	}

	public static double average(int a, int b) {
		return (a + b) / 2.0;
	}

	public static void getMedian(int[] array, int size) {
		double m = 0.0;
		PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());
		PriorityQueue<Integer> right = new PriorityQueue<>();
		for (int e : array) {
			m = calcMedian(e, m, left, right);
			System.out.println(m);
		}
	}

	public static double calcMedian(int e, double m, PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
		int sign = signNum(left.size(), right.size());
		switch (sign) {
		case 1:
			if (e < m) {
				if (!left.isEmpty())
					right.add(left.poll());
				left.add(e);
			} else {
				right.add(e);
			}
			m = average(left.peek(), right.peek());
			break;
		case 0:
			if (e < m) {
				left.add(e);
				m = left.peek();
			} else {
				right.add(e);
				m = right.peek();
			}
			break;
		case -1:
			if(e < m){
				left.add(e);
			} else {
				if(!right.isEmpty())
					left.add(right.poll());
				right.add(e);
			}
			m = average(left.peek() , right.peek());
		};
		return m;
	}
}
