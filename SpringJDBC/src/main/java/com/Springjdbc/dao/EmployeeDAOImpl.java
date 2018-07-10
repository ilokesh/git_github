package com.Springjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.sql.DataSource;

import com.Springjdbc.model.Employee;


public class EmployeeDAOImpl implements EmployeeDao{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	public void save(Employee employee) {
		String query = "Insert into Employee(id, name, role) values(?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps= con.prepareStatement(query);
			ps.setInt(1, employee.getId());
			ps.setString(2,employee.getName());
			ps.setString(3, employee.getRole());
			int out = ps.executeUpdate();

			if (out != 0)
			{
				System.out.println("Employee deatils saveed with "+employee.getId());
			}
			else {
				System.out.println("Saving deatais failed"+employee.getId());

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	public Employee getById(int id) {
		Employee emp = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select name, role from Employee where id = ?"; 

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next())
			{
				emp = new Employee();
				emp.setId(id);
				emp.setName(rs.getString("name"));
				emp.setRole(rs.getString("role"));
				System.out.println("Employee found"+emp);
			}else{
				System.out.println("Employee details not found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				ps.close();
				rs.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}

		return emp;
	}

	public EmployeeDAOImpl() {

	}





	public void update(Employee employee) {
		Connection con = null;
		PreparedStatement ps = null;
		String query = "update Employee set name=?, role=? where id=? ";
		try {
			con =dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getRole());
			ps.setInt(3, employee.getId());

			int out= ps.executeUpdate();
			if (out!=0)
			{
				System.out.println("updated emplopyee details"+employee.getId());
			}else{
				System.out.println("Canot able to upadate the details of the employee"+employee.getId());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
				ps.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	} 


	public void deleteById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		String query = "Delete from Employee where id =?";

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			int out = ps.executeUpdate();
			if (out!= 0)
			{
				System.out.println("Deleted the id"+id);
			}else {
				System.out.println("cannot able to delete"+id);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public List<Employee> getAll() {
		List<Employee> emplist = new ArrayList<Employee>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from employee";

		try {
			con = dataSource.getConnection();
			ps =con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setRole(rs.getString("role"));
				emplist.add(emp);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return emplist;

	}


}
