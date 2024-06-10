package Hospital_Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalManagement {
	public static final String url="jdbc:mysql://localhost:3306/hospital_management";
	public static final String username="root";
	public static final String password="root";
	static boolean loopNext=true;
	

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Connection connection=DriverManager.getConnection(url,username,password);
			Patient patient=new Patient(connection);
			Doctor doctor=new Doctor(connection);
			Appointment appointment=new Appointment(patient, doctor, connection);
			while(loopNext) {
				    System.out.println("**************************************************");
		            System.out.println("*            HOSPITAL MANAGEMENT SYSTEM          *");
		            System.out.println("**************************************************");
		            System.out.println("*  1. Add Patient                               *");
		            System.out.println("*  2. View Patients                             *");
		            System.out.println("*  3. View Doctors                              *");
		            System.out.println("*  4. Book Appointment                          *");
		            System.out.println("*  5. Exit                                      *");
		            System.out.println("**************************************************");
		            System.out.print("*  Enter your choice:                           *\n");
		            System.out.println("**************************************************");
		            int choice=scanner.nextInt();
	                
	                switch (choice) {
					case 1:
						patient.addPatient();
						System.out.println();
						break;
					
					case 2:
						patient.getPatient();
						System.out.println();
						break;
						
					case 3:
						doctor.viewDoctors();
						System.out.println();
						break;
						
					case 4:
						appointment.makeAppointment();
						System.out.println();
						break;
					
					case 5:
						 System.out.println("THANK YOU! FOR USING HOSPITAL MANAGEMENT SYSTEM!!");
						loopNext=false;
						break;

					default:
						System.out.println("Please Enter a Valid choice");
						break;
					}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
