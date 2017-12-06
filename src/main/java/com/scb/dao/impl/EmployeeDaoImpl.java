package com.scb.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scb.dao.EmployeeDao;
import com.scb.po.Employee;
import com.scb.vo.EmployeeVo;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	@Autowired
	private SessionFactory sessionFactory;
	public Employee addEmployee(Employee employee) {
	
			  sessionFactory.getCurrentSession().save(employee);
		    	return employee;
		   
	
		
	}
	public Employee getEmployee(Employee employee) {
		String hql="from Employee where pwId=? and password=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, employee.getPwId());
		query.setString(1, employee.getPassword());
		List<Employee> employees=query.list();
	    if(employees.size()==0){
	    	
	    	return null;
	    }else{
	    	return employees.get(0);
	    }
	}
	public String updateEmployee(Employee employee) {
		String hql = "update Employee set name=?, phone=?,superId=? where pwId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, employee.getName());
		query.setString(1, employee.getPhone());
		query.setString(2, employee.getSuperId());
		query.setInteger(3, employee.getPwId());
		int j = query.executeUpdate();
		if (j > 0) {
			return "success";
		} else {
			return null;
		}
	}
	public List<Employee> findAllEmployee() {
		String hql="from Employee  ";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<Employee> users=query.list();
	    if(users.size()==0){
	    	
	    	return null;
	    }else{
	    	return users;
	    }
	}
	public List<Employee> findEmployeeById(String superId) {
		// TODO Auto-generated method stub
		
		String hql=" from  Employee where superId=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		
		query.setString(0,superId);
		List<Employee> employees=query.list();
	    if(employees.size()==0){
	    	
	    	return null;
	    }else{
	    	return employees; 
	    }
	}
	public Employee findEmployeeBypwId(int pwId) {
		String hql="from Employee where pwId=? ";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, pwId);

		List<Employee> employees=query.list();
	    if(employees.size()==0){
	    	
	    	return null;
	    }else{
	    	return employees.get(0);
	    }
	}
	public List<Employee> search(String msg) {
		String hql="from Employee where name like '%"+msg+"%' or pwId  like '"+msg+"%' ";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);

		List<Employee> employees=query.list();
	    if(employees.size()==0){
	    	
	    	return null;
	    }else{
	    	return employees;
	    }
	}
	@Override
	public String updateEmployeeSuper(String superId, int pwId) {
		String hql = "update Employee set superId=? where pwId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, superId);
		query.setInteger(1, pwId);
		int j = query.executeUpdate();
		if (j > 0) {
			return "success";
		} else {
			return null;
		}
	}
	@Override
	public String updateHead(Employee employee) {
		String hql = "update Employee set headportrait=? where pwId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, employee.getHeadportrait());
		query.setInteger(1, employee.getPwId());
	
		int j = query.executeUpdate();
		if (j > 0) {
			return "success";
		} else {
			return null;
		}
	}
	@Override
	public String updatedetail(Employee employee) {
		String hql = "update Employee set name=?, phone=?,position=? where pwId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, employee.getName());
		query.setString(1, employee.getPhone());
		query.setString(2, employee.getPosition());
		query.setInteger(3, employee.getPwId());
		int j = query.executeUpdate();
		if (j > 0) {
			return "success";
		} else {
			return null;
		}
	}

}
