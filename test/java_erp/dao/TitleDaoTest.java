package java_erp.dao;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java_erp.dao.Impl.TitleDaoImpl;
import java_erp.dto.Title;

// 메소드 호출 순서 고정 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TitleDaoTest {
	private TitleDao dao;

	@Before
	public void setUp() throws Exception {
		dao = TitleDaoImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println();
		dao = null;
	}

	@Test
	public void test01SelectTitleByAll() {
		System.out.println("testSelectTitleByAll()");
		ArrayList<Title> list = dao.selectTitleByAll();
		Assert.assertNotEquals(0, list.size()); // list.size가 0이 아니라면 결과 값 가져온다

		list.stream().forEach(System.out::println);
	}

	@Test
	public void test02SelectTitleByNo() {
		System.out.println("testSelectTitleByNo()");
		Title seleTitle = dao.selectTitleByNo(new Title(2));
		Assert.assertNotNull(seleTitle); // null이 아니면 결과 값 가져온다

		System.out.println(seleTitle);
	}

	@Test
	public void test03InsertTitle() {
		System.out.println("testInsertTitle()");
		Title newTitle = new Title(3, "과장");

		int res = dao.insertTitle(newTitle);
		Assert.assertEquals(1, res); // res가 1이라면 성공 (Assert = 검증하는 것)
		System.out.println("res >> " + res);

		test01SelectTitleByAll();
	}

	@Test
	public void test04UpdateTitle() {
		System.out.println("test04UpdateTitle()");
		Title updateTitle = new Title(3, "대리");

		int res = dao.updateTitle(updateTitle);
		Assert.assertEquals(1, res);
		System.out.println("res >> " + res);

	}

	@Test
	public void test05DeleteTitle() {
		System.out.println("testDeleteTitle");
		Title deleteTitle = new Title(3, "대리");
		
		int res = dao.deleteTitle(deleteTitle);
		Assert.assertEquals(1, res);
		System.out.println("res >> " + res);

	}

}
