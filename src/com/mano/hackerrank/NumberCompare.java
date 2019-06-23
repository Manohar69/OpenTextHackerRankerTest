package com.mano.hackerrank;

public class NumberCompare {
	public static void main(String[] args) {
		int digits[] = {562,196};
		int sumCount=0;
		for(int i=1;i<String.valueOf(digits[0]).length();) {
			if(digits[0]!=digits[1]) {
				System.out.println(digits[0]);
				System.out.println(digits[1]);
				int first=digits[0]%10;
				int last=digits[1]%10;
				digits[0]/=10;
				digits[1]/=10;
				System.out.println(digits[0]);
				System.out.println(digits[1]);
				if(last>first) {
					sumCount+=last-first;

				} else if(last<first) {
					sumCount+=first-last;
				}
				System.out.println(first+":::"+last+":::"+sumCount+":::"+String.valueOf(digits[0]).length());
			}
		}
		if(digits[0]!=digits[1]) {
			if(digits[0]>digits[1]) {
				sumCount+=digits[0]-digits[1];
			} else {
				sumCount+=digits[1]-digits[0];
			}
		}
		System.out.println("count to make same:"+sumCount);
	}

}
