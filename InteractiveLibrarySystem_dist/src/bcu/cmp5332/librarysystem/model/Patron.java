package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patron {
    
    private int id;
    private String name;
    private String phone;
    private String email;
    private final List<Book> books = new ArrayList<>();
    
public Patron(int id, String name, String phone, String email) {
	this.id = id;
    this.name = name;
    this.phone = phone;
    this.email = email;
}
    
	public int getId() {
	    return id;
	}
	
	public String getName() {
		return name;
	}

    public void borrowBook(Book book, LocalDate dueDate) throws LibraryException {
        if(book.isOnLoan()) {
        	throw new LibraryException("Book is on loan.");
        }
        
        Loan loan = new Loan(this, book, LocalDate.now(), dueDate);
        
        book.setLoan(loan);
        
        books.add(book);
        
    }

    public void renewBook(Book book, LocalDate dueDate) throws LibraryException {
    	
    }

    public void returnBook(Book book) throws LibraryException {
    	if(!books.contains(book)) {
    		throw new LibraryException("The book was not loaned to this patron.");
    	}
    	
    	book.returnToLibrary();
    	
    	books.remove(book);
    }
    
    public void addBook(Book book) {
        // TODO: implementation here
    }
    
   
    
    
    public String getEmail() {
    	return email;
    }
}
 