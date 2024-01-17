package com.kh.jdbc.day03.stmt.employee.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day03.stmt.employee.model.vo.Employee;

public class EmployeeDAO {
	final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	final String USERNAME = "STUDENT";
	final String PASSWORD = "STUDENT";
	/* 1. 드라이버 등록
	 * 2. DBMS 연결 생성
	 * 3. Statement 생성
	 * 4. 쿼리문 실행 및 결과받기
	 * 5. 자원 해제
	 */
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		Class.forName(DRIVER_NAME);
		return conn;
	}
	public int updateEmployee(Employee emp) {
		int result = 0;
		String query = "UPDATE EMPLOYEE SET EMAIL = '"+emp.getEmail()+"', PHONE = '"+emp.getPhone()+"' WHERE EMP_ID = '"+emp.getEmpId()+"' ";
		try {
			Connection conn = this.getConnection();
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			if(result > 0) {
				// commit
			}else {
				// rollback
			}
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
		String query = "DELETE FROM EMPLOYEE WHERE EMP_ID = '"+empId+"'";
		try { 
			Connection conn = this.getConnection();
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
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
		String query = "INSERT INTO EMPLOYEE VALUES('"+emp.getEmpId()+"', '"+emp.getEmpName()+"', '"+emp.getEmpNo()+"', '"+emp.getEmail()+"', '"+emp.getPhone()+"', '"+emp.getDeptCode()+"', '"+emp.getJobCode()+"', '"+emp.getSalLevel()+"', "+emp.getSalary()+", "+emp.getBonus()+", '"+emp.getManagerId()+"', SYSDATE, null, 'N')";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement stmt = conn.createStatement();
			int result = stmt.executeUpdate(query);
			if (result > 0) {
				// 성공 - 커밋
			}else {
				// 실패 - 롤백
			}
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
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
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
