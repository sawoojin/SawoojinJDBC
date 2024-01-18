package com.kh.jdbc.day04.pstmt.employee.model.dao;

import java.sql.*;
import java.util.*;

import com.kh.jdbc.day04.pstmt.employee.common.JDBCTemplate;
import com.kh.jdbc.day04.pstmt.employee.model.vo.Employee;

public class EmployeeDAO {
	// JDBCTemplate 값 가져오는 생성자
	private JDBCTemplate jdbcTemplaye;
	
	public EmployeeDAO() {
		jdbcTemplaye = JDBCTemplate.getInstance();
	}

	public int updateEmployee(Employee emp) {
		int result = 0;
		String query = "UPDATE EMPLOYEE SET EMAIL = ?, PHONE = ? WHERE EMP_ID = ?";
		try {
			Connection conn = jdbcTemplaye.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emp.getEmail());
			pstmt.setString(2, emp.getPhone());
			pstmt.setString(3, emp.getEmpId());
			result = pstmt.executeUpdate();
			if(result > 0) {
				// commit
			}else {
				// rollback
			}
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int deleteEmployee(String empId) {
		int result = 0;
		String query = "DELETE FROM EMPLOYEE WHERE EMP_ID = ?";
		try { 
			Connection conn = jdbcTemplaye.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empId);
			result = pstmt.executeUpdate();
			if (result < 0) {
				// commit
			}else {
				// rollback
			}
			pstmt.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public void insertEmployee(Employee emp) {
		String query = "INSERT INTO EMPLOYEE VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, null, 'N')";
		int result = -1;
		try {
			Connection conn = jdbcTemplaye.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emp.getEmpId());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getEmpNo());
			pstmt.setString(4, emp.getEmail());
			pstmt.setString(5, emp.getPhone());
			pstmt.setString(6, emp.getDeptCode());
			pstmt.setString(7, emp.getJobCode());
			pstmt.setString(8, emp.getSalLevel());
			pstmt.setInt(9, emp.getSalary());
			pstmt.setDouble(10, emp.getBonus());
			pstmt.setString(11, emp.getManagerId());
			result = pstmt.executeUpdate();
			if (result > 0) {
				// 성공 - 커밋
			}else {
				// 실패 - 롤백
			}
			pstmt.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<Employee> selectAllEmployees() {
		String query = "SELECT * FROM EMPLOYEE";
		List<Employee> eList = null;
		try {
			Connection conn = jdbcTemplaye.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			eList = new ArrayList<Employee>();
			// 후처리
			while(rset.next()) {
//				System.out.println("직원명 : " + rset.getString("EMP_NAME"));
				Employee employee = new Employee();
				employee.setEmpId(rset.getString("EMP_ID"));
				employee.setEmpName(rset.getString("EMP_NAME"));
				employee.setEmpNo(rset.getString("EMP_NO"));
				employee.setEmail(rset.getString("EMAIL"));
				employee.setPhone(rset.getString("PHONE"));
				employee.setDeptCode(rset.getString("DEPT_CODE"));
				employee.setSalLevel(rset.getString("SAL_LEVEL"));
				employee.setSalary(rset.getInt("SALARY"));
				employee.setBonus(rset.getDouble("BONUS"));
				employee.setManagerId(rset.getString("MANAGER_ID"));
				employee.setHireDate(rset.getDate("HIRE_DATE"));
				employee.setEntDate(rset.getDate("ENT_DATE"));
				employee.setEntYn(rset.getString("ENT_YN"));
				eList.add(employee);
			}
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eList;
	}
}
