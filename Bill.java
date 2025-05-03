package hms;

import java.time.LocalDate;
import java.util.ArrayList;

public class Bill implements Payable {
	private static long serialVersionUID = 1L;
	private int billID;
	private int appointmentID;
	private double amount;
	private ArrayList<String> services;
	private LocalDate date;
	
	public Bill(int billID, int appointmentID, LocalDate date) {
		super();
		this.billID = billID;
		this.appointmentID = appointmentID;
		this.date = date;
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public static void setSerialVersionUID(long serialVersionUID) {
		Bill.serialVersionUID = serialVersionUID;
	}
	public int getBillID() {
		return billID;
	}
	public void setBillID(int billID) {
		this.billID = billID;
	}
	public int getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public ArrayList<String> getServices() {
		return services;
	}
	public void setServices(ArrayList<String> services) {
		this.services = services;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public void addService(String service) {
		this.services.add(service);
	}
	
	public String generateInvoice() {
		String invoice = "Hospital Bill\n";
        invoice += "Bill ID: " + billID + "\n";
        invoice += "Appointment ID: " + appointmentID + "\n";
        invoice += "Date: " + date + "\n";  // Date will auto-convert to String
        invoice += "Services:\n";
        
        for (String service : services) {
            invoice += "- " + service + "\n";
        }
        
        invoice += "Total Amount: $" + String.format("%.2f", amount) + "\n";
        return invoice;

	}

	public double calculatePayment(int billId) {
		this.billID = billId;
		return amount;
	}
}
