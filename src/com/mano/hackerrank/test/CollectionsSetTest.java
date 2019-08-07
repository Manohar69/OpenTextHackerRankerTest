package com.mano.hackerranker.test;

import java.util.Set;
import java.util.TreeSet;

public class CollectionsSetTest {
	public static void main(String[] args) {
		Set<Employee> list = new TreeSet<Employee>();
		/*
		 * list.add("Aryan"); list.add("Somu"); list.add("sample"); list.add("");
		 * list.add("Apple"); list.add(""); list.add(""); list.add("");
		 * list.add("Apple");
		 */
		Employee emp = new Employee("Shruthi", 1, "TSE", 20000);
		Employee emp1 = new Employee("Aryan", 2, "ASE", 25000);
		Employee emp2 = new Employee("Shruthi", 1, "TSE", 20000);
		Employee emp3 = new Employee("Aryan", 2, "ASE", 25000);
		list.add(emp);
		list.add(emp1);
		list.add(emp2);
		list.add(emp3);
		list.forEach(temp -> System.out.println(temp));
	}
}
