package hms;

// displayDetails unimplemented

public class Doctor extends Person {
	
	private String specialization;
	private int departmentID;
	
	public int getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	public Doctor(int id, String name, Contact contact, Gender gender, String specialization, int departmentID) {
		super(id, name, contact, gender);
		this.specialization = specialization;
		this.departmentID = departmentID;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public void displayDetails() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return "Doctor [specialization=" + specialization + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getContact()=" + getContact() + ", getGender()=" + getGender() + ", getDepartmentID="+ getDepartmentID() + "]";
	}
	
	

}
