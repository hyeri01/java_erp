package java_erp.dao;



import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java_erp.dao.Impl.EmployeeDaoImpl;
import java_erp.dto.Department;
import java_erp.dto.Employee;
import java_erp.dto.Title;


public class EmployeeDaoTest {
	private EmployeeDao dao = EmployeeDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void testSelectEmployeeByAll() {
		System.out.println("testSelectEmployeeByAll()");
		ArrayList<Employee> list = dao.selectEmployeeByAll();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void testSelectEmployeeByNo() {
		System.out.println("testSelectEmployeeByNo()");
		Employee selectEmp = dao.selectEmployeeByNo(new Employee(4377));
		Assert.assertNotNull(selectEmp);
		System.out.println(selectEmp);
	}
	
///////////////////////////////////////////////////////////////////
	@Test
	public void test01InsertEmployee() {
		System.out.println("test01InsertEmployee()");
		Employee newEmp = new Employee(1010, "김정우", null, null, 2500000, null);
		int res = dao.insertEmployee(newEmp);
		Assert.assertEquals(1, res);
		
		dao.deleteEmployee(newEmp);
		
	}
	
	@Test
	public void test02InsertEmployee() {
		System.out.println("test02InsertEmployee()");
		Title title = new Title(2, "부장");
		Employee newEmp = new Employee(1011, "김정우1", title, null, 2500000, null);
		int res = dao.insertEmployee(newEmp);
		Assert.assertEquals(1, res);
		
		dao.deleteEmployee(newEmp);
	}
	
	@Test
	public void test03InsertEmployee() {
		System.out.println("test03InsertEmployee()");
		Title title = new Title(2, "부장");
		Employee manager = new Employee(4377);
		Employee newEmp = new Employee(1012, "김정우2", title, manager, 2500000, null);
		int res = dao.insertEmployee(newEmp);
		Assert.assertEquals(1, res);
		
		dao.deleteEmployee(newEmp);
		
	}
	
	@Test
	public void test04InsertEmployee() {
		System.out.println("test04InsertEmployee()");
		Title title = new Title(2);
		Employee manager = new Employee(4377);
		Department dno = new Department(1);
		Employee newEmp = new Employee(1013, "김정우3", title, manager, 2500000, dno);
		int res = dao.insertEmployee(newEmp);
		Assert.assertEquals(1, res);
		
		dao.deleteEmployee(newEmp);
		
	}
///////////////////////////////////////////////////////////////////

	@Test
	public void test01UpdateEmployee() {
		System.out.println("test01UpdateEmployee()");
		Employee newEmp = new Employee(1003, "이민형", null, null, 2500000, null);
		dao.insertEmployee(newEmp);
		
		// 수정
		newEmp.setEmpName("이동혁");
		newEmp.setSalary(1500000);
		int res = dao.updateEmployee(newEmp);
		Assert.assertEquals(1, res);
		
		dao.deleteEmployee(newEmp);
		
	}
	
	@Test
	public void test02UpdateEmployee() {
		System.out.println("test02UpdateEmployee()");
		Title title = new Title(2);
		Employee manager = new Employee(4377);
		Department dno = new Department(1);
		Employee newEmp = new Employee(1013, "김정우3", title, manager, 2500000, dno);
		dao.insertEmployee(newEmp);
		
		// 수정
		newEmp.setTitle(new Title(1));
		newEmp.setManager(new Employee(4377));
		newEmp.setDept(new Department(2));
		int res = dao.updateEmployee(newEmp);
		Assert.assertEquals(1, res);
		
		dao.deleteEmployee(newEmp);
		
	}
	
///////////////////////////////////////////////////////////////////

	@Test
	public void testDeleteEmployee() {
		System.out.println("testDeleteEmployee()");
		Employee newEmp = new Employee(1010, "김정우", null, null, 2500000, null);
		dao.insertEmployee(newEmp);
		
		int res = dao.deleteEmployee(newEmp);
		Assert.assertEquals(1, res);
	}

}
