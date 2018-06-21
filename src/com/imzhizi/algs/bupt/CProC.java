package com.imzhizi.algs.bupt;

import java.util.Scanner;

public class CProC {
	 
    static Scanner in = new Scanner(System.in);
 
    static boolean Isrui(int year) {
         
        if((year%4==0 && year%100!=0) || (year%400==0) )
            return true;
        return false;
    }
     
    public static void main(String[] args) {
         
        int T = in.nextInt();
        String str = in.nextLine();
        while((T--)>0) {
            str = in.nextLine();
            String s[] = str.split(":");
            int year = Integer.parseInt(s[0]);
            int month = Integer.parseInt(s[1]);
            int day = Integer.parseInt(s[2]);
             
            int sum =0;
            for(int i=1;i<month;i++) {
                if(i == 1 || i==3 || i==5 || i==7 || i==8 || i==10 || i==12)
                    sum+=31;
                else if(i == 2) {
                    if(Isrui(year))
                        sum += 29;
                    else
                        sum += 28;
                }
                else
                    sum += 30;
                 
            }
            sum += day;
            System.out.println(sum);
        }
    }
}
