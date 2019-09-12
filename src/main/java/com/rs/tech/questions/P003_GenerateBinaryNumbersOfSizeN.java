package com.rs.tech.questions;

public class P003_GenerateBinaryNumbersOfSizeN {
	public static void main(String[] args) {
		binaryGenerator(4);
	}

	public static void binaryGenerator(int n) {
		if (n == 0) {
			return;
		}
		int[] A = new int[n];
		binary(n, A);
	}

	public static void binary(int n, int[] A) {
		if (n < 1) {
			for (int i = 0; i < A.length; i++) {
				System.out.print(A[i]);
			}
			System.out.print("\n");
			return;
		}
		for (int i = 0; i < 2; i++) {
			A[n - 1] = i;
			binary(n - 1, A);
		}
	}
}