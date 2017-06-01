package com.rs.harkerrank.crackingcoding.algorithms;

import java.util.Scanner;

/**
 * Created by rsharma on 6/2/2017.
 */
public class P003_MergeSort_CountingInversions {

	public static void main(String[] args) {
		//Scanner in = new Scanner(System.in);
		Scanner in = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("input/P003_MergeSort_CountingInversions-Input.txt"));
		int t = in.nextInt();
		for(int a0 = 0; a0 < t; a0++){
			int n = in.nextInt();
			int arr[] = new int[n];
			for(int arr_i=0; arr_i < n; arr_i++){
				arr[arr_i] = in.nextInt();
			}

			int inversionCount =  merge(arr , n , 0);
			System.out.println(inversionCount);
		}
	}
	// Time out solution in hacker rank
	public static int  merge(int[] arr , int size ,  int currSize){
		int inversionCount = 0;
		for(int i = currSize ; i >= 0 ; i--){
			if( i > 0 && arr[i] < arr[i - 1]){
				int temp = arr[i - 1];
				arr[i - 1] = arr[i];
				arr[i] = temp;
				inversionCount += 1;
			}
		}
		if(currSize < size - 1)
			inversionCount += merge(arr , size, currSize + 1);
		return inversionCount;
	}


}
