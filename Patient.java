package Hospital_Management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
	private Connection connection;
	Scanner scanner=new Scanner(System.in);
	
	public Patient(Connection connection) {
		this.connection=connection;
	}
	
	public void addPatient() {
		System.out.println("Enter Patient name:");
		String name =scanner.next();
		System.out.println("Enter your age");
		int age=scanner.nextInt();
		System.out.println("Enter your Gender");
		String gender=scanner.next();
		
		try {
			String querString="Insert into patients(name,age,gender) values(?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(querString);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, age);
			preparedStatement.setString(3, gender);
			int affectedRows=preparedStatement.executeUpdate();
			if(affectedRows>0) {
				System.out.println("Patient Added Successfully");
			}else {
				System.out.println("Failed to add patient");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getPatient() throws SQLException {
		 System.out.println("**************************************************");
	     System.out.println("*              VIEW PATIENTS                      *");
	     System.out.println("**************************************************");
	     System.out.printf("| %-10s | %-18s | %-8s | %-10s |\n", "ID", "Name", "Age", "Gender");
	     System.out.println("**************************************************");
		String querString="Select * from patients";
		PreparedStatement preparedStatement=connection.prepareStatement(querString);
		ResultSet resultSet=preparedStatement.executeQuery();
		while(resultSet.next()) {
			int id=resultSet.getInt("id");
			String name=resultSet.getString("name");
			int age=resultSet.getInt("age");
			String gender=resultSet.getString("gender");
			System.out.printf("| %-10s | %-18s | %-8s | %-10s |\n", id, name, age, gender); 
		}
		 System.out.println("**************************************************");
	}
	
	public boolean getPatientById(int id) {
		String querString="Select * from patients where id=?";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(querString);
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}else {
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	

}
