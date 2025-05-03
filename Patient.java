package hms;

//displaydetails and updatedMedicalInfo unimplemented

public class Patient extends Person{
	private int age;
	private String medicalHistory;
	
	public Patient(int id, String name, Contact contact, Gender gender, int age, String medicalHistory) {
		super(id, name, contact, gender);
		this.age = age;
		this.medicalHistory = medicalHistory;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public void updateMedicalHistory(String newMedicalInfo) {
		this.medicalHistory = this.medicalHistory+ newMedicalInfo;
	}
	
	public void displayDetails() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return "Patient [age=" + age + ", medicalHistory=" + medicalHistory + ", getId()=" + getId() + ", getName()="
				+ getName() + ", getContact()=" + getContact() + ", getGender()=" + getGender() + "]";
	}
	
	
}
