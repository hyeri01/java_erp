package java_erp.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java_erp.dao.DepartmentDao;
import java_erp.dto.Department;
import java_erp.util.JdbcUtil;

public class DepartmentDaoImpl implements DepartmentDao {

	private static final DepartmentDaoImpl instance = new DepartmentDaoImpl();

	public static DepartmentDaoImpl getInstance() {
		return instance;
	}

	/*
	 * 1. DB 접속 (시간이 많이 걸림) <- Connection Pool
	 * 2. SQL을 Database에 맞는 명령문으로 변경(스트림을 -> 명령문)
	 * 3. SQL에서 ?를 입력 매개변수 값으로 치환해서 SQL 명령문을 완성
	 * 4. SQL명령문 실행 (select : exectueQuery
	 * 					Insert, Update, Delete : executeUpdate)
	 * 5. 만약, executeQurey일 경우 : SQL 결과 (ResulSet)를 클래스로 변환
	 */
	
	
	@Override
	public ArrayList<Department> selectDepartmentByAll() {
		String sql = "SELECT deptno, deptname, floor from department";
		ArrayList<Department> list = null;

		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			list = new ArrayList<Department>();
			while (rs.next()) {
				/* 
				 * rs에 추가 되어야 할 요소 (return ~)
				 * deptno, deptname, floor
				 * int deptNo = rs.getint("deptNo");
				 * String deptName = rs.getString("deptname");
				 * int floor = rs.getint("floor);
				 * Department newDept = new Department(deptNo, deptName, floor);
				 * list.add(newDept);
				 * >> 이런 식으로도 가능하지만 위의 내용을 메소드로 만들어 사용하는 것이 편하다 (다른 곳에서도 사용되기 떄문)
				 */
				list.add(getDepartment(rs));
			}

		} catch (SQLException e) {
			System.out.println("!sql문 오류! sql문 수정 요망");
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Department selectDepartmentByNo(Department department) {
		String sql = "SELECT deptno, deptname, floor from department where deptno = ?";
		
		try (Connection con = JdbcUtil.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1, department.getDeptNo());
			
			try (ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
				
					return getDepartment(rs);
				}
			}
			
		} catch (SQLException e) {
			System.out.println("!sql문 오류! sql문 수정 요망");
			e.printStackTrace();
		}
		
		return null;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt("deptno");
		String deptName = rs.getString("deptName");
		int floor = rs.getInt("floor");
		return new Department(deptNo, deptName, floor);
	}

	@Override
	public int insertDepartment(Department department) {
		String sql = "INSERT into department values(?, ?, ?)";
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1, department.getDeptNo());
			pstmt.setString(2, department.getDeptName());
			pstmt.setInt(3, department.getFloor());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("!sql문 오류! sql문 수정 요망");
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateDepartment(Department department) {
		String sql = "UPDATE department set deptname = ? where deptno = ?";
		
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, department.getDeptName());
			pstmt.setInt(2, department.getDeptNo());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("!sql문 오류! sql문 수정 요망");
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteDepartment(Department department) {
		String sql = "DELETE from department where deptno = ?";
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1, department.getDeptNo());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("!sql문 오류! sql문 수정 요망");
			e.printStackTrace();
		}
		return 0; 
	}

}
