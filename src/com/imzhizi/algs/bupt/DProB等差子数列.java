package com.imzhizi.algs.bupt;

import java.util.Scanner;

public class DProB等差子数列 {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int t=input.nextInt();
		for (int i = 0; i < t; i++) {
			int n=input.nextInt();
			if(n<3) {
				System.out.println(n);
				for(int j=0;j<n;j++) input.nextInt();
				continue;}

			int last2=input.nextInt();
			int last1=input.nextInt();
			int last0=0;
			int l1=2;
			int l2=0;
			for(int j=2;j<n;j++) {
				last0=input.nextInt();
				if(last2-last1==last1-last0) {
					l1++;
				}else {	
					if(l1>l2) l2=l1;l1=2;
				}
				last2=last1;
				last1=last0;
			}
			System.out.println(l1>l2?l1:l2);
		}
		input.close();
	}
}
