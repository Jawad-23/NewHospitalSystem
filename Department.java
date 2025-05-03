package hms;

//all methods unimplemented

import java.util.ArrayList;

public class Department {
	private int departmentId;
	private String departmentName;
	private ArrayList<Doctor> doctors;
	
	
	
	public Department(int departmentId, String departmentName) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public ArrayList<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(ArrayList<Doctor> doctors) {
		this.doctors = doctors;
	}

	public void addDoctor(Doctor doctor) {
		doctors.add(doctor);
	}
	
	public void removeDoctor(int doctorId) {
		for (Doctor d: doctors) {
			if (d.getId() == doctorId)
				doctors.remove(d);
		}
	}
	
	public void displayDepartmentDetails() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + 
				", departmentName=" + departmentName + "]";
	}
}
