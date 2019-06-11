package Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Models.Author;
import Models.Book;

public class BooksRepository {
	private AuthorsRepository authorsRepository;
	private String filename = "books.txt";
	
	public ArrayList<Book> GetBooks() {
		authorsRepository = new AuthorsRepository();
		
		ArrayList<String> books = new ArrayList<String>();
		ArrayList<Book> bks = new ArrayList<Book>();
		
		try {
			String line = null;

            FileReader fileReader = 
                new FileReader(filename);
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	String[] split1 = line.split("> ");
            	String[] split2 = split1[1].split(", ");
            	
            	bks.add(new Book(Integer.parseInt(split1[0]), 
            			split2[0], 
            			Integer.parseInt(split2[1]), 
            			authorsRepository.GetSpecificAuthor(Integer.parseInt(split1[0])),
            			Integer.parseInt(split2[3]),
            			split2[4]));
            }   

            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + filename + "'");                  
        }
		
		return bks;
	}
	
	public Book GetSpecificBook(int id) {
		authorsRepository = new AuthorsRepository();
		
		Book bk = new Book();
		
		try {
			String line = null;

            FileReader fileReader = 
                new FileReader(filename);
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	if (line.contains(id + ">")) {
            		String[] split1 = line.split("> ");
                	String[] split2 = split1[1].split(", ");
                	
                	bk = new Book(Integer.parseInt(split1[0]), 
                			split2[0], 
                			Integer.parseInt(split2[1]), 
                			authorsRepository.GetSpecificAuthor(Integer.parseInt(split2[2])),
                			Integer.parseInt(split2[3]),
                			split2[4]);
            	}
            }   

            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + filename + "'");                  
        }
		
		return bk;
	}
	
	public void AddBook(Book book) {
		int id = 0;
		
		try {
			String line = null;
			String lastLine = null;

            FileReader fileReader = 
                new FileReader(filename);
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	lastLine = line;
            }   
            if (lastLine != null)
            	id = Integer.parseInt(lastLine.substring(0, lastLine.indexOf(">")));

            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + filename + "'");                  
        }
		
		try {
			book.setId(++id);
			
            FileWriter fileWriter =
                new FileWriter(filename, true);
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);
            
            String input = String.format("%d> %s, %d, %d, %d, %s",
            		book.getId(),
            		book.getName(),
            		book.getTome(),
            		book.getAuthor().getId(),
            		book.getYear(),
            		book.getCategory());
            
            bufferedWriter.write(input + "\r\n");
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + filename + "'");
        }
	}
	
	public void EditBook(Book book) {	
		String newDoc = null;
		
		try {
			String line = null;
			String dataToModify = null;
			String doc = "";

            FileReader fileReader = 
                new FileReader(filename);
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	doc += line + "\r\n";
				
				if (line.contains(book.getId() + ">")) { 
					dataToModify = line;
					}
            }   
            
            String input = String.format("%d> %s, %d, %d, %d, %s",
            		book.getId(),
            		book.getName(),
            		book.getTome(),
            		book.getAuthor().getId(),
            		book.getYear(),
            		book.getCategory());
            
            newDoc = doc.replace(dataToModify, input);

            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + filename + "'");                  
        }
		
		try {
            FileWriter fileWriter =
                new FileWriter(filename);
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);
            
            bufferedWriter.write(newDoc);
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + filename + "'");
        }
	}
}
