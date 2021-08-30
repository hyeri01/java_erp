package java_erp.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import java_erp.dao.EmployeeDao;
import java_erp.dto.Department;
import java_erp.dto.Employee;
import java_erp.dto.Title;
import java_erp.util.JdbcUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	private static final EmployeeDaoImpl instance = new EmployeeDaoImpl();

	public static EmployeeDaoImpl getInstance() {
		return instance;
	}

	private EmployeeDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * 1. DB 접속 (시간이 많이 걸림) <- Connection Pool
	 * 2. SQL을 Database에 맞는 명령문으로 준비 (스트림을 -> 명령문) PreparedStatement
	 * 3. SQL에서 ?를 입력 매개변수 값으로 치환해서 SQL 명령문을 완성
	 * 4. SQL명령문 실행 (select : exectueQuery
	 * 					Insert, Update, Delete : executeUpdate)
	 * 5. 만약, executeQurey일 경우 : SQL 결과 (ResulSet)를 클래스로 변환
	 */

	@Override
	public ArrayList<Employee> selectEmployeeByAll() {
		String sql = "SELECT empno, empname, title, manager, salary, dno from employee";
		ArrayList<Employee> list = null;
		
		try(Connection con = JdbcUtil.getConnection(); 
				PreparedStatement pstmt = con.prepareCall(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				list = new ArrayList<Employee>();
				do {
					list.add(getEmployee(rs));
				} while(rs.next());
				
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}



	@Override
	public Employee selectEmployeeByNo(Employee emp) {
		String sql = "SELECT empno, empname, title, manager, salary, dno from employee where empno = ?";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, emp.getEmpNo());
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					return getEmployee(rs);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private Employee getEmployee(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("empno");
		String empName = rs.getString("empName");
		
		// title, manager, dno은 null일 수도 있으니까 삼항 연산자 사용해서 0인 경우 null 아니면 값 대입
		Title title = rs.getInt("title") == 0? null : new Title(rs.getInt("title"));
		Employee manager = rs.getInt("manager") == 0 ? null : new Employee(rs.getInt("manager"));
		int salary = rs.getInt("salary");
		Department dept = rs.getInt("dno") == 0? null : new Department(rs.getInt("dno"));
		return new Employee(empNo, empName, title, manager, salary, dept);
	}

	@Override
	public int insertEmployee(Employee emp) {
		// empno, empname, title, manager, salary, dno
		String sql = "INSERT into employee values(?, ?, ?, ?, ?, ?)";

		try (Connection con = JdbcUtil.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// 값이 null인 경우도 있을 수 있으니 감안해야 한다 (null일 수 있는 경우 -> title, manager, dno)
		
			pstmt.setInt(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			
			try {
			pstmt.setInt(3, emp.getTitle().gettNo());
			} catch(NullPointerException e) {
				pstmt.setNull(3, Types.INTEGER);
			}
			
			try {
			pstmt.setInt(4, emp.getManager().getEmpNo());
			} catch(NullPointerException e) {
				pstmt.setNull(4, Types.INTEGER);
			}
			
			pstmt.setInt(5, emp.getSalary());
			
			try {
				pstmt.setInt(6, emp.getDept().getDeptNo());
			} catch (NullPointerException e) {
				pstmt.setNull(6, Types.INTEGER);
			}
			
			System.out.println(pstmt);
			
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("!sql문 오류! sql문 확인 요망");
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int deleteEmployee(Employee emp) {
		String sql = "DELETE from employee where empno = ?";
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, emp.getEmpNo());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("!sql문 오류! sql문 확인 요망");
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int updateEmployee(Employee emp) {
		String sql = "UPDATE employee \n"
				+ "	set empname = ?, salary = ?, title = ?, manager = ?, dno = ? \n"
				+ "where empno = ?";
		
		try (Connection con = JdbcUtil.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			// 값이 null인 경우도 있을 수 있으니 감안해야 한다 (null일 수 있는 경우 -> title, manager, dno)
			
			pstmt.setString(1, emp.getEmpName());
			pstmt.setInt(2, emp.getSalary());
			
			try {
				pstmt.setInt(3, emp.getTitle().gettNo());
			} catch(NullPointerException e) {
					pstmt.setNull(3, Types.INTEGER);
			}
				
			try {
				pstmt.setInt(4, emp.getManager().getEmpNo());
			} catch(NullPointerException e) {
					pstmt.setNull(4, Types.INTEGER);
			}
			
			try {
				pstmt.setInt(5, emp.getDept().getDeptNo());
			} catch (NullPointerException e) {
				pstmt.setNull(5, Types.INTEGER);
			}
			
			pstmt.setInt(6, emp.getEmpNo());
			System.out.println("update pstmt : " + pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}



}
