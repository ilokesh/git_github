package com.main.Springjdbc;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.Springjdbc.dao.EmployeeDao;
import com.Springjdbc.model.Employee;

public class Springmain {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring.xml");
		EmployeeDao employeedao = (EmployeeDao) ctx.getBean("EmployeeDao");
		Employee emp = new Employee();
		Employee emp1 = new Employee();
		emp.setId(2);
		emp.setName("lokesh");
		emp.setRole("JD");
		emp1.setId(3);
		emp1.setName("loke");
		emp1.setRole("CD");
		
		// create
		employeedao.save(emp);
		employeedao.save(emp1);

		//read
		Employee emp2 = employeedao.getById(2);
		System.out.println("employee deatils retrieves"+emp2);
		
		//update
		System.out.println("changing the role");
		emp.setRole("Ceo");
		employeedao.update(emp);
		
		List<Employee> emplist = employeedao.getAll();
		System.out.println("all details"+emplist);
		// delete
		
	/*	employeedao.deleteById(3);
		((AbstractApplicationContext) ctx).close();
		System.out.println("All operations done"); */
		employeedao.deleteById(1);
	}

}
