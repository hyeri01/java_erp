package java_erp;

import java_erp.dto.Department;
import java_erp.dto.Employee;
import java_erp.dto.Title;

public class Main {
	public static void main(String[] args) {
		Employee emp = new  Employee(1004, "이동혁", null, null, 3500000, null);
		Employee emp1 = new  Employee(1004, "이동혁", new Title(1), null, 3500000, null);
		Employee emp2 = new  Employee(1004, "이동혁", new Title(1), new Employee(1002), 3500000, null);
		Employee emp3 = new  Employee(1004, "이동혁", new Title(1), new Employee(1002), 3500000, new Department(1));

		
		System.out.println("emp " + emp);
		System.out.println();
		System.out.println("emp1 " + emp1);
		System.out.println();
		System.out.println("emp2 " + emp2);
		System.out.println();
		System.out.println("emp3 " + emp3);
		System.out.println();
	
		
		
		
		
		Title title = new Title(3, "과장");
		Employee manager = new Employee(1002, "김정우", new Title(1), null, 5000000, new Department(1));
		Department dept = new Department(2, "영업", 10);
		
		Employee newEmp = new Employee(1004, "문태일", title, manager, 2500000, dept);
		
		//1. emp3의 직책
		Title emp3Title = newEmp.getTitle();
		String titleName = newEmp.getTitle().gettName();
		
		System.out.printf("직책 : %s\n", emp3Title);
		System.out.printf("직책명 : %s\n", titleName);
		//2. emp3의 
		
	}

}
