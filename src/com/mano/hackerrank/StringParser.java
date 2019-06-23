package com.mano.hackerrank;
import java.util.Scanner;

public class StringParser {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		String[] names= {"venkat","Manohar"};
		if(names[0].equalsIgnoreCase(names[1])) {
			System.out.println("Same");
		}else if(names[0].length()>names[1].length()) {
			System.out.println("First name is larger than second");
			sortFun(names[0],names[1]);
		} else {
			System.out.println("Second name is larger than first");
			sortFun(names[1],names[0]);
		}


		sc.close();
	}

	private static void sortFun(String a, String b) {
		char[] first = a.toCharArray();
		String second=b;
		for(int i=0;i<first.length-1;i++) {
			if(first[i] != second.charAt(i)) {
				int dest=first.length-1;
				first = swap(first,i,dest);
				System.out.println(first);
			}
		}

	}
	private static  char[] swap(char[] source,int start,int destination) {
		char temp=source[start];
		source[start]=source[destination];
		source[destination]=temp;
		System.out.println(source);
		return source;
	}

}
