package com.Springjdbc.dao;

import java.util.List;

import com.Springjdbc.model.Employee;

//curd operation the interface
public interface EmployeeDao {
	//create 
	public void save(Employee employee);
	
	//read
	public Employee getById(int id);
	
	//update
	public void update(Employee employee);
	
	//delete
	public void deleteById(int id);
	
	public List<Employee> getAll();
	
	
}
