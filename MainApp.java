package hms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {
	
	private static Scanner scanner = new Scanner(System.in);
	private static HospitalSystem hospitalSystem = new HospitalSystem();
	
	

	public static HospitalSystem getHospitalSystem() {
		return hospitalSystem;
	}
	public static void setHospitalSystem(HospitalSystem hospitalSystem) {
		MainApp.hospitalSystem = hospitalSystem;
	}
	public static void main(String[] args) {
		boolean running = true;
		while (running) {
		    
		    System.out.println("====== Hospital Management Menu =======");
		// ... menu options ...
		System.out.println("1. Add Patient \t 2. Add Doctor \n"
				+ "3. Add Department \t4. Assign Doctor to Department \n"
				+ "5. Schedule Appointment \t6. Generate Bill \n7. Show Reports \t"
				+ "8. Show All Doctors \t9. Show all Departments \n"
				+ "10. Save and Exit");
		System.out.println("Enter your choice: ");
		
		try {
		    int choice = Integer.parseInt(scanner.nextLine());
		    switch(choice) {
		        // cases...
		    case(1):
				addPatient();
				break;
			case(2):
				addDoctor();
				break;
			case(3):
				addDepartment();
				break;
			case(4):
				assignDoctorToDepartment();
				break;
			case(5):
				scheduleAppointment();
				break;
			case(6):
				generateBill();
				break;
			case(7):
				showReports();
				break;
			case(8):
				showAllDoctors();
				break;
			case(9):
				showAllDepartments();
				break;
		    case 10: 
		        running = false;
		        break;
		    }
		} catch (NumberFormatException e) {
		    System.out.println("Invalid input. Please enter a number.");
		    }
		}
		scanner.close();
}
	
	
	private static void addPatient() {
		
		System.out.println("Name: ");
		String name = scanner.nextLine();

		System.out.println("Email: ");
		String email = scanner.nextLine();
		
		System.out.println("Phone number: ");
		String phoneNumber = scanner.nextLine();
		
		System.out.println("Gender (Male/Female): ");
		String g = scanner.nextLine();
		
		Gender gender;
		if (g.equalsIgnoreCase("male")) {
		    gender = Gender.MALE;
		} else {
		    gender = Gender.FEMALE;
		}
		
		System.out.println("Age: ");
		int age = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Medical History: ");
		String medicalHistory = scanner.nextLine();
		
		Contact contact = new Contact(email, phoneNumber);
		
		Patient patient = new Patient(hospitalSystem.getNextPatientID(), 
				name, contact, gender, age, medicalHistory);
		
		hospitalSystem.addPatient(patient);
		
	}
	
	private static void addDoctor() {
		
		System.out.println("Name: ");
		String name = scanner.nextLine();
		
		System.out.println("Email: ");
		String email = scanner.nextLine();
		
		System.out.println("Phone number: ");
		String phoneNumber = scanner.nextLine();
		
		System.out.println("Gender (Male/Female): ");
		String g = scanner.nextLine();
		
		Gender gender;
		if (g.equalsIgnoreCase("male")) {
		    gender = Gender.MALE;
		} else {
		    gender = Gender.FEMALE;
		}
		
		System.out.println("Specialization: ");
		String specialization = scanner.nextLine();
		
		System.out.println("Department ID: ");
		int departmentID = Integer.parseInt(scanner.nextLine());
		
		Contact contact = new Contact(email, phoneNumber);
		
		Doctor doctor = new Doctor(hospitalSystem.getNextDoctorID(), 
				name, contact, gender, specialization , departmentID);
		
		hospitalSystem.addDoctor(doctor);
		
	}
	
	private static void addDepartment() {
		System.out.println("Departtment ID: ");
		int depID = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Department Name: ");
		String depName = scanner.nextLine();
		
		Department department = new Department(depID, depName);
		
		hospitalSystem.addDepartment(department);
	}
	
	private static void assignDoctorToDepartment() {
		
		System.out.println("Assign Doctor of ID: ");
		int docID = Integer.parseInt(scanner.nextLine());
		
		System.out.println("To the Department of ID: ");
		int depID = Integer.parseInt(scanner.nextLine());
		
		hospitalSystem.assignDoctorToDepartment(docID, depID);
		
	}
	
	
	private static void scheduleAppointment() {
		
		System.out.println("Patient ID: ");
		int patientID = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Doctor ID: ");
		int doctorID = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Appointment Date (yyyy-mm-dd): ");
		String appDate = scanner.nextLine();
		
		LocalDate date = LocalDate.parse(appDate);
		
		Appointment app = new Appointment(hospitalSystem.getNextAppointmentID(),
				patientID, doctorID, date);
		
		boolean scheduleApp = hospitalSystem.scheduleAppointment(app);
		
		if (scheduleApp)
			System.out.println("---Scheduling Successful---");
		else
			System.out.println("---Scheduling Unsuccessful---");
		
	}
	
	private static void generateBill() {
		
		System.out.println("Appointment ID: ");
		int appointmentID = Integer.parseInt(scanner.nextLine());
		
		System.out.println("Bill Date (yyyy-mm-dd): ");
		String billDate = scanner.nextLine();
		
		LocalDate date = LocalDate.parse(billDate);
		
		System.out.println("Number of services: ");
		int serviceCount = Integer.parseInt(scanner.nextLine());
		
		System.out.println("\nList of services: ");
		int s = 0;
		ArrayList<String> services = null;
		
		while (s < serviceCount) {
			System.out.println("service #"+ (s+1)+ " : ");
			String service = scanner.nextLine();
			services.add(service);
			s++;
		
		}
		int billID = hospitalSystem.getNextBillID();
		
		Bill bill = new Bill(billID,
				appointmentID, date);
		bill.setServices(services);
		
		hospitalSystem.generateBill(billID);
		
	}
	
	private static void showReports() {
		
		System.out.println("====== Generate a Report =======");
		System.out.println("1. Patients Report\n"
				+ "2. Doctors Report\n"
				+ "3. Appointments Report\n"
				+ "4. Billing Report\n"
				+ "5. Departments Report\n");
		System.out.println("Enter your choice: ");
		
		int choice = Integer.parseInt(scanner.nextLine());
		
		switch (choice) {
		case (1):
			hospitalSystem.generatePatientReport();
			break;
		case(2):
			hospitalSystem.generateDoctorReport();
			break;
		case(3):
			hospitalSystem.generateAppointmentReport();
			break;
		case(4):
			hospitalSystem.generateBillingReport();
			break;
		case(5):
			hospitalSystem.generateDepartmentReport();
			break;
		}
		
	}
	
	private static void showAllDoctors() {
		
		ArrayList<Doctor> doctors = hospitalSystem.getDoctors();
		if (doctors.isEmpty()) {
	        System.out.println("No doctors are currently registered in the system.");
	        return;
	    }

	    // Table header
	    System.out.println("\nLIST OF ALL DOCTORS");
	    System.out.println("+----+----------------------+----------------------+----------------+");
	    System.out.println("| ID | Name                 | Specialization       | Department ID  |");
	    System.out.println("+----+----------------------+----------------------+----------------+");

	    // Table rows
	    for (Doctor doctor : doctors) {
	        System.out.printf("| %-2d | %-20s | %-20s | %-14d |\n",
	                doctor.getId(),
	                doctor.getName(),
	                doctor.getSpecialization(),
	                doctor.getDepartmentID());
	    }

	    // Table footer
	    System.out.println("+----+----------------------+----------------------+----------------+");
	    System.out.printf("Total doctors: %d\n\n", doctors.size());
	}
	
	private static void showAllDepartments() {
		
		ArrayList<Department> departments = hospitalSystem.getDepartments();
		
		if (departments.isEmpty()) {
	        System.out.println("\nNo departments are currently registered in the system.");
	        return;
	    }

	    // Table header
	    System.out.println("\nLIST OF ALL DEPARTMENTS");
	    System.out.println("+-----+------------------------------+");
	    System.out.println("| ID  | Department Name              |");
	    System.out.println("+-----+------------------------------+");

	    // Table rows
	    for (Department department : departments) {
	        System.out.printf("| %-3d | %-28s |\n",
	                department.getDepartmentId(),
	                department.getDepartmentName());
	    }

	    // Table footer
	    System.out.println("+-----+------------------------------+");
	    System.out.printf("Total departments: %d\n\n", departments.size());
	}
	
}
