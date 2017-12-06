package com.scb.dao;

import java.util.List;

import com.scb.po.Employee;
import com.scb.vo.EmployeeVo;

public interface EmployeeDao {

	
	public Employee addEmployee(Employee employee);
	public Employee getEmployee(Employee employee);
	public String updateEmployee(Employee employee);
	public List<Employee>findAllEmployee();
	public List<Employee>findEmployeeById(String superId);
	public Employee findEmployeeBypwId(int pwId);
	public List<Employee>search(String msg);
	public String updateEmployeeSuper(String superId,int pwId);
	public String updateHead(Employee employee);
	
	public String updatedetail(Employee employee);
}
