package Hospital_Management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
	
	private Connection connection;
	
	Doctor(Connection connection){
		this.connection=connection;
	}
	
	public void viewDoctors() throws SQLException {
		String queryString="Select * from doctor";
		PreparedStatement preparedStatement=connection.prepareStatement(queryString);
		ResultSet resultSet=preparedStatement.executeQuery();
		 System.out.println("**************************************************");
	     System.out.println("*                 VIEW DOCTORS                   *");
	     System.out.println("**************************************************");
	     System.out.printf("| %-10s | %-18s | %-16s |\n", "ID", "Name", "Specialization");
	     System.out.println("**************************************************");
		while(resultSet.next()) {
			int id=resultSet.getInt("id");
			String name=resultSet.getString("name");
			String specialization=resultSet.getString("specialization");
            System.out.printf("| %-10s | %-18s | %-16s |\n", id, name, specialization);

			
		}
		 System.out.println("**************************************************");
	}
	
	public boolean getDoctorById(int id) {
		String queryString="Select * from doctor where id=?";
		try {
		PreparedStatement preparedStatement=connection.prepareStatement(queryString);
		preparedStatement.setInt(1, id);
		ResultSet resultSet=preparedStatement.executeQuery();
		 if(resultSet.next()){
             return true;
         }else{
             return false;
         }
		}catch (Exception e) {
			e.printStackTrace();
		}
		 
		 return false;
		
	}

}
