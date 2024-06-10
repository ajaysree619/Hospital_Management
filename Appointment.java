package Hospital_Management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Appointment {
	
	Connection connection;
	private Patient patient;
	private Doctor doctor;
	
	Scanner scanner=new Scanner(System.in);
	
	Appointment(Patient patient, Doctor doctor, Connection connection) {
		this.connection=connection;
		this.doctor=doctor;
		this.patient=patient;
		
	}
	
	public static boolean checkAvailability(int doctorId, String appointmentDate, Connection connection) {
		 String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
		 try {
			 PreparedStatement preparedStatement= connection.prepareStatement(query);
			 preparedStatement.setInt(1, doctorId);
			 preparedStatement.setString(2, appointmentDate);
			 ResultSet resultSet=preparedStatement.executeQuery();
			 if(resultSet.next()){
	                int count = resultSet.getInt(1);
	                if(count==0){
	                    return true;
	                }else{
	                    return false;
	                }
	            }
		 }catch (Exception e) {
			e.printStackTrace();
		}
		 return false;
	}
	
	public void makeAppointment() {
		 System.out.print("Enter Patient Id: ");
	        int patientId = scanner.nextInt();
	        System.out.print("Enter Doctor Id: ");
	        int doctorId = scanner.nextInt();
	        System.out.print("Enter appointment date (YYYY-MM-DD): ");
	        String appointmentDate = scanner.next();
	        if(patient.getPatientById(patientId) && doctor.getDoctorById(doctorId)){
	        	if(checkAvailability(doctorId, appointmentDate, connection)) {
	        		String appointmentQueryString="INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES(?, ?, ?)";
	        		try {
	        			PreparedStatement preparedStatement=connection.prepareStatement(appointmentQueryString);
	        			 preparedStatement.setInt(1, patientId);
	                     preparedStatement.setInt(2, doctorId);
	                     preparedStatement.setString(3, appointmentDate);
	                     int rowsAffected = preparedStatement.executeUpdate();
	                     if(rowsAffected>0) {
	                    	 System.out.println("Appointment Booked");
	                     }else {
	                    	 System.out.println("Failed to Book Appointment");
	                     }
	        		}catch (Exception e) {
						e.printStackTrace();
					}
	        	}else {
	        		 System.out.println("Doctor not available on this date!!");
	        	}
	        }else {
	        	System.out.println("Either doctor or patient doesn't exist!!!");
	        }
	}

}
