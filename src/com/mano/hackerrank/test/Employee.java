package com.mano.hackerranker.test;

public class Employee implements Comparable<Employee> {
	private String name;
	private int id;
	private String desc;
	private float salary;

	public String getName() {
		return name;
	}

	public Employee(String name, int id, String desc, float salary) {
		super();
		this.name = name;
		this.id = id;
		this.desc = desc;
		this.salary = salary;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [desc=" + desc + ", id=" + id + ", name=" + name + ", salary=" + salary + ", getDesc()="
				+ getDesc() + ", getId()=" + getId() + ", getName()=" + getName() + ", getSalary()=" + getSalary()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	/*
	 * @Override public int hashCode() { return this.id; }
	 * 
	 * @Override public boolean equals(Object obj) { if (this == obj) return true;
	 * if (obj == null || obj.getClass() != this.getClass()) return false; Employee
	 * emp = (Employee) obj; return (emp.desc.equals(this.desc) && emp.id == this.id
	 * && emp.name.equals(this.name)); }
	 */
	@Override
	public int compareTo(Employee o) {
		if (this.id == o.id)
			return 0;
		else if (this.id < o.id)
			return 1;
		else
			return -1;
	}
}
