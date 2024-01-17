package com.kh.jdbc.day03.stmt.employee.controller;

import java.util.List;

import com.kh.jdbc.day03.stmt.employee.model.dao.EmployeeDAO;
import com.kh.jdbc.day03.stmt.employee.model.vo.Employee;

public class EmployeeController {
	private EmployeeDAO eDao;
	
	public EmployeeController() {
		eDao = new EmployeeDAO();
	}
	public void registerEmployee(Employee emp) {
		eDao.insertEmployee(emp);
	}
	public List<Employee> printAllEmployee() {
		List<Employee> eList = eDao.selectAllEmployees();
		return eList;
	}
	public int removeEmployee(String empId) {
		int result = eDao.deleteEmployee(empId);
		return result;
	}
	public int updateEmployee(Employee emp) {
		int result = eDao.updateEmployee(emp);
		return result;
	}
}
