package hms;

import java.time.LocalDate;

public class Appointment {
	private int appointmentID;
	private int patientID;
	private int doctorID;
	private LocalDate appointmentDate;
	private AppointmentStatus appointmentStatus = AppointmentStatus.UNKNOWN;
	
	
	
	public Appointment(int appointmentID, int patientID, int doctorID, LocalDate appointmentDate) {
		super();
		this.appointmentID = appointmentID;
		this.patientID = patientID;
		this.doctorID = doctorID;
		this.appointmentDate = appointmentDate;
	}
	
	public int getAppointmentID() {
		return appointmentID;
	}
	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}
	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public int getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}
	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public AppointmentStatus getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	
	public void confirmAppointment() {
		this.setAppointmentStatus(AppointmentStatus.CONFIRMED);
	}
	
	public void cancelAppointment() {
		this.setAppointmentStatus(AppointmentStatus.CANCELLED);
	}
	
	public void rescheduleAppointment(LocalDate newDate) {
		this.appointmentDate = newDate;
	}

	@Override
	public String toString() {
		return "Apppointment [appointmentID=" + appointmentID + ", patientID=" +
				patientID + ", doctorID=" + doctorID+ ", appointmentDate=" +
				appointmentDate + ", appointmentStatus=" + appointmentStatus + "]";
	}
	
	
	
}
