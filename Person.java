package hms;

//all methods unimplemented

public class Person {
	private int id;
	private String name;
	private Contact contact;
	private Gender gender;
	
	public Person(int id, String name, Contact contact, Gender gender) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void updateContactInfo(Contact newContact) {
		this.contact = newContact;
	}
	
	public boolean validateId(int Id) {
		if (Id == this.id)
			return true;
		return false;
	}
	
	public void displayDetails() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", email=" + contact.getEmail() +
				", phone number="+ contact.getPhoneNumber()+ ", gender=" + gender + "]";
	}
	

}
