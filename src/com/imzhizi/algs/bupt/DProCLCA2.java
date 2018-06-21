package com.imzhizi.algs.bupt;

import java.util.Scanner;

public class DProCLCA2 {

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int a=input.nextInt();
		int b=input.nextInt();
		input.close();
		while(true) {
			if(a>b) a/=2;
			else if(a<b) b/=2;
			else if(a==b){
				System.out.println(a);
				return;
			}
		}
	}

}
