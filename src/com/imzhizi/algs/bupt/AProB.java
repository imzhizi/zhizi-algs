package com.imzhizi.algs.bupt;

import java.util.Scanner;

public class AProB {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int t = input.nextInt();
		for (int i = 0; i < t; i++) {
			int n = input.nextInt();
			int[] nums = new int[n];
			for (int j = 0; j < nums.length; j++) {
				nums[j]=input.nextInt();
			}
			int s=0;
			for (int j = 0; j < n-2; j++) {
				for (int k = j+1; k < n-1; k++) {
					if(nums[j]>nums[k]) {
						for (int l = k+1; l < nums.length; l++) {
							if(nums[k]<nums[l]) s++;
						}
					}
				}
				
			}
			System.out.println(s);

		}

		input.close();

	}

}
