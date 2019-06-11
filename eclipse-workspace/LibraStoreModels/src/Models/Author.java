package Models;

public class Author {
	private int id;
	private String firstname;
	private String lastname;
	private String birthDay;
	private String country;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public Author(String firstname, String lastname, String birthDay, String country) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthDay = birthDay;
		this.country = country;
	}
	public Author(int id, String firstname, String lastname, String birthDay, String country) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthDay = birthDay;
		this.country = country;
	}
	public Author() {}
}
