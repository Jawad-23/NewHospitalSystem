package hms;
import java.time.LocalDate;
import java.util.ArrayList;

public class HospitalSystem implements Payable{
	private int nextPatientID=100;
	private int nextDoctorID=2000;
	private int nextAppointmentID=3000;
	private int nextBillID=4000;
	private ArrayList<Patient> patients= new ArrayList<>();;
	private ArrayList<Doctor> doctors= new ArrayList<>();;
	private ArrayList<Appointment> appointments= new ArrayList<>();;
	private ArrayList<Bill> bills=new ArrayList<>();;
	private ArrayList<Department> departments= new ArrayList<>();;
	
	
	
	public HospitalSystem() {
		super();
	}
	
	public int getNextPatientID() {
		return nextPatientID;
	}
	public void setNextPatientID(int nextPatientID) {
		this.nextPatientID = nextPatientID;
	}
	public int getNextDoctorID() {
		return nextDoctorID;
	}
	public void setNextDoctorID(int nextDoctorID) {
		this.nextDoctorID = nextDoctorID;
	}
	public int getNextAppointmentID() {
		return nextAppointmentID;
	}
	public void setNextAppointmentID(int nextAppointmentID) {
		this.nextAppointmentID = nextAppointmentID;
	}
	public int getNextBillID() {
		return nextBillID;
	}
	public void setNextBillID(int nextBillID) {
		this.nextBillID = nextBillID;
	}
	public ArrayList<Patient> getPatients() {
		return patients;
	}
	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}
	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}
	public void setDoctors(ArrayList<Doctor> doctors) {
		this.doctors = doctors;
	}
	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}
	public ArrayList<Bill> getBills() {
		return bills;
	}
	public void setBills(ArrayList<Bill> bills) {
		this.bills = bills;
	}
	public ArrayList<Department> getDepartments() {
		return departments;
	}
	public void setDepartments(ArrayList<Department> departments) {
		this.departments = departments;
	}
	
	public void addPatient(Patient patient) {
		this.patients.add(patient);
		
	}
	
	public void deletePatient(Patient patient) {
		this.patients.remove(patient);
	}
	
	public Patient searchPatientByID(int patientID) {
		for (Patient p: patients) {
			if (p.getId()== patientID) 
				return p;
		}
		return null;
	}
	
	public void addDoctor(Doctor doctor) {
		this.doctors.add(doctor);
	}
	
	public void deleteDoctor(int doctorID) {
		for (Doctor d: doctors) {
			if (d.getId()== doctorID)
				doctors.remove(d);
		}
	}
	
	public Doctor searchDoctorByID(int doctorID) {
		for (Doctor d: doctors) {
			if (d.getId()== doctorID)
				return d;
		}
		return null;
	}
	
	public boolean scheduleAppointment(Appointment appointment) {
		// validations
		
				//doctor exists?
				Doctor doctor = searchDoctorByID(appointment.getDoctorID());
				if (doctor == null) {
					return false; // no doctor 
				}
				Patient patient = searchPatientByID(appointment.getPatientID());
				if (patient == null) {
					return false; // no patient
				}
				for (Appointment existing: appointments) {
					if (existing.getDoctorID() == appointment.getDoctorID()
							&& existing.getAppointmentDate().equals(appointment.getAppointmentDate())) {
						return false; // doctor got another appointment at the same time
					}
				}
				appointment.setAppointmentID(nextAppointmentID++);
				appointment.setAppointmentStatus(AppointmentStatus.CONFIRMED);		
				
				appointments.add(appointment);
				
				return true;
	}
	
	public boolean cancelAppointmentByID(int appointmentID) {
		for (Appointment a: appointments) {
			if (a.getAppointmentID()== appointmentID)
				a.setAppointmentStatus(AppointmentStatus.CANCELLED);
				a.cancelAppointment();
				return true;
		}
		return false;
	}
	
	public Appointment searchAppointmentByID(int appointmentID) {
		for (Appointment a: appointments) {
			if (a.getAppointmentID()== appointmentID)
				return a;
		}
		return null;
	}
	
	public void generateBill(int billID) {
		Bill newBill = new Bill(this.nextBillID, this.nextAppointmentID, LocalDate.now());
		String invoice = newBill.generateInvoice();
		this.bills.add(newBill);
		System.out.println(invoice);
	}
	
	public Bill searchBillByID(int billID) {
		for (Bill b: bills) {
			if (b.getBillID()==billID) {
				return b;
			}
		}
		return null;
	}
	
	public void addDepartment(Department department){
		this.departments.add(department);
	}
	
	public void assignDoctorToDepartment(int doctorID, int departmentID) {
		Doctor doc = null;
		for (Doctor d: doctors) {
			if (d.getId()==doctorID)
				d.setDepartmentID(departmentID);
				doc = d;
		}
		for (Department d: departments) {
			if(d.getDepartmentId()== departmentID);
				d.addDoctor(doc);
		}
	}
	
	public void generatePatientReport() {
		System.out.println("__Patients Report__"+"\n");
		for (Patient p: patients) {
			System.out.println(p.toString()+ "\n");
		}
	}
	
	public void generateDoctorReport() {
		System.out.println("__Doctors Report__"+"\n");
		for (Doctor d: doctors) {
			System.out.println(d.toString()+ "\n");
		}
	}
	
	public void generateAppointmentReport() {
		System.out.println("__Appointments Report__"+"\n");
		for (Appointment a: appointments) {
			System.out.println(a.toString()+"\n");
		}
	}
	
	public void generateBillingReport() {
		double totalRevenue = 0.0;
		System.out.println("__Appointments Report__"+"\n");
		for (Bill b: bills) {
			System.out.println(b.toString()+"\n");
			totalRevenue+= b.calculatePayment(b.getBillID());
		}
		
		System.out.println("Total Revenue = " + totalRevenue);
		System.out.println("__Doctors Earnings__");
		for (Doctor d: doctors) {
			for (Bill b: bills) {
				int app= b.getAppointmentID();
				for (Appointment a: appointments) {
					if (a.getAppointmentID()== app) {
						if (d.getId() == a.getDoctorID())
							System.out.println(d.getName()+ " - $" + this.calculatePayment(b.getBillID()));
						
					}
				}
			}
		}
	}
	
	public void generateDepartmentReport() {
		System.out.println("__Departments Report__"+"\n");
		for (Department d: departments) {
			System.out.println(d.toString()+"\n");
		}
	}

	@Override
	public double calculatePayment(int billID) {
		for (Bill b: bills) {
			if (b.getBillID()==billID) {
				return b.getAmount()*0.3;
			}
		}
		return 0.0;
	}
	
	
	
	
}
