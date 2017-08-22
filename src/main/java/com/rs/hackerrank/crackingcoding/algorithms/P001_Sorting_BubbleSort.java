package com.rs.hackerrank.crackingcoding.algorithms;

import java.util.Scanner;

/**
 * Created by rsharma on 6/2/2017.
 */
public class P001_Sorting_BubbleSort {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(
				Thread.currentThread().getContextClassLoader().getResourceAsStream("input/P001_Sorting_BubbleSort-Input.txt"));
		int size = scanner.nextInt();
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = scanner.nextInt();
		}
		int numSwaps = getNumberOfSwapToSort(array , size);
		System.out.println("Array is sorted in " + numSwaps + " swaps.");
		System.out.println("First Element: " + array[0]);
		System.out.println("Last Element: " + array[size - 1]);
	}

	public static int getNumberOfSwapToSort(int[] a, int n) {
		int sumNumberOfSwaps = 0;
		for (int i = 0; i < n; i++) {
			// Track number of elements swapped during a single array traversal
			int numberOfSwaps = 0;

			for (int j = 0; j < n - 1; j++) {
				// Swap adjacent elements if they are in decreasing order
				if (a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
					numberOfSwaps++;
				}
			}
			// If no elements were swapped during a traversal, array is sorted
			if (numberOfSwaps == 0) {
				break;
			} else {
				sumNumberOfSwaps += numberOfSwaps;
			}
		}
		return sumNumberOfSwaps;
	}
}
