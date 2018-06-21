package com.imzhizi.algs.bupt;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DProD数据库检索 {

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		ArrayList<String> result=new ArrayList<>();
		int t=input.nextInt();
		ArrayList<Student> stulist=new ArrayList<>();
		for (int i = 0; i < t; i++) {
			int n=input.nextInt();
			int m=input.nextInt();
			input.nextLine();
			for(int j=0;j<n;j++) stulist.add(new Student(input.nextLine()));
				
			for (int j = 0; j < m; j++) {
				ArrayList<Student> list=stulist;
				String query=input.nextLine();
				String[] queries=query.split("'");
				if(query.contains("Name")) {
					list=(ArrayList<Student>) list.stream().filter(stu->stu.name.equals(queries[1])).collect(Collectors.toList());
					if(query.contains("Sex")) {
						list=(ArrayList<Student>)list.stream().filter(stu->stu.sex.equals(queries[3])).collect(Collectors.toList());
						if(query.contains("Birthday")) list=birthday(list, queries[5]);
					}
					else if(query.contains("Birthday")) list=birthday(list, queries[3]);
				}
				else if(query.contains("Sex")) {
					list=(ArrayList<Student>) list.stream().filter(stu->stu.sex.equals(queries[1])).collect(Collectors.toList());
					if(query.contains("Birthday")) list=birthday(list, queries[3]);
				}else list=birthday(list, queries[3]);
				
				if(list.isEmpty())result.add("NULL");
				else for(Student stu:list) result.add(stu.name);
			}
			stulist.clear();
		}
		
		for(String s:result) System.out.println(s);
		input.close();
	}
	
	public static ArrayList<Student> birthday(ArrayList<Student> list,String date){
		String[] birth = date.split("/");
		if(!birth[0].equals("*"))
		list=(ArrayList<Student>) list.stream().filter(stu->stu.year.equals(birth[0])).collect(Collectors.toList());
		if(!birth[1].equals("*"))
		list=(ArrayList<Student>) list.stream().filter(stu->stu.month.equals(birth[1])).collect(Collectors.toList());					
		if(!birth[2].equals("*"))
		list=(ArrayList<Student>) list.stream().filter(stu->stu.day.equals(birth[2])).collect(Collectors.toList());
		return list;
	}

}