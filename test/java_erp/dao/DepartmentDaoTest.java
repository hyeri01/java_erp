package java_erp.dao;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java_erp.dao.Impl.DepartmentDaoImpl;
import java_erp.dto.Department;

//@FixMethodOrder (MethodSorters.NAME_ASCENDING)

public class DepartmentDaoTest {

	private DepartmentDao dao;

	@Before
	public void setUp() throws Exception {
		dao = DepartmentDaoImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println();
		dao = null;
	}

	@Test
	public void test01SelectDepartmentByAll() {
		System.out.println("test01SelectDepartmentByAll()");
		ArrayList<Department> list = dao.selectDepartmentByAll();
		Assert.assertNotEquals(0, list.size()); // list.size() 0이 아닌 경우 결과 값 가져온다
		
		list.stream().forEach(System.out::println);
		
	}

	@Test
	public void test02SelectDepartmentByNo() {
		System.out.println("test02SelectDepartmentByNo()");
		Department selectDepartment = dao.selectDepartmentByNo(new Department(2));
		Assert.assertNotNull(selectDepartment); // selectDepartment가 not null인 경우 결과 값 가져온다
		
		System.out.println(selectDepartment);
	}

	@Test
	public void test03InsertDepartment() {
		System.out.println("test03InsertDepartment()");
		Department insertdepartment = new Department(3, "총무", 4);
		
		int res = dao.insertDepartment(insertdepartment);
		Assert.assertEquals(1, res); // res가 1이라면 성공 
		System.out.println("res >> : " + res);
		
		dao.deleteDepartment(insertdepartment);
	}

	@Test
	public void test04UpdateDepartment() {
		System.out.println("test04UpdateDepartment()");
		// 추가
		Department dept = new Department(3, "총무", 4);
		dao.insertDepartment(dept);
		
		// 수정 - 검증
		dept.setDeptName("인사");
		dept.setFloor(19);
		int res = dao.updateDepartment(dept);
		Assert.assertEquals(1, res);
		
		// 변경 유무 출력
		Department selDept = dao.selectDepartmentByNo(dept);
		System.out.println(selDept);
		
		// 삭제
		dao.deleteDepartment(selDept);

		


	}

	@Test
	public void test05DeleteDepartment() {
		System.out.println("test05DeleteDepartment()");
		// 추가
		Department dept = new Department(3, "총무", 4);
		dao.insertDepartment(dept);
		
		// 삭제 - 검증
		int res = dao.deleteDepartment(dept);
		Assert.assertEquals(1, res);
		System.out.println("res >> : " + res);
		
		Department selDept = dao.selectDepartmentByNo(dept); // null
		Assert.assertNull(selDept);
	}

}
