package com.mano.interview;
/*
 * Fibonacci using recursion
 */
class FibonacciNumbers{  
	static int a=0,b=1,c=0;    
	static void printFibonacci(int count){    
		if(count>0){    
			c = a + b;    
			a = b;    
			b = c;    
			System.out.print(" "+c);   
			printFibonacci(count-1);    
		}    
	}    
	public static void main(String args[]){    
		int count=10;    
		System.out.print(a+" "+b);
		printFibonacci(count-2);
		/*
		 * we can use fallowing code for non recursion
		 * for(int i=2;i<=count;i++){
		 * 	c=a+b;
		 * 	System.out.print(" "+c);
		 * 	a=b;
		 * 	b=c;
		 * }
		 */
	}  
}  