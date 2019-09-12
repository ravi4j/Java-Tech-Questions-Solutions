package com.rs.hackerrank.crackingcoding.algorithms;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int row = in.nextInt();
		int col = in.nextInt();
		int i = 0, j = 0;
		while (i < row) {
			while (j < col) {
				if (i == 0 || i == row - 1) {
					System.out.print("*");
				} else {
					if (j == 0 || j == col - 1) {
						System.out.print("*");
					} else {
						System.out.print(" ");
					}
				}
				j++;
			}
			System.out.println();
			i++;
			j = 0;
		}
	}
}

