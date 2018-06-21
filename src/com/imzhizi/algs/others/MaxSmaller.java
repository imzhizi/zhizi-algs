package com.imzhizi.algs.others;

/**
 * input 51234
 * output 45321
 *
 * input 54321
 * output 54312
 *
 * created by zhizi on 2018/6/13
 */
public class MaxSmaller {
    public int smallNum(int num){
        int[] nums=new int[10];
        int high=num%10;
        int low=num%10;
        while(num>10){
            high=num%10;
            if(high>low){
                int temp =low;
                low=high;
                high=temp;
                break;
            }else{
                nums[high]++;
            }
            num=num/10;
        }
        int total=0;
        int j=0;
        for (int i=0;i<10;i++){
            if(nums[i]>0){
            }
        }

        return 0;
    }
}
