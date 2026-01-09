package bcu.cmp5332.librarysystem.model;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.commands.ShowPatron;
import java.time.LocalDate;


public class Book {
    
	private boolean deleted = false;
	
    private int id;
    private String title;
    private String author;
    private String publicationYear;
    private String publisher;

    private Loan loan;

    public Book(int id, String title, String author, String publicationYear, String publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        
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
        		"Publicaton Year: " + publicationYear +"\n" +
        		"Publisher: " + publisher +"\n";
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
        return isOnLoan() ? "On loan" : "Available";
    }

    public LocalDate getDueDate() {
        return (loan == null) ? null : loan.getDueDate();
    }
    
    public void setDueDate(LocalDate dueDate) throws LibraryException {
        if (loan == null) {
        	throw new LibraryException("Due date cant be set right now, book is not on loan");
        }
        loan.setDueDate(dueDate);
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
    
    public String getPublisher() {
    	return publisher;
    }
    
    public void setPublisher(String setPublisher) {
    	this.publisher = publisher;
    }
    
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    
}
