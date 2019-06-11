package Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Models.Author;

public class AuthorsRepository {
	private String filename = "Authors.txt";
	
	public String[] GetAuthors() {
		ArrayList<String> authors = new ArrayList<String>();
		ArrayList<Author> auths = new ArrayList<Author>();
		
		try {
			String line = null;

            FileReader fileReader = 
                new FileReader(filename);
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	String[] split1 = line.split("> ");
            	String[] split2 = split1[1].split(", ");
            	
            	auths.add(new Author(Integer.parseInt(split1[0]), 
            			split2[0], 
            			split2[1], 
            			split2[2],
            			split2[3]));
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
		
		for (Author auth : auths) {
			String authStr = String.format("%d> %s %s (ur. %s, kraj: %s)", 
					auth.getId(),
					auth.getFirstname(),
					auth.getLastname(),
					auth.getBirthDay(),
					auth.getCountry());
			
			authors.add(authStr);
		}

		String[] authorList = new String[authors.size()];
		authorList = authors.toArray(authorList);
		
		return authorList;
	}
	
	public Author GetSpecificAuthor(int id) {
		Author auth = new Author();
		
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
                	
                	auth = new Author(Integer.parseInt(split1[0]), 
                			split2[0], 
                			split2[1], 
                			split2[2],
                			split2[3]);
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
		
		return auth;
	}
	
	public String GetFormatedAuthor(Author auth) {
		String authStr = String.format("%d> %s %s (ur. %s, kraj: %s)", 
				auth.getId(),
				auth.getFirstname(),
				auth.getLastname(),
				auth.getBirthDay(),
				auth.getCountry());
		
		return authStr;
	}
	
	public void AddAuthor(Author author) {
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
			author.setId(++id);
			
            FileWriter fileWriter =
                new FileWriter(filename, true);
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);
            
            String input = String.format("%d> %s, %s, %s, %s",
            		author.getId(),
            		author.getFirstname(),
            		author.getLastname(),
            		author.getBirthDay(),
            		author.getCountry());
            
            bufferedWriter.write(input + "\r\n");
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + filename + "'");
        }
	}
}
