package com.mano.interview;

import java.util.Scanner;

public class PrimeNumbers {  
	public static void main(String[] args) {  
		Scanner sc = new Scanner(System.in);
		int sum=0;
		System.out.print("Enter the first number : ");  
		int start = sc.nextInt();  
		System.out.print("Enter the second number : ");  
		int end = sc.nextInt();  
		System.out.println("List of prime numbers between " + start + " and " + end);  
		for (int i = start; i <= end; i++) {  
			if (isPrime(i)) {
				sum+=i;
				System.out.println(i);
			}  
		}
		System.out.println("Sum of primenumbers:"+sum);
		sc.close();
	}  
	public static boolean isPrime(int n) {  
		if (n <= 1) {  
			return false;  
		}  
		for (int i = 2; i <= Math.sqrt(n); i++) {  
			if (n % i == 0) {  
				return false;  
			}  
		}  
		return true;  
	}
}  