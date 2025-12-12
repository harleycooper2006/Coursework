package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

public class Book {
    
    private int id;
    private String title;
    private String author;
    private String publicationYear;

    private Loan loan;

    public Book(int id, String title, String author, String publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public int getId() {
        return id;
    } 

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }
	
    public String getDetailsShort() {
        return "Book #" + id + " - " + title;
    }

    public String getDetailsLong() {
        String details = 
        		"Book #" + id + "\n" +
        		"Title: " + title + "\n" + 
        		"Author: " + author + "\n" + 
        		"Publicaton Year: " + publicationYear +"\n";
        if(isOnLoan()) {
        	details += "Status: on loan\n";
        	details += "Loaned to: " + loan.getPatron().getName() + "\n" ;
        	details += "Return date: " + loan.getDueDate();
        }
        else {
        details += "Status: Available\n";
        }
        return details;
        	
        
    }
    
    public boolean isOnLoan() {
        return (loan != null);
    }
    
    public String getStatus() {
        // TODO: implementation here
        return null;
    }

    public LocalDate getDueDate() {
        // TODO: implementation here
        return null;
    }
    
    public void setDueDate(LocalDate dueDate) throws LibraryException {
        // TODO: implementation here
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public void returnToLibrary() {
        loan = null;
    }
}
