package Models;

public class Book {
	private int id;
	private String name;
	private int tome;
	private Author author;
	private int year;
	private String category;
	
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
	public int getTome() {
		return tome;
	}
	public void setTome(int tome) {
		this.tome = tome;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Book(String name, int tome, Author author, int year, String category) {
		super();
		this.name = name;
		this.tome = tome;
		this.author = author;
		this.year = year;
		this.category = category;
	}
	public Book(int id, String name, int tome, Author author, int year, String category) {
		super();
		this.id = id;
		this.name = name;
		this.tome = tome;
		this.author = author;
		this.year = year;
		this.category = category;
	}
	public Book(int id) {
		super();
		this.id = id;
	}
	public Book() {}
}
